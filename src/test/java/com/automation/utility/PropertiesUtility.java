package com.automation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtility {

	public static String readDataFromPropertiesFile(String path,String key) {
		FileInputStream fileInput=null;
		Properties  propertyFile=new Properties();
		String data=null;
		try {
			InputStream input = PropertiesUtility.class.getClassLoader().getResourceAsStream(path);
			propertyFile.load(input);
			data = propertyFile.getProperty(key,"");
			input.close();			
		}
		catch(FileNotFoundException e) {
			System.out.println("error in file path");
			e.printStackTrace();
		}
		catch(IOException e) {
			System.out.println("Error while loading property file");
		}
		return data;
	}

	public static void writeDataToPropertiesFile(String path,String key,String value) {

		Properties propertyFile=new Properties();
		propertyFile.setProperty(key, value);
		FileOutputStream fileOutput=null;
		File file=new File(path);
		try {
			fileOutput=new FileOutputStream(file);
			propertyFile.store(fileOutput,"adding new properties");
			fileOutput.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("error on adding properties");

		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


}
