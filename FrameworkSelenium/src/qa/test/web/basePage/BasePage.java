package qa.test.web.basePage;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import qa.test.web.basePage.TestRunner;

public class BasePage {
	
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
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Elemento não pode ser localizado pela referência " + getLocator(element));
			Assert.fail();
		}
	}

	public boolean elementExist(WebElement element) throws IOException {
		try {
			scrollToElementJavascript(element);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			TestRunner.addStepWarning(BasePage.getClassMethod(this), "Timeout ao tentar encontrar o elemento" + getLocator(element));
			return false;
		}
	}

	public void waitToBeClickable(WebElement element) throws IOException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Não foi possível clicar no element " + getLocator(element) + ". Causa: " + e.getMessage());
			Assert.fail();
		}

	}

	public void sendkeys(WebElement element, String text) throws IOException {
		waitToBeClickable(element);
		element.click();
		element.sendKeys(text);
		TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Texto '" + text + "' enviado ao elemento com a referência " + getLocator(element));
		}
	
	public String getValue(WebElement element) throws IOException {

		waitVisibility(element);
		String value = element.getText();
		TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Valor '" + value + "' encontrado " + getLocator(element));
		return value;
		}

	public void click(WebElement element) throws IOException {
		waitToBeClickable(element);
		element.click();
		TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Click realizado no elemento com a referência " + getLocator(element));
	}
	
	public void submit(WebElement element) throws IOException {
		waitToBeClickable(element);
		element.submit();
		TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Click realizado no elemento com a referência " + getLocator(element));
	}

	public void clickByCoordinates(WebElement element, int x, int y) throws IOException {
		waitToBeClickable(element);
		action.moveToElement(element, 0, 0);
		action.moveByOffset(x, y);
		action.click();
		action.build();
		action.perform();
		TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Click por coordenada realizado no elemento com a referência " + getLocator(element) + " (x: " + x + ", y: " + y + ")");
	}
	
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
			TestRunner.addStepPassedNoPrint(BasePage.getClassMethod(this), "Tecla '" + key.toString() + "' pressionada");
			//Exemplo de uso
			//keyBoardEvent(Keys.LEFT_CONTROL, driver.findElement(By.tagName("body")) );
		} catch (Exception e) {
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
	
	public void scrollToElementSelenium(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}
	
	public void scrollToElementJavascript(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); 
	}
}
