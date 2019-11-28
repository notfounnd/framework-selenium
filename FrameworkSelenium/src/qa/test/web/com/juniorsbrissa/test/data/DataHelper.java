package qa.test.web.com.juniorsbrissa.test.data;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import qa.test.web.utils.JsonHelper;

public class DataHelper extends qa.test.web.utils.DataHelper {
	
	private JsonHelper jsonHelper = new JsonHelper();
	private String className = getPackageName();
	
	public DataHelper() {
		super();
	}
	
	public String getData(String fileName, String key) throws ParseException, IOException {
		
		String data;
		
		//Corrigir string classMethod
		className = className.replace("class ", "");
		
		//Coletar informação do json
		data = jsonHelper.readJsonFile(className, fileName, key);
		return data;
		
	}
	
}
