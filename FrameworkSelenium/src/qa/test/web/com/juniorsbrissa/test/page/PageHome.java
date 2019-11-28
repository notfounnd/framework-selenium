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
	 * Página: Home
	 */
	@FindBy(xpath = "//ul[@id='et-menu']//a[contains(text(),'Home')]")
	WebElement linkMenuHome;
	
	@FindBy(xpath = "//div[contains(@class,'et_pb_row_0')]//h1[contains(text(),'Henrique')]")
	WebElement headerTituloSite;
	
	@FindBy(xpath = "//a[contains(@class,'color') and contains(text(),'chegar')]")
	WebElement buttonComoChegar;
	
	/*
	 * Página: Home
	 * Área: Contador
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
	 * Página: Home
	 * Área: RSVP
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
	
	@FindBy(xpath = "//div[@id='rsvpPlugin']//input[@type='submit' and contains(@value,'Não')]")
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
	 * Página: Home
	 * Metodos
	 */
	
	private void validateStartFormRSVP() throws IOException {
		
		//Verificar área RSVP em estágio inicial
		if (elementExist(rsvpInputCodigoRSVP)) {
			TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Campo 'Código RSVP' encontrado com sucesso");
		} else {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Campo 'Código RSVP' não encontrado");
			Assert.fail();
		}
		
		if (elementExist(rsvpButtonVerificarCodigo)) {
			TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Botão 'Verificar Código' encontrado com sucesso");
		} else {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Botão 'Verificar Código' não encontrado");
			Assert.fail();
		}
		
	}
	
	public void insertValueRSVP(String codeRSVP) throws IOException {
		
		//Preencher campo 'rsvpInputCodigoRSVP'
		try {
			validateStartFormRSVP();
			sendkeys(rsvpInputCodigoRSVP, codeRSVP);
			TestRunner.addStepPassed(BasePage.getClassMethod(this), "Campo 'Código RSVP' encontrado com sucesso");
		} catch (Exception e) {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Erro ao inserir informação 'Código RSVP'");
			Assert.fail();
		}
		
	}
	
	public void clickButtonVerificarCodigoRSVP() throws IOException {
		
		//Clicar no botão 'Verificar Código'
		try {
			submit(rsvpInputCodigoRSVP);
			TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Botão 'Verificar Código' acionado com sucesso");
		} catch (Exception e) {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Erro ao clicar no botão 'Verificar Código'");
			Assert.fail();
		}
		
	}
	
	public void validadeFieldNameRSVP(String name) throws IOException, InterruptedException {
		
		//Verificar informação 'Nome' da massa de teste
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
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Campo 'Nome' não encontrado");
			Assert.fail();
		}
		
	}
	
	private void validateFormAnswerRSVP() throws IOException {
		
		//Verificar opções de resposta
		if (elementExist(rsvpInputRadioSim) && elementExist(rsvpInputRadioNao) && elementExist(rsvpButtonEnviar)) {
			
			TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Opção de resposta 'Sim' encontrada com sucesso");
			TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Opção de resposta 'Não' encontrada com sucesso");
			TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Botão 'Enviar' encontrado com sucesso");
			
		} else {
			
			if (!elementExist(rsvpInputRadioSim)) {
				TestRunner.addStepFailed(BasePage.getClassMethod(this), "Opção 'Sim' não foi encontrado");
			}
			
			if (!elementExist(rsvpInputRadioNao)) {
				TestRunner.addStepFailed(BasePage.getClassMethod(this), "Opção 'Não' não foi encontrado");
			}
			
			if (!elementExist(rsvpButtonEnviar)) {
				TestRunner.addStepFailed(BasePage.getClassMethod(this), "Botão 'Enviar' não foi encontrado");
			}
			
			Assert.fail();
		}

	}
	
	public void setAnswerRSVP(String answer) throws IOException {
		
		//Validar formulário de resposta RSVP
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
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Erro ao clicar no botão 'Enviar'");
			Assert.fail();
		}

	}
	
	public void validateSendAnswerRSVP(String name) throws IOException {
		
		//Sobreescrever elemento 'rsvpFieldMessageConfirmationRSVP'
		rsvpFieldMessageConfirmationRSVP = driver.findElement(By.xpath("//div[@id='rsvpPlugin']//p[@class='rsvpParagraph' and contains(text(),'Obrigado " + name + "')]"));
		
		//Verificar RSVP concluído
		if (elementExist(rsvpFieldMessageConfirmationRSVP)) {
			TestRunner.addStepPassed(BasePage.getClassMethod(this), "RSVP efetuado com sucesso para a pessoa '" + name + "'");
		} else {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Não foi possível validar a conclusão do RSVP para a pessoa '" + name + "'");
			Assert.fail();
		}
		
	}
	
	private void validateReloadFormRSVP() throws IOException {
		
		//Validar formulário RSVP apto para reload
		if (elementExist(rsvpFieldRelaoadFormRSVP)) {
			TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Link para reiniciar o formulário RSVP localizado com sucesso");
		} else {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Não foi possível localizar o link para reiniciar o formulário RSVP");
			Assert.fail();
		}
		
	}
	
	public void reloadFormRSVP() throws IOException {
		
		//Efetuar reload formulário RSVP
		validateReloadFormRSVP();
		
		try {
			click(rsvpFieldRelaoadFormRSVP);
			validateStartFormRSVP();
			TestRunner.addStepPassed(BasePage.getClassMethod(this), "Formulário RSVP reiniciado com sucesso");
		} catch (Exception e) {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Erro ao reiniciar o formulário RSVP");
			Assert.fail();
		}
		
	}
	
}