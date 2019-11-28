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
	 * Pré-teste
	 */
	@Before
	public void setUp() throws Throwable {
		
		dataHelper = new DataHelper();
		navigator = new Browser();
		driver = navigator.openBrowser("CHROME", PageLoadStrategy.NORMAL);
	
	}
	
	/*
	 * Pós-teste
	 */
	@After
	public void tearDown() throws IOException, ParserConfigurationException, SAXException {
		
		navigator.closeBrowser();

	}
	
	/*
	 * Cenário: Efetuar RSVP (Confirmar presença)
	 * 
	 * Abrir o site 'http://test.juniorsbrissa.com/'
	 * Localizar área 'RSVP'
	 * Efetuar processo 'RSVP'
	 * Reiniciar o formulário 'RSVP'
	 * 
	 */
	@Test
	public void CN001_CT001() throws Exception {
		
		//
		pageHome = new PageHome(driver);
		
		//Variáveis
		String dataFile = "COD001TESTE";
		String url		= dataHelper.getData(dataFile, "site");
		String codeRSVP = dataHelper.getData(dataFile, "codigoRSVP");
		String name 	= dataHelper.getData(dataFile, "nome");
		String answer	= dataHelper.getData(dataFile, "resposta");
		
		//Abrir o site 'http://test.juniorsbrissa.com/'
		navigator.openURL(url);
		
		//Localizar área 'RSVP'
		//Efetuar processo 'RSVP'
		pageHome.insertValueRSVP(codeRSVP);
		pageHome.clickButtonVerificarCodigoRSVP();
		pageHome.validadeFieldNameRSVP(name);
		pageHome.setAnswerRSVP(answer);
		pageHome.validateSendAnswerRSVP(name);
		
		//Reiniciar o formulário 'RSVP'
		pageHome.reloadFormRSVP();
		
	}

}