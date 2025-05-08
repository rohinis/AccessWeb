import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.exception.StepFailedException as StepFailedException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import internal.GlobalVariable as GlobalVariable



//==================================================================
def Browser = GlobalVariable.G_Browser
//===============================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
//CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=============================================================
try {

	if (Browser == 'Edge Chromium') {
		WebUI.click(findTestObject('Object Repository/GenericObjects/EdgeChromium_Details_link'))

		WebUI.delay(3)

		WebUI.click(findTestObject('Object Repository/GenericObjects/EdgeChromium_proceed_link'))

		WebUI.delay(3)
		WebUI.deleteAllCookies()
	}
	else if (Browser == 'IE') {

		WebUI.click(findTestObject('Object Repository/GenericObjects/IE_Details_Link'))

		WebUI.delay(3)
		WebUI.waitForElementVisible(findTestObject('Object Repository/GenericObjects/EdgeProceedeLink'), 2)
		WebUI.click(findTestObject('Object Repository/GenericObjects/EdgeProceedeLink'))

		WebUI.delay(3)
	}
	extentTest.log(Status.PASS, 'Navigated to url - ' +GlobalVariable.G_BaseUrl)

	WebUI.setText(findTestObject('Object Repository/LoginPage/username_txtbx'), username)
	extentTest.log(Status.PASS, 'Entered Username  - ' +username)
	
	WebUI.setText(findTestObject('LoginPage/password_txtbx'), password)
	extentTest.log(Status.PASS, 'Entered Password  - ' +password)
	
	WebUI.click(findTestObject('LoginPage/login_btn'))
	extentTest.log(Status.PASS, 'Clicked Login Button')
	
		
	WebUI.delay(2)
	switch(userChoice)
	{
		case "Valid":

			def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'),10,extentTest,"valid case")

			if (jobsTab) {
				WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
			}
			break

		case "Invalid":
			def errUserName = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/LoginPage/ErrorMsg_Incorrect_Username_or_Password'),2,extentTest,"invalidcase")

			if (errUserName) {
				extentTest.log(Status.PASS, 'Error msg is displyed for Incorrect Username or Password')
			}
			break;


			case "Blank":
			def errBlank = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/LoginPage/ErrorMsg_Incorrect_Username_or_Password'),2,extentTest,"blank")

			if (errBlank) {
				extentTest.log(Status.PASS, 'Error msg is displyed for Incorrect Username or Password')
			}
			break;

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

