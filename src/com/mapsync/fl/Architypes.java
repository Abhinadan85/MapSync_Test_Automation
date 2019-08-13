package com.mapsync.fl;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Architypes {
	
	private Reporter reporter;
	String TestName;
	Initialize init;
	
	public Architypes(String tName , Initialize initInstance){		
		reporter = Reporter.getInstance(initInstance);
		TestName=tName;
		init = initInstance;
	}
	
	//Verifies if Element specified by Element Name on Object Repository Exists
	public boolean ElementExists(String Element){
		if(locateElements(Element).size()>0){
			System.out.println("Web Element " + Element + " Exists");
			reporter.Pass("Web Element " + Element + " Exists", false);
			return true;
		}else{
			System.out.println("Web Element " + Element + " does Not Exist");
			reporter.Fail("Web Element " + Element + " does Not Exist", true);
			return false;
		}
	}
	
	//Verifies if Element specified by XPath Exists
	public boolean ElementExistsByXpath(String XP){
		if(init.driver.findElements(By.xpath(XP)).size()>0){
			System.out.println("Web Element specified by Xpath: " + XP + " Exists");
			reporter.Pass("Web Element specified by Xpath: " + XP + " Exists", false);
			return true;
		}else{
			System.out.println("Web Element specified by Xpath:" + XP + " does Not Exist");
			reporter.Fail("Web Element specified by Xpath:" + XP + " does Not Exist", true);
			return false;
		}
	}
	
	
	//Verifies if Element specified by Element Name on Object Repository does not Exist
	public boolean ElementDoesNotExist(String Element){
		if(locateElements(Element).size()==0){
			System.out.println("Web Element " + Element + " does not Exist");
			reporter.Pass("Web Element " + Element + " does not Exist", false);
			return true;
		}else{
			System.out.println("Web Element " + Element + " Exists");
			reporter.Fail("Web Element " + Element + " Exists", true);
			return false;
		}
	}
	
	//Move Mouse over an Element specified by Element Name on Object Repository
	public boolean MouseOverElement(String Element){
		Boolean b = false;
		Actions action = new Actions(init.driver);		
		try{action.moveToElement(locateElement(Element)).build().perform();	
			b=true;
		    wait(2000);
		    reporter.Pass("Mouse is successfully moved over " + Element, true);
		}catch(Exception e){
			reporter.Log("Error @ MouseOverElement(). " + e.getMessage(), true);
			reporter.Fail("Mouse is Not moved over " + Element, true);
		}		
		return b;
	}
	
	
	//Click on an Element specified by Xpath
		public boolean ClickElementByXpath(String XP){
			Boolean b = false;
			try{init.driver.findElement(By.xpath(XP)).click();	
				b=true;
			    //wait(5000);
			    reporter.Pass("Click on required Element using Xpath is successful", true);
			}catch(Exception e){
				reporter.Log("Error @ ClickElementByXpath(). " + e.getMessage(), true);
				reporter.Fail("Click on required Element using Xpath is unsuccessful", true);
			}		
			return b;
		}
	
		
	
	//Press Key or type from Keyboard specified by Key Name
		public boolean pressKey(String key){
			Boolean b = false;
			Actions action = new Actions(init.driver);
			switch (key) {
			case "ARROW_LEFT":	try{action.sendKeys(Keys.ARROW_LEFT).build().perform();
									b=true;
								    wait(1000);
								    reporter.Pass(key + " press from keyboard is successfully simulated", true);
								}catch(Exception e){
									reporter.Log("Error @ pressKey(). " + e.getMessage(), true);
									reporter.Fail(key + " press from keyboard is not simulated", true);
								}	    	
								break;
			case "ARROW_DOWN":	try{action.sendKeys(Keys.ARROW_DOWN).build().perform();
									b=true;
								    wait(1000);
								    reporter.Pass(key + " press from keyboard is successfully simulated", true);
								}catch(Exception e){
									reporter.Log("Error @ pressKey(). " + e.getMessage(), true);
									reporter.Fail(key + " press from keyboard is not simulated", true);
								}	    	
								break;
				case "ENTER":	try{action.sendKeys(Keys.ENTER).build().perform();
									b=true;
								    wait(1000);
								    reporter.Pass(key + " press from keyboard is successfully simulated", true);
								}catch(Exception e){
									reporter.Log("Error @ pressKey(). " + e.getMessage(), true);
									reporter.Fail(key + " press from keyboard is not simulated", true);
								}	    	
								break;
			case "CLEAR":    	try{action.sendKeys(Keys.CONTROL,"a",Keys.DELETE).build().perform();
										b=true;
									    wait(1000);
									    reporter.Pass("Clearing text from input box using keyboard is successfully simulated", true);
									}catch(Exception e){
										reporter.Log("Error @ pressKey(). " + e.getMessage(), true);
										reporter.Fail("Clearing text from input box using keyboard is successfully simulated press from keyboard is not simulated", true);
									}	    	
									break;
			case "ESCAPE":    	try{action.sendKeys(Keys.ESCAPE).build().perform();
									b=true;
								    wait(1000);
								    reporter.Pass(key + " press from keyboard is successfully simulated", true);
								}catch(Exception e){
									reporter.Log("Error @ pressKey(). " + e.getMessage(), true);
									reporter.Fail(key + " press from keyboard is not simulated", true);
								}	    	
								break;
			case "TAB":    	try{action.sendKeys(Keys.TAB).build().perform();
									b=true;
								    wait(1000);
								    reporter.Pass(key + " press from keyboard is successfully simulated", true);
								}catch(Exception e){
									reporter.Log("Error @ pressKey(). " + e.getMessage(), true);
									reporter.Fail(key + " press from keyboard is not simulated", true);
								}	    	
								break;
								
			default :			try{action.sendKeys(key).build().perform();
									b=true;
								    wait(1000);
								    reporter.Pass(key + " is typed successfully from keyboard", true);
								}catch(Exception e){
									reporter.Log("Error @ pressKey(). " + e.getMessage(), true);
									reporter.Fail(key + " is not typed from keyboard", true);
								}
						
			}
			
			return b;
		}
		
	//Click on an Element specified by Element Name on Object Repository
	public boolean ClickElement(String Element){
		Boolean b = false;
		try{wait(1000);
			locateElement(Element).click();	
			b=true;		    
		    reporter.Pass("Click on " + Element + " is successful", false);
		}catch(Exception e){
			reporter.Log("Error @ ClickElement(). " + e.getMessage(), true);
			reporter.Fail("Click on " + Element + " is NOT successful", true);
		}		
		return b;
	}
	
	//Click on Element specified by Element Text
	public boolean ClickText(String Text){
		Boolean b = false;
		String xPath = "//*[text()='" + Text + "']";
		try{init.driver.findElement(By.xpath(xPath)).click();;	
			b=true;
		    wait(5000);
		    reporter.Pass("Click on Element by Element Text: " + Text + " is successful", true);
		}catch(Exception e){
			reporter.Log("Error @ ClickText(). " + e.getMessage(), true);
			reporter.Fail("Click on Element by Element Text: " + Text + " is Not successful", true);
		}		
		return b;
	}
	
	//Clear text from Element specified by Element Name on Object Repository
	public boolean ClearElement(String Element){
		Boolean b = false;
		try{locateElement(Element).clear();	
			b=true;
		    wait(2000);
		    reporter.Pass("Clear text from " + Element + " is successful", true);
		}catch(Exception e){
			reporter.Log("Error @ ClearElement(). " + e.getMessage(), true);
			reporter.Fail("Clear text from " + Element + " is NOT successful", true);
		}		
		return b;
	}
	
	//Typing on an Element specified by Element Name on Object Repository
	public boolean Type(String Element,String Text){
		Boolean b = false;
		//String Txt = PropFileRead.GetKeyValue(Text,"TestData.prop");
		//String Txt = getData(Text);
		try{locateElement(Element).sendKeys(Keys.CONTROL,"a",Keys.DELETE);
			locateElement(Element).sendKeys(Text);
			locateElement(Element).sendKeys(Keys.ENTER);
			locateElement(Element).sendKeys(Keys.TAB);
			b=true;
		    reporter.Pass("Typing " + Text + " is successful", false);
		}catch(Exception e){
			reporter.Log("Error @ Type(). " + e.getMessage(), true);
			reporter.Fail("Typing " + Text + " is NOT successful", true);
		}		
		return b;
	}
	
	//Typing text specified by Name on Test Data file on an Element specified by Element Name on Object Repository
	public boolean TypeFromTestData(String Element,String Name){
		Boolean b = false;
		//String Txt = PropFileRead.GetKeyValue(Text,"TestData.prop");
		String Txt = getData(Name);
		try{locateElement(Element).sendKeys(Txt);
			locateElement(Element).sendKeys(Keys.ENTER);
			locateElement(Element).sendKeys(Keys.TAB);
			b=true;
		    reporter.Pass("Typing " + Txt + " is successful", true);
		}catch(Exception e){
			reporter.Log("Error @ Type(). " + e.getMessage(), true);
			reporter.Fail("Typing " + Txt + " is NOT successful", true);
		}		
		return b;
	}
	
	
	//Typing on an Element specified by Element Name on Object Repository
	public boolean enterTime(String Element,String Text){
		Boolean b = false;
		//String Txt = PropFileRead.GetKeyValue(Text,"TestData.prop");
		//String Txt = getData(Text);
		try{locateElement(Element).sendKeys(Keys.CONTROL,"a",Keys.DELETE);
			//wait(1000);
			locateElement(Element).sendKeys(Keys.TAB);
			//wait(1000);
			//pressKey("CLEAR"); //Somehow not working
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_DELETE);
            robot.keyRelease(KeyEvent.VK_DELETE);
			//wait(1000);
			locateElement(Element).sendKeys(Text);
			//wait(1000);
			locateElement(Element).sendKeys(Keys.TAB);
			//wait(1000);
			b=true;
		    reporter.Pass("Typing " + Text + " is successful", true);
		}catch(Exception e){
			reporter.Log("Error @ Type(). " + e.getMessage(), true);
			reporter.Fail("Typing " + Text + " is NOT successful", true);
		}		
		return b;
	}
	
	//Typing on an Element specified by Element Name on Object Repository and text on TestData file
	public boolean typeUsingTestData(String Element,String Text){
		Boolean b = false;
		//String Txt = PropFileRead.GetKeyValue(Text,"TestData.prop");
		String Txt = getData(Text);
		try{locateElement(Element).sendKeys(Txt);
			b=true;
		    reporter.Pass("Typing " + Text + " is successful", true);
		}catch(Exception e){
			reporter.Log("Error @ Type(). " + e.getMessage(), true);
			reporter.Fail("Typing " + Text + " is NOT successful", true);
		}		
		return b;
	}
	
	//Select an Option by Name from a Drop-down List specified by Element Name on Object Repository using the method sendKeys
	public boolean SelectDropListOptionByName(String Element,String Option){
		Boolean b = false;
		String Opt=getData(Option);
/*		//String Opt = PropFileRead.GetKeyValue(Option,"TestData.prop");
		if(Option.indexOf(" ")!=-1)
			Opt = Option.trim();
		else
			Opt = PropFileRead.GetKeyValue(Option,"TestData.prop");*/
			
		try{locateElement(Element).sendKeys(Opt);	
			b=true;
		    wait(2000);
		    reporter.Pass("Option " + Option +  " is successful selected from the Drop-down List " + Element, true);
		}catch(Exception e){
			reporter.Log("Error @ SelectDropListOptionByName(). " + e.getMessage(), true);
			reporter.Fail("Option " + Option +  " is Not selected from the Drop-down List " + Element, true);
		}		
		return b;
	}
	
	//Select an Option by Name from a List(ui - Unordered List) specified by Element Name on Object Repository
	public boolean SelectListItemByName(String Element,String ListItem){
		Boolean b = false;		
		String liName;
		int liCount;
		//String li = PropFileRead.GetKeyValue(ListItem,"TestData.prop");
		String li=getData(ListItem);
/*		if(ListItem.indexOf(" ")!=-1)
			li = ListItem.trim();
		else
			li = PropFileRead.GetKeyValue(ListItem,"TestData.prop");*/
		
		//Retrieve No of List Item(li) in Unordered List(ui)
		liCount = locateElements(Element).size();
		System.out.println("No of List Items -> " + liCount);
		
		//Loop through the List Items checking the Item Names starting from the 1st one
		for(int i=0;i<liCount;i++){
			liName = locateElements(Element).get(i).getText();
			System.out.println("List Item " + (i+1) + " Name: " + liName);
			//If Item Name matches the Name of the Item to be selected click on the Item
			if(liName.equalsIgnoreCase(li)){
				try{locateElements(Element).get(i).click();	
					b=true;
				    wait(2000);
				    reporter.Pass("List Item " + liName +  " is successful selected from List " + Element, true);
				    break;
				}catch(Exception e){
					reporter.Log("Error @ SelectListOptionByName(). " + e.getMessage(), true);
					reporter.Fail("List Item " + liName +  " is Not selected from List " + Element, true);
				}				
			}
		}	
		
		return b;
	}
	
	//Select an Option by Index from a Drop-down List specified by Element Name on Object Repository using the method sendKeys
	public boolean SelectDropListOptionByIndex(String Element,int i){
		Boolean b = false;		
		try{Select s = new Select(locateElement(Element));
			s.selectByIndex(i-1);	
			b=true;
		    wait(2000);
		    reporter.Pass("Option No. " + i +  " is successful selected from the Drop-down List " + Element, true);
		}catch(Exception e){
			reporter.Log("Error @ SelectDropListOptionByIndex(). " + e.getMessage(), true);
			reporter.Fail("Option No. " + i +  " is Not selected from the Drop-down List " + Element, true);
		}		
		return b;
	}
	
	
	//Select an Option by Index from a List(ui - Unordered List) specified by Element Name on Object Repository using the method sendKeys
	public boolean SelectListItemByIndex(String Element,int i){
		Boolean b = false;
		int liCount;
		
		//Retrieve No of List Item(li) in Unordered List(ui)
		liCount = locateElements(Element).size();
		System.out.println("No of List Items -> " + liCount);
		
		if(liCount>i){
				//Click on List Item specified by Index
				try{locateElements(Element).get(i-1).click();	
					b=true;
				    wait(2000);
				    reporter.Pass("List Item No." + i +  " is successful selected from List " + Element, true);
				}catch(Exception e){
					reporter.Log("Error @ SelectListOptionByIndex(). " + e.getMessage(), true);
					reporter.Fail("List Item No." + i +  " is Not selected from List " + Element, true);
				}				
			}
		else{reporter.Fail("List Item Index specified -> " + i +  " exceeds Total Number of Items in the List " + Element, true);
			}	
		
		return b;
	}
	
	//Return a List of Strings containing all options in a Drop-List specified by Element Name on Object Repository
	public List<String> GetAllDropListOptions(String Element){
		List<String> OptionNames = new ArrayList<String>();
		List<WebElement> Options= locateElement(Element).findElements(By.tagName("option"));
		System.out.println("No of Options available in the Drop-List " + Element + " is " + Options.size());
		if(Options.size()>0){
			reporter.Pass("No of Options available in the Drop-List " + Element + " is " + Options.size(), true);
			//Retrieve Options and put it inside a List of Strings
			for(WebElement e:Options){  
			     System.out.println(e.getText());
		     	 System.out.println(e.getAttribute("selected"));
			     OptionNames.add(e.getText());			     
			   }  
		}else{
			reporter.Fail("No of Options available in the Drop-List " + Element + " is " + Options.size(), true);
		}
		reporter.Pass("The Options available in the Drop-List " + Element + " are " + OptionNames, false);
		return OptionNames;
	}
	
	//Return a List of Strings containing all inputs in a Radio Group specified by Element Name on Object Repository
	public List<String> GetAllRadioInputs(String Element){
		List<String> OptionNames = new ArrayList<String>();
		List<WebElement> Options= locateElement(Element).findElements(By.tagName("input"));
		System.out.println("No of Inputs available in the Drop-List " + Element + " is " + Options.size());
		if(Options.size()>0){
			reporter.Pass("No of Inputs available in the Drop-List " + Element + " is " + Options.size(), true);
			//Retrieve Options and put it inside a List of Strings
			for(WebElement e:Options){  
			     System.out.println(e.getText());
		     	 System.out.println(e.getAttribute("selected"));
			     OptionNames.add(e.getText());			     
			   }  
		}else{
			reporter.Fail("No of Inputs available in the Drop-List " + Element + " is " + Options.size(), true);
		}
		reporter.Pass("The Inputs available in the Drop-List " + Element + " are " + OptionNames, false);
		return OptionNames;
	}
	
	
	//Verify selected option in a Drop-List specified by Element Name on Object Repository matches the Expected Option
	public boolean verifySelectedDropListOption(String Element, String ListItem){
		String SelectedOption = "";
		boolean b = false;
		//String ExpectedListItem = PropFileRead.GetKeyValue(ListItem,"TestData.prop");
		String ExpectedListItem=getData(ListItem);
/*		if(ListItem.indexOf(" ")!=-1)
			ExpectedListItem = ListItem.trim();
		else
			ExpectedListItem = PropFileRead.GetKeyValue(ListItem,"TestData.prop");*/
		
		List<WebElement> Options= locateElement(Element).findElements(By.tagName("option"));
		if(Options.size()>0){
			//Retrieve Selected Option along with the text as String
			for(WebElement e:Options){  			     
			     if(e.isSelected()){
			    	SelectedOption = e.getText();
			    	reporter.Pass("The Selected Option in the Drop-List " + Element + " is " + SelectedOption, true);
			    	System.out.println("The Selected Option in the Drop-List " + Element + " is " + SelectedOption);
			    	//Verify selected option in a Drop-List match with Expected value
			    	if(SelectedOption.equals(ExpectedListItem)){
			    		reporter.Pass("Selected Option in Drop-List " + Element + " matches exactly with Expected Option " + ExpectedListItem, false);
				    	System.out.println("Selected Option in Drop-List " + Element + " matches exactly with Expected Option " + ExpectedListItem);
				    	b = true;
			    	}else{
			    		reporter.Fail("Selected Option in Drop-List " + Element + " does Not match with Expected Option " + ExpectedListItem, true);
				    	System.out.println("Selected Option in Drop-List " + Element + " does Not match with Expected Option " + ExpectedListItem);
			    	}			    		
			    	break;
			     }				
			   }  
		}else{
			reporter.Fail("No of Options available in the Drop-List " + Element + " is " + Options.size(), true);
		}
		return b;
	}	
	
	//Verifies if Element specified by Element Name on Object Repository contains Text specified in Expected Text ExpText
	public boolean TextExists(String Element, String ExpText){
		Boolean b=false;
		//ExpText=ExpText.toUpperCase();
		
		String ExpectedText = getData(ExpText).toUpperCase();
/*		if(ExpText.indexOf(" ")!=-1)
			ExpectedText = ExpText.toUpperCase().trim();
		else
			ExpectedText = PropFileRead.GetKeyValue(ExpText,"TestData.prop").toUpperCase();*/
		
		if(ElementExists(Element)){
			if(locateElements(Element).get(0).getText().toUpperCase().trim().indexOf(ExpectedText)!=-1){
				System.out.println("Text present in Element " + Element + " matches the Expected Text " + ExpectedText);
				b=true;
				reporter.Pass("Text present in Element " + Element + " matches the Expected Text " + ExpectedText, false);
			}else{
				System.out.println("Text present in Element " + Element + " does Not match the Expected Text " + ExpectedText);
				reporter.Fail("Text present in Element " + Element + " does Not match the Expected Text " + ExpectedText, true);
			}			
		}
		return b;
	}
	
	//Verifies if Element specified by Element Name on Object Repository contains Text exactly equal to the text specified in Expected Text ExpText
	public boolean TextEquals(String Element, String ExpText){
		Boolean b=false;
		String ExpectedText = getData(ExpText);
/*		if(ExpText.indexOf(" ")!=-1)
			ExpectedText = ExpText.trim();
		else
			ExpectedText = PropFileRead.GetKeyValue(ExpText,"TestData.prop");*/

		
		if(ElementExists(Element)){
			String ActualText = locateElements(Element).get(0).getText().trim();
			if(ActualText.equals(ExpectedText)){
				System.out.println("Text present in Element: " + ActualText + " matches exactly with the Expected Text: " + ExpectedText);
				b=true;
				reporter.Pass("Text present in Element: " + ActualText + " matches exactly with the Expected Text: " + ExpectedText, false);
			}else{
				System.out.println("Text present in Element: " + ActualText + " does Not match exactly with the Expected Text: " + ExpectedText);
				reporter.Fail("Text present in Element: " + ActualText + " does Not match exactly with the Expected Text: " + ExpectedText, true);
			}
		}
	return b;
	}
	
	//Retrieve text from Element specified by Element Name on Object Repository
	public String getText(String Element){
		String text="";
		if(ElementExists(Element)){
			text = locateElements(Element).get(0).getText().trim();
			System.out.println("Text present in Element: " + Element + " is " + text);
			reporter.Pass("Text present in Element: " + Element + " is " + text, false);
		}
	return text;
	}
	
	//Retrieve text from Element specified by Xpath
		public String getTextByXpath(String Xpath){
			String text="";
			if(init.driver.findElements(By.xpath(Xpath)).size()>0){
				text = init.driver.findElements(By.xpath(Xpath)).get(0).getText().trim();
				System.out.println("Text present in Element specified by Xpath is " + text);
			}
		return text;
		}
	
	//Retrieve attribute value from Element specified by Xpath
		public String getAttributeValueByXpath(String Xpath, String Attr){
			String value="";
			if(init.driver.findElements(By.xpath(Xpath)).size()>0){
				value = init.driver.findElements(By.xpath(Xpath)).get(0).getAttribute(Attr);
				System.out.println("Text present in Element specified by Xpath is " + value);
			}
		return value;
		}		
		
		
		//Verify if the Element specified by Xpath is disabled using the Class attribute value of the Element. Report failure if the element is not disabled.
		public boolean verifyDisabledByXpath(String Xpath){
			String value="",date="";
			boolean b = true;
			if(init.driver.findElements(By.xpath(Xpath)).size()>0){
				date = init.driver.findElements(By.xpath(Xpath)).get(0).getText();
				value = init.driver.findElements(By.xpath(Xpath)).get(0).getAttribute("class");
				if(value.contains("disabled")){
					System.out.println("Date -> " + date + " is disabled as expected");
				}else{
					System.out.println("Date -> " + date + " is enabled which is NOT as expected");
					b=false;
					reporter.Fail("Date -> " + date + " is enabled which is NOT as expected", true);
				}
			}			
			
		return b;
		}
		
		
	
	//Returns WebElement specified by Element Name on Object Repository
	public WebElement locateElement(String ElementName){
		WebElement we = null;
		WebDriver d = init.driver;
		String Element = PropFileRead.GetKeyValue(ElementName,"ObjRepo.prop",init);
		String Locator = Element.substring(1);
		char firstChar = Element.charAt(0);
		switch (firstChar) {
		case '#':	try{we =  d.findElement(By.id(Locator));	
					}catch(Exception e){
						reporter.Log("Error @ ClickElement(). " + e.getMessage(), true);
						reporter.Fail("Unable to Identify element " + ElementName + " with id = " + Locator, true);
					}	    	
					break;
		case '@':	try{we =  d.findElement(By.name(Locator));	
					}catch(Exception e){
						reporter.Log("Error @ ClickElement(). " + e.getMessage(), true);
						reporter.Fail("Unable to Identify element " + ElementName + " with name = " + Locator, true);
					}	    	
					break;
/*		case '.':	try{we =  d.findElement(By.className(Locator));	
					}catch(Exception e){
						reporter.Log("Error @ ClickElement(). " + e.getMessage(), true);
						reporter.Fail("Unable to Identify element " + ElementName + " with className = " + Locator, true);
					}	    	
					break;*/
		case '(':
		case '/':	try{we =  d.findElement(By.xpath(Element));	
					}catch(Exception e){
						reporter.Log("Error @ ClickElement(). " + e.getMessage(), true);
						reporter.Fail("Unable to Identify element " + ElementName + " with xpath = " + Element, true);
					}
					break;					
		default :	try{we =  d.findElement(By.cssSelector(Element));	
					}catch(Exception e){
						reporter.Log("Error @ ClickElement(). " + e.getMessage(), true);
						reporter.Fail("Unable to Identify element " + ElementName + " with cssSelector = " + Element, true);
					}
		}
		return we;
	}
	
	//Returns List of WebElements specified by Element Group on Object Repository
	public List<WebElement> locateElements(String ElementGroup){
		List<WebElement> Group = null;
		WebDriver d = init.driver;
		String Element = PropFileRead.GetKeyValue(ElementGroup,"ObjRepo.prop",init);
		char firstChar = Element.charAt(0);
		String Locator = Element.substring(1);
		switch (firstChar) {
		case '#':	try{Group =  d.findElements(By.id(Locator));	
					}catch(Exception e){
						reporter.Log("Error @ ClickElement(). " + e.getMessage(), true);
						reporter.Fail("Unable to Identify group of elements " + ElementGroup + " with id = " + Locator, true);
					}	    	
					break;
		case '@':	try{Group =  d.findElements(By.name(Locator));	
					}catch(Exception e){
						reporter.Log("Error @ ClickElement(). " + e.getMessage(), true);
						reporter.Fail("Unable to Identify group of elements " + ElementGroup + " with name = " + Locator, true);
					}	    	
					break;
/*		case '.':	try{Group =  d.findElements(By.className(Locator));	
					}catch(Exception e){
						reporter.Log("Error @ ClickElement(). " + e.getMessage(), true);
						reporter.Fail("Unable to Identify group of element " + ElementGroup + " with className = " + Locator, true);
					}	    	
					break;*/
		case '(':
		case '/':	try{Group =  d.findElements(By.xpath(Element));	
					}catch(Exception e){
						reporter.Log("Error @ ClickElement(). " + e.getMessage(), true);
						reporter.Fail("Unable to Identify group of element " + ElementGroup + " with xpath = " + Element, true);
					}
					break;
		default :	try{Group =  d.findElements(By.cssSelector(Element));	
					}catch(Exception e){
						reporter.Log("Error @ ClickElement(). " + e.getMessage(), true);
						reporter.Fail("Unable to Identify element " + ElementGroup + " with cssSelector = " + Element, true);
					}
		}
		return Group;
	}
	
	
	//Retrieves Data as required i.e. Either directly from Test Scripts inside Test Suites or from TestData Properties File 
	public String getData(String TestData){
		String Data;
		//If TestData contains any spaces consider that as Actual Data else it represents corresponding parameter in TestData Properties File
		if(TestData.indexOf(" ")!=-1)
			Data = TestData.trim();
		else
			Data = PropFileRead.GetKeyValue(TestData,"TestData.prop",init);
		
		return Data;
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
