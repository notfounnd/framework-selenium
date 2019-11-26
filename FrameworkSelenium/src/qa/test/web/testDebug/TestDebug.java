package qa.test.web.testDebug;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import qa.test.web.basePage.BasePage;
import qa.test.web.basePage.TestRunner;
import qa.test.web.utils.BaseExtentReports;
import qa.test.web.utils.Browser;

//@SuppressWarnings({"unused"})

public class TestDebug {
	
	Browser navigator;
	//static ExtentReports report;
	static BaseExtentReports reports;
	static ExtentTest test;
	String testClass = TestRunner.testClass;
	String testMethod = TestRunner.testMethod;
	//BaseExtentReports report = new BaseExtentReports(testClass +"_"+ testMethod);
	private WebDriver driver;
	
//	@BeforeClass
//	public void startTestReport() {
//		report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
//		test = report.startTest("ExtentDemo");
//		try {
//			report.startTest(TestDebug.getClassMethod(this));
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	
	@Before
	public void setUp() throws Throwable {
				
		//report.startTest("TestReport");
		navigator = new Browser();
		navigator.openBrowser("CHROME", PageLoadStrategy.NORMAL);
	
	}
	
	@After
	public void tearDown() throws IOException, ParserConfigurationException, SAXException {
		
		BaseExtentReports.endTest();
		navigator.closeBrowser();
		

	}
	
	/*
	*===================================================================================================================================================================
	*Caso de Teste: CT001 - Acessar aplicativo BSC com cliente que possui produto Saúde
	*Função: Acessar aplicativo BSC com cliente que possui produto Saúde
	*Desenvolvedora: Ricardo Cremonez
	*Data: 03/09/2019
	*Revisão: 
	*Data: 
	*===================================================================================================================================================================
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
