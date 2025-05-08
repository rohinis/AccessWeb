import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver

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


//====================================================================================
WebDriver driver = DriverFactory.getWebDriver()
RemoteWebDriver katalonWebDriver = (RemoteWebDriver) driver
//==================================================================
def Browser = GlobalVariable.G_Browser
def result=false
//===============================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=============================================================

println "*****************************************************"
println GlobalVariable.G_Platform
println "*****************************************************"

def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()
def location = (navLocation + '/JobsModule/')
def errScreenShot = (RunConfiguration.getProjectDir() + '/ExtentReports/'+TestCaseName)
println "*****************************************************"
println location
println "*****************************************************"


TestObject newFileObj=null

try {
	WebUI.enableSmartWait()
	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

	extentTest.log(Status.PASS, 'Navigated Jobs Tab')


	TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'), 'id', 'equals',
			AppName, true)
	WebUI.click(newAppObj)
	extentTest.log(Status.PASS, 'Navigated to Job Submission For for - ' + AppName)
	WebUI.doubleClick(findTestObject('Object Repository/LoginPage/NewJobPage/GenericProfile'))


	if(AppName.contains('InComplete'))
	{
		WebUI.doubleClick(findTestObject('Object Repository/JobSubmissionForm/TxtBox_ReqFiled_ToFill'))
		WebUI.setText(findTestObject('Object Repository/JobSubmissionForm/TxtBox_ReqFiled_ToFill'), 'testString')
	}

	CustomKeywords.'operations_JobsModule.JobSubmissions.JSAllFileds'(ToChange, ChangeValue, extentTest)

	if (ExecMode.equals('Array'))
	{
		WebUI.delay(2)
		extentTest.log(Status.PASS, 'No file required for Array Job')
	}
	else
	{

		WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)
		WebUI.disableSmartWait()

		newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
		WebUI.rightClick(newFileObj)
		extentTest.log(Status.PASS, 'Right Clicked on Input file ' + InputFile)
		WebUI.delay(2)
		String idForCntxtMn = 'Add as ' + FileArg
		TestObject newRFBContextMnOption = WebUI.modifyObjectProperty(findTestObject('Object Repository/LoginPage/NewJobPage/ContextMenu_RFB_FilePicker'),
				'id', 'equals', idForCntxtMn, true)
		WebUI.click(newRFBContextMnOption)
		extentTest.log(Status.PASS, 'Clicked on context menu - ' + idForCntxtMn)
	}

	boolean isEnabled=WebUI.verifyElementPresent(findTestObject('JobSubmissionForm/button_Submit_Job'), 20)
	if (isEnabled) {
		WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))
	} else {
		println("Submit button is disabled! Waiting for it to become enabled...")
		WebUI.delay(5) // Adjust as needed
		WebUI.waitForElementClickable(findTestObject('JobSubmissionForm/button_Submit_Job'), 20)
		WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))
	}
	extentTest.log(Status.PASS, 'Clicked on Submit Button')
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



	WebUI.enableSmartWait()
	if(result)
	{
		if (ToChange.equals('SetOutPutDir')) {
			WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
			extentTest.log(Status.PASS, 'Navigated to Files Tab')
			CustomKeywords.'operations_FileModule.ChangeView.changePageView'(TestCaseName, extentTest)
			CustomKeywords.'generateFilePath.filePath.navlocation'(location, extentTest)

			TestObject newFileObjJS = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title',
					'equals', InputFile, true)
			def fileItem = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newFileObjJS, 20, extentTest, InputFile)
			println(fileItem)
			if (fileItem) {
				extentTest.log(Status.PASS, 'Output file - jobFile.out exists ')
			}
		}

		TestObject jobIdEle = CustomKeywords.'buildTestObj.CreateTestObjJobs.myTestObjJobRow'(GlobalVariable.G_JobID)
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

		if (ExecMode.equals('Array')) {

			CustomKeywords.'operations_JobsModule.GetJobRowDetails.checkSubJobs'(katalonWebDriver, 'JS', extentTest)
		}

		extentTest.log(Status.PASS, 'Verified - ' + TestCaseName)

		WebUI.disableSmartWait()
	}
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





