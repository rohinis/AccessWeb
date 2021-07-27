import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable


//====================================================================================
ReportFile = (GlobalVariable.G_ReportName + '.html')
def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)
def LogStatus = com.relevantcodes.extentreports.LogStatus
def extentTest = extent.startTest(TestCaseName)
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================


def OpsCom=RunConfiguration.getProjectDir() + '/Upload/OpsCompleted.zip'
def Ops=RunConfiguration.getProjectDir() + '/Upload/Ops.zip'

def filePathOpsCom = CustomKeywords.'generateFilePath.filePath.getFilePath'(OpsCom)
def filePathOps = CustomKeywords.'generateFilePath.filePath.getFilePath'(Ops)


try {
	WebUI.delay(3)
	def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'),
			10,extentTest,'JobsTab')
	println(jobsTab)
	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}
	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))
	TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'equals',
			AppName, true)

	WebUI.click(newAppObj)
	//extentTest.log(LogStatus.PASS, 'Navigated Job Tab')

	//extentTest.log(LogStatus.PASS, 'Navigated to Job Submission For for - ' + AppName)

	//	WebUI.doubleClick(newAppObj)


	def navLocation =CustomKeywords.'generateFilePath.filePath.execLocation'()
	def location=navLocation
	println('##################################################################')
	println (location)
	println('##################################################################')

	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))

	WebUI.delay(2)

	//location=location+'/ForJM'

	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))


	def fJob=CustomKeywords.'preReq.jobSubmissionForPreReq.OpsJobs'(filePathOpsCom,extentTest)
	jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'),
			10,extentTest,'JobsTab')

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}

	String myXpath="//a[contains(@class,'show-text-ellipsis')][contains(text(),'"+fJob+"')]"
	println (myXpath)
	TestObject jobRow = new TestObject('objectName')
	jobRow.addProperty('xpath', ConditionType.EQUALS, myXpath)
	WebUI.delay(2)
	WebUI.mouseOver(jobRow)
	WebUI.click(jobRow)
	def result=CustomKeywords.'operations_JobsModule.executeJobAction_JobDetails_Topmenu.perfromJobAction'('job_detail_terminate_btn',TestCaseName,extentTest)

	if (GlobalVariable.G_Browser == 'Edge') {
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}
}
catch (Exception ex) {
	String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

	WebUI.takeScreenshot(screenShotPath)

	//extentTest.log(LogStatus.FAIL, ex)

	KeywordUtil.markFailed('ERROR: ' + e)
}
catch (StepErrorException e) {
	String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

	WebUI.takeScreenshot(screenShotPath)

	//extentTest.log(LogStatus.FAIL, e)

	KeywordUtil.markFailed('ERROR: ' + e)
}
finally {
	//extent.endTest(//extentTest)

	//extent.flush()
}
