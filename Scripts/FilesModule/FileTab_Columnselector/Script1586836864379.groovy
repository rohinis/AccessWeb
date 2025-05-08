import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
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


//WebUI.delay(2)
WebUI.enableSmartWait()
try {
	def filesTab =CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'),
			20,extentTest,'App def')
	if (filesTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
	}

	extentTest.log(Status.PASS, 'Navigated to Files Tab')

	//WebUI.delay(2)

	CustomKeywords.'operations_FileModule.ChangeView.changePageView'(TestCaseName, extentTest)

	CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('FilesPage/Column_selector'),20,extentTest,'GrearIcon')
	WebUI.click(findTestObject('FilesPage/Column_selector'))


	WebUI.waitForElementPresent(findTestObject('Object Repository/FilesPage/Column_filter'), 5)

	WebUI.setText(findTestObject('Object Repository/FilesPage/Column_filter'), ColName)

	extentTest.log(Status.PASS, 'Searched column to be added/removed - ' + ColName)

	//WebUI.delay(3)

	TestObject filterCB = WebUI.modifyObjectProperty(findTestObject('Object Repository/JobMonitoringPage/JM_FilterCheckBox'),
			'id', 'equals', ColCheckBx, true)

	TestObject filterLabel = WebUI.modifyObjectProperty(findTestObject('Object Repository/JobMonitoringPage/JM_FilterLable'),
			'id', 'equals', ColLable, true)

	def isElementChecked = WebUI.verifyElementChecked(filterCB, 5, FailureHandling.CONTINUE_ON_FAILURE)


	println(isElementChecked)

	//extentTest.log(Status.INFO, 'isElementChecked - ' + isElementChecked)
	//result=CustomKeywords.'operations_JobsModule.GetJobRowDetails.newCol'(katalonWebDriver, dataAttribute,ColName,extentTest)


	switch (userChoice) {
		case 'add':

			if (isElementChecked)
			{
				println('Boxed checked')
				WebUI.click(filterLabel)
				WebUI.click(filterLabel)
				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Apply'))
				extentTest.log(Status.PASS, 'Column already selected')
				
			//	WebUI.delay(2)
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

		//extentTest.log(Status.INFO, 'result value - ' + result)
			if(result)
			{
				extentTest.log(Status.PASS, 'Column - ' + ColName+' added in files table')
				extentTest.log(Status.PASS, ('Verified :: ' + TestCaseName) + ' :: Sucessfully')
			}
			else{
				extentTest.log(Status.FAIL, 'Column - ' + ColName+' not added in files table')
				extentTest.log(Status.FAIL,  TestCaseName + ' :: failed')

			}
			break
		case 'remove':
			isElementChecked = WebUI.verifyElementChecked(filterCB, 5, FailureHandling.CONTINUE_ON_FAILURE)

			if (isElementChecked) {
				WebUI.click(filterLabel)
				extentTest.log(Status.PASS, 'Checked the checkbox to uncheck the column')
				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Apply'))
				extentTest.log(Status.PASS, 'Clicked on Apply button')
			}
			else
			{
				println ("col is not added")
			}
			result=CustomKeywords.'operations_JobsModule.GetJobRowDetails.newCol'(katalonWebDriver, dataAttribute,ColName,
					extentTest)
			//extentTest.log(Status.INFO, 'result value - ' + result)
			if(result)
			{
				extentTest.log(Status.FAIL, 'Column - ' + ColName+' not removed from files table')
				extentTest.log(Status.FAIL,  TestCaseName + ' :: failed')
			}
			else{
				extentTest.log(Status.PASS, 'Column - ' + ColName+' removed from files table')
				extentTest.log(Status.PASS, ('Verified ::  ' + TestCaseName) + ' ::  Sucessfully')
			}
			break

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
