import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

//==================================================================
def Browser = GlobalVariable.G_Browser
//===============================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//====================================================================================


if (TestOperation.contains('icon')) {
	TestCaseName = (TestCaseName + ' thorugh top menu icons')
}
//=====================================================================================
def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()
def location
def result
WebUI.enableSmartWait()
if (TestOperation.contains('icon'))
{
	location = navLocation + '/FoldersModule/FolderOpsIcons'
}
else
{
	location = navLocation + '/FoldersModule/FolderOpsContextMenu'
}
//=====================================================================================
//WebUI.delay(2)
TestObject newFolderObj

try {
	def filesTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'),
			20, extentTest, 'App def')

	if (filesTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
	}

	CustomKeywords.'operations_FileModule.ChangeView.changePageView'(TestCaseName, extentTest)

	if (TestCaseName.contains('tile view')) {
		newFolderObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_TileView'), 'data-automation-id', 'equals', folderName,
				true)
	} else {
		newFolderObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_ListView'), 'data-automation-id', 'equals', folderName,
				true)
	}

	println(TestCaseName)

	if (TestCaseName.contains('Upload')) {
		println(TestOperation //	WebUI.click(newFolderObj)
				//WebUI.click(newFolderObj)
				)
	} 
	else 
	{
		/*WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
		WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
		WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
		extentTest.log(Status.PASS, 'Navigated to - ' + location)*/
		
		CustomKeywords.'generateFilePath.filePath.navlocation'(location, extentTest)
		
		WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))
		WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 2)
		WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), folderName)
		WebUI.sendKeys(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), Keys.chord(Keys.ENTER))
		extentTest.log(Status.PASS, 'Clicked on File  - ' + folderName)
		def folderItem = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newFolderObj, 20, extentTest, folderName)
		println(folderItem)

		if (TestOperation.contains('icon'))
		{
			WebUI.waitForElementPresent(newFolderObj, 3)
			WebUI.click(newFolderObj)-+9
			extentTest.log(Status.PASS, 'Clicked on file ' + folderName)
			result = CustomKeywords.'operations_FileModule.folderOperations_Icon.executeFolderOperations'(TestOperation, TestCaseName, extentTest)
		}
		else
		{
			WebUI.waitForElementPresent(newFolderObj, 3)
			WebUI.click(newFolderObj)
			WebUI.rightClick(newFolderObj)
			extentTest.log(Status.PASS, 'Right Clicked File to invoke context menu on  - ' + folderName)
			result = CustomKeywords.'operations_FileModule.folderOperations.executeFolderOperations'(TestOperation, TestCaseName, extentTest)
		}

	}

	//WebUI.delay(2)

	println('after is else ' + TestOperation)

	if (result)
	{
		extentTest.log(Status.PASS, ('Verified - ' + TestCaseName) + '  Sucessfully')
	} else {
		extentTest.log(Status.FAIL, TestCaseName + ' - failed')
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
