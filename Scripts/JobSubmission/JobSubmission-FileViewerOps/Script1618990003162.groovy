import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status
import com.aventstack.extentreports.MediaEntityBuilder

import internal.GlobalVariable as GlobalVariable


if(TestCaseName.contains('tile view'))
{
	println('tile view not supported in this module')
}
else
{

WebDriver driver = DriverFactory.getWebDriver()
EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()
RemoteWebDriver katalonWebDriver = (RemoteWebDriver) wrappedWebDriver
//RemoteWebDriver katalonWebDriver = ((wrappedWebDriver) as RemoteWebDriver)


TestCaseName='JobSubmission RFB - '+TestCaseName
if(userChoice.equals('DoubleClick'))
{
	TestCaseName=TestCaseName+' - open file by Double Clicking on it'

}
else
{
	TestCaseName=TestCaseName+' - open file through context menu open option'

}
//====================================================================================
def Browser = GlobalVariable.G_Browser
//====================================================================================
def extentTest=GlobalVariable.G_ExtentTest
//====================================================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//====================================================================================

def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()
def location = navLocation + '/FilesModule/FileOps/'
TestObject newFileObj

	newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'data-automation-id', 'equals', fileName, true)

try {

	def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'),
				20,extentTest,'Jobs Tab')

		if (jobsTab) {
			WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
		}

		extentTest.log(Status.PASS, 'Navigated Job Tab')
		WebUI.delay(2)

		TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'), 'id', 'equals',
				AppName, true)

		WebUI.click(newAppObj)
		extentTest.log(Status.PASS, 'Navigated to Job Submission For for - '+AppName)

		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/LoginPage/NewJobPage/GenericProfile'))
		WebUI.delay(2)


	if (TestCaseName.contains('Upload')) {
		println(TestOperation
				)
	}
	else
	{

	/*	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
		WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
		WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
		extentTest.log(Status.PASS, 'Navigated to '+location)*/
		
		CustomKeywords.'generateFilePath.filePath.navlocation'(location, extentTest)
		
		WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))
		WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 2)
		println(fileName)
		WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), fileName)
		extentTest.log(Status.PASS, 'Looking for file - '+fileName+' to perfrom operation - ' + TestOperation)
		WebUI.sendKeys(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), Keys.chord(Keys.ENTER))


		def fileItem = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newFileObj,20,extentTest,fileName)
		println(fileItem)

		if (fileItem) {
			WebUI.waitForElementPresent(newFileObj, 3)
			WebUI.click(newFileObj)
			extentTest.log(Status.PASS, 'Clicked on File  - ' + fileName)
			if(userChoice.equals('DoubleClick'))
			{

				WebUI.doubleClick(newFileObj)
				extentTest.log(Status.PASS, 'Double clicked on file to open it ')

			}
			else
			{
				WebUI.rightClick(newFileObj)
				extentTest.log(Status.PASS, 'RightClicked on File  - ' + fileName)
				WebUI.delay(2)
				WebUI.click(findTestObject('FilesPage/ContextMenu_FileOperation_Open'))
				extentTest.log(Status.PASS, 'Clicked on Open menu item ')
				println('after is else ' + TestOperation)
				WebUI.delay(3)

			}

		}
	}

	def result = CustomKeywords.'operations_FileModule.fileViewerOperations.executeFileOperations'(katalonWebDriver,TestOperation, TestCaseName,
			extentTest)


	if (result)
	{
		extentTest.log(Status.PASS, ('Verified - ' + TestCaseName) + '  Sucessfully')
	} else {
		extentTest.log(Status.FAIL, TestCaseName + ' - failed')
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

//=====================================================================================
}
