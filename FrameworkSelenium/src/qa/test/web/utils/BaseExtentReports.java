package qa.test.web.utils;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import qa.test.web.basePage.TestRunner;

public class BaseExtentReports {
	
	static ExtentTest test;
	static ExtentReports report;
	
	public BaseExtentReports(String testSuite) {
		startTest(testSuite);
	}
	
	public void startTest(String testSuite) {
		try {
			report = new ExtentReports(TestRunner.folderExecution+ "\\" +testSuite+ ".html", true);
			report.loadConfig(new File("C:\\Temp\\Reports\\config.xml"));
			test = report.startTest(testSuite);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startTestNoOverride(String testSuite) {
		try {
			report = new ExtentReports(System.getProperty("user.dir") + "\\report\\" + testSuite + ".html", false);
			test = report.startTest(testSuite);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void logStatusPassed(String messageStep) {
		try {
			String screenShotPath = ScreenShot.capture();
			test.log(LogStatus.PASS, messageStep+test.addScreenCapture(screenShotPath));
			report.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void logStatusFailed(String messageStep) {
		try {
			if (messageStep.contains("[Stacktrace Error Execution]")) {
				test.log(LogStatus.FAIL, messageStep);
			} else {
				String screenShotPath = ScreenShot.capture();
				test.log(LogStatus.FAIL, messageStep+test.addScreenCapture(screenShotPath));
			}
			report.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void logStatusWarning(String messageStep) {
		try {
			String screenShotPath = ScreenShot.capture();
			test.log(LogStatus.WARNING, messageStep+test.addScreenCapture(screenShotPath));
			report.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void logStatusInfo(String messageStep) {
		try {
			test.log(LogStatus.INFO, messageStep);
			report.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void endTest() {
		try {
			report.endTest(test);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}