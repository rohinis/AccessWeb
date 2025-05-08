import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status
import com.aventstack.extentreports.MediaEntityBuilder


import internal.GlobalVariable as GlobalVariable


//====================================================================================
//====================================================================================
def Browser = GlobalVariable.G_Browser
//====================================================================================
def extentTest=GlobalVariable.G_ExtentTest
//====================================================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================


WebUI.delay(2)

try {
		def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'),
		20,extentTest,'Jobs Tab')

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}

	extentTest.log(Status.PASS, 'Navigated Jobs Tab')

	WebUI.delay(2)

	TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'), 'id', 'equals', AppName, true)
	TestObject labelApp1=WebUI.modifyObjectProperty(	findTestObject('Object Repository/JobSubmissionForm/Label_ApplicationName'), 'text', 'equals', AppName,true)
	TestObject labelApp2=WebUI.modifyObjectProperty(	findTestObject('Object Repository/JobSubmissionForm/Label_ApplicationName'), 'text', 'equals', NewApp,true)

	WebUI.click(newAppObj)

	extentTest.log(Status.PASS, 'Navigated to Job Submission For for - '+AppName)

	WebUI.delay(2)
	def isLabelPresent=WebUI.verifyElementPresent(labelApp1, 3, FailureHandling.CONTINUE_ON_FAILURE)
	if(isLabelPresent)
	{
		extentTest.log(Status.PASS,'Navigated to First App -'+AppName)
	}

	//	findTestObject('Object Repository/JobSubmissionForm/Label_ApplicationName')
	TestObject LeftNavAppIdentifier = CustomKeywords.'buildTestObj.CreateTestObjJobs.myLeftNavAppIdentifier'(NewApp)
	WebUI.click(LeftNavAppIdentifier)


	isLabelPresent=WebUI.verifyElementPresent(labelApp2, 3, FailureHandling.CONTINUE_ON_FAILURE)
	if(isLabelPresent)
	{
		extentTest.log(Status.PASS,'Navigated to Second App -'+NewApp)
	}
	extentTest.log(Status.PASS,'Verified - '+TestCaseName)
	

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

