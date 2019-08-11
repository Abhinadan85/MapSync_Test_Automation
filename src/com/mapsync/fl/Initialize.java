package com.mapsync.fl;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


import com.mapsync.pages.Logon;
import com.mapsync.testsuite.Root;

/*import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
*/

public class Initialize {
	public static WebDriver driver;
	public static int TestRun = 1;
	String TASKLIST = "tasklist";
	String KILL = "taskkill /F /IM ";
	
	public void InitiateExecution(String URL){
		
		TestRun++;	
		
		//Terminate desired Processes
		Root.TerminateProcess();
		
		//Set webdriver.chrome.driver property based on OS
		try {
			String OS = System.getProperty("os.name").toLowerCase();
			System.out.println("Operating System of the execution device -> " + OS);
			if(OS.indexOf("linux") >= 0) {
				if(PropFileRead.GetKeyValue("BROWSER","Config.prop").equalsIgnoreCase("Chrome")){
					System.setProperty("webdriver.chrome.driver", "./Dependencies/chromedriver");
				} else if (PropFileRead.GetKeyValue("BROWSER","Config.prop").equalsIgnoreCase("IE")){
					System.setProperty("webdriver.ie.driver", "./Dependencies/IEDriverServer");
				}
			}
			else {
				if(PropFileRead.GetKeyValue("BROWSER","Config.prop").equalsIgnoreCase("Chrome")){
				System.setProperty("webdriver.chrome.driver", "./Dependencies/chromedriver.exe");
			} else if (PropFileRead.GetKeyValue("BROWSER","Config.prop").equalsIgnoreCase("IE")){
				System.setProperty("webdriver.ie.driver", "./Dependencies/IEDriverServer.exe");
			}
			}
			
				
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
				
		//Capabilities are options that you can use to customize and configure a browser session
        //Capabilities are used here to disable save password prompt in chrome
        ChromeOptions options = new ChromeOptions();	
  	    Map<String, Object> prefs = new HashMap<String, Object>();
  	    prefs.put("credentials_enable_service", false);
  	    //prefs.put("profile.password_manager_enabled", false);
  	    options.setExperimentalOption("prefs", prefs);
  	    DesiredCapabilities cap=new DesiredCapabilities();
  	    System.out.println("No of Test Running " + (TestRun-1));
  	    
		switch (Logon.Device.toUpperCase()) {
	    case "LAPTOP": if(PropFileRead.GetKeyValue("BROWSER","Config.prop").equalsIgnoreCase("Chrome")){
			    		  cap = DesiredCapabilities.chrome();
				    	  cap.setCapability(ChromeOptions.CAPABILITY, options);
				    	  driver = new ChromeDriver(cap);
		    			  driver.manage().window().maximize();
			    	  }else if(PropFileRead.GetKeyValue("BROWSER","Config.prop").equalsIgnoreCase("IE")){
			    		  cap = DesiredCapabilities.internetExplorer();
				    	  driver = new InternetExplorerDriver(cap);
		    			  driver.manage().window().maximize();
			    	  }else{
			    	  
			    		  driver = new HtmlUnitDriver();
			    	  }
	    			  break;
	    			   
/*	    case "NEXUS": cap.setCapability("browserName", "Chrome");
			          cap.setCapability("deviceName","Nexus 6P");
			          cap.setCapability("platformName","Android");
			          cap.setCapability("unicodeKeyboard", true);
			          cap.setCapability("resetKeyboard", true);			          
			          try {			        	  
			        	//Unlock Screen 
			        	Runtime.getRuntime().exec("adb shell am start -n io.appium.unlock/.Unlock");			        	
						driver= new AppiumDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
						((AppiumDriver) driver).rotate(ScreenOrientation.PORTRAIT);
			          } catch (Exception e) {
						e.printStackTrace();
			          }			          
					  break;*/
	
	    }		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		System.out.println(URL);
		driver.get(URL);
	}
	
	
	public void TerminateExecution(){
		
		driver.quit();
		
		//Terminate desired Processes
		Root.TerminateProcess();		
		}
	
	


}
