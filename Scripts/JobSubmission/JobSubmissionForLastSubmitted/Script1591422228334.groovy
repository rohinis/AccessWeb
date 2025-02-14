import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement as RemoteWebElement
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

//====================================================================================
WebDriver driver = DriverFactory.getWebDriver()
EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()
RemoteWebDriver katalonWebDriver = (RemoteWebDriver) wrappedWebDriver
//RemoteWebDriver katalonWebDriver = ((wrappedWebDriver) as RemoteWebDriver)
//====================================================================================
def Browser = GlobalVariable.G_Browser
//====================================================================================
def extentTest=GlobalVariable.G_ExtentTest
//====================================================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================
def result
TestObject newFileObj

try {
	def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'),
			20,extentTest,'App def')

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}

	extentTest.log(Status.PASS, 'Navigated Jobs Tab')
	WebUI.delay(2)
	TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'), 'id', 'equals',
			AppName, true)
	WebUI.click(newAppObj)

	extentTest.log(Status.PASS, 'Navigated to Job Submission For for - ' + AppName)

	//	WebUI.doubleClick(newAppObj)
	WebUI.delay(2)

	/*def errorPanel = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/JS_ErrorModalPanel'),
			5,extentTest,'ErrorPanel')

	if (errorPanel) {
		WebUI.click(findTestObject('Object Repository/JobSubmissionForm/button_Close'))
	}*/

	WebUI.click(findTestObject('Object Repository/LoginPage/NewJobPage/GenericProfile'))

	WebUI.delay(2)

	CustomKeywords.'operations_JobsModule.JobSubmissions.JSAllFileds'(ToChange, ChangeValue, extentTest)

	WebUI.delay(2)

	WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)

	WebUI.delay(3)

	if (ExecMode.equals('LocalForm')) {
		newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
	} else {
		if (ExecMode.equals('Local')) {
			newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
		} else {
			if (TestCaseName.contains('ShortCut')) {
				ExecMode = 'ShortCut'

				newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
			} else {
				newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
			}
		}

		WebUI.click(newFileObj)

		WebUI.rightClick(newFileObj)

		extentTest.log(Status.PASS, 'Right Clicked on Input file ' + InputFile)

		WebUI.delay(2)

		String idForCntxtMn = 'Add as ' + FileArg

		TestObject newRFBContextMnOption = WebUI.modifyObjectProperty(findTestObject('Object Repository/LoginPage/NewJobPage/ContextMenu_RFB_FilePicker'),
				'id', 'equals', idForCntxtMn, true)

		WebUI.click(newRFBContextMnOption)

		extentTest.log(Status.PASS, 'Clicked on context menu - ' + idForCntxtMn)
	}

	def submitBtn = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/button_Submit_Job'),20,extentTest,'SubmitButton')

	if (submitBtn) {
		WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))

		extentTest.log(Status.PASS, 'Clicked on Submit Button ')
	}

	WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)

	def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))

	extentTest.log(Status.PASS, 'Notification Generated')

	println('Job Text = ' + jobText)
	GlobalVariable.G_JobID=CustomKeywords.'operations_JobsModule.GetJobRowDetails.getJobID'(jobText)
	extentTest.log(Status.PASS, 'Job ID - ' + GlobalVariable.G_JobID)

	println('********************************************')

	jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'),20,extentTest,'Jobs Tab')

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}
	WebUI.delay(3)
	//String myXpath = "//a[@id='mon_recent_jobname_label']"
	String myXpath= "//div[@class='job-name-column']"
	println(myXpath)
	WebElement LS = katalonWebDriver.findElement(By.xpath(myXpath))
	WebUI.delay(3)
	RemoteWebElement ele = LS
	String myText = ele.getText()
	myText=myText.trim()
	println('********************************************')
	println('LSJobID---- ' + myText)
	println('********************************************')

	extentTest.log(Status.PASS, 'Job ID from LastSubmitted Link- ' + myText)
	extentTest.log(Status.PASS, 'userChoice- ' + userChoice)
	
	if (userChoice.equals('Details')) {
		TestObject jobLink = CustomKeywords.'buildTestObj.CreateJobSubmissionObjs.myJobLinkRow'(GlobalVariable.G_JobID)
		WebUI.click(jobLink)
		//TestObject jobTitle =CustomKeywords.'buildTestObj.CreateTestObj.myJobFromLastSubmitted'()
		String jobIdDetailsPage=CustomKeywords.'buildTestObj.CreateTestObj.myJobFromLastSubmitted'()
		String detailsJobID='('+myText+')'
		extentTest.log(Status.PASS, 'Job id  from detais page - '+detailsJobID)
		result=detailsJobID.contains(GlobalVariable.G_JobID)
		if (result) {
			extentTest.log(Status.PASS, 'Job ID - Form LastSubmitted matched the Job Id on details page')
		} else {
			extentTest.log(Status.FAIL, 'Job ID - Form LastSubmitted not matched the Job Id on details page')
		}

	}


	if(userChoice.equals('Match'))
	{
		if (GlobalVariable.G_JobID.equals(myText)) {
			extentTest.log(Status.PASS, 'Job ID - from notofication and Form LastSubmitted link on JM page Match ')
		} else {
			extentTest.log(Status.FAIL, 'Job ID - from notofication and Form LastSubmitted link on JM page Do Not Match ')
		}
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



