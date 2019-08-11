# MapSync_Test_Automation
Repository contains automation tests for MapSync Application
1) Goto GitHub and clone the repository from the following location :    https://github.com/Abhinadan85/MapSync_Test_Automation.git
2) This framework consists of 1 test suite, which conssts of 3 tests :
   i) MapsynqRegistration - Opens www.mapsync.com -> Clicks on Register -> Registers a new user -> Closes the                                       browser
  ii) OpenCamera - Opens www.mapsync.com -> Clicks on Camera tab in the left hand side bar -> Selects                                                                     location -> Closes the       browser.                                            
 iii) OpenGalactico - Opens www.mapsync.com -> Clicks on Galactico link on upper right hand side menu bar ->Focuses on the Galactico page -> Closes the browser
3) To run the full test suite, please complete the given steps:
   - Open commmnd prompt and navigate to the downloaded framework i.e .\MapSync_Test_Automation
   - Type 'java -jar MapSync_TestAutomation.jar'

4) To execute any single test, open the testng.xml in .\MapSync_Test_Automation and only mention the test that    need to be executed. For eg, If only MapsynqRegistration is to be executed, edit testng.xml to include only    the desired test name

5) To view the execution reports, please navigate to '.\MapSync_Test_Automation\Reports\ExecutionReports' and    double the report file based on date time stamp.

6) To view the framework, open Eclipse editor and import the downloaded project.

7) To run the test in a specific browser, expand the roject in the left hand side project explorer menu, expand 'Dependencies' folder and open the 'Config.prop' file. For execution in Chrome, write the value of Browser  to be 'Chrome', for internet explorer 'IE' and for headless execution 'HTML'

8) For parallel exeution, expand src -> com.mapsync.testsuite. Select any two tests (eg, OpenCamera and OpenGalactico) , right click on them , select TestNG->Convert to TestNG. On the refactoring dialog box, select the parallel mode dropdown to be 'classes' and put Thread Count = 2. Click on Finish.
   Right click Dependencies->testng.xml and select Run As -> TestNG suite

9) For single execution, expand src -> com.mapsync.testsuite. Select any test, right click and Run As->TestNG test.
