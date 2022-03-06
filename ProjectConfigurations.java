package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProjectConfigurations {
	
	public static String LoadProperties(String Key) throws IOException {
		
		Properties prop= new Properties() ;
			
		InputStream input = null;
		String Value = null;
		
		String ConfigFilePath= System.getProperty("user.dir") + "/Configuation/config.properties" ;
		
		try {
			input = new FileInputStream(ConfigFilePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prop.load(input);
		Value= prop.getProperty(Key);
		
		return Value;
		
	}

}
