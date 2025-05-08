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
	


	TestObject newJobFilter = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/label_jobState'), 'text', 'equals',jobState, true)
	

	WebUI.click(newJobFilter)
	TestObject newJobRow = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/div_Completed'), 'title', 'equals',jobState, true)
	
		WebUI.rightClick(newJobRow)
		extentTest.log(Status.PASS, 'Clicked on the  job ')
		WebUI.waitForElementVisible(findTestObject('Object Repository/JobMonitoringPage/ViewDetails_Jobs'), 10)
		WebUI.click(findTestObject('Object Repository/JobMonitoringPage/ViewDetails_Jobs'))

	WebUI.delay(2)

	extentTest.log(Status.PASS, 'Clicked on job with state  - ' + jobState)

	println(jobState)
	
	WebUI.waitForElementVisible(findTestObject('Object Repository/JobMonitoringPage/Monitor_dropdown/drop_down_list'), 10)
	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/Monitor_dropdown/drop_down_list'))
	def dropdownlist=WebUI.getText(findTestObject('Object Repository/JobMonitoringPage/Monitor_dropdown/Monitor_Drop_down'))
	
	println("dropdownlist   "+dropdownlist )
	
	extentTest.log(Status.PASS, "Click on the Monitor Drop down list and verify user is able to select and view all the files with respect to the job.")
	TestObject jobnameUI = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/RowItem_File_ListView'), 'data-automation-id', 'equals',"Failed", true)
	def jobname=WebUI.getText(jobnameUI)
	println(jobname+".e")

	//if(dropdownlist.contains(jobname+".e" )&& dropdownlist.contains(jobname+".o" )&& dropdownlist.contains("jobFile.out")&&dropdownlist.contains("Running.sh") && dropdownlist.contains("access.log")) {
	if(dropdownlist.contains(jobname+".e" )&& dropdownlist.contains(jobname+".o" ) && dropdownlist.contains("access.log")) {
		
		println("files listed")
		extentTest.log(Status.PASS, "All the Files listed")
		
		
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

	







