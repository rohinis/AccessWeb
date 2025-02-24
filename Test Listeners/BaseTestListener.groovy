import java.text.SimpleDateFormat

import org.junit.After
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver


import com.assertthat.selenium_shutterbug.utils.web.Browser
import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.aventstack.extentreports.ReactiveSubject

import internal.GlobalVariable as GlobalVariable

class BaseTestListener {
	ExtentReports extent
  def LocalDateTime startTime
   def LocalDateTime endTime
	def reportFolder=(RunConfiguration.getReportFolder()+'/')
	def ReportFile
	def filePath
	def reportFliePath
	String BrowserVer=GlobalVariable.G_BrowserVersion
	def totalTime
	String execID=null
	@BeforeTestSuite
	def BeforeTestSuite(TestSuiteContext testSuiteContext)
	{
		execID = RunConfiguration.getExecutionSourceName()
		startTime = LocalDateTime.now()
		// creating executionID and storing in String
		String execID = RunConfiguration.getExecutionSourceName()
		def Browser=GlobalVariable.G_Browser
		
		// creating filepath for reports
		filePath = (RunConfiguration.getProjectDir() + '/reports/'+execID+Browser+'.txt')
		reportFliePath=(RunConfiguration.getProjectDir() + '/ExtentReports/')
		GlobalVariable.G_TestSuite=execID
		GlobalVariable.G_ReportFolder=RunConfiguration.getReportFolder()
		GlobalVariable.G_ConfigFile=filePath
		def date = new Date()
		def sdf = new SimpleDateFormat("ddMMyyyy_HHmmss")
		def execTime = sdf.format(date)
		
		// creating Reportfile for extent reports which will unique for every suite
		String execTag=Browser+'_'+execTime
		ReportFile =execID+'_'+execTag+'.html'
		
		// Reusing extent from generate reports keyword
		extent = CustomKeywords.'generateReports.GenerateReport.createSpark'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion, totalTime)
		println ("From Brefore Suite")
		println(RunConfiguration.getReportFolder())
		
	}
	@BeforeTestCase
	def BeforeTestCase(TestCaseContext testCaseContext) {

	//	extent = CustomKeywords.'generateReports.GenerateReport.createSpark'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)
		
		//String tcName="{variable_1=v3, passwd=rohini, variable_2=v4, variable_0=v2, variable=v1, usename=rohini, TestCaseName=Login,variable_0=v2,}"
		
		println(execID)
		if(execID.equals(null))
		{


			def date = new Date()
			def sdf = new SimpleDateFormat("ddMMyyyy_HHmmss")
			def execTime = sdf.format(date)
			String execTag=Browser+'_'+execTime
			ReportFile =execTag+'.html'
			extent = CustomKeywords.'generateReports.GenerateReport.createSpark'(ReportFile, Browser, BrowserVer, totalTime)

			}

		String variableList=testCaseContext.getTestCaseVariables()
	
		println("context - "+variableList)
		println(testCaseContext.getTestCaseId())
		def tcID=testCaseContext.getTestCaseId()
		println("tcID"+ tcID)
		

		String [] tcScript=tcID.split('\\/')
		def len=tcScript.size()
		def tcScriptName=tcScript[len-1]
		println("==========================")
		println(tcScriptName)
		println("==========================")
		String testcaseName=CustomKeywords.'generateReports.testCaseNameChange.toSplit'(variableList,'TestCaseName')
		String UpdatedtestcaseName=CustomKeywords.'generateReports.testCaseNameChange.updateTCName'(testcaseName,variableList,tcScriptName)
		String XrayID=CustomKeywords.'generateReports.testCaseNameChange.toSplit'(variableList,'XrayID')
		println("==========================")
		println("XrayID : " + XrayID)
		println("==========================")
		println ("new tn  --- "+UpdatedtestcaseName)
		println("-------------------------")
		if(tcScriptName.equals('JobSubmission-FileOperations')&&UpdatedtestcaseName.contains('tile view'))
		{
			println('Skipping ')
			testCaseContext.skipThisTestCase()

		}
		else
		{
			//GlobalVariable.G_ExtentTest = extent.createTest(UpdatedtestcaseName)
			GlobalVariable.	G_ExtentTest= extent.createTest(UpdatedtestcaseName).assignAuthor(XrayID).assignCategory("Regression").assignDevice("Chrome")
			
			
			def extentTest=GlobalVariable.G_ExtentTest
			try {
				WebUI.openBrowser('')
				GlobalVariable.G_ExtentTest.log(Status.PASS, 'Navigated to Acces Instance - '+GlobalVariable.G_BaseUrl)
				WebUI.deleteAllCookies()
				WebUI.delay(2)
				WebUI.navigateToUrl(GlobalVariable.G_BaseUrl)
				WebUI.maximizeWindow()
				WebDriver driver = DriverFactory.getWebDriver()

			
				
				
			}
			catch (Exception ex)
			{
				String screenShotPath = ((reportFolder + tn) + GlobalVariable.G_Browser) + '.png'
				WebUI.takeScreenshot(screenShotPath)
				String p = (tn + GlobalVariable.G_Browser) + '.png'
				extentTest.log(Status.FAIL, ex)
				extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(p).build())
			}

		}

	}
	
	
	
	

		@AfterTestSuite
		def AfterTestSuite()
		{
		
		endTime = LocalDateTime.now();
		println ("*****************************************************************")
		Duration duration = Duration.between(startTime, endTime)
		long minutes = duration.toMinutes();
		long seconds = duration.minusMinutes(minutes).getSeconds();
		
		totalTime = String.format("%d minutes and %d seconds", minutes, seconds);
		System.out.println(totalTime);
		extent.setSystemInfo("Total Duration", totalTime);
		println("After Suite ")
		println ("*****************************************************************")
		println ("Report location - "+reportFliePath)
		extent.flush()
	}
	
	
	
	@AfterTestCase
	void AfterTestCase() {
		//extentTest.log(Status.PASS, 'Closing the browser after executinge test case - ' + TestCaseName)
		extent.flush()
		
	}
	

}