package qa.test.web.testDebug;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;

import qa.test.web.utils.Browser;

@SuppressWarnings({"unused"})

public class TestDebug {
	
	Browser navigator;
	private WebDriver driver;

	@Before
	public void setUp() throws Throwable {
		
		navigator = new Browser();
		navigator.openBrowser("FIREFOX", PageLoadStrategy.NORMAL);
	
	}
	
	@After
	public void tearDown() throws IOException, ParserConfigurationException, SAXException {
		
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
