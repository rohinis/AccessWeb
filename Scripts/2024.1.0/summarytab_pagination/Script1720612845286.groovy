import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.concurrent.ConcurrentHashMap.KeySetView

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
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.exception.StepFailedException as StepFailedException


//==================================================================
def Browser = GlobalVariable.G_Browser
//===============================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)

//=====================================================================================
def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()
def location = navLocation + '/FilesModule/FileOps/'
//=====================================================================================


boolean result=false


try {
		def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'),
		20,extentTest,'App def')

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}
	
	
	WebUI.delay(2)
	
	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))

	//TestObject newJobFilter = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/label_jobState'), 'text', 'equals',
			//jobState, true)

	//WebUI.click(newJobFilter)

	WebUI.delay(2)

	extentTest.log(Status.PASS, 'Clicked on job with Name  - ' + jobname)

	
	
	
		TestObject newJobRow = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/Name'), 'text', 'contains',jobname, true)
		WebUI.delay(2)
			WebUI.rightClick(newJobRow)
			extentTest.log(Status.PASS, 'Clicked on the  job ')
			WebUI.waitForElementVisible(findTestObject('Object Repository/JobMonitoringPage/ViewDetails_Jobs'), 10)
			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/ViewDetails_Jobs'))
			
			extentTest.log(Status.PASS, 'Clicked on the  Summary Drop down option ')
			
			extentTest.log(Status.PASS, 'Select the 25 MB file from the drop down to verify the pagination ')
			WebUI.waitForElementVisible(findTestObject('Object Repository/JobMonitoringPage/Monitor_dropdown/drop_down_list'), 10)
			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/Monitor_dropdown/drop_down_list'))
			def dropdownlist=WebUI.getText(findTestObject('Object Repository/JobMonitoringPage/Monitor_dropdown/Monitor_Drop_down'))
			
			println("dropdownlist   "+dropdownlist )
			extentTest.log(Status.PASS, 'Verified all the files are listed')
			WebUI.delay(4)
			
			//WebUI.click(findTestObject('Object Repository/JobMonitoringPage/Monitor_dropdown/textfile'))
			//	WebUI.sendKeys(findTestObject('Object Repository/JobMonitoringPage/Monitor_dropdown/drop_down_input'), Keys.chord(Keys.ARROW_DOWN))
				//WebUI.sendKeys(findTestObject('Object Repository/JobMonitoringPage/Monitor_dropdown/textfile'), Keys.chord(Keys.ARROW_DOWN))
				//WebUI.sendKeys(findTestObject('Object Repository/JobMonitoringPage/Monitor_dropdown/textfile'), Keys.chord(Keys.ARROW_DOWN))
	
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

	








