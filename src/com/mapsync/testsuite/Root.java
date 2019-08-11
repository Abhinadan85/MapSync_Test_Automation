package com.mapsync.testsuite;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JOptionPane;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

public class Root {
	static String TASKLIST = "tasklist";
	static String KILL = "taskkill /F /IM ";
		
	public static void main(String[] args) throws Exception {
		//Terminate desired Processes
		TerminateProcess();
		
		TestNG testng = new TestNG();
		
		String xmlFileName = "./Dependencies/testng.xml";
		List<XmlSuite> suite;
		try
		{
		    suite = (List <XmlSuite>)(new Parser(xmlFileName).parse());
		    testng.setXmlSuites(suite);
		    testng.run();
		    TerminateProcess();
		    JOptionPane.showMessageDialog(null, "Execution Complete !!!", "Si Message Validation Tool Automation", JOptionPane.INFORMATION_MESSAGE);
		}catch (Exception e)
		{
		    e.printStackTrace();
		    JOptionPane.showMessageDialog(null, "Execution Failure !!!", "Si Message Validation Tool Automation", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public static void TerminateProcess(){
		
		try {
			String OS = System.getProperty("os.name").toLowerCase();
			if(OS.indexOf("mac") >= 0) {
				String[] ProcessName={"AdobeARM","Google Chrome","Firefox"};
				for(String pName:ProcessName){
					Runtime.getRuntime().exec("pkill -a -i " + pName);
					System.out.println(pName+" is Terminated");
				}				
			}else if(OS.indexOf("win") >= 0){

				String[] ProcessName={"AdobeARM.exe","chromedriver.exe","geckodriver.exe"};
				Process P = Runtime.getRuntime().exec(TASKLIST);
				BufferedReader Reader = new BufferedReader(new InputStreamReader(
					   P.getInputStream()));
				String line;
				while ((line = Reader.readLine()) != null) {
					for(String pName:ProcessName){
						if(line.contains(pName)){
							// When there is a match, terminate the process
							Runtime.getRuntime().exec(KILL + pName);
							System.out.println(pName+" is Terminated");
						}					
					}				
				}
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}

}
