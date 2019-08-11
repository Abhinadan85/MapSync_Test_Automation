package com.mapsync.testsuite;

import org.testng.annotations.Test;

import com.mapsync.pages.Camera;
import com.mapsync.pages.Galactico;
import com.mapsync.pages.Landing;
import com.mapsync.pages.Logon;
import com.mapsync.pages.Registration;

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
