package com.mapsync.testsuite;

import org.testng.annotations.Test;
import com.mapsync.pages.Galactico;
import com.mapsync.pages.Logon;



public class OpenGalactico {
	
	@Test
	public void OpenGalactico_Test() {

		 String TestName = this.getClass().getSimpleName();
	 
		 Logon L = new Logon(TestName);
		 L.Login();
		 
		 
		 
		 Galactico GL = new Galactico(TestName);
		 GL.openGalactico();
		 
		 L.Close();

	}
	
}
