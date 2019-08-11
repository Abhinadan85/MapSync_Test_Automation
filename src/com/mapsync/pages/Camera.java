package com.mapsync.pages;


import com.mapsync.fl.Architypes;
import com.mapsync.fl.Reporter;



public class Camera {

	Reporter reporter;
	String TestName;
	Architypes A;
	
	public Camera(String tName){
		reporter = Reporter.getInstance();
		TestName = tName;
		A = new Architypes(TestName);
	}
	
	

	public void openCamera(){
//System.setProperty("webdriver.chrome.driver","C:\\Users\\AM040674\\OneDrive - Cerner Corporation\\Desktop\\Webdriver\\Chrome\\chromedriver_win32\\chromedriver.exe");
		
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
