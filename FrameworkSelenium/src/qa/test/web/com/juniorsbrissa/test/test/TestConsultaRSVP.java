package qa.test.web.com.juniorsbrissa.test.test;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;

import qa.test.web.com.juniorsbrissa.test.data.DataHelper;
import qa.test.web.com.juniorsbrissa.test.page.PageHome;
import qa.test.web.utils.Browser;

public class TestConsultaRSVP {
	
	WebDriver driver;
	Browser navigator;
	PageHome pageHome;
	DataHelper dataHelper;
	
	/*
	 * Pr�-teste
	 */
	@Before
	public void setUp() throws Throwable {
		
		dataHelper = new DataHelper();
		navigator = new Browser();
		driver = navigator.openBrowser("CHROME", PageLoadStrategy.NORMAL);
	
	}
	
	/*
	 * P�s-teste
	 */
	@After
	public void tearDown() throws IOException, ParserConfigurationException, SAXException {
		
		navigator.closeBrowser();

	}
	
	/*
	 * Cen�rio: Efetuar RSVP (Confirmar presen�a)
	 * 
	 * Abrir o site 'http://test.juniorsbrissa.com/'
	 * Localizar �rea 'RSVP'
	 * Efetuar processo 'RSVP'
	 * Reiniciar o formul�rio 'RSVP'
	 * 
	 */
	@Test
	public void CN001_CT001() throws Exception {
		
		//
		pageHome = new PageHome(driver);
		
		//Vari�veis
		String dataFile = "COD001TESTE";
		String url		= dataHelper.getData(dataFile, "site");
		String codeRSVP = dataHelper.getData(dataFile, "codigoRSVP");
		String name 	= dataHelper.getData(dataFile, "nome");
		String answer	= dataHelper.getData(dataFile, "resposta");
		
		//Abrir o site 'http://test.juniorsbrissa.com/'
		navigator.openURL(url);
		
		//Localizar �rea 'RSVP'
		//Efetuar processo 'RSVP'
		pageHome.insertValueRSVP(codeRSVP);
		pageHome.clickButtonVerificarCodigoRSVP();
		pageHome.validadeFieldNameRSVP(name);
		pageHome.setAnswerRSVP(answer);
		pageHome.validateSendAnswerRSVP(name);
		
		//Reiniciar o formul�rio 'RSVP'
		pageHome.reloadFormRSVP();
		
	}

}