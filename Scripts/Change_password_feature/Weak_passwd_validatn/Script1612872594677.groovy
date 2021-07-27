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
import com.relevantcodes.extentreports.LogStatus

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.exception.StepErrorException

def Browser = GlobalVariable.G_Browser
ReportFile = (GlobalVariable.G_ReportName + '.html')
def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)
def LogStatus = com.relevantcodes.extentreports.LogStatus
def extentTest = extent.startTest(TestCaseName)
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
try {

WebUI.delay(1)
extentTest.log(LogStatus.PASS, 'Navigated to url - ' +GlobalVariable.G_BaseUrl)


//click on the user menu

WebUI.click(findTestObject('Object Repository/change_password/unityuser'))
//click on the change passwd option
WebUI.click(findTestObject('Object Repository/change_password/changepass_menu'))
extentTest.log(LogStatus.PASS, 'Clicked on the change password icon  ')

//verify the title
println(WebUI.verifyElementPresent(findTestObject('Object Repository/change_password/title'), 20))
String title=WebUI.getText(findTestObject('Object Repository/change_password/title'));
println("Error message******"+title)
extentTest.log(LogStatus.PASS, 'Verify the Title '+title + 'is present')

//set the old password

WebUI.setText(findTestObject('Object Repository/change_password/oldpasswd'), oldpasswd)
extentTest.log(LogStatus.PASS, 'Entered oldpassword::'+oldpasswd)


WebUI.setText(findTestObject('Object Repository/change_password/newpasswd'), newpassword)
extentTest.log(LogStatus.PASS, 'Entered new password::'+newpassword)

WebUI.setText(findTestObject('Object Repository/change_password/confrmpasswd'), confirmpassword)
extentTest.log(LogStatus.PASS, 'Entered new password::'+confirmpassword)

WebUI.click(findTestObject('Object Repository/change_password/button'))
WebUI.delay(10)

//click on the notification

WebUI.click(findTestObject('Object Repository/Notificactions/Notification'))
extentTest.log(LogStatus.PASS, 'Clicked on the Notification tab')
//verify the error message 
TestObject error_msg = WebUI.modifyObjectProperty(findTestObject('Object Repository/Notificactions/Notificationlistitem'), 'data-tip', 'contains', message, true)
println(WebUI.verifyElementPresent(error_msg, 4))
String errormesage=WebUI.getText(error_msg);

println("Error message******"+errormesage)
extentTest.log(LogStatus.PASS, 'Error message:: '+ message)

}
catch(Exception ex){
	String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

	  WebUI.takeScreenshot(screenShotPath)

	  String p =TestCaseName+GlobalVariable.G_Browser+'.png'
	  extentTest.log(LogStatus.FAIL,ex)
	  extentTest.log(LogStatus.FAIL,extentTest.addScreenCapture(p))


	 KeywordUtil.markFailed('ERROR: ' + ex)
}
catch (StepErrorException e) {
String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

WebUI.takeScreenshot(screenShotPath)

String p =TestCaseName+GlobalVariable.G_Browser+'.png'
extentTest.log(LogStatus.FAIL,ex)
extentTest.log(LogStatus.FAIL,extentTest.addScreenCapture(p))


KeywordUtil.markFailed('ERROR: ' + e)
}

finally {
extent.endTest(extentTest)

extent.flush()
}


