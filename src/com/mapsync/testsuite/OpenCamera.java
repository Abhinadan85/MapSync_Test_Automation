package com.mapsync.testsuite;

import org.testng.annotations.Test;

import com.mapsync.fl.Initialize;
import com.mapsync.pages.Camera;

import com.mapsync.pages.Logon;


public class OpenCamera {
	
	@Test
	public void MapsynqRegistration_Test() {

		 String TestName = this.getClass().getSimpleName();
		 Initialize Init = new Initialize();
	 
		 Logon L = new Logon(TestName,Init);
		 L.Login();
		 
				 
		 Camera CAM = new Camera(TestName,Init);
		 CAM.openCamera();
		 
		 L.Close();
	}

}
