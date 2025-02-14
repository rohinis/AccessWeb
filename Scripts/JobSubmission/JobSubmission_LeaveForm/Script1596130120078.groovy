import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status
import com.aventstack.extentreports.MediaEntityBuilder

import internal.GlobalVariable as GlobalVariable

//====================================================================================
def Browser = GlobalVariable.G_Browser
//====================================================================================
def extentTest=GlobalVariable.G_ExtentTest
//====================================================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================


try {

	def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'),
			20,extentTest,'App def')

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}
	extentTest.log(Status.PASS, 'Navigated Job Tab')
	WebUI.delay(2)

	TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'), 'id', 'equals',
			AppName, true)

	WebUI.click(newAppObj)
	extentTest.log(Status.PASS, 'Navigated to Job Submission For for - '+AppName)
	WebUI.delay(2)
	WebUI.click(findTestObject('Object Repository/LoginPage/NewJobPage/GenericProfile'))
	WebUI.delay(2)
	jobsTab=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'), 20,extentTest,'Jobs Tab')
	if(jobsTab)
	{
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
		extentTest.log(Status.PASS, 'Clicked on Jobs Tab')
		def LeaveJobs=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/JobSubmissionForm/Title_Leave_Job_submission_form'), 20,extentTest,'LeaveSubmissionPagePanel')
		if(LeaveJobs)
		{
			WebUI.verifyElementPresent(findTestObject('Object Repository/JobSubmissionForm/Text_LeavePage'), 4)
			extentTest.log(Status.PASS, 'Verified the - Leave Job submission form? - dialog box')
		}
	}

	switch(userChoice)
	{
		case 'Yes':
			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Yes'))
			extentTest.log(Status.PASS, 'Clicked on Yes on dialog box')
			WebUI.verifyElementPresent(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'),3)
			extentTest.log(Status.PASS, 'Verified user is navigated  to Jobs Page')
			break
		case 'No':
			WebUI.click(findTestObject('Object Repository/GenericObjects/btn_No'))
			extentTest.log(Status.PASS, 'Clicked on No on dialog box')
			WebUI.verifyElementPresent(findTestObject('Object Repository/LoginPage/NewJobPage/GenericProfile'), 3)
			extentTest.log(Status.PASS, 'Verified user stays on Job Submission Form')
			break
		case 'Close':
			WebUI.click(findTestObject('Object Repository/JobSubmissionForm/Icon_Close_LeavePage'))
			extentTest.log(Status.PASS, 'Clicked on Close Icon on dialog box')
			WebUI.verifyElementPresent(findTestObject('Object Repository/LoginPage/NewJobPage/GenericProfile'), 3)
			extentTest.log(Status.PASS, 'Verified user stays on Job Submission Form')
			break

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
