import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver

import com.assertthat.selenium_shutterbug.utils.web.Browser
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.remote.RemoteWebDriver
import com.kms.katalon.core.webui.driver.DriverFactory

//====================================================================================
WebDriver driver = DriverFactory.getWebDriver()
RemoteWebDriver katalonWebDriver = (RemoteWebDriver) driver

//==================================================================


//==================================================================
def Browser = GlobalVariable.G_Browser
def errScreenShot = (RunConfiguration.getProjectDir() + '/ExtentReports/'+TestCaseName)
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=============================================================

println('*****************************************************')

println(GlobalVariable.G_Platform)

println('*****************************************************')

def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()

def location = navLocation + '/JobsModule/'

println('*****************************************************')

println(location)

println('*****************************************************')

TestObject newFileObj = null

try {
	WebUI.enableSmartWait()


	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))


	extentTest.log(Status.PASS, 'Navigated Jobs Tab')


	println(AppName)


	WebUI.click(findTestObject('Object Repository/LoginPage/NewJobPage/TestAppDef-AppComp'))

	extentTest.log(Status.PASS, 'Navigated to Job Submission For for - ' + AppName)

	WebUI.doubleClick(findTestObject('Object Repository/LoginPage/NewJobPage/GenericProfile'))


		newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)

		WebUI.rightClick(newFileObj)

		extentTest.log(Status.PASS, 'Right Clicked on Input file ' + InputFile)

		String idForCntxtMn = 'Add as ' + FileArg

		TestObject newRFBContextMnOption = WebUI.modifyObjectProperty(findTestObject('Object Repository/LoginPage/NewJobPage/ContextMenu_RFB_FilePicker'),
			'id', 'equals', idForCntxtMn, true)

		WebUI.click(newRFBContextMnOption)

		extentTest.log(Status.PASS, 'Clicked on context menu - ' + idForCntxtMn)

	
	def submitBtn = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/button_Submit_Job'),
		20, extentTest, 'Submit Button')

	if (submitBtn) {
		WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))

		extentTest.log(Status.PASS, 'Clicked on Submit Button ')
	}
	WebUI.waitForElementPresent(findTestObject('Object Repository/GenericObjects/Notification-Popup'), 5)
	String msg='Job has been submitted successfully on'
	String notificationMsg
	if	(WebUI.waitForElementPresent(findTestObject('Object Repository/GenericObjects/Notification-Popup'), 5))
	{
		notificationMsg = (new operations_FileModule.notifications()).getNotificationsText(extentTest,katalonWebDriver)
		if(notificationMsg.contains(msg))
		{
			GlobalVariable.G_JobID=CustomKeywords.'operations_JobsModule.GetJobRowDetails.getJobID'(notificationMsg)
			extentTest.log(Status.PASS,"Verified Notification Msg - "+notificationMsg )
			result=true
		}
		else
		{
			extentTest.log(Status.PASS," Notification Msg - "+notificationMsg)
		}
	}
	else
	{
		extentTest.log(Status.PASS,"Notification Pop-Up not presnt")
	}

	

	
	extentTest.log(Status.PASS, 'Verified - ' + TestCaseName)



	if (GlobalVariable.G_Browser == 'Edge') {
		WebUI.callTestCase(findTestCase('XRepeated_TC/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}
}
catch (Exception ex) {
	println('From TC - ' + GlobalVariable.G_ReportFolder)
	String screenShotPath = (errScreenShot+ GlobalVariable.G_Browser) + '.png'
	WebUI.takeScreenshot(screenShotPath)
	String p = (TestCaseName + GlobalVariable.G_Browser) + '.png'
	extentTest.log(Status.FAIL, ex)
	extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(p).build())
}
catch (StepErrorException e) {
	String screenShotPath = (errScreenShot+ GlobalVariable.G_Browser) + '.png'
	WebUI.takeScreenshot(screenShotPath)
	String p = (TestCaseName + GlobalVariable.G_Browser) + '.png'
	extentTest.log(Status.FAIL, ex)
	extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(p).build())
}
finally {
	extentTest.log(Status.PASS, 'Closing the browser after executinge test case - ' + TestCaseName)
}



