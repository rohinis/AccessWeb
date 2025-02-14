import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration

import internal.GlobalVariable as GlobalVariable
//==================================================================
def Browser = GlobalVariable.G_Browser
//===============================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=============================================================

WebUI.delay(2)
try
{
	'Navigate to Files Tab\r\n'
	
	/*
	
	def isElemenetPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Files'),5)
	
		if (isElemenetPresent)
		{
			WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
			extentTest.log(Status.PASS, "Navigated to Files Tab" )
		}
	*/

	WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
	extentTest.log(Status.PASS, 'Click on File tab')
	WebUI.delay(2)
	WebUI.waitForElementVisible(findTestObject('2020.1/Upload_File'), 5)

	'Click Upload File Button '
	
	def filePath = (RunConfiguration.getProjectDir() + '/Upload/new3.zip')
				def newFP=(new generateFilePath.filePath()).getFilePath(filePath)
				println(newFP)
				WebUI.uploadFile(findTestObject('FilesPage/UploadFileBtn'), newFP )
				
				extentTest.log(Status.PASS, 'Upload zip file')
	
	
    WebUI.verifyElementPresent(findTestObject('2020.1/Verify_unzip_message'), 3)
	extentTest.log(Status.PASS, 'Verify unzip message')
	

	

	if (GlobalVariable.G_Browser == 'IE') {
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