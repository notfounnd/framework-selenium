package qa.test.web.utils;

import java.io.File;
import java.io.IOException;

import qa.test.web.basePage.BasePage;
import qa.test.web.basePage.TestRunner;

@SuppressWarnings({"unused"})
public class FolderHelper {
	
	private String path;
	private File file;
	
	public FolderHelper(String path) throws IOException {
		this.path = path;
		setFolder();
	}
	
	private FolderHelper setFolder() throws IOException {
		file = new File(path);
		createFolder();
		return this;
	}
	
	private void createFolder() {
		File directory = new File(path);
		directory.mkdir();
		try {
			TestRunner.addStepInfo(BasePage.getClassMethod(this), "Diretório criado: " + path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void renameFolder(String path, String status) {
		File directory = new File(path);
		
		String pathRename = path.replace("Status", status);
		File newNameDirectory = new File(pathRename);
	
		try {
			TestRunner.addStepInfo(BasePage.getClassMethod(this), "Diretório renomeado de: " +path+ "/Para: " +pathRename);
			directory.renameTo(newNameDirectory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void changeIconFolder(String path, String status) throws IOException, InterruptedException {	
		
		this.path = path;

		if (status.equalsIgnoreCase("Passed")) {
			
			File source = new File(System.getProperty("user.dir") +"\\resources\\_pass.bat");
			File dest = new File(path+"\\_pass.bat");
			
			//Copia arquivo para a pasta de execução(path)
			FileHelper.copyPasteFile(source, dest);
			
			//Executa .bat para alterar ícone da pasta
			ProcessBuilder cmd = new ProcessBuilder("cmd", "/c", "_pass.bat");
			File dir = new File(path);
			cmd.directory(dir);
			Thread.sleep(1000);
			Process exec = cmd.start();
			
			//Deleta .bat copiado
			Thread.sleep(1000);
			dest.delete();
			
		} else {

			File source = new File(System.getProperty("user.dir") +"\\resources\\_fail.bat");
			File dest = new File(path+"\\_fail.bat");
			
			//Copia arquivo para a pasta de execução(path)
			FileHelper.copyPasteFile(source, dest);
			
			//Executa .bat para alterar ícone da pasta
			ProcessBuilder cmd = new ProcessBuilder("cmd", "/c", "_fail.bat");
			File dir = new File(path);
			cmd.directory(dir);
			Thread.sleep(1000);
			Process exec = cmd.start();
			
			//Deleta .bat copiado
			Thread.sleep(1000);
			dest.delete();
		}
	}
}
