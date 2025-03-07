import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
//import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status

import internal.GlobalVariable as GlobalVariable

//====================================================================================
WebDriver driver = DriverFactory.getWebDriver()
//EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)
//WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()
//RemoteWebDriver katalonWebDriver = (RemoteWebDriver) wrappedWebDriver

RemoteWebDriver katalonWebDriver = (RemoteWebDriver) driver
//====================================================================================
//==================================================================
def Browser = GlobalVariable.G_Browser
//===============================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================


def result=false

def isElementPresentRight=false

def isElementPresentDown=false

WebUI.delay(2)

try {
		def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'),
		20,extentTest,'App def')

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}	
	
	WebUI.delay(2)

	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))

	
	
	/*def isFilterPresent= CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/JobMonitoringPage/icon_removeFilter'),20,extentTest,'REmoveFilterIcon')
	if(isFilterPresent)
	{
		WebUI.click(findTestObject('Object Repository/JobMonitoringPage/icon_removeFilter'))
		extentTest.log(Status.PASS, 'Clicked on filter delete icon' )
		WebUI.refresh()
	}*/
	
	TestObject newJobFilterCategoryDown = CustomKeywords.'buildTestObj.CreateTestObjJobs.myTestObjFilterCategoryIdentifierDown'(FilterCategory)

	TestObject newJobFilterCategoryRight = CustomKeywords.'buildTestObj.CreateTestObjJobs.myTestObjFilterCategoryIdentifierRight'(FilterCategory)

	TestObject newJobFilterCategory = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/Title_FilterCategory'),'text', 'equals', FilterTitle, true)

	TestObject newJobFilterValue = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/Title_FilterCategory'),'text', 'equals', FilterValue, true)

	isElementPresentDown = WebUI.waitForElementPresent(newJobFilterCategoryDown, 3, FailureHandling.CONTINUE_ON_FAILURE)

	isElementPresentRight = WebUI.waitForElementPresent(newJobFilterCategoryRight, 3, FailureHandling.CONTINUE_ON_FAILURE)

	println('**************************')

	println(isElementPresentDown)

	println(isElementPresentRight)

	println('**************************')

	WebUI.delay(4)

	if (isElementPresentDown) {
		println('down')

		WebUI.click(newJobFilterValue)
	}

	if (isElementPresentRight) {
		println('right')

		WebUI.click(newJobFilterCategory)

		extentTest.log(Status.PASS, 'Selected filter category ' + FilterTitle)

		WebUI.delay(2)

		WebUI.click(newJobFilterValue)

		extentTest.log(Status.PASS, 'Selected filter value ' + FilterValue)
	}

	extentTest.log(Status.PASS, 'Applied filter for - Filter Category - '+FilterCategory + 'Filter Value - '+ FilterValue)

	for (int i=0;i<3;i++)
	{
		def NewFilterName=FilterName+"_"+i
		println(NewFilterName)
		WebUI.click(findTestObject('Object Repository/JobMonitoringPage/Icon_save_filter'))
		WebUI.delay(1)
		WebUI.setText(findTestObject('Object Repository/JobMonitoringPage/textBx_SaveFilter'), NewFilterName)
		WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Save'))
		extentTest.log(Status.PASS, 'Saved new filter with name - '+ NewFilterName)
		TestObject newFilterItem=WebUI.modifyObjectProperty(findTestObject('Object Repository/JobMonitoringPage/newFilter_Item'),'text', 'equals', NewFilterName, true)
		//result=WebUI.verifyElementPresent(newFilterItem, 5, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.click(newFilterItem)
		extentTest.log(Status.PASS, 'Verified new filetr in under filter section' )
		println(i)
	}
	println("for done")

	result=WebUI.verifyElementPresent(findTestObject('Object Repository/JobMonitoringPage/Icon_save_filter_disabled'),5, FailureHandling.CONTINUE_ON_FAILURE)
	println("====================================================")
	println(result)
	println("====================================================")

	if(result)
	{
		extentTest.log(Status.PASS, 'Save filter icon disabled' )

	}
	else
	{
		extentTest.log(Status.FAIL, 'Save filter icon is not disabled' )

	}
	
	for (int i=0;i<3;i++)
		{
			def NewFilterName=FilterName+"_"+i
			println(NewFilterName)
			TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('Object Repository/JobMonitoringPage/Filter_name'), 'data-automation-id', 'equals', NewFilterName, true)
			WebUI.mouseOver(newAppObj )
			WebUI.delay(3)
			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/icon_removeFilter'))
			extentTest.log(Status.PASS, 'Clicked on filter delete icon' )
			WebUI.waitForElementVisible(findTestObject('Object Repository/JobMonitoringPage/button_Yes'), 5)
			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Yes'))
			if(GlobalVariable.G_Browser=="Firefox") {
			WebUI.refresh()}
		}

	if (GlobalVariable.G_Browser == 'Edge') {
		WebUI.callTestCase(findTestCase('XRepeated_TC/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}
}
catch (Exception ex) {
	String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'

	WebUI.takeScreenshot(screenShotPath)

	extentTest.log(Status.FAIL, ex)

	KeywordUtil.markFailed('ERROR: ' + e)
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

