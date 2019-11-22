package qa.test.web.basePage;

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

import qa.test.web.utils.TextHelper;

public class TestRunner {
	
	/*
	 * Desenvolvedor(a): Natália Nuñez
	 * Criação: 02/07/2019
	 * Data da Última Modificação:
	 * Descritivo da Modificação:
	 * 
	 * Descrição:
	 *  Função Main() para executar os testes com Junit via Jar.
	 *  Métodos para edição do arquivo de logs
	 *  
	 * Variáveis estáticas públicas:
	 *  arquivolog: arquivo .txt para armazenar status dos steps executados
	 *  testClass: a classe de teste que está em execução
	 *  testMethod: o caso de teste que está em execução
	 */
	
	public static String arquivoLog;
	public static String testClass;
	public static String testMethod;
	public static String pastaDeRede = "\\\\10.205.73.126\\DirGerTI\\Seguros TI Ctba\\Digital\\Acompanhamento Projetos\\ProjetoAmbienteTestes\\2-DOC.EXEC.PROJ\\FRENTE2-PROCESSO_E_EXECUCAO\\INMETRICS\\Automacao\\PROD\\Android\\";
	private static TextHelper logFile;
	private static List<String> logsList = new ArrayList<String>();
	private static String[] parameters;
	private static String argsHardCoded = "qa.test.web.testDebug.TestDebug#CN001_CT001#Relatorio_CN001_CT001.txt";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		
		//instancia JunitCore responsável por executar/gerenciar a execução dos testes via cmd
		JUnitCore jUnitCore = new JUnitCore();
		
		//implementa listener
		jUnitCore.addListener(new TextListener(System.out));
		
		/*
		 * se o parâmetro não foi enviado
		 * popula atributos da classe com valores fixos [debug]
		 */
		if(args.length == 0) {
			parameters = argsHardCoded.split("#");
		}
		else
		{
			parameters = args[0].split("#");
		}
		
		//verifica se há os 3 argumentos esperados para início dos testes
		if(parameters.length ==  3) {
			
			/*
			 * tratamento para o parâmetro enviado via cmd
			 */			
			testClass = parameters[0];
			testMethod = parameters[1];
			arquivoLog = "C:\\Temp\\" + parameters[2].substring(parameters[2].indexOf("Relatorio"));
			
			/*
			 * instancia arquivo .txt de log criado pelo script que executa o projeto
			 */
			
			initLogFile();
			addStep("\n[JunitCore] Início dos Testes: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()) +
					"\n[JunitCore] Total de parâmetros para início dos testes: " + parameters.length +
					"\n[JunitCore] Classe de teste: " + testClass  +
					"\n[JunitCore] Método de teste: " + testMethod +
					"\n[JunitCore] Arquivo de log: " + arquivoLog + "\n");
			
			/*
			 * executa o método de teste dentro da classe de teste indicada
			 */
			Request request = Request.method(Class.forName(testClass), testMethod);
			Result result = jUnitCore.run(request);
			
						
			/*
			 * Finaliza execução
			 */
			for(Failure fail: result.getFailures()) {
				addStep("[Stacktrace]" + fail.getMessage() + "\n" + fail.getTrace());
			}
			
			if(result.getIgnoreCount() > 0) {
				addStep("[Step Status: Failed] Caso de Teste " + testMethod + " foi ignorado");
			}
			
			/*
			 * inputa no arquivo .txt de log os passos registrados durante o teste
			 */
			writeSteps();
			
			System.exit(result.wasSuccessful() ? 0 : 1);
		} 	
	}
	
	private static void initLogFile() throws IOException {
		logFile = new TextHelper(arquivoLog);
	}
	
	private static void addStep(String stepMessage) throws IOException {
		System.out.println(stepMessage);
		logsList.add(stepMessage);
	}
	
	public static void addStepPassed(String classMethod, String messageStep) throws IOException {
		//gerar timestamp para o arquivo de log
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		String dateLog = dateFormat.format(timeStamp).toString();
		
		//corrigir string classMethod
		classMethod = classMethod.replace("class ", "");
		
		String message = "[" + dateLog + "][Step Status: Passed][" + classMethod + "] " + messageStep;
		System.out.println(message);
		logsList.add(message);
	}
	
	public static void addStepFailed(String classMethod, String messageStep) throws IOException {
		//gerar timestamp para o arquivo de log
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		String dateLog = dateFormat.format(timeStamp).toString();
		
		//corrigir string classMethod
		classMethod = classMethod.replace("class ", "");
		
		String message = "[" + dateLog + "][Step Status: Failed][" + classMethod + "] " + messageStep;
		System.out.println(message);
		logsList.add(message);
	}
	
	public static void addStepWarning(String classMethod, String messageStep) throws IOException {
		//gerar timestamp para o arquivo de log
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		String dateLog = dateFormat.format(timeStamp).toString();
		
		//corrigir string classMethod
		classMethod = classMethod.replace("class ", "");
		
		String message = "[" + dateLog + "][Step Status: Warning][" + classMethod + "] " + messageStep;
		System.out.println(message);
		logsList.add(message);
	}
	
	public static void addStepInfo(String classMethod, String messageStep) throws IOException {
		//gerar timestamp para o arquivo de log
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		String dateLog = dateFormat.format(timeStamp).toString();
		
		//corrigir string classMethod
		classMethod = classMethod.replace("class ", "");
		
		String message = "[" + dateLog + "][Step Info][" + classMethod + "] " + messageStep;
		System.out.println(message);
		logsList.add(message);
	}
	
	private static void writeSteps() throws IOException {
		for(String s : logsList) {
			logFile.write(s + ";fimstep\n");
		}
		logFile.closeTxtFile();
 	}
	
}