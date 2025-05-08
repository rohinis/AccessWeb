import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
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
//=============================================================



//WebUI.delay(2)
TestObject newFileObj

//=====================================================================================
def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()
def location = navLocation+'/UnZipOps'
//=====================================================================================
WebUI.enableSmartWait()
try {
    def filesTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'), 
        20, extentTest, 'App def')

    if (filesTab) {
        WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
    }
    extentTest.log(Status.PASS, 'Naviagted to Files Tab')

    CustomKeywords.'operations_FileModule.ChangeView.changePageView'(TestCaseName, extentTest)
   // WebUI.delay(2)

/*	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
    WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
    WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
    extentTest.log(Status.PASS, 'Navigated to - ' + location)
    WebUI.delay(2)*/
	CustomKeywords.'generateFilePath.filePath.navlocation'(location, extentTest)
	
    def filePath = (RunConfiguration.getProjectDir() + '/Upload/') + fileToUpload
    def newFP = CustomKeywords.'generateFilePath.filePath.getFilePath'(filePath)

    WebUI.uploadFile(findTestObject('FilesPage/UploadFileBtn'), newFP)
    extentTest.log(Status.PASS, 'Uploading zip file - '+fileToUpload)

		def result=CustomKeywords.'operations_FileModule.checkUnZip.performUnzip'(TestCaseName, extentTest, userChoice)
		
	if(result){
    extentTest.log(Status.PASS, 'Verified test case - '+TestCaseName)
	}
	else
	{
    extentTest.log(Status.FAIL, 'Test case - '+TestCaseName+' failed')
	}
	WebUI.disableSmartWait()
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