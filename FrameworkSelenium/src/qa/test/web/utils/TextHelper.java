package qa.test.web.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import qa.test.web.basePage.BasePage;
import qa.test.web.basePage.TestRunner;

public class TextHelper {
	
	private String path;
	private Boolean fileExist;
	private Boolean fileCanWrite;
	private BufferedReader buffReader;
	private BufferedWriter buffWriter;
	private File file;
	public List<String> allText;
	
	public TextHelper(String path) throws IOException {
		this.path = path;
		setFile();
		setReader();
		setWriter();
	}
	
	private TextHelper setFile() throws IOException {
		file = new File(path);
		fileExist();
		canWriteOnFile();
		return this;
	}
	
	private Boolean fileExist() throws IOException {
		if(file.exists()) {
			//TestRunner.addStep("[Text Helper] Arquivo existe: " + path.substring(path.indexOf("Relatorio")));
			TestRunner.addStepInfo(BasePage.getClassMethod(this), "Arquivo existe: " + path.substring(path.indexOf("Relatorio")));
			fileExist = true;
		}
		else 
		{
			//TestRunner.addStep("[Text Helper] Arquivo não existe: " + path.substring(path.indexOf("Relatorio")));
			TestRunner.addStepInfo(BasePage.getClassMethod(this), "Arquivo não existe: " + path.substring(path.indexOf("Relatorio")));
			fileExist = false;
		}
		return fileExist;
	}
	
	private TextHelper canWriteOnFile() throws IOException {
		if(file.canWrite()) {
			//TestRunner.addStep("[Text Helper] Arquivo disponível para escrita: " + path.substring(path.indexOf("Relatorio")));
			TestRunner.addStepInfo(BasePage.getClassMethod(this), "Arquivo disponível para escrita: " + path.substring(path.indexOf("Relatorio")));
			fileCanWrite = true;
		}
		else
		{
			//TestRunner.addStep("[Text Helper] Arquivo indisponível para escrita: " + path.substring(path.indexOf("Relatorio")));
			TestRunner.addStepInfo(BasePage.getClassMethod(this), "Arquivo indisponível para escrita: " + path.substring(path.indexOf("Relatorio")));
			fileCanWrite = false;
		}
		return this;
	}

	private TextHelper setReader() throws IOException {
		buffReader = new BufferedReader(new FileReader(path));		
		return this;
	}
	
	private TextHelper setWriter() throws IOException {
		buffWriter = new BufferedWriter(new FileWriter(path));
		return this;
	}

	public TextHelper write(String text) throws IOException {
		if(fileExist && fileCanWrite) {
			buffWriter.write(text);
		}
		return this;
	}

	public void readAll() throws IOException {
		allText = new ArrayList<String>();
		String line = buffReader.readLine();
		while (line != null) {
			allText.add(line);
			line = buffReader.readLine();
		}
	}

	public void closeTxtFile() throws IOException {
		buffWriter.flush();
		buffWriter.close();
	}

}
