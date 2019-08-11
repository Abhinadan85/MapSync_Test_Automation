package com.mapsync.fl;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import com.mapsync.pages.Logon;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reporter {
	boolean bool_LogDetailed;
	private static ExtentReports extent;
	private ExtentTest test;
	
	//Singleton class
	private static Reporter reporter;
    private String fileName;
    public static Reporter getInstance(){
        if(reporter == null){
        	reporter = new Reporter();
        }
        return reporter;
    }	
    
    //Constructor for Singleton class has to be private
    private Reporter(){
		if (extent == null) {			
			Date d=new Date();
			
			fileName=d.toString().replace(":", "_").replace(" ", "_");
			extent = new ExtentReports(System.getProperty("user.dir")+"//Reports//ExecutionReports//"+fileName+".html", true, DisplayOrder.OLDEST_FIRST);
			extent.loadConfig(new File(System.getProperty("user.dir")+"//Dependencies//extent-config.xml"));
			
			// Optional
			extent.addSystemInfo("Selenium Version", "2.53.0").addSystemInfo("Environment", "QA");
			String xmlFileName = "./Dependencies/testng.xml";
			String cpoyofXML = System.getProperty("user.dir")+ "//Reports//Screenshots//" + fileName + "//testng.xml";
		    try {
				FileUtils.copyFile(new File(xmlFileName), new File(cpoyofXML));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}		
		if (PropFileRead.GetKeyValue("LOGTYPE","Config.prop").equalsIgnoreCase("DETAILED"))
			bool_LogDetailed = true;
		else
			bool_LogDetailed = false;		
	}
    
    public void BeginTest(String testName){
		test = extent.startTest("#" +Initialize.TestRun + ". " + testName + " on " + Logon.Browser);
		Pass("Starting Test -> " + testName + " on " + Logon.Device, false);
	}
	
    //Need to check this method
	public void endTest(String testName){
		reporter.Pass("Terminating Test -> " + testName + " on " + Logon.Device, false);
		Initialize.driver.quit();
		extent.endTest(test);
		extent.flush();			
	}
	
	////----------------------
	public void Pass(String Message,Boolean ScreenshotRequired){
		if(ScreenshotRequired){
			Date dt=new Date();
			String screenshotFile=dt.toString().replace(":", "_").replace(" ", "_")+".png";
			screenshotFile = System.getProperty("user.dir")+ "//Reports//Screenshots//" + fileName + "//" + screenshotFile;
			// store screenshot in that file
			File scrFile = ((TakesScreenshot)Initialize.driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(screenshotFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
			test.log(LogStatus.PASS,Message +".  Screenshot file->" +screenshotFile+". "+ test.addScreenCapture(screenshotFile));
		}
		else{
			test.log(LogStatus.PASS,Message);
		}
		extent.flush();
	}
	
	public void Fail(String Message,Boolean ScreenshotRequired){
		if(ScreenshotRequired){
			Date dt=new Date();
			String screenshotFile=dt.toString().replace(":", "_").replace(" ", "_")+".png";
			screenshotFile = System.getProperty("user.dir")+ "//Reports//Screenshots//" + fileName + "//" + screenshotFile;
			// store screenshot in that file
			File scrFile = ((TakesScreenshot)Initialize.driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(screenshotFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
			test.log(LogStatus.FAIL,Message +".  Screenshot file->" +screenshotFile+". "+ test.addScreenCapture(screenshotFile));
		}
		else{
			test.log(LogStatus.FAIL,Message);
		}
		extent.flush();
	}
	
	
	public void FailAndExit(String TestName, String Message,Boolean ScreenshotRequired){
		if(ScreenshotRequired){
			Date dt=new Date();
			String screenshotFile=dt.toString().replace(":", "_").replace(" ", "_")+".png";
			screenshotFile = System.getProperty("user.dir")+ "//Reports//Screenshots//" + fileName + "//" + screenshotFile;
			// store screenshot in that file
			File scrFile = ((TakesScreenshot)Initialize.driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(screenshotFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
			test.log(LogStatus.FAIL,Message +".  Screenshot file->" +screenshotFile+". "+ test.addScreenCapture(screenshotFile));
			endTest(TestName);
			Assert.fail(Message);
		}
		else{
			test.log(LogStatus.FAIL,Message);
			endTest(TestName);
			Assert.fail(Message);
		}
		extent.flush();
	}
	
	
	public void Log(String Message,Boolean ScreenshotRequired){
		
		if (bool_LogDetailed)
		{	if(ScreenshotRequired){
				Date dt=new Date();
				String screenshotFile=dt.toString().replace(":", "_").replace(" ", "_")+".png";
				screenshotFile = System.getProperty("user.dir")+ "/..//Reports//Screenshots//" + fileName + "//" + screenshotFile;
				// store screenshot in that file
				File scrFile = ((TakesScreenshot)Initialize.driver).getScreenshotAs(OutputType.FILE);
				try {
					FileUtils.copyFile(scrFile, new File(screenshotFile));
				} catch (IOException e) {
					e.printStackTrace();
				}
				test.log(LogStatus.INFO,Message +"  Screenshot-> "+ test.addScreenCapture(screenshotFile));
			}
			else{
				test.log(LogStatus.INFO,Message);
			}
			extent.flush();
		}
	}

}
