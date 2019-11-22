package qa.test.web.basePage;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

import qa.test.web.basePage.TestRunner;

//@SuppressWarnings("rawtypes")

public class BasePage {
	
	/*
	 * Data da Última Modificação: 05/11/2019
	 * Descritivo da Modificação: Merge efetuado entre códigos: Ricardo e Natália. Editado por: Júnior Sbrissa e Ricardo Cremonez.
	 */
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Actions action;
	
	public BasePage(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		action = new Actions(driver);
	}
	
	public void waitVisibility(WebElement element) throws IOException {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));			
		} catch (TimeoutException e) {
			//TestRunner.addStep("[Step Status: Failed][Elemento não está visível] Elemento não pode ser localizado pela referência " + getLocator(element));
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Elemento não pode ser localizado pela referência " + getLocator(element));
			Assert.fail();
		}
	}

	public boolean elementExist(WebElement element) throws IOException {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			//TestRunner.addStep("[Step Status: Warning][Elemento não existe] Causa: Tempo de espera alcançado " + getLocator(element));
			TestRunner.addStepWarning(BasePage.getClassMethod(this), "Timeout ao tentar encontrar o elemento" + getLocator(element));
			return false;
		}
	}

	public void waitToBeClickable(WebElement element) throws IOException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			//TestRunner.addStep("[Step Status: Failed][Elemento não é clicável] Elemento não é clicável " + getLocator(element) + ". Causa: " + e.getMessage());
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Não foi possível clicar no element " + getLocator(element) + ". Causa: " + e.getMessage());
			Assert.fail();
		}

	}

	public void sendkeys(WebElement element, String text) throws IOException {
		waitToBeClickable(element);
		element.click();
		element.sendKeys(text);
		//TestRunner.addStep("[Step Status: Passed][SendKeys] Texto '" + text + "' enviado ao elemento com a referência " + getLocator(element));
		TestRunner.addStepPassed(BasePage.getClassMethod(this), "Texto '" + text + "' enviado ao elemento com a referência " + getLocator(element));
		}
	
	public String getValues(WebElement element) throws IOException {

		waitVisibility(element);
		String valor = element.getText();
		//TestRunner.addStep("[Step Status: Passed][GetValue] Valor '" + valor + "' encontrado " + getLocator(element));
		TestRunner.addStepPassed(BasePage.getClassMethod(this), "Valor '" + valor + "' encontrado " + getLocator(element));
		return valor;
		}

	public void click(WebElement element) throws IOException {
		waitToBeClickable(element);
		element.click();
		//TestRunner.addStep("[Step Status: Passed][Click] Elemento com a referência " + getLocator(element));
		TestRunner.addStepPassed(BasePage.getClassMethod(this), "Click realizado no elemento com a referência " + getLocator(element));
	}

	public void clickByCoordinates(WebElement element, int x, int y) throws IOException {
		waitToBeClickable(element);
		action.moveToElement(element, 0, 0);
		action.moveByOffset(x, y);
		action.click();
		action.build();
		action.perform();
		//TestRunner.addStep("[Step Status: Passed][Click por Coordenada] Elemento com a referência " + getLocator(element)	+ " referente a coordenada x:" + x + " e coordenada y:" + y);
		TestRunner.addStepPassed(BasePage.getClassMethod(this), "Click por coordenada realizado no elemento com a referência " + getLocator(element) + " (x: " + x + ", y: " + y + ")");
	}
	
//	public void setContextToWebView() throws IOException {
//		((AndroidDriver) device).context("WEBVIEW_br.com.bradseg.bscelular");
//		TestRunner.addStep("[Step Status: Passed] Contexto modificado com sucesso para 'WEBVIEW_br'");
//	}
	
	/** 
	 * 
	 * Metodo genérico para pressionar teclas especiais.
	 * 
	 * Parametro Keys pode recever as seguintes opções:
	 *
	 *	NULL 			= '\ue000'
	 *	CANCEL 			= '\ue001'  # ^break
	 *	HELP 			= '\ue002'
	 *	BACKSPACE 		= '\ue003'
	 *	BACK_SPACE 		= BACKSPACE
	 *	TAB 			= '\ue004'
	 *	CLEAR 			= '\ue005'
	 *	RETURN 			= '\ue006'
	 *	ENTER 			= '\ue007'
	 *	SHIFT 			= '\ue008'
	 *	LEFT_SHIFT 		= SHIFT
	 *	CONTROL 		= '\ue009'
	 *	LEFT_CONTROL 	= CONTROL
	 *	ALT 			= '\ue00a'
	 *	LEFT_ALT 		= ALT
	 *	PAUSE 			= '\ue00b'
	 *	ESCAPE 			= '\ue00c'
	 *	SPACE 			= '\ue00d'
	 *	PAGE_UP 		= '\ue00e'
	 *	PAGE_DOWN 		= '\ue00f'
	 *	END 			= '\ue010'
	 *	HOME 			= '\ue011'
	 *	LEFT 			= '\ue012'
	 *	ARROW_LEFT 		= LEFT
	 *	UP 				= '\ue013'
	 *	ARROW_UP 		= UP
	 *	RIGHT 			= '\ue014'
	 *	ARROW_RIGHT 	= RIGHT
	 *	DOWN 			= '\ue015'
	 *	ARROW_DOWN 		= DOWN
	 *	INSERT 			= '\ue016'
	 *	DELETE 			= '\ue017'
	 *	SEMICOLON 		= '\ue018'
	 *	EQUALS 			= '\ue019'
	 *	
	 *	NUMPAD0 		= '\ue01a'  # number pad keys
	 *	NUMPAD1 		= '\ue01b'
	 *	NUMPAD2 		= '\ue01c'
	 *	NUMPAD3 		= '\ue01d'
	 *	NUMPAD4 		= '\ue01e'
	 *	NUMPAD5 		= '\ue01f'
	 *	NUMPAD6 		= '\ue020'
	 *	NUMPAD7 		= '\ue021'
	 *	NUMPAD8 		= '\ue022'
	 *	NUMPAD9 		= '\ue023'
	 *	MULTIPLY 		= '\ue024'
	 *	ADD 			= '\ue025'
	 *	SEPARATOR 		= '\ue026'
	 *	SUBTRACT 		= '\ue027'
	 *	DECIMAL 		= '\ue028'
	 *	DIVIDE 			= '\ue029'
	 *	
	 *	F1 				= '\ue031'  # function  keys
	 *	F2 				= '\ue032'
	 *	F3 				= '\ue033'
	 *	F4 				= '\ue034'
	 *	F5 				= '\ue035'
	 *	F6 				= '\ue036'
	 *	F7 				= '\ue037'
	 *	F8 				= '\ue038'
	 *	F9 				= '\ue039'
	 *	F10 			= '\ue03a'
	 *	F11 			= '\ue03b'
	 *	F12 			= '\ue03c'
	 *	
	 *	META 			= '\ue03d'
	 *	COMMAND 		= '\ue03d'
	 *
	 **/
	
	public void keyBoardEvent(Keys key, WebElement element) throws IOException {
		
		try {
			element.sendKeys(key);
			//TestRunner.addStep("[Step Status: Passed] Tecla [" + key.toString() + "] pressionada");
			TestRunner.addStepPassed(BasePage.getClassMethod(this), "Tecla '" + key.toString() + "' pressionada");
			//Comentario do mau pra não esquecer como usa
			//keyBoardEvent(Keys.ADD, driver.findElement(By.tagName("body")) );
		} catch (Exception e) {
			//TestRunner.addStep("[Step Status: Failed] Não foi possível pressionar a tecla [" + key.toString() + "]");
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Não foi possível pressionar a tecla '" + key.toString() + "'");
			Assert.fail();
		}
	
	}

	private String getLocator(WebElement element) {
		String locator;
		try {
			if (!element.toString().contains("->")) {
				locator = element.toString().substring(element.toString().indexOf("'"));
			} else {
				locator = element.toString().substring(element.toString().indexOf("->"));
			}
			return locator;
		} catch (Exception e) {
			return "";
		}

	}
	
	public static String getClassMethod(Object object) {
		return object.getClass().toString();
	}
}
