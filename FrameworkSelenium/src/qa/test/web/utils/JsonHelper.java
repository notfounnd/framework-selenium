package qa.test.web.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import qa.test.web.basePage.BasePage;
import qa.test.web.basePage.TestRunner;

@SuppressWarnings("unchecked")

public class JsonHelper {

	private static JSONObject jsonObject;
	private static JSONParser parser;
	private static FileReader readerFile;
	private static FileWriter writeFile;

	public JsonHelper() {
		super();
	}

	public void createJsonFile(String fileName) throws IOException {

		// Cria um Objeto JSON
		jsonObject = new JSONObject();

		// Insere dados no objeto para armazenamento inicial
		jsonObject.put(fileName, fileName);

		try {
			writeFile = new FileWriter(System.getProperty("user.dir") + "\\qa.test.web.testData\\" + fileName + ".json");

			// Escreve no arquivo conteudo do Objeto JSON
			writeFile.write(jsonObject.toJSONString());
			writeFile.close();
			TestRunner.addStepInfo(BasePage.getClassMethod(this), "Arquivo JSON '" + fileName  + "' criado com sucesso");
		} catch (IOException e) {
			e.printStackTrace();
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Erro ao tentar criar o arquivo JSON '" + fileName + "'");
			Assert.fail();
		}
		
	}

	public String readJsonFile(String fileName, String key) throws ParseException, IOException {
		
		// Cria o parse de tratamento
		parser = new JSONParser();

		try {
			// Salva no objeto JSONObject o que o parse tratou do arquivo
			jsonObject = (JSONObject) parser.parse(new FileReader(System.getProperty("user.dir") + "\\qa.test.web.testData\\" + fileName + ".json"));
			String value = (String) jsonObject.get(key);
			TestRunner.addStepInfo(BasePage.getClassMethod(this), "Lendo arquivo JSON: " + fileName  + " \\ Campo: " + key + " \\ Valor: " + value);
			return value;
		} catch (Exception e) {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Erro ao tentar ler informação '" + key + "' no arquivo JSON '"+ fileName +"'");
			Assert.fail();
			return "";
		}
		
	}
	
	public void writeJsonFile(String fileName, String key, String value) throws IOException {
		
		readerFile = new FileReader(System.getProperty("user.dir") + "\\qa.test.web.testData\\" + fileName + ".json");
		
		// Cria o parse de tratamento
		parser = new JSONParser();

		try {
			// Salva no objeto JSONObject o que o parse tratou do arquivo
			//jsonObject = (JSONObject) parser.parse(new FileReader(System.getProperty("user.dir") + "\\qa.test.web.testData\\" + fileName + ".json"));
			jsonObject = (JSONObject) parser.parse(readerFile);
			
			jsonObject.put(key, value);
			
			writeFile = new FileWriter(System.getProperty("user.dir") + "\\qa.test.web.testData\\" + fileName + ".json");
			
			// Escreve no arquivo conteudo do Objeto JSON
			writeFile.write(jsonObject.toJSONString());
			writeFile.close();
			
			TestRunner.addStepInfo(BasePage.getClassMethod(this), "Gravando arquivo JSON: " + fileName  + " \\ Campo: " + key + " \\ Valor: " + value);
		} catch (Exception e) {
			TestRunner.addStepFailed(BasePage.getClassMethod(this), "Erro ao tentar gravar informação '" + key + ": " + value + "' no arquivo JSON '"+ fileName +"'");
			Assert.fail();
		}
		
	}

}
