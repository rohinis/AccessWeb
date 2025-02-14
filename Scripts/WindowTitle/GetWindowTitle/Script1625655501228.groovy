import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status
import com.aventstack.extentreports.MediaEntityBuilder

import internal.GlobalVariable as GlobalVariable



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

try {
	
		WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))
		extentTest.log(Status.PASS, 'Click on profile tab')
		WebUI.delay(2)
		
		WebUI.click(findTestObject('Landing_Page/ListItem_Logout'))
		extentTest.log(Status.PASS, 'Click on logout')
		
		WebUI.verifyElementVisible(findTestObject('LogOut-PopUp/Title_Logout'))
		
		WebUI.delay(2)
		
		WebUI.click(findTestObject('GenericObjects/btn_Yes'))
		extentTest.log(Status.PASS, 'Click on yes button')
		WebUI.delay(2)
		
		def title = WebUI.getWindowTitle()
		println(title)
		if(title== "Logout-Altair Access") {
		
		extentTest.log(Status.PASS, 'Browser page  -  ' + title)}
		else
			extentTest.log(Status.FAIL, 'Failed to verify the title')
		
		
	
	
	if (TestCaseName.contains('Login'))
	{
		WebUI.click(findTestObject('AppComposer/Login'))
		extentTest.log(Status.PASS, 'Click on Login again')
		
		def title1 = WebUI.getWindowTitle()
		println(title1)
		extentTest.log(Status.PASS, 'Browser page  -  ' + title1)
	}
   
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

