import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.Capabilities
import org.openqa.selenium.remote.RemoteWebDriver
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver
import java.awt.Robot
import java.awt.event.KeyEvent as KeyEvent
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status


//==================================================================
def Browser = GlobalVariable.G_Browser
//===============================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=============================================================


def result=false
WebUI.delay(2)

try
{
	WebUI.delay(2)
	//WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

	WebUI.mouseOver(findTestObject('Cluster_Registration/Manageservice'))
	WebUI.click(findTestObject('Cluster_Registration/Manageservice'))
	extentTest.log(Status.PASS, 'Click on Manage Service')
	WebUI.delay(2)
	 
	   
		boolean ispresent=WebUI.verifyElementPresent(findTestObject('Cluster_Registration/Available'), 3, FailureHandling.CONTINUE_ON_FAILURE)
		if(ispresent) {
			extentTest.log(Status.PASS, 'Verified the Server is available')
			
		}
		else {
			extentTest.log(Status.FAIL, 'Failed to verify the server')
			
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



