package com.mapsync.pages;

import com.mapsync.fl.Architypes;
import com.mapsync.fl.Initialize;
import com.mapsync.fl.PropFileRead;
import com.mapsync.fl.Reporter;

public class Logon {
	
	private Reporter reporter;
	Initialize Init;
	String TestName;
	public static String Device;
	public static String Browser;
	Architypes A;
	
	public Logon(String tName){		
		Init = new Initialize();
		reporter = Reporter.getInstance();
		Device = PropFileRead.GetKeyValue("DEVICE","Config.prop");
		Browser = PropFileRead.GetKeyValue("BROWSER","Config.prop");
		TestName = tName;
		reporter.BeginTest(tName);
		A = new Architypes(TestName);
	}
	
	/**
	 * Login to OPENLink entering Username and Password on Logon Page
	 * @return Boolean true or false
	 * Method will return true or false depending on Pass or Fail
	 */
	public Boolean Login(){
		Boolean b=false;
		
		//Reading Properties File for URL, Device Name, Username and Password
		reporter.Log("Reading CONFIG.PROP Properties File...", false);
		String URL = PropFileRead.GetKeyValue("URL","Config.prop");
		reporter.Log("Reading CONFIG.PROP Properties File is successful", false);

		Init.InitiateExecution(URL);
		
			
		 //Verification of Application Landing Page
		 wait(3000);
		 if(A.ElementExists("LOGO")){
			reporter.Pass("Navigation to Mapsynq is successful", false);
			b=true;
			}
		 else
			reporter.FailAndExit(TestName,"Failed to navigate to Mapsynq", true); 
		
	 	return b; 
	}
	
	/**
	 * Log Off from OPENLink using Left Menu and Close the Application
	 * @return Boolean true or false
	 * Method will return true or false depending on Pass or Fail
	 */
	public Boolean Close(){
		Boolean b=false;
		
		
		 reporter.Pass("Closing of Application is successful", true);
		 Init.TerminateExecution();
		 reporter.endTest(TestName);
		
	 	return b; 
	}
	
	
	public void wait(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
