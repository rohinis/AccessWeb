import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status

import internal.GlobalVariable as GlobalVariable

//====================================================================================
WebDriver driver = DriverFactory.getWebDriver()
EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()
RemoteWebDriver katalonWebDriver = (RemoteWebDriver) wrappedWebDriver
//==================================================================
def Browser = GlobalVariable.G_Browser
//===============================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=============================================================


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

	extentTest.log(Status.PASS, 'Navigated to Jobs Page')

	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/JM_column_selector_icon'))

	extentTest.log(Status.PASS, 'Clicked on Gear Icon ')

	WebUI.waitForElementPresent(findTestObject('Object Repository/JobMonitoringPage/JM_Job_ColumnFilter'), 5)

	WebUI.setText(findTestObject('Object Repository/JobMonitoringPage/JM_Job_ColumnFilter'), ColName)

	extentTest.log(Status.PASS, 'Searched column to be added/removed - ' + ColName)

	WebUI.delay(3)

	TestObject filterCB = WebUI.modifyObjectProperty(findTestObject('Object Repository/JobMonitoringPage/JM_FilterCheckBox'),
			'id', 'equals', ColCheckBx, true)

	TestObject filterLabel = WebUI.modifyObjectProperty(findTestObject('Object Repository/JobMonitoringPage/JM_FilterLable'),
			'id', 'equals', ColLable, true)

	def isElementChecked = WebUI.verifyElementChecked(filterCB, 5, FailureHandling.CONTINUE_ON_FAILURE)


	println(isElementChecked)

	extentTest.log(Status.INFO, 'isElementChecked - ' + isElementChecked)
	result=CustomKeywords.'operations_JobsModule.GetJobRowDetails.newCol'(katalonWebDriver, dataAttribute,ColName,extentTest)
	

	switch (userChoice) {
		case 'add':

			if (isElementChecked)
			 {
				println('Boxed checked')
				WebUI.click(filterLabel)
				WebUI.click(filterLabel)
				/*extentTest.log(Status.INFO, 'check 1')

				WebUI.click(filterLabel)
				extentTest.log(Status.INFO, 'check 2')
*/

				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Apply'))
				extentTest.log(Status.PASS, 'col already selected')
				result=CustomKeywords.'operations_JobsModule.GetJobRowDetails.newCol'(katalonWebDriver, dataAttribute,ColName,extentTest)
				
			}
			else {
				println('in else block ')
				WebUI.click(filterLabel)
				extentTest.log(Status.PASS, 'Checked the checkbox to select the column')
				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Apply'))
				extentTest.log(Status.PASS, 'Clicked on Apply button')
				result=CustomKeywords.'operations_JobsModule.GetJobRowDetails.newCol'(katalonWebDriver, dataAttribute,ColName,extentTest)
				
				}

			extentTest.log(Status.INFO, 'result value - ' + result)
			if(result)
			{
				extentTest.log(Status.PASS, 'Jobs col added - ' + ColName)
			}
			else{
				extentTest.log(Status.FAIL, 'Jobs col not added - ' + ColName)

			}
			break
		case 'remove':
			isElementChecked = WebUI.verifyElementChecked(filterCB, 5, FailureHandling.CONTINUE_ON_FAILURE)

			if (isElementChecked) {
				WebUI.click(filterLabel)

				extentTest.log(Status.PASS, 'Checked the checkbox to select the column')

				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Apply'))

				extentTest.log(Status.PASS, 'Clicked on Apply button')
			}
			else
			{
				println ("col is not added")
			}
			result=CustomKeywords.'operations_JobsModule.GetJobRowDetails.newCol'(katalonWebDriver, dataAttribute,ColName,
					extentTest)
			extentTest.log(Status.INFO, 'result value - ' + result)
			if(result)
			{
				extentTest.log(Status.FAIL, 'Jobs col not removed - ' + ColName)
			}
			else{
				extentTest.log(Status.PASS, 'Jobs col removed - ' + ColName)

			}
			break

	}

	WebUI.delay(1)

	if (GlobalVariable.G_Browser == 'Edge') {
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