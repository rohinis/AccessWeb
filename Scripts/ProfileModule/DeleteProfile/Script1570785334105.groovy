import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable as GlobalVariable


//====================================================================================
ReportFile = (GlobalVariable.G_ReportName + '.html')
def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)
def LogStatus = com.relevantcodes.extentreports.LogStatus
TestCaseName='Test Case to delete profile - '+proName
def extentTest = extent.startTest(TestCaseName)
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================
def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()
def location = navLocation + '/FilesModule/FileOps/'
//=====================================================================================


WebUI.delay(2)
try
{

	def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('NewJobPage/AppList_ShellScript'),
			20,extentTest,'App def')

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}

	WebUI.delay(2)

	TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'equals', AppName, true)

	WebUI.click(newAppObj)
	extentTest.log(LogStatus.PASS, 'Navigated to Job Submission form for - '+AppName )

	WebUI.delay(2)

	TestObject LeftNavAppIdentifier = CustomKeywords.'buildTestObj.CreateTestObjJobs.myLeftNavAppIdentifier'(proName)
	WebUI.waitForElementPresent(LeftNavAppIdentifier, 5)
	WebUI.click(LeftNavAppIdentifier)

	WebUI.delay(2)

	WebUI.rightClick(LeftNavAppIdentifier)
	def deleteOption = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/ProfileOptions/ContextMenu_Item_Delete'),5,extentTest,'DeleteOption')
	extentTest.log(LogStatus.PASS, 'Right Cicked on Profile -  '+proName )

	if (deleteOption) {
		extentTest.log(LogStatus.PASS, 'Test to verify delete option exists - Pass ')
		
		
		
		WebUI.mouseOver(findTestObject('Object Repository/ProfileOptions/ContextMenu_Item_Delete'))
		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/ProfileOptions/ContextMenu_Item_Delete'))
		extentTest.log(LogStatus.PASS, 'Clicked on Delete ')
		WebUI.delay(3)
		WebUI.click(findTestObject('GenericObjects/btn_Yes'))
	}

	def result = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(LeftNavAppIdentifier,5,extentTest,'deletedProfile')
	if (result)
	{
		extentTest.log(LogStatus.FAIL,'Profile not deleted')
		extentTest.log(LogStatus.FAIL, ( TestCaseName) + ' :: failed')

	}
	else {
		extentTest.log(LogStatus.PASS, 'Deleted Profile - '+proName )
		extentTest.log(LogStatus.PASS, ('Verified ::  ' + TestCaseName) + ' :: Sucessfully')

	}

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

