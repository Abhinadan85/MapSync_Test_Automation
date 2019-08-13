package com.mapsync.pages;


import com.mapsync.fl.Architypes;
import com.mapsync.fl.Reporter;
import com.mapsync.fl.Initialize;



public class Camera {

	Reporter reporter;
	String TestName;
	Architypes A;
	Initialize init;
	
	public Camera(String tName, Initialize initInstance){
		init = initInstance;
		reporter = Reporter.getInstance(init);
		TestName = tName;
		A = new Architypes(TestName,init);
	}
	
	

	public void openCamera(){

		
	//	WebDriver driver = new ChromeDriver();
		A.ClickElement("CAMERA");
		A.ClickElement("ROAD");
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
