import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status

import internal.GlobalVariable as GlobalVariable

//==================================================================
def Browser = GlobalVariable.G_Browser
//===============================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================

def result=false
def msgPass=false
def mssFail=false

WebUI.delay(2)

try {
	WebUI.delay(2)
	
		def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'),
		20,extentTest,'Jobs Tab')

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}	
	
	WebUI.delay(2)
	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))
	WebUI.delay(2)

	println(AllJobsUser)

	
		WebUI.click(findTestObject('Object Repository/JobMonitoringPage/RadioBtn_AllJobs'))
		WebUI.delay(2)
		extentTest.log(Status.PASS, 'All Jobs Filter Applied')
		WebUI.click(findTestObject('JobMonitoringPage/JM_SearchBox'))
		//WebUI.setText(findTestObject('JobMonitoringPage/JM_SearchBox'),AllJobsUser)
		WebUI.sendKeys(findTestObject('JobMonitoringPage/JM_SearchBox'), AllJobsUser)
		//	WebUI.sendKeys(findTestObject('JobMonitoringPage/JM_SearchBox'), Keys.chord(Keys.ENTER))

		if(GlobalVariable.G_Browser.equals('Firefox')) {
			WebUI.delay(5)
			extentTest.log(Status.PASS, 'Waiting for jobs table to load on FireFox')
		}
	

	TestObject newJobFilter = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/label_jobState'), 'text', 'equals',
			jobState, true)
	WebUI.delay(2)
	WebUI.click(newJobFilter)
	WebUI.delay(2)
	extentTest.log(Status.PASS, 'Clicked on job with state  - ' + jobState)
	println(jobState)


	TestObject newJobRow = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/div_Completed'), 'title', 'equals',	jobState, true)
	WebUI.rightClick(newJobRow)

	WebUI.click(findTestObject('JobMonitoringPage/ViewDetails_Jobs'))


	switch (folder) {

		case 'Running':
			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/RunningFolder'))
			extentTest.log(Status.PASS, 'Navigated to Running Folder')
			result=WebUI.verifyElementClickable(findTestObject('JobMonitoringPage/ErrMsg_NotJobOwner'), FailureHandling.CONTINUE_ON_FAILURE)
			break;
		case 'Output':
			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/OutputFolder'))
			extentTest.log(Status.PASS, 'Navigated to Output Folder')
			result=WebUI.verifyElementClickable(findTestObject('JobMonitoringPage/ErrMsg_NotJobOwner'), FailureHandling.CONTINUE_ON_FAILURE)
			break;
			case 'Input':
			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/InputFolder'))
			extentTest.log(Status.PASS, 'Navigated to Inputjob_detail_download_btn Folder')
			result=WebUI.verifyElementClickable(findTestObject('JobMonitoringPage/ErrMsg_NotJobOwner'), FailureHandling.CONTINUE_ON_FAILURE)
			break;
	}


		msgPass='Verified - Non Job Owners see msg for other’s job'
		msgFail='Non Job Owners do not see msg for other’s job'
	

	if (result) {
		extentTest.log(Status.PASS, msgPass)
	}
	else
	{	 extentTest.log(Status.FAIL, msgFail)

	}

	if (GlobalVariable.G_Browser == 'Edge') {
		WebUI.callTestCase(findTestCase('XRepeated_TC/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}
}
catch (Exception ex) {
	String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

	WebUI.takeScreenshot(screenShotPath)

	extentTest.log(Status.FAIL, ex)

	KeywordUtil.markFailed('ERROR: ' + e)
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

