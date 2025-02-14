import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
//import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver

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
//EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)
//WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()
//RemoteWebDriver katalonWebDriver = (RemoteWebDriver) wrappedWebDriver

RemoteWebDriver katalonWebDriver = (RemoteWebDriver) driver
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
	
	/*def isFilterPresent= CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/JobMonitoringPage/icon_removeFilter'),5,extentTest,'RemoveFilterIcon')
	if(isFilterPresent)
	{
		WebUI.mouseOver(findTestObject('Object Repository/JobMonitoringPage/icon_removeFilter'))
		WebUI.click(findTestObject('Object Repository/JobMonitoringPage/icon_removeFilter'))
		extentTest.log(Status.PASS, 'Clicked on filter delete icon' )
		WebUI.refresh()
	}*/
    WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))

	
    TestObject newJobFilterCategoryDown = CustomKeywords.'buildTestObj.CreateTestObjJobs.myTestObjFilterCategoryIdentifierDown'(
        FilterCategory)

    TestObject newJobFilterCategoryRight = CustomKeywords.'buildTestObj.CreateTestObjJobs.myTestObjFilterCategoryIdentifierRight'(
        FilterCategory)

    TestObject newJobFilterTitle = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/Title_FilterCategory'), 
        'text', 'equals', FilterTitle, true)

    TestObject newJobFilterValue = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/Title_FilterCategory'), 
        'text', 'equals', FilterValue, true)

    isElementPresentDown = WebUI.waitForElementPresent(newJobFilterCategoryDown, 3, FailureHandling.CONTINUE_ON_FAILURE)

    isElementPresentRight = WebUI.waitForElementPresent(newJobFilterCategoryRight, 3, FailureHandling.CONTINUE_ON_FAILURE)

    println('**************************')

    println(isElementPresentDown)

    println(isElementPresentRight)

    println('**************************')

    WebUI.delay(4)
	WebUI.scrollToElement(newJobFilterTitle, 3)

    if (isElementPresentDown) {
        println('down')

        WebUI.click(newJobFilterValue)
		extentTest.log(Status.PASS,'Verification for AD-1418 - Expand and Collapse the left navigation filters')
		
    }
    
    if (isElementPresentRight) {
        println('right')

        WebUI.click(newJobFilterTitle)

        extentTest.log(Status.PASS, 'Selected filter category ' + FilterTitle)
		extentTest.log(Status.PASS,'Verification for AD-1418 - Expand and Collapse the left navigation filters')
		
        WebUI.delay(2)

        WebUI.click(newJobFilterValue)

        extentTest.log(Status.PASS, 'Selected filter value ' + FilterValue)
		
		extentTest.log(Status.PASS,'Verification for AD-1419 - Check an Un-check the left navigation filters')
    }
    
	println("AllJobsUser -- "+AllJobsUser)
    if (TestCaseName.contains('AllJobs')) {
		
		WebUI.click(findTestObject('Object Repository/JobMonitoringPage/RadioBtn_AllJobs'))
		WebUI.delay(1)
		result =CustomKeywords.'operations_JobsModule.GetJobRowDetails.getJobByUser'(katalonWebDriver, dataAttribute, FilterValue, 
            extentTest, AllJobsUser)
		
       
    } 
	if (TestCaseName.contains('MyJobs')) {
		
		WebUI.click(findTestObject('Object Repository/JobMonitoringPage/RadioBtn_MyJobs'))
		WebUI.delay(1)
		result =CustomKeywords.'operations_JobsModule.GetJobRowDetails.getJobByUser'(katalonWebDriver, dataAttribute, FilterValue,
			extentTest, AllJobsUser)
		
	   
	}
	else {
        result = CustomKeywords.'operations_JobsModule.GetJobRowDetails.newGrid'(katalonWebDriver, dataAttribute, FilterValue, 
            extentTest)
    }
    
    println(('==================' + result) + '==========================')

    if (result) {
        extentTest.log(Status.FAIL, 'Job Not Filtered for ' + FilterValue)
    } else {
        extentTest.log(Status.PASS, ('Job  Filtered for ' + FilterValue) + ' Jobs')
    }
    
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