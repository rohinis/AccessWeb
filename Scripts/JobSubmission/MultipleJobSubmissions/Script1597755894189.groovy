import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status
import com.aventstack.extentreports.MediaEntityBuilder
import org.openqa.selenium.Keys as Keys

import internal.GlobalVariable as GlobalVariable

//====================================================================================
def Browser = GlobalVariable.G_Browser
//====================================================================================
def extentTest=GlobalVariable.G_ExtentTest
//====================================================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================
def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()
def location = navLocation + '/FilesModule/FileOps/'
//=====================================================================================

def filePathShellRun = RunConfiguration.getProjectDir() + '/Upload/JobFiles/shJob.sh'
def newFPSHR = CustomKeywords.'generateFilePath.filePath.getFilePath'(filePathShellRun)
def filePathShellFail = RunConfiguration.getProjectDir() + '/Upload/JobFiles/pyJob.py'
def newFPSHFail = CustomKeywords.'generateFilePath.filePath.getFilePath'(filePathShellFail)

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
	extentTest.log(Status.PASS, 'Navigated to job submission form ')
	
	def jobidSH
	def jobidPY
	jobidSH = CustomKeywords.'todelete_preReq_Old.jobSubmissionForPreReq.JSMulti'(newFPSHR, AppName,extentTest)
	extentTest.log(Status.PASS,"Submitted job for .sh extension")
	extentTest.log(Status.PASS,"job id for .sh extension - "+ jobidSH)

	jobidPY=CustomKeywords.'todelete_preReq_Old.jobSubmissionForPreReq.JSMulti'(newFPSHFail, AppName,extentTest)
	extentTest.log(Status.PASS,"Submitted job for .py extension")
	extentTest.log(Status.PASS,"job id for .py extension - "+ jobidPY)
	jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'),20,extentTest,'Jobs Tab')

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}
	extentTest.log(Status.PASS, 'Navigated to jobs page ')
	WebUI.delay(2)
	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))

	WebUI.click(findTestObject('JobMonitoringPage/JM_SearchBox'))
	//WebUI.setText(findTestObject('JobMonitoringPage/JM_SearchBox'),AllJobsUser)
	WebUI.sendKeys(findTestObject('JobMonitoringPage/JM_SearchBox'), 'shJob')
	WebUI.sendKeys(findTestObject('JobMonitoringPage/JM_SearchBox'), Keys.chord(Keys.ENTER))
	extentTest.log(Status.PASS, 'Searched for shjob')
	//String myXpath="//a[contains(@class,'show-text-ellipsis')][contains(text(),'"+jobidSH+"')]"
	String myXpath="//a[contains(@data-automation-id,'"+jobidSH+"')]"
	println (myXpath)
	TestObject jobRow = new TestObject('objectName')
	jobRow.addProperty('xpath', ConditionType.EQUALS, myXpath)
	WebUI.delay(2)
	WebUI.mouseOver(jobRow)
	WebUI.delay(4)
	

	extentTest.log(Status.PASS, 'Selected the job row and initiated resubmit action for shjob ')
	if(GlobalVariable.G_Browser.equals('Chrome'))
	{
		WebUI.rightClick(jobRow)
		println("HI =============")
		def result = CustomKeywords.'operations_JobsModule.executeJobAction.perfromJobAction'('Resubmit', TestCaseName,userChoice,extentTest)
	}
	else
	{
		WebUI.click(jobRow)
		def result=CustomKeywords.'operations_JobsModule.executeJobAction_JobDetails_Topmenu.perfromJobAction'('job_detail_resubmit_btn',TestCaseName,extentTest)
	}
	
	jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'),20,extentTest,'Jobs Tab')
	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	
	}
	extentTest.log(Status.PASS, 'Navigated to jobs page ')
	WebUI.delay(2)
	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))
	WebUI.click(findTestObject('JobMonitoringPage/JM_SearchBox'))
	WebUI.sendKeys(findTestObject('JobMonitoringPage/JM_SearchBox'), 'pyJob')
	WebUI.sendKeys(findTestObject('JobMonitoringPage/JM_SearchBox'), Keys.chord(Keys.ENTER))
	extentTest.log(Status.PASS, 'Searched for pyjob')
	String myXpath1="//a[contains(@class,'show-text-ellipsis')][contains(text(),'"+jobidPY+"')]"
	TestObject jobRow1 = new TestObject('objectName')
	jobRow1.addProperty('xpath', ConditionType.EQUALS, myXpath1)
	WebUI.delay(2)
	WebUI.mouseOver(jobRow1)
	

	extentTest.log(Status.PASS, 'Selected the job row and initiated resubmit action for pyjob')
	if(GlobalVariable.G_Browser.equals('Chrome'))
	{
		WebUI.rightClick(jobRow1)
		def result = CustomKeywords.'operations_JobsModule.executeJobAction.perfromJobAction'('Resubmit', TestCaseName,userChoice,extentTest)
	}
	else
	{
		WebUI.click(jobRow1)
		def result=CustomKeywords.'operations_JobsModule.executeJobAction_JobDetails_Topmenu.perfromJobAction'('job_detail_resubmit_btn',TestCaseName,extentTest)
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
