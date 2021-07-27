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

	println('Error message******' + title)

	extentTest.log(LogStatus.PASS, ('Verify the Title ' + title) + 'is present')

	//set the old password
	WebUI.setText(findTestObject('Object Repository/change_password/oldpasswd'), oldpasswd)

   
	WebUI.delay(1)

	WebUI.setText(findTestObject('Object Repository/change_password/newpasswd'),newpassword )

	WebUI.delay(1)

	WebUI.setText(findTestObject('Object Repository/change_password/confrmpasswd'), confirmpassword)
	WebUI.delay(1)
	
	if (oldpasswd == "blank" || newpassword == "blank" ) {
		WebUI.delay(1)
		WebUI.clearText(findTestObject('Object Repository/change_password/newpasswd'))
		WebUI.delay(1)
		WebUI.clearText(findTestObject('Object Repository/change_password/confrmpasswd'))
		
		}


	WebUI.delay(2)
	
	extentTest.log(LogStatus.PASS, 'Entered oldpassword::' + oldpasswd)
	
	extentTest.log(LogStatus.PASS, 'Entered new password::' + newpassword)
	extentTest.log(LogStatus.PASS, 'Entered Confirm password::' + confirmpassword)

	WebUI.click(findTestObject('Object Repository/change_password/button'))
	WebUI.delay(1)

	extentTest.log(LogStatus.PASS, 'Clicked on the change button')


  
		 //verify the error message
			TestObject error_msg1 = WebUI.modifyObjectProperty(findTestObject('Object Repository/Notificactions/Notification_Item'),
				'text', 'equals', message, true)

			TestObject error_msg2 = WebUI.modifyObjectProperty(findTestObject('Object Repository/Notificactions/Notification_Item'),
				'text', 'equals', msg2, true)
	

		  String errormesage = WebUI.getText(error_msg1)

			extentTest.log(LogStatus.PASS, 'Error message 1:: ' + message)

			extentTest.log(LogStatus.PASS, 'Error messages 2::   ' + msg2)
	
			


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

