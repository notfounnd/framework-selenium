package qa.test.web.com.juniorsbrissa.test.page;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.test.web.basePage.BasePage;
import qa.test.web.basePage.TestRunner;

public class PageHome extends BasePage {

	public PageHome(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Elements Reference
	 */
	
	/*
	 * P�gina: Home
	 */
	@FindBy(xpath = "//ul[@id='et-menu']//a[contains(text(),'Home')]")
	WebElement linkMenuHome;
	
	@FindBy(xpath = "//div[contains(@class,'et_pb_row_0')]//h1[contains(text(),'Henrique')]")
	WebElement headerTituloSite;
	
	@FindBy(xpath = "//a[contains(@class,'color') and contains(text(),'chegar')]")
	WebElement buttonComoChegar;
	
	/*
	 * P�gina: Home
	 * �rea: Contador
	 */
	@FindBy(xpath = "//div[contains(@class,'et_pb_countdown_timer_container')]")
	WebElement areaContador;
	
	@FindBy(xpath = "//div[contains(@class,'et_pb_countdown_timer_container')]//div[@data-short='Dia']")
	WebElement areaContadorDia;
	
	@FindBy(xpath = "//div[contains(@class,'et_pb_countdown_timer_container')]//div[@data-short='Hrs']")
	WebElement areaContadorHoras;
	
	@FindBy(xpath = "//div[contains(@class,'et_pb_countdown_timer_container')]//div[@data-short='Min']")
	WebElement areaContadorMinutos;
	
	@FindBy(xpath = "//div[contains(@class,'et_pb_countdown_timer_container')]//div[@data-short='Seg']")
	WebElement areaContadorSegundos;
	
	/*
	 * P�gina: Home
	 * �rea: RSVP
	 */
	@FindBy(xpath = "//div/h2[contains(text(),'Confirmar')]")
	WebElement rsvpHeaderArea;
	
	@FindBy(xpath = "//div/a[@id='rsvpArea']")
	WebElement rsvpArea;
	
	@FindBy(xpath = "//div[@id='rsvpPlugin']//input[@id='firstName']")
	WebElement rsvpInputCodigoRSVP;
	
	@FindBy(xpath = "//div[@id='rsvpPlugin']//input[@type='submit' and contains(@value,'Verificar')]")
	WebElement rsvpButtonVerificarCodigo;
	
	@FindBy(xpath = "//div[@id='rsvpPlugin']//span[@id='rsvpcParagraph']")
	WebElement rsvpFieldName;

	@FindBy(xpath = "//div[@id='rsvpPlugin']//input[@id='mainRsvpY']")
	WebElement rsvpInputRadioSim;
	
	@FindBy(xpath = "//div[@id='rsvpPlugin']//input[@id='mainRsvpN']")
	WebElement rsvpInputRadioNao;
	
	@FindBy(xpath = "//div[@id='rsvpPlugin']//input[@type='submit' and contains(@value,'Enviar')]")
	WebElement rsvpButtonEnviar;
	
	@FindBy(xpath = "//div[@id='rsvpPlugin']//input[@type='submit' and contains(@value,'Sim')]")
	WebElement rsvpButtonEditConfirmationSim;
	
	@FindBy(xpath = "//div[@id='rsvpPlugin']//input[@type='submit' and contains(@value,'N�o')]")
	WebElement rsvpButtonEditConfirmationNao;
	
	@FindBy(xpath = "//div[@id='rsvpPlugin']//p[@class='rsvpParagraph' and contains(text(),'Obrigado')]")
	WebElement rsvpFieldMessageConfirmationRSVP;
	
	@FindBy(xpath = "//div[@id='rsvpPlugin']//a[@id='rsvpcReloadPage']")
	WebElement rsvpFieldRelaoadFormRSVP;
	
	@FindBy(xpath = "//div[@id='rsvpPlugin']//div[@class='rsvpAdditionalAttendee'][1]//span[@class='rsvpcAdditionalAttendee']")
	WebElement rsvpFieldAdditionalAttendee;
	
	@FindBy(xpath = "//div[@id='rsvpPlugin']//div[@class='rsvpAdditionalAttendee'][1]//input[@value='Y']")
	WebElement rsvpFieldAdditionalRadioSim;
	
	@FindBy(xpath = "//div[@id='rsvpPlugin']//div[@class='rsvpAdditionalAttendee'][1]//input[@value='N']")
	WebElement rsvpFieldAdditionalRadioNao;
	
	/*
	 * P�gina: Home
	 * Metodos
	 */
	
	private void validateStartFormRSVP() throws IOException {
		
		//Verificar �rea RSVP em est�gio inicial
		if (elementExist(rsvpInputCodigoRSVP)) {
			TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Campo 'C�digo RSVP' encontrado com sucesso");
		} else {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Campo 'C�digo RSVP' n�o encontrado");
			Assert.fail();
		}
		
		if (elementExist(rsvpButtonVerificarCodigo)) {
			TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Bot�o 'Verificar C�digo' encontrado com sucesso");
		} else {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Bot�o 'Verificar C�digo' n�o encontrado");
			Assert.fail();
		}
		
	}
	
	public void insertValueRSVP(String codeRSVP) throws IOException {
		
		//Preencher campo 'rsvpInputCodigoRSVP'
		try {
			validateStartFormRSVP();
			sendkeys(rsvpInputCodigoRSVP, codeRSVP);
			TestRunner.addStepPassed(BasePage.getClassMethod(this), "Campo 'C�digo RSVP' encontrado com sucesso");
		} catch (Exception e) {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Erro ao inserir informa��o 'C�digo RSVP'");
			Assert.fail();
		}
		
	}
	
	public void clickButtonVerificarCodigoRSVP() throws IOException {
		
		//Clicar no bot�o 'Verificar C�digo'
		try {
			submit(rsvpInputCodigoRSVP);
			TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Bot�o 'Verificar C�digo' acionado com sucesso");
		} catch (Exception e) {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Erro ao clicar no bot�o 'Verificar C�digo'");
			Assert.fail();
		}
		
	}
	
	public void validadeFieldNameRSVP(String name) throws IOException, InterruptedException {
		
		//Verificar informa��o 'Nome' da massa de teste
		if (elementExist(rsvpFieldName)) {
			
			String valueFieldName = getValue(rsvpFieldName);
			if (name.equalsIgnoreCase(valueFieldName)) {
				scrollToElementJavascript(rsvpHeaderArea);
				TestRunner.addStepPassed(BasePage.getClassMethod(this), "Campo 'Nome' encontrado com valor " + valueFieldName);
			} else {
				TestRunner.addStepFailed(BasePage.getClassMethod(this), "Nomes divergem - Coletado: " + valueFieldName +" // Informado: " + name);
				Assert.fail();
			}
			
		} else {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Campo 'Nome' n�o encontrado");
			Assert.fail();
		}
		
	}
	
	private void validateFormAnswerRSVP() throws IOException {
		
		//Verificar op��es de resposta
		if (elementExist(rsvpInputRadioSim) && elementExist(rsvpInputRadioNao) && elementExist(rsvpButtonEnviar)) {
			
			TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Op��o de resposta 'Sim' encontrada com sucesso");
			TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Op��o de resposta 'N�o' encontrada com sucesso");
			TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Bot�o 'Enviar' encontrado com sucesso");
			
		} else {
			
			if (!elementExist(rsvpInputRadioSim)) {
				TestRunner.addStepFailed(BasePage.getClassMethod(this), "Op��o 'Sim' n�o foi encontrado");
			}
			
			if (!elementExist(rsvpInputRadioNao)) {
				TestRunner.addStepFailed(BasePage.getClassMethod(this), "Op��o 'N�o' n�o foi encontrado");
			}
			
			if (!elementExist(rsvpButtonEnviar)) {
				TestRunner.addStepFailed(BasePage.getClassMethod(this), "Bot�o 'Enviar' n�o foi encontrado");
			}
			
			Assert.fail();
		}

	}
	
	public void setAnswerRSVP(String answer) throws IOException {
		
		//Validar formul�rio de resposta RSVP
		validateFormAnswerRSVP();
		
		//Enviar resposta
		try {
			if (answer.equalsIgnoreCase("sim")) {
				click(rsvpInputRadioSim);
				TestRunner.addStepPassed(BasePage.getClassMethod(this), "Resposta '" + answer.toLowerCase() + "' selecionada com sucesso");
				submit(rsvpButtonEnviar);
				TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Resposta '" + answer.toLowerCase() + "' enviada com sucesso");
			} else {
				click(rsvpInputRadioNao);
				TestRunner.addStepPassed(BasePage.getClassMethod(this), "Resposta '" + answer.toLowerCase() + "' selecionada com sucesso");
				submit(rsvpButtonEnviar);
				TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Resposta '" + answer.toLowerCase() + "' enviada com sucesso");
			}
		} catch (Exception e) {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Erro ao clicar no bot�o 'Enviar'");
			Assert.fail();
		}

	}
	
	public void validateSendAnswerRSVP(String name) throws IOException {
		
		//Sobreescrever elemento 'rsvpFieldMessageConfirmationRSVP'
		rsvpFieldMessageConfirmationRSVP = driver.findElement(By.xpath("//div[@id='rsvpPlugin']//p[@class='rsvpParagraph' and contains(text(),'Obrigado " + name + "')]"));
		
		//Verificar RSVP conclu�do
		if (elementExist(rsvpFieldMessageConfirmationRSVP)) {
			TestRunner.addStepPassed(BasePage.getClassMethod(this), "RSVP efetuado com sucesso para a pessoa '" + name + "'");
		} else {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "N�o foi poss�vel validar a conclus�o do RSVP para a pessoa '" + name + "'");
			Assert.fail();
		}
		
	}
	
	private void validateReloadFormRSVP() throws IOException {
		
		//Validar formul�rio RSVP apto para reload
		if (elementExist(rsvpFieldRelaoadFormRSVP)) {
			TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Link para reiniciar o formul�rio RSVP localizado com sucesso");
		} else {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "N�o foi poss�vel localizar o link para reiniciar o formul�rio RSVP");
			Assert.fail();
		}
		
	}
	
	public void reloadFormRSVP() throws IOException {
		
		//Efetuar reload formul�rio RSVP
		validateReloadFormRSVP();
		
		try {
			click(rsvpFieldRelaoadFormRSVP);
			validateStartFormRSVP();
			TestRunner.addStepPassed(BasePage.getClassMethod(this), "Formul�rio RSVP reiniciado com sucesso");
		} catch (Exception e) {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Erro ao reiniciar o formul�rio RSVP");
			Assert.fail();
		}
		
	}
	
}