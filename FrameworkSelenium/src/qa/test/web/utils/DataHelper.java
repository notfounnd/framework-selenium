package qa.test.web.utils;

public class DataHelper {
	
	protected DataHelper() {
		super();
	}
	
	protected final String getPackageName() {
		
		//Recuperar nome da classe
		String namePackage = this.getClass().toString();
		namePackage = namePackage.replace("class ", "");
		namePackage = namePackage.replace(".", "#");
		
		//Recuperar nome do package
		String[] namePackageVet = namePackage.split("#");
		int countPackageVet = namePackageVet.length - 2;
		namePackage = "";
		
		for (int i = 0; i <= countPackageVet ; i++) {
			
			if (i < countPackageVet) {
				namePackage = namePackage + namePackageVet[i] + ".";
			} else {
				namePackage = namePackage + namePackageVet[i];
			}
			
		}
		
		return namePackage;
	
	}

}
