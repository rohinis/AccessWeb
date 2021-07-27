import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable as GlobalVariable

//====================================================================================
ReportFile = (GlobalVariable.G_ReportName + '.html')
def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)
def LogStatus = com.relevantcodes.extentreports.LogStatus
def extentTest = extent.startTest(TestCaseName)
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================


def filePathShellRun = RunConfiguration.getProjectDir() + '/Upload/JobFiles/Running.sh'
def newFPSHR = CustomKeywords.'generateFilePath.filePath.getFilePath'(filePathShellRun)
def filePathShellFail = RunConfiguration.getProjectDir() + '/Upload/JobFiles/bar.fem'
def newFPSHFail = CustomKeywords.'generateFilePath.filePath.getFilePath'(filePathShellFail)
def filePathOpti = RunConfiguration.getProjectDir() + '/Upload/JobFiles/box.fem'
def newFPOpti = CustomKeywords.'generateFilePath.filePath.getFilePath'(filePathOpti)

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
	def navLocation =CustomKeywords.'generateFilePath.filePath.execLocation'()
	def location=navLocation
	println('##################################################################')
	println (location)
	println('##################################################################')

	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))

	WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 10)
	WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))
	WebUI.click(findTestObject('FilesPage/ListItem_Folder'))
	WebUI.waitForElementVisible(findTestObject('FilesPage/TextBxFolder_input'), 5)
	WebUI.setText(findTestObject('FilesPage/TextBxFolder_input'), 'ForJM')
	WebUI.click(findTestObject('FilesPage/btn_Save'))
	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_Refresh'))

	WebUI.delay(2)

	location=location+'/ForJM'

	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))

	def jobid
	jobid =CustomKeywords.'preReq.jobSubmissionForPreReq.JSPreReq'(newFPSHR, AppName,extentTest)
	jobid =CustomKeywords.'preReq.jobSubmissionForPreReq.JSPreReq'(newFPSHFail, AppName,extentTest)
	jobid =CustomKeywords.'preReq.jobSubmissionForPreReq.JSPreReq'(newFPOpti, 'Optistruct',extentTest)
	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

	CustomKeywords.'preReq.jobMonitorigColFilter.addColumn'()

}
catch (Exception ex) {
    String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'
    WebUI.takeScreenshot(screenShotPath)
	String p = (TestCaseName + GlobalVariable.G_Browser) + '.png'
	extentTest.log(LogStatus.FAIL, ex)
	extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(p))
	} 
catch (StepErrorException e) {
    String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'
    WebUI.takeScreenshot(screenShotPath)
    extentTest.log(LogStatus.FAIL, e)
} 
finally { 
    extentTest.log(LogStatus.PASS, 'Closing the browser after executinge test case - '+ TestCaseName)
    extent.endTest(extentTest)
    extent.flush()
}
//=====================================================================================

