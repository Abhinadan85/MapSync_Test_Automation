package com.mapsync.testsuite;

import org.testng.annotations.Test;

import com.mapsync.pages.Logon;
import com.mapsync.pages.Registration;

public class MapsynqRegistration {
	
	@Test
	public void MapsynqRegistration_Test() {

		 String TestName = this.getClass().getSimpleName();
	 
		 Logon L = new Logon(TestName);
		 L.Login();
		 
		 Registration RG = new Registration(TestName);
		 RG.registerUser();
		 
		 L.Close();
		 


	}

}
