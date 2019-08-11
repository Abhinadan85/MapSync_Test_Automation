package com.mapsync.fl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropFileRead {
	public static String GetKeyValue(String Key, String Path){
		Properties Prop = new Properties();
		FileInputStream FIS = null;
		Path = "./Dependencies/" + Path;
		try {
			FIS = new FileInputStream(Path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Reporter.getInstance().FailAndExit(Path,"Unable to read property file." + e.getMessage(), false);
		}
		try {
			Prop.load(FIS);
		} catch (IOException e) {
			e.printStackTrace();
			Reporter.getInstance().FailAndExit(Path,"Unable to read property file." + e.getMessage(), false);
		}
		
		String Value = Prop.getProperty(Key);
		System.out.println("Value of Key: " + Key + " read from " + Path.toUpperCase() + " File -> " + Value);
		return Value;
	}
}
