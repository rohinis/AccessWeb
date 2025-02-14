import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys

import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.exception.StepFailedException as StepFailedException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration

import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status

//====================================================================================

def username=null
def passwd=null
WebDriver driver = DriverFactory.getWebDriver()

EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)

// Get the driver wrapped inside
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()
//def Browser = GlobalVariable.G_Browser
RemoteWebDriver katalonWebDriver = (RemoteWebDriver) wrappedWebDriver

//===========================================================
def Browser = GlobalVariable.G_Browser
//===========================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
//extentTest.log(Status.PASS, 'Navigated to Acces Instance - '+GlobalVariable.G_BaseUrl)

//===========================================================
if (user=='admin')
{
			  username='pbsworks'
			  passwd='pbsworks'
}
else
{
			  username=GlobalVariable.G_userName
			  passwd=GlobalVariable.G_Password
}
WebUI.setText(findTestObject('LoginPage/username_txtbx'), username)
WebUI.setText(findTestObject('LoginPage/password_txtbx'), passwd)
WebUI.click(findTestObject('LoginPage/login_btn'))
extentTest.log(Status.PASS, 'Entered Creds - username - '+username +' password - '+passwd)

extentTest.log(Status.PASS, 'Clicked on Login Button ')


def jobsTab = (new customWait.WaitForElement()).WaitForelementPresent(findTestObject('Object Repository/Landing_Page/LandigPage_AltairAccess_Link'),
							 10,extentTest, 'AltairAccess Logo ')

if (jobsTab) {
			  WebUI.click(findTestObject('Object Repository/Landing_Page/LandigPage_AltairAccess_Link'))
}

extentTest.log(Status.PASS, 'Verified AltairAccess Logo post login ')

//=====================================================================================
def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()

def location = navLocation + '/FilesModule/ForHidden'

//=====================================================================================
def result

try {
			  extentTest.log(Status.PASS, 'prefValue --- ' + prefValue)

CustomKeywords.'customWait.setPrefrenceHidden.setprefrence'(prefValue, extentTest)

			  extentTest.log(Status.PASS, 'Setting the prefrence to view hidden files')

			  extentTest.log(Status.PASS, 'UserChoice - '+userChoice)
CustomKeywords.'customWait.setPrefrenceHidden.navigateTo'(TestCaseName, userChoice, extentTest,user)

			  result = CustomKeywords.'customWait.setPrefrenceHidden.checkHiddenItems'(prefValue, TestCaseName, extentTest)

			  if (result) {
							 extentTest.log(Status.PASS, ('Hidden file - .hiddenFile is listed after log out and login '))
			  } else {
							 extentTest.log(Status.FAIL, ('Hidden file - .hiddenFile is not listed '))
			  }

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

			  WebUI.click(findTestObject('AppComposer/Login'))

			  extentTest.log(Status.PASS, 'Click on Login again')

			  WebUI.setText(findTestObject('LoginPage/username_txtbx'), username)

			  extentTest.log(Status.PASS, 'Enter username -  ' + username)

			  WebUI.setText(findTestObject('LoginPage/password_txtbx'), passwd)

			  extentTest.log(Status.PASS, 'Enter  password  - ' + passwd)

			  WebUI.click(findTestObject('LoginPage/login_btn'))

			  extentTest.log(Status.PASS, 'Click on Login')

			  WebUI.delay(2)

CustomKeywords.'customWait.setPrefrenceHidden.navigateTo'(TestCaseName, userChoice, extentTest,user)

			  result = CustomKeywords.'customWait.setPrefrenceHidden.checkHiddenItems'(prefValue, TestCaseName, extentTest)

			  if (result) {
							 if(prefValue)
							 {
										   extentTest.log(Status.PASS, ('******************************************************* '))
										   extentTest.log(Status.PASS, ('Hidden file - .hiddenFile is listed after log out and login - for prefrence value - '+prefValue))
										   extentTest.log(Status.PASS, ('******************************************************* '))

							 }
							 else
							 {
										   extentTest.log(Status.PASS, ('******************************************************* '))
										   extentTest.log(Status.PASS, ('Hidden file - .hiddenFile is not listed after log out and login - for prefrence value - '+prefValue))
										   extentTest.log(Status.PASS, ('******************************************************* '))

							 }
			  }
			  else {

							 if(prefValue)
							 {
										   extentTest.log(Status.PASS, ('******************************************************* '))
										   extentTest.log(Status.FAIL, ('Hidden file - .hiddenFile is not listed after log out and login - for prefrence value - '+prefValue))
										   extentTest.log(Status.PASS, ('******************************************************* '))

							 }
							 else
							 {
										   extentTest.log(Status.PASS, ('******************************************************* '))
										   extentTest.log(Status.FAIL, ('Hidden file - .hiddenFile is  listed after log out and login - for prefrence value - '+prefValue))
										   extentTest.log(Status.PASS, ('******************************************************* '))


							 }
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
