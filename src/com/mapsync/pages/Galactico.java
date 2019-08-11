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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.mapsync.fl.Architypes;
import com.mapsync.fl.Initialize;
import com.mapsync.fl.Reporter;

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


public class Galactico {

	Reporter reporter;
	String TestName;
	Architypes A;
	
	public Galactico(String tName){
		reporter = Reporter.getInstance();
		TestName = tName;
		A = new Architypes(TestName);
	}
	
	

	public void openGalactico(){
//System.setProperty("webdriver.chrome.driver","C:\\Users\\AM040674\\OneDrive - Cerner Corporation\\Desktop\\Webdriver\\Chrome\\chromedriver_win32\\chromedriver.exe");
		
	//	WebDriver driver = new ChromeDriver();
		A.ClickElement("GALACTICO");
		
		   ArrayList<String> tabs2 = new ArrayList<String> (Initialize.driver.getWindowHandles());
		   Initialize.driver.switchTo().window(tabs2.get(1));
		   
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
