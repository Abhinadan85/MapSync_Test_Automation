package com.mapsync.pages;




import org.openqa.selenium.By;


import com.mapsync.fl.Architypes;
import com.mapsync.fl.Initialize;
import com.mapsync.fl.Reporter;

public class Registration {
	
	//private Reporter reporter;
	Reporter reporter;
	String TestName;
	Architypes A;
	Initialize Init;
	
	public Registration(String tName,Initialize initInstance){
		reporter = Reporter.getInstance(initInstance);
		TestName = tName;
		A = new Architypes(TestName,initInstance);
		Init = initInstance;
	}
	
	/**
	 * Filter Search Results based on Last Name on Landing Page
	 * @return String Number of Requisitions
	 * Method will return the Number of Requisitions 
	 */
	public void registerUser(){
		
		//Expand Filter Options
		A.ClickElement("REGISTER");
		A.Type("FIRSTNAME", "Abhinandan");
		A.TypeFromTestData("LASTNAME", "LASTNAME"); 
		A.ClickElement("TNC");
	
		A.Type("DOB", "10-02-1985");
		A.ClickElementByXpath("SEX");
		A.Type("EMAILID", "abhinandan.maity@gmail.com");
		A.Type("PASSWORD", "August@2019");
		A.Type("CONFPASS", "August@2019");
	
		A.ClickElementByXpath("SUBMIT"); 
	}
	

}

