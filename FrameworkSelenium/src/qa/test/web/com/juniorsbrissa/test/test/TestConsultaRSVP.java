package qa.test.web.com.juniorsbrissa.test.test;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.xml.sax.SAXException;

import qa.test.web.utils.Browser;

public class TestConsultaRSVP {
	
	Browser navigator;

	@Before
	public void setUp() throws Throwable {
		
		navigator = new Browser();
		navigator.openBrowser("CHROME", PageLoadStrategy.NORMAL);
	
	}
	
	@After
	public void tearDown() throws IOException, ParserConfigurationException, SAXException {
		
		navigator.closeBrowser();

	}
	
	@Test
	public void CN001_CT001() throws Exception {
		
		navigator.openURL("http://test.juniorsbrissa.com");
		
	}

}