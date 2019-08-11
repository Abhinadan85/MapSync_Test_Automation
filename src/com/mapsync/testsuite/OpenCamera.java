package com.mapsync.testsuite;

import org.testng.annotations.Test;

import com.mapsync.pages.Camera;

import com.mapsync.pages.Logon;


public class OpenCamera {
	
	@Test
	public void MapsynqRegistration_Test() {

		 String TestName = this.getClass().getSimpleName();
	 
		 Logon L = new Logon(TestName);
		 L.Login();
		 
				 
		 Camera CAM = new Camera(TestName);
		 CAM.openCamera();
		 
		 L.Close();
	}

}
