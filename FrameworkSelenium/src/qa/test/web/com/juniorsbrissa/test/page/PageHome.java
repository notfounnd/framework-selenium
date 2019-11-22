package qa.test.web.com.juniorsbrissa.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.test.web.basePage.BasePage;

public class PageHome extends BasePage {

	public PageHome(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
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
}