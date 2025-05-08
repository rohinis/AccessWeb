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


//==================================================================
def Browser = GlobalVariable.G_Browser
//==================================================================
def extentTest=GlobalVariable.G_ExtentTest
//=================================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=================================================================
def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()

def location = navLocation + '/FilesModule/FileOpsIcons/'

TestObject newFileObj

//WebUI.delay(2)
WebUI.enableSmartWait()

try {
    def filesTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/FilesTab_disabled'), 
        20, extentTest, 'Files Tab')

    if (filesTab) {
        WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
    }
  
    extentTest.log(Status.PASS, 'Navigated to Files Tab')

    CustomKeywords.'operations_FileModule.ChangeView.changePageView'(TestCaseName, extentTest)

    if (TestCaseName.contains('tile view')) {
      //  WebUI.delay(2)

        newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals', fileName, 
            true)
    } else {
        newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'data-automation-id', 'equals', fileName, 
            true)
    }
    
   // WebUI.delay(2)

    println(TestCaseName)

    if (TestCaseName.contains('Upload')) {
        println(Operation)
    } else {
     /*   WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))

        WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)

        WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))

        extentTest.log(Status.PASS, 'Navigated to ' + location)*/
		
		CustomKeywords.'generateFilePath.filePath.navlocation'(location, extentTest)

        WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))

        WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 2)

        println(fileName)

        WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), fileName)

        extentTest.log(Status.PASS, 'Looking for file to perfrom operation - ' + Operation)

        WebUI.sendKeys(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), Keys.chord(Keys.ENTER))

        extentTest.log(Status.PASS, 'Clicked on File  - ' + fileName)

        def fileItem = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newFileObj, 5, extentTest, fileName)

        println(fileItem)

        if (fileItem) {
            WebUI.waitForElementPresent(newFileObj, 3)

            WebUI.click(newFileObj)
        }
    }
    
   // WebUI.delay(2)

    println('after if else ' + Operation)

    def result = CustomKeywords.'operations_FileModule.fileOperations_Icon.executeFileOperations'(Operation, TestCaseName,extentTest)

	
	//def result =CustomKeywords.'demo.hello.one'(Operation, TestCaseName,extentTest)
WebUI.disableSmartWait()
	
	
    if (result) {
        extentTest.log(Status.PASS, ('File Operation - ' + TestCaseName) + ' Performed Sucessfully')
    } else {
        extentTest.log(Status.FAIL, 'File Operation - ' + TestCaseName + ' failed')
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