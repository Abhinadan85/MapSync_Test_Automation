package com.mapsync.testsuite;

import org.testng.annotations.Test;

import com.mapsync.pages.Camera;
import com.mapsync.pages.Galactico;

import com.mapsync.pages.Logon;
import com.mapsync.pages.Registration;


public class ParallelExecution {
	
	@Test
	public void OpenGalactico_Test() {

		 String TestName = this.getClass().getSimpleName();
	 
		 Logon L = new Logon(TestName);
		 L.Login();
		 
		 System.out.println("1st test login");
		 
		 
		 Galactico GL = new Galactico(TestName);
		 GL.openGalactico();
		 
		 L.Close();
		 System.out.println("1st test end");
	}
	@Test
	public void OpenCamera_Test() {

		 String TestName = this.getClass().getSimpleName();
	 
		 Logon L = new Logon(TestName);
		 L.Login();
		 
		 System.out.println("2nd test login");		 
		 Camera CAM = new Camera(TestName);
		 CAM.openCamera();
		 
		 L.Close();
		 System.out.println("2nd test end");
	} 
	
	@Test
	public void MapsynqRegistration_Test() {

		 String TestName = this.getClass().getSimpleName();
	 
		 Logon L = new Logon(TestName);
		 L.Login();
		 System.out.println("3rd test login");
		 Registration RG = new Registration(TestName);
		 RG.registerUser();
		 
		 L.Close();
		 System.out.println("3rd test end");


	}
}
