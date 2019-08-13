package com.mapsync.pages;


import java.util.ArrayList;



import com.mapsync.fl.Architypes;
import com.mapsync.fl.Initialize;
import com.mapsync.fl.Reporter;




public class Galactico {

	Reporter reporter;
	String TestName;
	Architypes A;
	Initialize init;
	
	public Galactico(String tName , Initialize initInstance){
		reporter = Reporter.getInstance(initInstance);
		TestName = tName;
		A = new Architypes(TestName, initInstance);
		init = initInstance;
		
	}
	
	

	public void openGalactico(){

		
	//	WebDriver driver = new ChromeDriver();
		A.ClickElement("GALACTICO");
		
		   ArrayList<String> tabs2 = new ArrayList<String> (init.driver.getWindowHandles());
		   init.driver.switchTo().window(tabs2.get(1));
		   
		   wait(5000);
		   
	//	   Initialize.driver.quit();		
	
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
