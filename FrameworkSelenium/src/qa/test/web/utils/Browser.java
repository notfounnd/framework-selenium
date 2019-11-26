package qa.test.web.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.JavascriptExecutor;

import qa.test.web.basePage.BasePage;
import qa.test.web.basePage.TestRunner;

public class Browser {
		
	static WebDriver driver;
	WebDriverWait wait;
	ArrayList<String> tabs;
	
	public Browser(){
		//PageFactory.initElements(driver, this);
	}
	
	/*
	 * Abrir navegador
	 * 
	 * Variaveis
	 * navegador 		= Selecionar navegador
	 * pageLoadStrategy = Modo de sincronismo das páginas
	 * 
	 * Exemplo:
	 * 	optionBrowser.setPageLoadStrategy(PageLoadStrategy.NORMAL);
	 * 	optionBrowser.setPageLoadStrategy(PageLoadStrategy.EAGER);
	 * 	optionBrowser.setPageLoadStrategy(PageLoadStrategy.NONE);
	 * 
	 * Chamada:
	 *	Browser var = new Browser();
	 *	var.browser("CHROME", PageLoadStrategy.NORMAL);
	 * 
	 */
	
	private void waitPageLoad() throws IOException {
		
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
			    return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
        };
        wait = new WebDriverWait(driver, 30);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        try {
        	TestRunner.addStepInfo(BasePage.getClassMethod(this), "Aguardando carregar página (até 30 segundos)");
        	if (wait.until(pageLoadCondition)) {
        		String status = (String)js.executeScript("return document.readyState");
        		status = "Status da página: " + status;
        		TestRunner.addStepInfo(BasePage.getClassMethod(this), status);
			} else {
				String status = (String)js.executeScript("return document.readyState");
				status = "Status da página: " + status;
				TestRunner.addStepInfo(BasePage.getClassMethod(this), status);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public void openBrowser(String navegador, PageLoadStrategy pageLoadStrategy) throws IOException {
		navegador = navegador.toUpperCase();
		try {
			switch(navegador) {
				case "CHROME":
					System.setProperty("webdriver.chrome.driver", "drivers\\chrome\\chromedriver.exe");
					ChromeOptions optionChrome = new ChromeOptions();
					driver = new ChromeDriver(optionChrome);
					driver.manage().window().maximize();
					TestRunner.addStepInfo(BasePage.getClassMethod(this), "Navegador " + navegador.toLowerCase() + " iniciado com sucesso");
					break;
				case "FIREFOX":
					System.setProperty("webdriver.gecko.driver", "drivers\\firefox\\geckodriver.exe");
					FirefoxOptions optionFirefox = new FirefoxOptions();
					optionFirefox.setPageLoadStrategy(pageLoadStrategy);
					driver = new FirefoxDriver(optionFirefox);
					driver.manage().window().maximize();
					TestRunner.addStepInfo(BasePage.getClassMethod(this), "Navegador " + navegador.toLowerCase() + " iniciado com sucesso");
					break;
				default:
					TestRunner.addStepFailed(BasePage.getClassMethod(this), "Não foi possível iniciar o navegador " + navegador.toLowerCase());
					Assert.fail();
			}
		} catch (Exception e) {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Erro ao tentar abrir o navegador " + navegador.toLowerCase());
			Assert.fail();
		}
	}
	
	public void closeBrowser() throws IOException {
		try {
			driver.close();
			driver.quit();
			TestRunner.addStepInfo(BasePage.getClassMethod(this), "Navegador encerrado com sucesso");
		} catch (Exception e) {
			TestRunner.addStepWarning(BasePage.getClassMethod(this), "Não foi possível encerrar o navegador");
			try {
				Assert.fail();
			} catch (Exception e2) {
				//Se voce remover esse try o mundo ira cair sobre sua cabeca
			}
		}
	}
	
	/*
	 * Override do método 'closeBrowser' para não para a execução
	 */
	public void closeBrowser(boolean stopAtError) throws IOException {
		try {
			driver.close();
			driver.quit();
			TestRunner.addStepInfo(BasePage.getClassMethod(this), "Navegador encerrado com sucesso");
		} catch (Exception e) {
			TestRunner.addStepWarning(BasePage.getClassMethod(this), "Não foi possível encerrar o navegador");
			if (stopAtError) {
				try {
					Assert.fail();
				} catch (Exception e2) {
					//Se voce remover esse try o mundo ira cair sobre sua cabeca
				}
			}
		}
	}
	
	public void openURL(String url) throws IOException {
		try {
			driver.get(url);
			waitPageLoad();
			TestRunner.addStepPassed(BasePage.getClassMethod(this), "URL " + url + " acessada sucesso");
		} catch (Exception e) {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Erro ao tentar acessar a URL " + url);
			Assert.fail();
		}
	}
	
	public void refreshBrowser() throws IOException {
		try {
			driver.navigate().refresh();
			waitPageLoad();
			TestRunner.addStepPassed(BasePage.getClassMethod(this), "Refresh da página realizado com sucesso");
		} catch (Exception e) {
			TestRunner.addStepWarning(BasePage.getClassMethod(this), "Não foi possível realizar o refresh da página");
			try {
				Assert.fail();
			} catch (Exception e2) {
				//Se voce remover esse try o mundo ira cair sobre sua cabeca
			}
		}
	}
	
	public void forwardBrowser() throws IOException {
		try {
			driver.navigate().forward();
			waitPageLoad();
			TestRunner.addStepPassed(BasePage.getClassMethod(this), "Forward da página realizado com sucesso");
		} catch (Exception e) {
			TestRunner.addStepWarning(BasePage.getClassMethod(this), "Não foi possível realizar o forward da página");
			try {
				Assert.fail();
			} catch (Exception e2) {
				//Se voce remover esse try o mundo ira cair sobre sua cabeca
			}
		}
	}
	
	public void backwardBrowser() throws IOException {
		try {
			driver.navigate().back();
			waitPageLoad();
			TestRunner.addStepPassed(BasePage.getClassMethod(this), "Backward da página realizado com sucesso");
		} catch (Exception e) {
			TestRunner.addStepWarning(BasePage.getClassMethod(this), "Não foi possível realizar o backward da página");
			try {
				Assert.fail();
			} catch (Exception e2) {
				//Se voce remover esse try o mundo ira cair sobre sua cabeca
			}
		}
	}
	
}
