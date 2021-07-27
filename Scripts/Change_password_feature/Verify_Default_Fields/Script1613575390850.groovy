import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.relevantcodes.extentreports.LogStatus as LogStatus
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.exception.StepErrorException as StepErrorException

def Browser = GlobalVariable.G_Browser
ReportFile = (GlobalVariable.G_ReportName + '.html')
def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)
def LogStatus = com.relevantcodes.extentreports.LogStatus
def extentTest = extent.startTest(TestCaseName)
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
try {

	WebUI.delay(1)

	extentTest.log(LogStatus.PASS, 'Navigated to url - ' + GlobalVariable.G_BaseUrl)

	//click on the user menu
	WebUI.click(findTestObject('Object Repository/change_password/unityuser'))

	//click on the change passwd option
	WebUI.click(findTestObject('Object Repository/change_password/changepass_menu'))

	extentTest.log(LogStatus.PASS, 'Clicked on the change password icon  ')

	//verify the title
	println(WebUI.verifyElementPresent(findTestObject('Object Repository/change_password/title'), 20))

	String title = WebUI.getText(findTestObject('Object Repository/change_password/title'))

	println('Title******' + title)

	extentTest.log(LogStatus.PASS, ('Verify the Title ' + title) + ' is present')
	
//Verify Default Fields
	def oldpwd= WebUI.modifyObjectProperty(findTestObject('Object Repository/change_password/placeholders') ,'placeholder', 'equals', message, true)
	def newpwd= WebUI.modifyObjectProperty(findTestObject('Object Repository/change_password/placeholders') ,'placeholder', 'equals', msg2, true)
	def confmpwd= WebUI.modifyObjectProperty(findTestObject('Object Repository/change_password/placeholders') ,'placeholder', 'equals', msg3, true)
	
	println(WebUI.verifyElementPresent(oldpwd, 20))
	println(WebUI.verifyElementPresent(newpwd, 20))
	
	println(WebUI.verifyElementPresent(confmpwd, 20))
	extentTest.log(LogStatus.PASS, 'Verifying the Field::  '+ message + ' is present ')
	extentTest.log(LogStatus.PASS, 'Verifying the Field::  '+ msg2 + ' is present ')
	extentTest.log(LogStatus.PASS, 'Verifying the Field::  '+ msg3 + ' is present ')
	WebUI.closeBrowser()
	
	
}
catch (Exception ex) {
	String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

	WebUI.takeScreenshot(screenShotPath)

	String p = (TestCaseName + GlobalVariable.G_Browser) + '.png'

	extentTest.log(LogStatus.FAIL, ex)

	extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(p))

	KeywordUtil.markFailed('ERROR: ' + ex)
}
catch (StepErrorException e) {
	String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

	WebUI.takeScreenshot(screenShotPath)

	String p = (TestCaseName + GlobalVariable.G_Browser) + '.png'

	extentTest.log(LogStatus.FAIL, ex)

	extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(p))

	KeywordUtil.markFailed('ERROR: ' + e)
}
finally {
	extent.endTest(extentTest)

	extent.flush()
}



