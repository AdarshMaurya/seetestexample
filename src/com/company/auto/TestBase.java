package com.company.auto;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.experitest.client.InternalException;

public class TestBase {
	private int port = 0;
	private String host = null;
	protected MyClient client = null;
	protected Application application = null;
	protected String usedDeviceName = null;
	protected Map<String,String> data = null;
	protected int dataIndex = 0;
	protected boolean didFail = false;
	protected Throwable exception = null;
	
	@Before
	public void setUp() throws NumberFormatException, BiffException, IOException {
		initClientAndDevice();
		client.setDevice(usedDeviceName);
		if(application == null){
			if(usedDeviceName.startsWith("adb:")){
				application = new AndroidApplication(client, getProperty("app.user"), getProperty("app.password"));
			} else {
				application = new IOSApplication(client, getProperty("app.user"), getProperty("app.password"));
			}
		}
		if("true".equalsIgnoreCase(getProperty("app.install.onInit"))){
			application.install(getProperty("app.location"));
		}
		client.clearDeviceLog();
		application.init(false);
	}
	
	public void tearDown(){
		if (didFail) {
			System.out.println("<--- FAIL --- " + exception.toString() + " --->");
		}
		else {
			System.out.println("<--- SUCCESS --- " + usedDeviceName + " --- " + getTestName() + " --- data (" + dataIndex + ") --->");
		}
		if((didFail) && (exception == null)) {
			client.report("Test failed with null exception", false);
		}
		if((didFail) && (exception instanceof InternalException)) {
			String cause = ((InternalException)exception).getCauseType();
			if (cause != null) {
				switch (cause) {
					case ("UNKNOWN"):
						//
						break;
					case ("INTERNAL_ERROR"):
						//
						break;
					case ("STOP_BY_USER"):
						//
						break;
					case ("USER_INPUT_ERROR"):
						//
						break;
					case ("DEVICE_INTERACTION"):
						//
						break;
					case ("ELEMENT_IDENTIFICATION"):
						//
						break;
					case ("OPERATION_FAILURE"):
						//
						break;
				}
			}
		}
		if ((didFail) && (!(exception instanceof AssertionError))) {
			client.report(exception.toString(), false);
		}
		client.generateReport();
	}
	
	public void initClientAndDevice() throws NumberFormatException, BiffException, IOException {
		initHost();
		initPort();
		client = new MyClient(host,port);
		initDevice();
		initProjectBaseDirectory();
		getTestData(getTestName());
		initReport();
	}
	
	private void initHost(){
		if (host == null) {
			host = getProperty("host");
		}
	}
	
	private void initPort(){
		if (port == 0) {
			port = Integer.valueOf(getProperty("port"));
		}
	}
	
	private void initDevice(){
		if (usedDeviceName != null) {
			return;
		}
		usedDeviceName = getProperty("device.name").contains(":") ? getProperty("device.name") : client.waitForDevice(getProperty("device.name"), 300000);
	}
	
	private void initProjectBaseDirectory(){
		String projectBaseDirectory = getProperty("user.dir") + getProperty("project.base.directory");
		client.setProjectBaseDirectory(projectBaseDirectory);
	}
	
	private void initReport(){
		String mainFolder = getProperty("execution.start.time");
		String suiteName = "";
		if (mainFolder == null) {
			mainFolder = "Single Tests\\";
		}
		else {
			mainFolder += "\\";
			suiteName = getProperty("suite.name") + " ";
		}
		String reportFolder = getProperty("user.dir") + "\\reports\\" + mainFolder + suiteName + usedDeviceName.split(":")[1];
		String reportName = getTestName() + " (data " + dataIndex + ")";
		client.setReporter("xml", reportFolder, reportName);
	}
	
	private void getTestData(String testName) throws BiffException, IOException {
		if (dataIndex == -1) {
			return;
		}
		if (dataIndex == 0) {
			dataIndex = Integer.valueOf(getProperty("data.set"));
		}
		data = new HashMap<String,String>();
		Sheet dataSheet = Workbook.getWorkbook(new File(getProperty("data.spreadsheet.name"))).getSheet(getTestName());
		for (int i=1; i<dataSheet.getColumns(); i++) {
			String key = dataSheet.getCell(i,0).getContents();
			String value = dataSheet.getCell(i,dataIndex).getContents();
			data.put(key, value);
		}
	}
	
	private static String getProperty(String property){
		if(System.getProperty(property) != null){
			return System.getProperty(property);
		}
		File setupPropFile = new File("setup.properties");
		if(setupPropFile.exists()){
			Properties prop = new Properties();
			FileReader reader;
			try {
				reader = new FileReader(setupPropFile);
				prop.load(reader);
				reader.close();
				return prop.getProperty(property);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String getTestName() {
		String[] klassNameSplit = this.getClass().getName().split("\\.");
		return klassNameSplit[klassNameSplit.length-1];
	}
	
	@Rule
	public TestWatcher rule = new TestWatcher() {
		protected void failed(Throwable e, Description description) {
			didFail = true;
			exception = e;
			tearDown();
		}
		protected void succeeded(Description description) {
			tearDown();
		}

	};

}