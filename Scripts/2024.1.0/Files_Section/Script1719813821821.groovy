import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

//==================================================================
def Browser = GlobalVariable.G_Browser
//===============================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=============================================================


TestObject newFileObj = null

def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()

def location = navLocation + '/FilesModule/FileOps/'
def result=false

if (TestCaseName.contains('tile view')) {
	newFileObj = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FolderRowItem_TileView'), 'data-automation-id', 'equals', fileName,true)
} else {
	newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'data-automation-id', 'equals',fileName, true)
}

try {
	def filesTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/FilesTab_disabled'),
		20, extentTest, 'Files Tab')

	if (filesTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
	}
	WebUI.enableSmartWait()
	extentTest.log(Status.PASS, 'Navigated to Files Tab')

   // WebUI.delay(2)*8

	println('==============================================')

	println(TestCaseName)

	println('==============================================')

	CustomKeywords.'operations_FileModule.ChangeView.changePageView'(TestCaseName, extentTest)

	
		//CustomKeywords.'generateFilePath.filePath.navlocation'(location, extentTest)
		
		WebUI.delay(2)
   

		
		//def fileItem = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newFileObj, 20, extentTest, fileName)

		//println(fileItem)

		//if (fileItem) {
			WebUI.waitForElementPresent(newFileObj, 3)

			WebUI.click(newFileObj)

			extentTest.log(Status.PASS, 'Clicked on file ' + fileName)

			WebUI.rightClick(newFileObj)

			extentTest.log(Status.PASS, 'Right Clicked File to invoke context menu on  - ' + fileName)
	
			
					//	WebUI.click(newFileObj)
			
						extentTest.log(Status.PASS, 'Clicked on Properties ')
						WebUI.delay(5)
					WebUI.click(findTestObject('Object Repository/FilesPage/Permissions/Properties'))
					WebUI.delay(2)	
					if(TestCaseName.contains("read")) {
						extentTest.log(Status.PASS, 'uncheck the read permission  and verify the message')
						WebUI.click(findTestObject('Object Repository/FilesPage/Permissions/read_permission'))
						WebUI.click(findTestObject('Object Repository/FilesPage/btn_Save'))
				
					 if(TestCaseName.contains("tile view")) {
						 result=WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/Permissions/message2'),10)
						 
					 }
					 else
					 {	 result=WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/Permissions/message'),10)
					 
					 }
						
					}
						
					
						if(TestCaseName.contains("write")) {
						extentTest.log(Status.PASS, 'uncheck the write permission  and verify the message')
						WebUI.click(findTestObject('Object Repository/FilesPage/Permissions/write_permission'))
						
						WebUI.click(findTestObject('Object Repository/FilesPage/btn_Save'))
						WebUI.delay(2)
						if(TestCaseName.contains("tile view")) {
							result=WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/Permissions/message2'),10)
							
						}
						else
						{	 result=WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/Permissions/message2'),10)
						
						}
											}

	WebUI.disableSmartWait()

	if (result) {
		extentTest.log(Status.PASS, ('Verified - ' + TestCaseName) + '  Sucessfully')
	} else {
		extentTest.log(Status.FAIL, TestCaseName + ' - failed')
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
	extentTest.log(Status.PASS, 'Closing the browser after executing test case - ' + TestCaseName)
	}


