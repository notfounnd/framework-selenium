package qa.test.web.com.juniorsbrissa.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.test.web.basePage.BasePage;

public class PageExemplo extends BasePage {

	public PageExemplo(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Elements Reference
	 */
	
	/*
	 * Página: Página Exemplo
	 */
	@FindBy(xpath = "//ul[@id='et-menu']//a[contains(text(),'Página')]")
	WebElement linkMenuPaginaExemplo;
	
	@FindBy(xpath = "//div[contains(@class,'et_pb_row_0')]//h1[contains(text(),'Página')]")
	WebElement headerTituloPaginaExemplo;
	
	@FindBy(xpath = "//div[contains(@class,'et_pb_row_1')]//h3[contains(text(),'Elegant')]")
	WebElement headerFormElegant;
	
	@FindBy(xpath = "//div[contains(@class,'et_pb_row_1')]//h3[contains(text(),'WPCF7')]")
	WebElement headerFormWPCF7;
	
	/*
	 * Form: Elegant
	 */
	@FindBy(xpath = "//div[@id='formElegantContato']")
	WebElement formElegant;
	
	@FindBy(xpath = "//div[@id='formElegantContato']//input[@data-original_id='inputname']")
	WebElement formElegantFieldInputName;
	
	@FindBy(xpath = "//div[@id='formElegantContato']//input[@data-original_id='inputemail']")
	WebElement formElegantFieldInputEmail;
	
	@FindBy(xpath = "//div[@id='formElegantContato']//p[@data-id='inputradio']")
	WebElement formElegantFieldInputRadio;
	
	@FindBy(xpath = "//div[@id='formElegantContato']//p[@data-id='inputradio']//label[contains(text(),'Sim')]")
	WebElement formElegantFieldInputRadioSim;
	
	@FindBy(xpath = "//div[@id='formElegantContato']//p[@data-id='inputradio']//label[contains(text(),'Não')]")
	WebElement formElegantFieldInputRadioNao;
	
	@FindBy(xpath = "//div[@id='formElegantContato']//textarea[@data-original_id='inputmessage']")
	WebElement formElegantFieldInputMensagem;
	
	@FindBy(xpath = "//div[@id='formElegantContato']//span[contains(@class,'captcha')]")
	WebElement formElegantFieldInputCaptchaQuiz;
	
	@FindBy(xpath = "//div[@id='formElegantContato']//input[contains(@name,'captcha')]")
	WebElement formElegantFieldInputCaptcha;
	
	@FindBy(xpath = "//div[@id='formElegantContato']//button[@type='submit']")
	WebElement formElegantFieldInputSubmit;
	
	/*
	 * Form: WPCF7
	 */
	@FindBy(id = "formWPCF7Contato")
	WebElement formWPCF7;
	
	@FindBy(xpath = "//div[@id='formWPCF7Contato']//input[@name='inputName']")
	WebElement formWPCF7FieldInputName;
	
	@FindBy(xpath = "//div[@id='formWPCF7Contato']//input[@name='inputEmail']")
	WebElement formWPCF7FieldInputEmail;
	
	@FindBy(xpath = "//div[@id='formWPCF7Contato']//input[@name='inputSubject']")
	WebElement formWPCF7FieldInputSubject;
	
	@FindBy(xpath = "//div[@id='formWPCF7Contato']//select[@name='inputOption']")
	WebElement formWPCF7FieldInputOption;
	
	@FindBy(xpath = "//div[@id='formWPCF7Contato']//textarea[@name='inputMessage']")
	WebElement formWPCF7FieldInputMessage;
	
	@FindBy(xpath = "//div[@id='formWPCF7Contato']//span[contains(@class,'quiz')]")
	WebElement formWPCF7FieldInputCaptchaQuiz;
	
	@FindBy(xpath = "//div[@id='formWPCF7Contato']//input[@name='inputCaptcha']")
	WebElement formWPCF7FieldInputCaptcha;
	
	@FindBy(xpath = "//div[@id='formWPCF7Contato']//input[@type='submit']")
	WebElement formWPCF7FieldInputSubmit;
}
