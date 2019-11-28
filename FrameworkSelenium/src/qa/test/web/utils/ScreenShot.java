package qa.test.web.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import qa.test.web.basePage.TestRunner;

public class ScreenShot {
	
	private static String dateLog;
	private static String screenShotName; 
	private static String dest;
	
	private static File source;
	private static File destination;
	private static TakesScreenshot ts;
	
	private static WebDriver driver = Browser.driver;
	
	private static Timestamp timeStamp;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS_");
	
     
    public static String capture() throws IOException    {
    	
    	//Print Screen do driver instanciado
    	ts = (TakesScreenshot)driver;
        source = ts.getScreenshotAs(OutputType.FILE);
        
        //Gerar timestamp para o arquivo de log
  		dateLog = getTimeStamp();
  		
  		//Trata Nome ScreenShot
  		screenShotName = replaceScreenShotName();
  		
  		//Armazena ScreenShot na pasta de execução
  		dest = saveScreenShot();
  		
        return dest;
    }
    
	private static String getTimeStamp() throws IOException {
		timeStamp = new Timestamp(System.currentTimeMillis());
		dateLog = dateFormat.format(timeStamp).toString();
		
		return dateLog;
	}
	
	private static String replaceScreenShotName() throws IOException {
		screenShotName = ScreenShot.class.toString();
		screenShotName = screenShotName.replace(".", "_");
		screenShotName = screenShotName.replace("class ", "");
		
		return screenShotName;
	}
	private static String saveScreenShot() throws IOException {
        //String dest = System.getProperty("user.dir") +"\\report\\screenshots\\" + dateLog + screenShotName+ ".png";
        dest = TestRunner.folderScreenShots + "\\" + dateLog + screenShotName+ ".png";
        TestRunner.addStepInfo(ScreenShot.class.toString(), "Evidência salva com sucesso no caminho: " + dest);
        
        destination = new File(dest);
        FileUtils.copyFile(source, destination);        
                    
		return dest;
	}
}