package com.example.seecucumber.stepdefinition;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import org.junit.runner.RunWith;

import com.example.seecucumber.custom.AfterSuite;
import com.example.seecucumber.custom.CustomCucumberRunner;

import cucumber.api.CucumberOptions;

@CucumberOptions(features = "src/test/resources", format = { "pretty",
		"html:target/cucumber-html-report", "json:target/cucumber.json" }, tags = {
		"@sanityTest", "@android", "@iphone" }, glue = { "com.example.seecucumber.stepdefinition" }

)
@RunWith(CustomCucumberRunner.class)
public class RunSeeCuckTest {
	@AfterSuite
	public static void generateReport() {
		/**
		 * Report Generated Under "Custom-Report".
		 */
		File reportOutputDirectory = new File("./Custom-Report");
		List<String> jsonFiles = new ArrayList<>();
		jsonFiles.add("target/cucumber.json");

		String jenkinsBasePath = "";
		String buildNumber = "1";
		String projectName = "seecucumber";
		boolean skippedFails = true;
		boolean pendingFails = false;
		boolean undefinedFails = true;
		boolean missingFails = true;
		boolean runWithJenkins = false;
		boolean parallelTesting = false;

		Configuration configuration = new Configuration(reportOutputDirectory,
				projectName);

		// optional only if you need

		configuration.setStatusFlags(skippedFails, pendingFails,
				undefinedFails, missingFails);
		configuration.setParallelTesting(parallelTesting);
		configuration.setJenkinsBasePath(jenkinsBasePath);
		configuration.setRunWithJenkins(runWithJenkins);
		configuration.setBuildNumber(buildNumber);

		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles,
				configuration);
		reportBuilder.generateReports();
	}
}
