package com.mapsync.testsuite;

import org.testng.annotations.Test;

import com.mapsync.fl.Initialize;
import com.mapsync.pages.Logon;
import com.mapsync.pages.Registration;

public class MapsynqRegistration {
	
	@Test
	public void MapsynqRegistration_Test() {

		 String TestName = this.getClass().getSimpleName();
		 Initialize Init = new Initialize();
	 
		 Logon L = new Logon(TestName,Init);
		 L.Login();
		 
		 Registration RG = new Registration(TestName,Init);
		 RG.registerUser();
		 
		 L.Close();
		 


	}

}
