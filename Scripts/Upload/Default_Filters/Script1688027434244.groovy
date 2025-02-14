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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration

import org.openqa.selenium.Keys as Keys

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
	WebUI.delay(2)
	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	extentTest.log(Status.PASS, 'Click on Jobs tab')
	WebUI.delay(2)
	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))
	extentTest.log(Status.PASS, 'Click on  All My Jobs')
	
	//extentTest.log(Status.PASS," Jobs filter label should display as bold ")
	
	//WebUI.verifyElementPresent(findTestObject('Object Repository/JobMonitoringPage/Job_Filters/Jobs_Header'),5)
	extentTest.log(Status.PASS,"My Jobs radio button should be checked by default.")
	
	WebUI.verifyElementHasAttribute(findTestObject('Object Repository/JobMonitoringPage/Job_Filters/my_jobs_radio_button'),"checked", 10)
	
	//extentTest.log(Status.PASS,"Verify the Time filter label should display as bold")
	//WebUI.verifyElementPresent(findTestObject('Object Repository/JobMonitoringPage/Job_Filters/Time_header'),5)

	extentTest.log(Status.PASS,"The 'Last 7 days' radio button  should be checked by default.")
	//extentTest.log(Status.PASS,"The All Status Filter should be selected by default")
	def res1=WebUI.verifyElementChecked(findTestObject('Object Repository/JobMonitoringPage/Job_Filters/Status_Filter_All_checkbox'),4)
	def res2=WebUI.verifyElementChecked(findTestObject('Object Repository/JobMonitoringPage/Job_Filters/Applications_All_Filter'),4)
	def res3=WebUI.verifyElementChecked(findTestObject('Object Repository/JobMonitoringPage/Job_Filters/Array_Job_Filter'),4)
	def res4=WebUI.verifyElementChecked(findTestObject('Object Repository/JobMonitoringPage/Job_Filters/Batch_job_Filter'),4)
	def res5=WebUI.verifyElementChecked(findTestObject('Object Repository/JobMonitoringPage/Job_Filters/Queue_All_Filter'),4)
	def res6=WebUI.verifyElementChecked(findTestObject('Object Repository/JobMonitoringPage/Job_Filters/Server_All_Filter'),4)
	if(res1 && res2 && res3 && res4 && res6) {
		extentTest.log(Status.PASS,"  when user clicks on'All my Jobs' filter default options are selected. "+
			"Status- ALL, Servers - All, Applications - All, Queues - All, Type -  Batch & Array")
		
	}
	println(res6)
	WebUI.verifyElementHasAttribute(findTestObject('Object Repository/JobMonitoringPage/Job_Filters/Time_Filter_last7days'),'checked', 10)
		
	
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
