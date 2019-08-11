package com.mapsync.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.datatransfer.StringSelection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.mapsync.fl.Architypes;
import com.mapsync.fl.Initialize;
import com.mapsync.fl.Reporter;

public class Landing {
	
	//private Reporter reporter;
	Reporter reporter;
	String TestName;
	Architypes A;
	
	public Landing(String tName){
		reporter = Reporter.getInstance();
		TestName = tName;
		A = new Architypes(TestName);
	}
	
	/**
	 * Filter Search Results based on Last Name on Landing Page
	 * @return String Number of Requisitions
	 * Method will return the Number of Requisitions 
	 */
	public String filterByPatientDetail(String Element, String Text){
		
		//Expand Filter Options
		A.ClickElement("FILTER");
		
		//Enter Filter Data
		if((Element.equalsIgnoreCase("FORMTYPE"))||(Element.equalsIgnoreCase("FORMSTATUS"))){
			//Select desired Item from List
			A.SelectListItemByName(Element, Text);			
		}else{
			//Clear Field
			A.ClearElement(Element);
			
			//Enter Desired Text in Text Edit Field
			A.Type(Element, Text);
		}
		
		//Click on Apply Button
		A.ClickElement("APPLY");
		
		//Retrieve No of Requisitions
		String Req = A.getText("REQUISITIONS");
		
		//Clear Filter
		A.ClickElement("CLEAR");
		
		//Collapse Filter Options
		A.ClickElement("FILTER");
		
		return Req;
	}
	
	/**
	 * Select Group and Role on Landing Page
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public boolean selectGroupRole(String Option){
		
		//Click on Group Role Drop Down 
		A.ClickElement("GROUPROLEDROPDOWN");
		
		//Select Group and Role
		boolean b = A.SelectListItemByName("GROUPROLE", Option);
		
		wait(5000);
		
		return b;
	}
	
	
	/**
	 * Select Start Date on Landing Page
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public boolean selectDate(String Element, String Day){
		boolean b = false;
		String DayXpath ="";
		System.out.println("\n Day is " + Day);
		//Click on StartDateCalender 
		A.ClickElement(Element);
		
/*		//Extract Date
		String DateArray[] = Date.split("/");
		String Day = DateArray[0];
		System.out.println("Day --> " + Day);*/
		
		//Check all the available date options and Click on matching Day
		int row = 6, col = 7;
		String txt, className;
		outerloop:
		for(int r=1;r<=row;r++) {
			for(int c=1;c<=col;c++) {
				if(Element.contains("START")) DayXpath = "/html/body/div[2]/div/div/div/div[2]/div[2]/div[" + r + "]/div[" + c + "]" ;
				else if(Element.contains("SCHEDULE")) {
					DayXpath = "/html/body/div[5]/div/div/div/div[2]/div[2]/div[" + r + "]/div[" + c + "]" ;
					}
				else DayXpath = "/html/body/div[3]/div/div/div/div[2]/div[2]/div[" + r + "]/div[" + c + "]" ;
				txt = A.getTextByXpath(DayXpath);
				className = A.getAttributeValueByXpath(DayXpath,"class");
				if(Day == "today") {
//					Date today = new Date();
					Calendar cal = Calendar.getInstance();
					Integer date = cal.get(Calendar.DAY_OF_MONTH);
					System.out.println("\n Day is " + date+" "+DayXpath+ " "+txt);
//					Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
//					int date = today.getDate();
					if(txt.equals(date.toString()) && !className.contains("disabled")) {
						System.out.println("Xpath of Date -> " + DayXpath);
						wait(1000);
						b = A.ClickElementByXpath(DayXpath);
						break outerloop;
						}
				}
				else if(txt.equals(Day) && !className.contains("disabled")) {
					System.out.println("Xpath of Date -> " + DayXpath);
					wait(1000);
					b = A.ClickElementByXpath(DayXpath);
					break outerloop;
					}
				//if(b)break;
			}
			//if(b)break;
		}
		
		return b;
	}
	
	
	/**
	 * Verify Dates prior to Start Date on Landing Page is disabled
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public boolean verifyPriorEndDatesDisabled(String Day){
		boolean b = false;
		
		//Click on StartDateCalender 
		A.ClickElement("ENDDATECALENDER");
		
/*		//Extract Date
		String DateArray[] = Date.split("/");
		String Day = DateArray[0];
		System.out.println("Day --> " + Day);*/
		
		//Check all the available date options and Click on matching Day
		int row = 6, col = 7;
		String txt;
		outerloop:
		for(int r=1;r<=row;r++) {
			for(int c=1;c<=col;c++) {
				String DayXpath = "/html/body/div[3]/div/div/div/div[2]/div[2]/div[" + r + "]/div[" + c + "]" ;
				txt = A.getTextByXpath(DayXpath);
				//className = A.getAttributeValueByXpath(DayXpath,"class");
				if(!txt.equals(Day)) A.verifyDisabledByXpath(DayXpath);
				else break outerloop;
/*				if(txt.equals(Day)) {
					b = A.ClickElementByXpath(DayXpath);
					break outerloop;
					}
				else {if (className.contains("disabled")) {
					 	  System.out.println("End Date: " + txt + " is disabled");}
					A.verifyDisabledByXpath(DayXpath);					  
				}*/
				//if(b)break;
			}
			//if(b)break;
		}
		
		return b;
	}
	
	/**
	 * Set Job Runtime on Landing Page
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public boolean setJobRuntime(String time){
		boolean b = false;

		//Enter Invalid Date
		b =  A.Type("JOBRUNTIME", time);
		
		return b;
	}
	
	
	/**
	 * Verify Default Job Runtime on Landing Page
	 * @return Boolean b
	 * Method will return true or false depending on Default Job Runtime 
	 */
	public boolean verifyDefaultJobRuntime(int time){
		boolean b = false;

		//Enter Invalid Date
		String tm =  A.getAttributeValueByXpath("//input[@class='Input-module__form-input___2z_wq RunOptionsView__input-spacing___RB-eD']", "placeholder");
		int time_act = Integer.parseInt(tm);
		if(time_act==time) {
			reporter.Pass("Default Job Runtime value is correctly displayed as " + time_act, false);
			b = true;
		}else
			reporter.Fail("Default Job Runtime value is NOT correctly displayed as " + time_act, false);
		
		return b;
	}
	
	
	/**
	 * Verify invalid values of JobRuntime such as blank, zero, alphabets and special characters are not accepted on Validation Tool
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public boolean verifyInvalidJobRuntime(){
		boolean bi = false;
		boolean bo = false;		

		//Enter Invalid Date such as blank, zero, alphabets and special characters
		String invalidJobTime[] = {" ","0","test","!@#$%^"};
		
		for (String JobTime : invalidJobTime) {			
			bi = A.Type("JOBRUNTIME", JobTime);
			String time_entered =  A.getAttributeValueByXpath("//input[@class='Input-module__form-input___2z_wq RunOptionsView__input-spacing___RB-eD']", "value");
			System.out.println("The value of Job Runtime accepted -> "+ time_entered);
			if(time_entered == null || time_entered.isEmpty()) {
				reporter.Pass("Job Runtime Field does not accept invalid value: " + JobTime, false);
				bo = true;
			}else
				reporter.Fail("Job Runtime Field accepts invalid value: " + JobTime, true);			
			}
		return (bi&&bo);
	}
	/**
	 * Enter Date on Landing Page
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public boolean enterData(String Element, String date){
		boolean b = false;		
		//Enter Date by typing
		b =  A.Type(Element, date);		
		return b;
	}
	
	/**
	 * Enter Date on Landing Page
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public boolean enterDelayFromPresentTime(String Element, int delay, String timeFormat){
		boolean b = false;		
		//Enter Date by typing
		Date date = new Date();
		SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
		String time =  simpleTimeFormat.format(date);
		Date d;
		switch (timeFormat) {
		case "min":
					try {
						d = simpleTimeFormat.parse(time);
						Calendar cal = Calendar.getInstance();
						 cal.setTime(d);
						 cal.add(Calendar.MINUTE, delay);
						 time = simpleTimeFormat.format(cal.getTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					break;
		case "hr":
					try {
						d = simpleTimeFormat.parse(time);
						Calendar cal = Calendar.getInstance();
						 cal.setTime(d);
						 cal.add(Calendar.HOUR, delay);
						 time = simpleTimeFormat.format(cal.getTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					break;
		default:
					break;
		}
		b =  A.Type(Element, time);		
		return b;
	}
	
	/**
	 * Enter Data from Test Data File on Validation Tool Page
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public boolean enterDataFromTestData(String Element, String Name){
		boolean b = false;		
		//Enter Date by typing
		b =  A.TypeFromTestData(Element, Name);	
		wait(1000);
		return b;
	}
	
	
	/**
	 * Enter Time and Verify on Landing Page
	 * @return Boolean b
	 * Method will enter Time and in case of Invalid Time verify whether the value of Time accepted 
	 */
	public boolean enterTimeAndVerify(String Element, String EnterTime, String AcceptedTime){
		boolean time_enter = false;
		boolean time_verify = false;
		boolean min_verify = false;
		int index ;
		
		//Split EnterTime into Hours and Mins
		String et[] = EnterTime.split(":");
		String hr_EnterTime = et[0]; //Stores Expected Hours in hr_AcceptedTime_Exp
		System.out.println("Hour to be Entered: " + hr_EnterTime);
		String min_EnterTime = et[1]; //Stores Expected Minutes in min_AcceptedTime_Exp
		System.out.println("Min to be Entered: " + min_EnterTime);
		
		//Enter Time by typing
		time_enter =  A.enterTime(Element, EnterTime);
				
		//Split AcceptedTime into Hours and Mins
		String at[] = AcceptedTime.split(":");
		String hr_AcceptedTime_Expected = at[0]; //Stores Expected Hours in hr_AcceptedTime_Exp
		System.out.println("Expected Accepted Hour: " + hr_AcceptedTime_Expected);
		String min_AcceptedTime_Expected = at[1]; //Stores Expected Minutes in min_AcceptedTime_Exp
		System.out.println("Expected Accepted Min: " + min_AcceptedTime_Expected);		
		
		//Set index based on Start or End Time so that the desired XPath can be constructed
		switch (Element) {
		case "STARTTIME":	index = 1;	    	
							break;
		case "ENDTIME":		index = 2;	    	
							break;
		default:			index = 0;
		}
		
		if(time_enter) {
			String hr_AcceptedTime_Actual =  A.getAttributeValueByXpath(("(//input[@name='terra-time-hour-input'])["+index+"]"), "value");
			System.out.println("Actual Accepted Hour: " + hr_AcceptedTime_Actual);
			String min_AcceptedTime_Actual =  A.getAttributeValueByXpath(("(//input[@name='terra-time-minute-input'])["+index+"]"), "value");
			System.out.println("Actual Accepted Min: " + min_AcceptedTime_Actual);
			//Verify Time entered
			if(hr_AcceptedTime_Expected.equals(hr_AcceptedTime_Actual) && min_AcceptedTime_Expected.equals(min_AcceptedTime_Actual)) {
				System.out.println("The Expected Time-> " + hr_AcceptedTime_Expected + ":" + min_AcceptedTime_Expected + " matches with the Actual Time-> " + hr_AcceptedTime_Actual + ":" + min_AcceptedTime_Actual);
				reporter.Pass("The Expected Time-> " + hr_AcceptedTime_Expected + ":" + min_AcceptedTime_Expected + " matches with the Actual Time-> " + hr_AcceptedTime_Actual + ":" + min_AcceptedTime_Actual, false);
				time_verify = true;
			}else {
				System.out.println("The Expected Time-> " + hr_AcceptedTime_Expected + ":" + min_AcceptedTime_Expected + " does not match with the Actual Time-> " + hr_AcceptedTime_Actual + ":" + min_AcceptedTime_Actual);
				reporter.Fail("The Expected Time-> " + hr_AcceptedTime_Expected + ":" + min_AcceptedTime_Expected + " does not match with the Actual Time-> " + hr_AcceptedTime_Actual + ":" + min_AcceptedTime_Actual, true);			
			}
		}
		
		return (time_enter && time_verify);
	}
	
	
	
	/**
	 * Upload a Rule set on Landing Page
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public boolean uploadFile(){
		boolean b = false;

		//Click on Browse Button 
		A.ClickElement("BROWSE");

		//Robot Class methods can be used to interact with keyboard/mouse events while doing browser automation. Alternatively AutoIT can be used, but its drawback is that it generates an executable file (exe) which will only work on windows, so it is not a good option to use
		try {
			Robot r = new Robot();
			int i =15;
			//For 15 Down Key press to reach the csv file to be selected
			while(i>0) {
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
			i--;
			}
			//Select the file with Enter Key
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
     * This method will set any parameter string to the system's clipboard.
     */
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	
	/**
	 * Upload a Rule set on Landing Page of given path
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public boolean uploadFile(String path){
		boolean b = false;

		//Click on Browse Button 
		A.ClickElement("BROWSE");

		//Robot Class methods can be used to interact with keyboard/mouse events while doing browser automation. Alternatively AutoIT can be used, but its drawback is that it generates an executable file (exe) which will only work on windows, so it is not a good option to use
		try {
			Landing.setClipboardData(path);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	
	/**
	 * Existence of Header Title.
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public boolean verifyHeaderTitle(){
		boolean b = false;
		wait(1000);
		
		//Verify Title of the Header
		b = A.TextEquals("ESILOGVALIDATIONTEXT", "ESI Log Validation Report Tool");
		
		return b;
	}
	/**
	 * Clicking on Submit PIIDialog.
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	
	public boolean clickSubmitPIIDialog(){
		boolean b = false;

		//Click on Initiate Download Link
		b = A.ClickElement("SUBMITPIIDIALOG");
		
		return b;
	}
	
	
	/**
	 * Verify_Preparing_To_Download.
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	
	public boolean VerifyText_Preparing_To_Download(){
		while (true)
		{	if(A.ElementExists("NEW_DYNAMICTXT") && A.TextExists("NEW_DYNAMICTXT", "Preparing")) {
			System.out.println("String Found");
			wait(500);
			return true;
			}			
	     }		
	}
	
	/**
	 * VerifyDownloadLink
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	
	public boolean VerifyDownloadLink(){
		boolean b = false;		
        //String txtDownloadLink ="Download";
        wait(5000);
		//String txtDynamicTXT = A.getText("NEW_DYNAMICTXT");
		if(A.TextEquals("DYNAMICTXT", "ACTION_LINK_TEXT")) {
			b = true;
			System.out.println("Download Text Exists");
			reporter.Pass("Download Text Exists", false);
		}else 
		{	System.out.println("Download Text Not Exists");
			reporter.Fail("Download Text Not Exists", false);
		}
		
		return b;
	}
	
	
	/**
	 * A Modal Dialog window needs to appear once the Schedule Run button has been clicked.
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public boolean verifyScheduleRun(){
		boolean b = false;
		wait(1000);
		
		//Click on Schedule Run button
		b = A.ClickElement("SCHEDULERUN");
		
		//Verify Schedule run Modal
		b = A.TextEquals("SCHEDULERUNHEADER", "Schedule run");
		
		return b;	
	}
	
	
	
	/**
	 * Verify Run Now button is disabled 
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of verification 
	 */
	public boolean verifyRunNowDisabled(){
		boolean b = false;
		wait(1000);
		
		//Retrieve the attribute value for aria-disabled
		String disabled = A.getAttributeValueByXpath("//button[@alt='Run now']","aria-disabled");
		
		//Verify if Run Now Button is disabled		
		if(disabled.equals("true")) {
			reporter.Pass("Run Now Button is disabled as Expected", false);
			b = true;
		}else
			reporter.Fail("Run Now Button is NOT disabled", false);
		
		return b;
	}
	
	/**
	 * Verify Run Now button is enabled 
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of verification 
	 */
	public boolean verifyRunNowEnabled(){
		boolean b = false;
		wait(1000);
		
		//Retrieve the attribute value for aria-disabled
		String disabled = A.getAttributeValueByXpath("//button[@alt='Run now']","aria-disabled");
		
		//Verify if Run Now Button is disabled		
		if(disabled.equals("false")) {
			reporter.Pass("Run Now Button is Enabled as Expected", true);
			b = false;
		}else
			reporter.Fail("Run Now Button is NOT Enabled", false);
		
		return b;
	}
	
	/**
	 * Choose Contributor System(s) or Interface(s) on Landing Page
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public boolean chooseField(String Element){
		boolean b = false;

		//Enter Invalid Date
		b =  A.ClickElement(Element);
		
		return b;
	}
	
	
	/**
	 * Click and verify Info Dialog on Encrypt File Modal on Landing Page
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public boolean activateVerifyInfoDialog(){
		boolean b = false;
		
		//When the user activates an info action the dialog box appears
		b = A.ClickElement("INFO");
		
		if(A.TextExists("INFODIALOG", "Encryption Key Rules")) {
			reporter.Pass("Info Dialog on Encrypt File Modal is correctly displayed", false);
			b = true;
		}else
			reporter.Fail("Info Dialog on Encrypt File Modal is Not correctly displayed", false);
		
		//Any action outside the dialog box closes it and returns to the encryption key dialog box
		b = A.pressKey("ESCAPE");
		
		return b;
	}
	
	
	
	/**
	 * Click on Initiate Download Link on Landing Page
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public boolean clickInitiateDownload(){
		boolean b = false;
		wait(10000);
		//Click on Initiate Download Link
		b = A.ClickElement("INITIATEDOWNLOAD");
		
		return b;
	}
	
	
	/**
	 * Enter Encryption Key on Landing Page
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public boolean enterEncryptionKey(String Txt, String ConfTxt){
		boolean b = false;
		b = A.ClickElement("ENCRYPTIONKEY");
		b = A.pressKey(Txt);
		b = A.ClickElement("CONFIRMENCRYPTIONKEY");
		b = A.pressKey(ConfTxt);
		
		//b = A.ClickElement("OK");//SUBMITPIIDIALOG
		//b = A.ClickElement("SUBMITPIIDIALOG");
		
		return b;
	}
	
	
	/** Validate the error status options are limited to existing, applicable, back-end error states on Landing Page: 
	 *  Failure, Info, Retry, Success, Terminate, Warning
	 *  @return Boolean b
	 *  Method will return true or false depending on the success or validation 
	 */
	public boolean validateErrorStatusOptions(){
		boolean b = false;
		String options;

		//ErrorStates_Exp contains the expected error status options
		ArrayList<String> ErrorStates_Exp = new ArrayList<String>();
		ErrorStates_Exp .add("Failure");
		ErrorStates_Exp.add("Terminate");
		ErrorStates_Exp.add("Warning");
		ErrorStates_Exp.add("Retry");
		ErrorStates_Exp.add("Success");
		ErrorStates_Exp.add("Info");
		//Expected count of the number of error status options
		int count_ErrorStates_exp = ErrorStates_Exp.size();
		
		//ErrorStates_Act contains the actual error status options as available in the application
		ArrayList<String> ErrorStates_Act = new ArrayList<String>();
		
		//Count the number of actual error status options from the Application		
		int count_ErrorStates_act  =  Initialize.driver.findElements(By.xpath("//span[@class='Checkbox-module__label-text___fY5lt']")).size();
		System.out.println("number of actual error status options -> " + count_ErrorStates_act);
		
		if (count_ErrorStates_exp==count_ErrorStates_act) {
			reporter.Pass("Count of number of actual error status options from the Application matches the expected value of " + count_ErrorStates_exp, false);
			for(int i=1; i<=count_ErrorStates_act; i++) {
				options = A.getTextByXpath("(//span[@class='Checkbox-module__label-text___fY5lt'])[" + i + "]");
				System.out.println("Option name is " + options);
				ErrorStates_Act.add(options);								
			}
			if(ErrorStates_Exp.equals(ErrorStates_Act)) {
				reporter.Pass("Actual error status options from the Application " + ErrorStates_Act + " matches the expected " + ErrorStates_Exp, false);
				b = true;
			}else
				reporter.Fail("Actual error status options from the Application " + ErrorStates_Act + " does not match the expected " + ErrorStates_Exp, false);
		}else {
			reporter.Pass("Count of number of actual error status options from the Application does not match the expected value of " + count_ErrorStates_exp, false);
		}
		return b;
	}
	
	
	/** Verify Error Status Options are Hidden on Landing Page
	 *  @return Boolean b
	 *  Method will return true or false depending on the success or validation 
	 */
	public boolean verifyErrorStatusOptionsHidden(boolean flag){
		boolean b = false;
		String Xpath = "//*[@id=\"root\"]/div/div[1]/div/div[2]/div/div/div/div[2]/div/div[2]/div/fieldset/div/div/div[3]/div/div[2]/div[3]";
		String Attr = "aria-hidden";
		
		//disp -> true indicates hidden
		String disp = A.getAttributeValueByXpath(Xpath, Attr);
		System.out.println("Verify Error Status Options are Hidden -> " + disp);
		
		if(flag) {
			if (disp.equals("true")){
				 reporter.Pass("Individual Error Status Options are hidden", false);
				 b = true;
				}
			else	reporter.Fail("Individual Error Status Options are Not hidden", true);
		}else {
			if (disp.equals("false")) {
				reporter.Pass("Individual Error Status Options are displayed", false);
				b = true;
			}	
			else	reporter.Fail("Individual Error Status Options are Not displayed", true);
		}
		
		return b;
	}
	
	
	/** Select an error status option on Landing Page: Failure, Info, Retry, Success, Terminate or Warning
	 *  @return Boolean b
	 *  Method will return true or false depending on the success or interaction 
	 */
	public boolean selectErrorStatus(String opt){
		boolean b = false;
		String options;

		/*//ErrorStates_Exp contains the expected error status options
		ArrayList<String> ErrorStates_Exp = new ArrayList<String>();
		ErrorStates_Exp .add("Failure");
		ErrorStates_Exp.add("Terminate");
		ErrorStates_Exp.add("Warning");
		ErrorStates_Exp.add("Retry");
		ErrorStates_Exp.add("Success");
		ErrorStates_Exp.add("Info");
		//Expected count of the number of error status options
		int count_ErrorStates_exp = ErrorStates_Exp.size();
		
		//ErrorStates_Act contains the actual error status options as available in the application
		ArrayList<String> ErrorStates_Act = new ArrayList<String>();
		*/
		
		//Count the number of actual error status options from the Application		
		int count_ErrorStates_act  =  Initialize.driver.findElements(By.xpath("//span[@class='Checkbox-module__label-text___fY5lt']")).size();
		System.out.println("number of actual error status options -> " + count_ErrorStates_act);
		
		for(int i=1; i<=count_ErrorStates_act; i++) {
			options = A.getTextByXpath("(//span[@class='Checkbox-module__label-text___fY5lt'])[" + i + "]");
			System.out.println("Option name is " + options);
			if(opt.equals(options))
				A.ClickElementByXpath("(//span[@class='Checkbox-module__label-text___fY5lt'])[" + i + "]");
				b = true;
		}
		
/*		if (count_ErrorStates_exp==count_ErrorStates_act) {
			reporter.Pass("Count of number of actual error status options from the Application matches the expected value of " + count_ErrorStates_exp, false);
			for(int i=1; i<=count_ErrorStates_act; i++) {
				options = A.getTextByXpath("(//span[@class='Checkbox-module__label-text___fY5lt'])[" + i + "]");
				System.out.println("Option name is " + options);
				ErrorStates_Act.add(options);								
			}
			if(ErrorStates_Exp.equals(ErrorStates_Act)) {
				reporter.Pass("Actual error status options from the Application " + ErrorStates_Act + " matches the expected " + ErrorStates_Exp, false);
				b = true;
			}else
				reporter.Fail("Actual error status options from the Application " + ErrorStates_Act + " does not match the expected " + ErrorStates_Exp, false);
		}else {
			reporter.Pass("Count of number of actual error status options from the Application does not match the expected value of " + count_ErrorStates_exp, false);
		}*/
		return b;
	}
	
	
	/** Select an error status option on Landing Page: Failure, Info, Retry, Success, Terminate or Warning
	 *  @return Boolean b
	 *  Method will return true or false depending on the success or interaction 
	 */
	public boolean abc(){
		boolean b = false;
	
		//Count the number of actual error status options from the Application		
		String txt  =  Initialize.driver.findElement(By.xpath("//span[@class='Checkbox-module__label-text___fY5lt']")).getText();
		System.out.println("number of actual error status options -> " + txt);
		
		
		String script = "return window.getComputedStyle(document.querySelector('#root > div > div.Application__application___3iab-.siMessageValidationApplication > div > div.ContentContainer-module__main___Y84CU > div > div > div > div.SlidePanel-module__panel___22lWd > div > div.ContentContainer-module__main___Y84CU > div > fieldset > div > div > div:nth-child(5) > div > div.GlobalStyling__component-vertical-spacing___13ulp > div.Toggle-module__toggle___34f0E > div > div > div > div > table > tbody > tr:nth-child(2) > td > div > label > span'),'::after')";
		JavascriptExecutor js = (JavascriptExecutor)Initialize.driver;
		Object elem = (Object) js.executeScript(script);
		System.out.println(elem);
		
		return b;
	}
	
	
	
	/**
	 * Verify PII Reminder message is displayed on clicking initiate download 
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of verification 
	 */
	public boolean verifyPIIReminder(String Element){
		boolean b = false;
		wait(2000);
		
		//Retrieve the attribute value for aria-disabled
//		String disabled = A.getAttributeValueByXpath("//p[@class='Alert-module__title___4ksHs']");
		System.out.println("PII "+Element);
		return A.TextExists(Element, "The report may contain sensitive patient information such as PHI or PII. Please handle accordingly.");
	}
	
	/**
	 * For closing the PII reminder Dialog box
	 * @returns Boolean b
	 * method return true if the dialog box was successfully closed
	 */
	public boolean closePIIDialog(){
		return A.ClickElement("CANCEL");
	}
	
	
	/**
	 * Verify Field Error Message on Landing Page
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of interaction 
	 */
	public void verifyErrorMessage(String Element, Boolean b){
		
		//Validate Error Message
		String ErrorMessage="";
		
		if(Element.contains("DATE"))
			ErrorMessage = "This field is invalid. Please enter a valid date.";
		else if (Element.contains("EMAIL"))
			ErrorMessage = "Your email address is invalid. Please enter a valid address.";
		else if (Element.contains("CONTRIBUTERSYSTEM")||Element.contains("INTERFACE"))
			ErrorMessage = "This field is required. Please choose Contributor System(s) or Interface(s)";
		
		if (b) {
			A.TextEquals(Element, ErrorMessage);
			System.out.println("Field Invaild Error Message is displayed");
/*			A.TextExists("CONTRIBUTERSYSTEMNOMATCHES", "No matching results for");
			System.out.println("No matching results Message is displayed");*/
			
		}
		else {
			A.ElementDoesNotExist(Element);
			System.out.println("Field Invaild Error Message is NOT displayed");
		}
	}
	
	
	/**
	 * Verify Encrypt File Modal Error Message
	 * @return Boolean b
	 * Method will return true or false depending on the success or failure of verification 
	 */
	public void verifyError(String Txt, Boolean b){
		
		if (b) {
			A.TextEquals("ENCRYPTFILEERROR", Txt);
			System.out.println("Field Invalid Error Message is displayed");
			A.ClickElement("CANCEL");			
		}
		else {
			A.ElementDoesNotExist("ENCRYPTFILEERROR");
			System.out.println("Field Invaild Error Message is NOT displayed");
			A.ElementExists("DOWNLOAD");
		}	
		
	}
	
	public Boolean typeEncryptedText() {
		int count = 0;
		Boolean result = false;
		WebElement element = A.locateElement("ENCRYPTED_MODAL");

		for(WebElement inputElement : element.findElements(By.tagName("input"))) {		
			if(inputElement.getAttribute("type").equalsIgnoreCase("password")) {
				count ++;	
			}
			inputElement.sendKeys(A.getData("PASSWORD"));
		}
		
		if(count == 2) {
			result = true;
		}
		
		if(result) {
			reporter.Pass("Encrypted text typing done sucessfully", true);
		} else {
			reporter.FailAndExit(TestName,"Encrypted text typing cannot be done", true);
		}
		
		A.ClickElement("CANCEL");
		
		return result;
	}
	
//	public Boolean openInitiateDownloadDialog() {
//		wait(5000);
//		Boolean result = A.ClickElement("INITIATE_DOWNLOAD");
//		
//		if(result) {
//			reporter.Pass("Initiate download open dialog sucessfully", true);
//		} else {
//			reporter.FailAndExit(TestName,"Failed to open dialog", true); 
//		}
//		return result;
//	}
//	
	public boolean verifyRuleSetSystemErrorDialog() {
		boolean b = false;
		wait(5000);
		
		//Click on Schedule Run button
		b = A.ClickElement("RUNNOW");
		
		//Verify Schedule run Modal
		b = A.TextEquals("VERIFYSYSTEMRULESMESSAGE", "A system error has occurred, please contact your administrator");

		A.ClickElement("MODALCLOSE");
		
		//WebElement errorDialogElement = Initialize.driver.findElement(By.cssSelector("div[role='dialog']"));
		//String messageText = errorDialogElement.findElement(By.xpath("//div/div/div[2]/div[2]")).getText();
		
		//System.out.println("Error message : "+messageText);
		return b;
	}
	
	public boolean verifyRuleSetExcessiveErrorDialog() {
		boolean b = false;
		wait(5000);
		
		//Click on Schedule Run button
		b = A.ClickElement("RUNNOW");

		b = A.TextEquals("VERIFYEXCESSIVERULESMESSAGE", "The ruleset contains errors.");
		
		//Verify Schedule run Modal
		A.ClickElement("MODALCLOSE");
		
		//WebElement errorDialogElement = Initialize.driver.findElement(By.cssSelector("div[role='dialog']"));
		//String messageText = errorDialogElement.findElement(By.xpath("//div/div/div[2]/div[2]")).getText();
		
		//System.out.println("Error message : "+messageText);
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

