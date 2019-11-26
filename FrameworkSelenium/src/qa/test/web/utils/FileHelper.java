package qa.test.web.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;

@SuppressWarnings("unused")
public class FileHelper {
	
	private String path;

	public FileHelper(String path) throws IOException {
		this.path = path;
	}
	
	public static void copyPasteFile(File source, File dest) throws IOException {
	    FileUtils.copyFile(source, dest);
	}

	
}
