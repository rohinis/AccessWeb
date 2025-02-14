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
import com.aventstack.extentreports.Status
import com.aventstack.extentreports.MediaEntityBuilder

WebDriver driver = DriverFactory.getWebDriver()


//====================================================================================
def Browser = GlobalVariable.G_Browser
//====================================================================================
def extentTest=GlobalVariable.G_ExtentTest
//====================================================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)

//=====================================================================================
//String screenShot='ExtentReports/'+TestCaseName+userChoice+GlobalVariable.G_Browser+'.png'
TestObject newFileObj
def result
WebUI.delay(2)
try
{
	WebUI.delay(2)
	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	extentTest.log(Status.PASS, 'Click on Jobs tab')
	WebUI.delay(2)
	
	WebUI.verifyElementPresent(findTestObject('Object Repository/JobMonitoringPage/JobId'), 3)
	extentTest.log(Status.PASS, 'Verify JobId column')
	
	WebUI.verifyElementPresent(findTestObject('Object Repository/JobMonitoringPage/Application'), 3)
	extentTest.log(Status.PASS, 'Verify Application column')
	
	WebUI.verifyElementPresent(findTestObject('Object Repository/JobMonitoringPage/Name'), 3)
	extentTest.log(Status.PASS, 'Verify Name column')
	
	WebUI.verifyElementPresent(findTestObject('Object Repository/JobMonitoringPage/Status'), 3)
	extentTest.log(Status.PASS, 'Verify Status column')
		
		if (GlobalVariable.G_Browser == 'Edge') {
		WebUI.callTestCase(findTestCase('XRepeated_TC/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
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
//=====================================================================================


