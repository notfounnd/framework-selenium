package qa.test.web.testDebug;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.ExtentTest;

import qa.test.web.basePage.TestRunner;
import qa.test.web.utils.BaseExtentReports;
import qa.test.web.utils.Browser;

@SuppressWarnings({"unused"})

public class TestDebug {
	
	Browser navigator;
	private WebDriver driver;
	static BaseExtentReports reports;
	static ExtentTest test;
	String testClass = TestRunner.testClass;
	String testMethod = TestRunner.testMethod;
	
	/*
	 * Pré-teste
	 */
	@Before
	public void setUp() throws Throwable {
				
		//report.startTest("TestReport");
		navigator = new Browser();
		navigator.openBrowser("CHROME", PageLoadStrategy.NORMAL);
		
	}
	
	/*
	 * Pós-teste
	 */
	@After
	public void tearDown() throws IOException, ParserConfigurationException, SAXException {
		
		navigator.closeBrowser();
		
	}
	
	/*
	 * Teste
	 */
	@Test
	public void CN001_CT001() throws Exception {
		
		navigator.openURL("http://test.juniorsbrissa.com");
		navigator.openURL("http://facebook.com");
		navigator.backwardBrowser();
		navigator.refreshBrowser();
		navigator.forwardBrowser();
		
	}
	
}
