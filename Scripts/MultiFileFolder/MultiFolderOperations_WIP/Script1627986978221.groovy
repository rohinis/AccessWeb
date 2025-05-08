import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status


import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint


def result=false

try {
	WebUI.delay(2)

	def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'),
			20, extentTest, 'App def')

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

		extentTest.log(Status.PASS, 'Navigated to Jobs Tab')
	}

	TestObject newFolderObj=null
	println(Type)
	switch (Type) {
		case 'Folders':
			newFolderObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_ListView'), 'data-automation-id', 'equals','MultipleFolderJS', true)

			break
		case 'FoldersIcon':
			newFolderObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_ListView'), 'data-automation-id', 'equals','MultipleFolderIconsJS', true)

			break
		case 'Files':
			newFolderObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_ListView'), 'data-automation-id', 'equals','MultipleFilesJS', true)

			break
		case 'FilesIcon':
			newFolderObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_ListView'), 'data-automation-id', 'equals','MultipleFilesIconsJS', true)

			break
		case 'Both':
			newFolderObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_ListView'), 'data-automation-id', 'equals','MultipleFolderFilesJS', true)

			break
		case 'BothIcon':
			newFolderObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_ListView'), 'data-automation-id', 'equals','MultipleFolderFilesIconsJS', true)

			break
	}

	TestObject newFolderObjLevel2 = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_ListView'), 'data-automation-id','equals', 'ListView', true)

	WebUI.delay(2)



	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))

	WebUI.click(findTestObject('JobMonitoringPage/JM_SearchBox'))

	WebUI.sendKeys(findTestObject('JobMonitoringPage/JM_SearchBox'), Type)

	TestObject newJobFilter = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/label_jobState'), 'text', 'equals',
			jobState, true)

	WebUI.click(newJobFilter)

	extentTest.log(Status.PASS, 'Searched for job with Job Name - '+Type)

	WebUI.delay(2)

	extentTest.log(Status.PASS, 'Clicked on job with state  - ' + jobState)

	println(jobState)

	TestObject newJobRow = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/div_Completed'), 'title', 'equals',
			jobState, true)

	WebUI.rightClick(newJobRow)

	WebUI.click(findTestObject('JobMonitoringPage/ViewDetails_Jobs'))

	extentTest.log(Status.PASS, 'Click on view details job')

	switch (userChoice) {
		case 'Input':
			WebUI.click(findTestObject('JobMonitoringPage/InputFolder'))

			extentTest.log(Status.PASS, 'Click on Input Folder')
			extentTest.log(Status.PASS, 'double-click on the folder')

			WebUI.doubleClick(newFolderObj)

			WebUI.delay(1)

			break
		case 'Output':
			WebUI.click(findTestObject('JobMonitoringPage/OutputFolder'))

			extentTest.log(Status.PASS, 'Click on Output Folder')
			//WebUI.waitForElementVisible(findTestObject('Object Repository/JobMonitoringPage/newFolderObj'), 5, FailureHandling.STOP_ON_FAILURE)
			WebUI.delay(2)
		    WebUI.doubleClick(newFolderObj)
			//WebUI.rightClick(newFolderObj)
			//WebUI.doubleClick(newFolderObj)
			//WebUI.delay(2)
			extentTest.log(Status.PASS, 'double-click on the folder')
			
		//	extentTest.log(Status.PASS, 'Right-click on Folder and select context Menu Open')	
		//	WebUI.click(findTestObject('Object Repository/FilesPage/ContextMenu_FileOperation_Open'))
			WebUI.delay(1)

			break
		case 'Running':
			WebUI.click(findTestObject('JobMonitoringPage/RunningFolder'))

			extentTest.log(Status.PASS, 'Click on Running Folder')

			WebUI.doubleClick(findTestObject('JobMonitoringPage/MultiFolders'))

			break
			case 'Output1':
			WebUI.click(findTestObject('JobMonitoringPage/OutputFolder'))

			extentTest.log(Status.PASS, 'Click on Output Folder')
			//WebUI.waitForElementVisible(findTestObject('Object Repository/JobMonitoringPage/newFolderObj'), 5, FailureHandling.STOP_ON_FAILURE)
			WebUI.delay(2)
		   // WebUI.doubleClick(findTestObject('Object Repository/JobMonitoringPage/newFolderObj'))
			WebUI.rightClick(newFolderObj)
			//WebUI.doubleClick(newFolderObj)
			WebUI.delay(2)
			
			extentTest.log(Status.PASS, 'Right-click on Folder and select context Menu Open')
			WebUI.click(findTestObject('Object Repository/FilesPage/ContextMenu_FileOperation_Open'))
			WebUI.delay(1)

			break
			
	}

	WebUI.delay(2)

	if (Type.contains('Icon')) {

		result=CustomKeywords.'operations_FileModule.four.executeFileOperations'(Operation, TestCaseName, extentTest)
	} else {
		result = CustomKeywords.'operations_FileModule.one.executeFileOperations'(Operation, TestCaseName, extentTest)
	}

	if (result) {
		extentTest.log(Status.PASS, TestCaseName + ' Performed Sucessfully')
	} else {
		extentTest.log(Status.FAIL, TestCaseName + ' failed')
	}

	if (GlobalVariable.G_Browser == 'IE') {
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
//=====================================================================================

