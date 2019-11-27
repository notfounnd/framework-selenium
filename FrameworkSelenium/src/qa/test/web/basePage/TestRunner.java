package qa.test.web.basePage;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.internal.runners.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.relevantcodes.extentreports.ExtentReports;

import qa.test.web.utils.BaseExtentReports;
import qa.test.web.utils.FolderHelper;
import qa.test.web.utils.ScreenShot;
import qa.test.web.utils.TextHelper;

@SuppressWarnings({"unused"})
public class TestRunner {
	
	/*
	 * Dev: Junior Sbrissa
	 */
	
	public  static String fileLog;
	public  static String folderScenario;
	public  static String folderExecution;
	public  static String folderScreenShots;
	public  static String testClass;
	public  static String testMethod;
	public  static String Scenario;
	private static String dateLog;
	private static String message;
	private static String argsHardCoded = "qa.test.web.testDebug.TestDebug#CN001_CT001#Relatorio_Padrao.txt";
	private static String[] parameters;
	private static String[] testClassVet;
	private static String[] argsIntern;
	
	private static int count;
	
	private static JUnitCore jUnitCore;
	private static TextHelper logFile;
	private static FolderHelper folder;
	private static BaseExtentReports report;
	private static Request request;
	private static Result result;
	
	private static Timestamp timeStamp;
	
	
	private static List<String> logsList = new ArrayList<String>();
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat dateFormatFolder = new SimpleDateFormat("yyyy_MM_dd_HHmmssSS");
	
	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		
		argsIntern =  args;	
		//Instancia JunitCore responsável por executar/gerenciar a execução dos testes via cmd
		jUnitCoreInstace();
		
		//Implementa listener
		addListener();
		
		//Se o parâmetro não foi enviado, popula atributos da classe com valores fixos [debug]
		verifyParameters();
		
		//Verifica se há os 3 argumentos esperados para início dos testes
		if(parameters.length ==  3) {
					
			//Tratamento para o parâmetro enviado via cmd	
			testClass = parameters[0];
			testMethod = parameters[1];	
			
			//Inicia estrutura de diretório
			initStructureFolder();
			
			//Inicia report do ExtentReport
			initReport();
			
			//Cria diretório de execução
			createFolder(folderScenario);
			createFolder(folderExecution);
			createFolder(folderScreenShots);

			//Instancia arquivo .txt de log criado pelo script que executa o projeto
			initLogFile();
			
			//Executa o método de teste dentro da classe de teste indicada
			initTest();
			
			//Finaliza execução
			for(Failure fail: result.getFailures()) {
				Scenario = folderScenario;
				//addStep("[Stacktrace]" + fail.getMessage() + "\n" + fail.getTrace());
				addStepFailed(testClass, "[Stacktrace Error Execution]" + fail.getMessage() + "\n" + fail.getTrace());
			}
			
			//Altera icone da pasta
			if(result.getFailureCount() > 0) {
				folder.changeIconFolder(folderExecution, "Failed");
			}else {
				folder.changeIconFolder(folderExecution, "Passed");
			}
			
			//Finaliza report
			endReport();
			
		    //Iputa no arquivo .txt de log os passos registrados durante o teste
			writeSteps();
			
			System.exit(result.wasSuccessful() ? 0 : 1);
		} 	
	}

	private static void jUnitCoreInstace() throws IOException {
		jUnitCore = new JUnitCore();
	}
	
	private static void addListener() throws IOException {
		jUnitCore.addListener(new TextListener(System.out));
	}
	
	private static void verifyParameters() throws IOException {
		if(argsIntern.length == 0) {
			parameters = argsHardCoded.split("#");
			fileLog = "C:\\Temp\\Reports\\" + parameters[2].substring(parameters[2].indexOf("Relatorio"));
		}
		else
		{
			parameters = argsIntern[0].split("#");
		}
	}
	private static void initLogFile() throws IOException {
		logFile = new TextHelper(fileLog);
		
		addStepInfo(TestRunner.class.toString(), "Início dos Testes");
		addStepInfo(TestRunner.class.toString(), "Total de parâmetros para início dos testes: " + parameters.length);
		addStepInfo(TestRunner.class.toString(), "Classe de teste: " + testClass);
		addStepInfo(TestRunner.class.toString(), "Método de teste: " + testMethod);
		addStepInfo(TestRunner.class.toString(), "Arquivo de log: " + fileLog);
	}
	
	private static void initStructureFolder() throws IOException {
		Scenario = testClass.replace(".", "#");
		
		testClassVet = Scenario.split("#");
		count = testClassVet.length;
		
		folderScenario = "C:\\Temp\\Reports\\" +testClassVet[count-1];
		folderExecution = folderScenario+ "\\Run_" +testMethod+ "_" +getTimeStampFolder()+ "_Execution";
		folderScreenShots = folderExecution+ "\\ScreenShots";		
	}
	
	private static void createFolder(String path) throws IOException {
		folder = new FolderHelper(path);
	}
	
	private static void initReport() throws IOException {
		report = new BaseExtentReports(testClass +"_"+ testMethod);
	}
	
	private static void endReport() throws IOException {
		BaseExtentReports.endTest();
	}
	
	private static void initTest() throws IOException, ClassNotFoundException {
		request = Request.method(Class.forName(testClass), testMethod);
		result = jUnitCore.run(request);
	}
	
	private static String getTimeStamp() throws IOException {
		timeStamp = new Timestamp(System.currentTimeMillis());
		dateLog = dateFormat.format(timeStamp).toString();
		
		return dateLog;
	}
	
	private static String getTimeStampFolder() throws IOException {
		timeStamp = new Timestamp(System.currentTimeMillis());
		dateLog = dateFormatFolder.format(timeStamp).toString();
		
		return dateLog;
	}
	
	private static void addStep(String stepMessage) throws IOException {
		System.out.println(stepMessage);
		logsList.add(stepMessage);
		BaseExtentReports.logStatusInfo(stepMessage);
	}
	
	public static void addStepPassed(String classMethod, String messageStep) throws IOException {
		//Gerar timestamp para o arquivo de log
		dateLog = getTimeStamp();
		
		//Corrigir string classMethod
		classMethod = classMethod.replace("class ", "");
		
		message = "[" + dateLog + "][Passed][" + classMethod + "] " + messageStep;
		System.out.println(message);
		logsList.add(message);
		
		message = "[" + classMethod + "] " + messageStep;
		BaseExtentReports.logStatusPassed(message);
	}
	
	public static void addStepPassedNoPrint(String classMethod, String messageStep) throws IOException {
		//Gerar timestamp para o arquivo de log
		dateLog = getTimeStamp();
		
		//Corrigir string classMethod
		classMethod = classMethod.replace("class ", "");
		
		message = "[" + dateLog + "][Passed][" + classMethod + "] " + messageStep;
		System.out.println(message);
		logsList.add(message);
		
		message = "[" + classMethod + "] " + messageStep;
		BaseExtentReports.logStatusPassedNoPrint(message);
	}
	
	public static void addStepFailed(String classMethod, String messageStep) throws IOException {
		//Gerar timestamp para o arquivo de log
		dateLog = getTimeStamp();
		
		//Corrigir string classMethod
		classMethod = classMethod.replace("class ", "");
		
		message = "[" + dateLog + "][Failed][" + classMethod + "] " + messageStep;
		System.out.println(message);
		logsList.add(message);
		
		message = "[" + classMethod + "] " + messageStep;
		BaseExtentReports.logStatusFailed(message);
	}
	
	public static void addStepWarning(String classMethod, String messageStep) throws IOException {
		//Gerar timestamp para o arquivo de log
		dateLog = getTimeStamp();
		
		//Corrigir string classMethod
		classMethod = classMethod.replace("class ", "");
		
		message = "[" + dateLog + "][Warning][" + classMethod + "] " + messageStep;
		System.out.println(message);
		logsList.add(message);
		
		message = "[" + classMethod + "] " + messageStep;
		BaseExtentReports.logStatusWarning(message);
	}
	
	public static void addStepInfo(String classMethod, String messageStep) throws IOException {
		//Gerar timestamp para o arquivo de log
		dateLog = getTimeStamp();
		
		//Corrigir string classMethod
		classMethod = classMethod.replace("class ", "");
		
		message = "[" + dateLog + "][Info][" + classMethod + "] " + messageStep;
		System.out.println(message);
		logsList.add(message);
		
		message = "[" + classMethod + "] " + messageStep;
		BaseExtentReports.logStatusInfo(message);
	}
	
	private static void writeSteps() throws IOException {
		for(String s : logsList) {
			logFile.write(s + ";fimstep\n");
		}
		logFile.closeTxtFile();
 	}
	
}