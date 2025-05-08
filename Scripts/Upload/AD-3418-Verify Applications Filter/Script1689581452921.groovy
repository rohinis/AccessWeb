import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.configuration.RunConfiguration

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.Keys as Keys

import internal.GlobalVariable as GlobalVariable

//==================================================================
def Browser = GlobalVariable.G_Browser
//===============================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=============================================================


WebDriver driver = DriverFactory.getWebDriver()
EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()
RemoteWebDriver katalonWebDriver = (RemoteWebDriver) wrappedWebDriver
//=========================================================================================================

WebUI.delay(2)
try
{
	WebUI.delay(2)
	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	extentTest.log(Status.PASS, 'Click on Jobs tab')
	WebUI.delay(2)
	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))
	extentTest.log(Status.PASS, 'Click on the  reset')

		
	String myXpath="//div[@class = 'job-monitor-application-tile']"
  
		  println(myXpath)
		  boolean value
		 
  
		  List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
		  println listElement.size()
		  extentTest.log(Status.PASS, "Iterate through all the Appdefs in the Submit jobs using section")
		  extentTest.log(Status.PASS, 'Verify the Applications Present under the Applications Filter ' )
  
		  for(int i =1;i<listElement.size();i++) {
			  RemoteWebElement ele = listElement.get(i)
			  String myText=ele.getText()
		
			
			  
			  println( myText)
				
				  
				  TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('Object Repository/JobMonitoringPage/Job_Filters/Label_Application_Filter'), 'id', 'equals',myText, true)
				  
						  value= WebUI.verifyElementPresent(newAppObj,10 )
						 extentTest.log(Status.PASS, " The appdefs are"+ myText)
						 
			
		  }
		  if(value== true) {
			  extentTest.log(Status.PASS, 'Verified that all the applications present under "Submit Job Using:" section Is displayed under Application filter as well' )
		  }
		   }
		   catch (Exception ex) {
			   println('From TC - ' + GlobalVariable.G_ReportFolder)
		   
			   String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'
		   
			   WebUI.takeScreenshot(screenShotPath)
		   
			   String p = (TestCaseName + GlobalVariable.G_Browser) + '.png'
		   
			   extentTest.log(Status.FAIL, ex)
		   
			   extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(p).build())
		   }
		   catch (StepErrorException e) {
			   String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'
		   
			   WebUI.takeScreenshot(screenShotPath)
		   
			   String p = (TestCaseName + GlobalVariable.G_Browser) + '.png'
		   
			   extentTest.log(Status.FAIL, ex)
		   
			   extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(p).build())
		   }
		   finally {
			   extentTest.log(Status.PASS, 'Closing the browser after executinge test case - ' + TestCaseName)
			   
			   
		   }



