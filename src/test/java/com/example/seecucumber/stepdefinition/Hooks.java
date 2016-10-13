package com.example.seecucumber.stepdefinition;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.example.seecucumber.core.AndroidApplication;
import com.example.seecucumber.core.Application;
import com.example.seecucumber.core.IOSApplication;
import com.example.seecucumber.core.MyClient;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	private int port = 0;
	private String host = null;

	protected  static MyClient client = null;

	protected Application application = null;
	protected String usedDeviceName = null;

	@Before
	public void setUp() throws NumberFormatException, IOException {
		initClientAndDevice();
		client.setDevice(usedDeviceName);
		if (application == null) {
			if (usedDeviceName.startsWith("adb:")) {
				application = new AndroidApplication(client,
						getProperty("app.package"),
						getProperty("app.lauchActivity"));
			} else {
				application = new IOSApplication(client,
						getProperty("app.package"),
						getProperty("app.lauchActivity"));
			}
		}
		if ("true".equalsIgnoreCase(getProperty("app.install.onInit"))) {
			application.install(getProperty("app.location"));
		}
		client.clearDeviceLog();
		application.init(false);
	}

	public void initClientAndDevice() throws NumberFormatException, IOException {
		initHost();
		initPort();
		client = new MyClient(host, port);
		initDevice();
		initProjectBaseDirectory();
		initReport();
	}

	private void initReport() {

		/*String mainFolder = getProperty("execution.start.time");
		String suiteName = "";
		if (mainFolder == null) {
			mainFolder = "SeeCucumber Tests\\";
		} else {
			mainFolder += "\\";
			suiteName = getProperty("suite.name") + " ";
		}
		String reportFolder = getProperty("user.dir") + "\\reports\\"
				+ mainFolder + suiteName + usedDeviceName.split(":")[1];
		String reportName = getTestName() + " (data " + dataIndex + ")";
		client.setReporter("xml", reportFolder, reportName);
*/

		/*
		 * String mainFolder = getProperty("execution.start.time"); String
		 * suiteName = ""; if (mainFolder == null) { mainFolder =
		 * "SeeCucumber Tests\\"; } else { mainFolder += "\\"; suiteName =
		 * getProperty("suite.name") + " "; } String reportFolder =
		 * getProperty("user.dir") + "\\reports\\" + mainFolder + suiteName +
		 * usedDeviceName.split(":")[1]; String reportName = getTestName() +
		 * " (data " + dataIndex + ")"; client.setReporter("xml", reportFolder,
		 * reportName);
		 */

	}

	private void initProjectBaseDirectory() {
		String projectBaseDirectory = getProperty("user.dir")
				+ getProperty("project.base.directory");
		client.setProjectBaseDirectory(projectBaseDirectory);
	}

	private void initDevice() {
		if (usedDeviceName != null) {
			return;
		}
		usedDeviceName = getProperty("device.name").contains(":") ? getProperty("device.name")
				: client.waitForDevice(getProperty("device.name"), 300000);
	}

	private void initPort() {
		if (port == 0) {

			port = Integer.parseInt(getProperty("device.port"));
			

			// port = Integer.valueOf(getProperty("device.port"));
			port = 8889;

		}
	}

	private void initHost() {
		if (host == null) {
			host = getProperty("device.host");
		}

	}

	private static String getProperty(String property) {
		if (System.getProperty(property) != null) {
			return System.getProperty(property);
		}
		File setupPropFile = new File("setup.properties");
		if (setupPropFile.exists()) {
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

	@After
	public void Logout() throws NumberFormatException, IOException {
		client.click("NATIVE", "xpath=//*[@id='logoutButton']", 0, 1);
		client.releaseClient();

	}
}
