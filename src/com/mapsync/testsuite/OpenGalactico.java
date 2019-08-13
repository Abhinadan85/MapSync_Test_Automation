package com.mapsync.testsuite;

import org.testng.annotations.Test;
import com.mapsync.pages.Galactico;
import com.mapsync.pages.Logon;
import com.mapsync.fl.Initialize;


public class OpenGalactico {
	
	@Test
	public void OpenGalactico_Test() {

		 String TestName = this.getClass().getSimpleName();
		Initialize Init = new Initialize();
	 
		 Logon L = new Logon(TestName,Init);
		 L.Login();
		 
		 
		 
		 Galactico GL = new Galactico(TestName, Init);
		 GL.openGalactico();
		 
		 L.Close();

	}
	
}
