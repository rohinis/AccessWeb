import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.event.KeyEvent as KeyEvent
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.exception.StepFailedException as StepFailedException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

/*
'Login into PAW '
WebUI.callTestCase(findTestCase('XRepeated_TC/Login'), [('username') : GlobalVariable.G_AdminUser, ('password') : GlobalVariable.G_AdminPasswd],
FailureHandling.STOP_ON_FAILURE)
*/
WebDriver driver = DriverFactory.getWebDriver()

EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)

// Get the driver wrapped inside
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()

// Cast the wrapped driver into RemoteWebDriver
RemoteWebDriver katalonWebDriver = (RemoteWebDriver) wrappedWebDriver

//==================================================================
def Browser = GlobalVariable.G_Browser
//===============================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=============================================================


def result=false

def text=null

def location = '/stage/pbsworks/'

WebUI.delay(2)

def TCName = TestCaseName + ' - through top menu icons'


TestObject newFileObj=null
 
if (TestCaseName.contains('tile view')) {
	newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals', fileName,true)
} else {
	newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'data-automation-id', 'equals',fileName, true)
}
try {
		def filesTab =CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'),
				20,extentTest,'App def')
	
		if (filesTab) {
			WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
		}
	
		extentTest.log(Status.PASS, 'Navigated to Files Tab')
	//	WebUI.delay(2)
		
		def fileItem = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newFileObj, 20, extentTest, fileName)
		
				println(fileItem)
		
				if (fileItem) {
					WebUI.waitForElementPresent(newFileObj, 3)
		
					WebUI.click(newFileObj)
		
					extentTest.log(Status.PASS, 'Clicked on file ' + fileName)
		
					WebUI.rightClick(newFileObj)
		
					extentTest.log(Status.PASS, 'Right Clicked File to invoke context menu on  - ' + fileName)
				
				newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'data-automation-id', 'equals',"Share A Copy", true)
				WebUI.delay(2)
				WebUI.click(newFileObj)
			
				}
				String [] userslist=WebUI.getText(findTestObject('Object Repository/ShareACopy/userslist'))
				println("userslist"+ userslist[0])
				println("userslist"+ userslist[1])
				//select the users list
				WebUI.waitForElementVisible(findTestObject('Object Repository/ShareACopy/select_users'), 10, FailureHandling.STOP_ON_FAILURE)
				WebUI.mouseOver(findTestObject('Object Repository/ShareACopy/select_users'))
				WebUI.click(findTestObject('Object Repository/ShareACopy/select_users'))
				WebUI.delay(2)
				WebUI.click(findTestObject('Object Repository/ShareACopy/right_arrow_icon'))
				WebUI.delay(2)
				WebUI.click(findTestObject('Object Repository/ShareACopy/Apply_button'))
				//click on the access managment option
				
				WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))
				
					extentTest.log(Status.PASS, 'Click on profile tab')
				
					WebUI.delay(2)
				
					WebUI.click(findTestObject('Access_Management/Access_management'))
				
					extentTest.log(Status.PASS, 'Click on access management')
				
				WebUI.click(findTestObject('Access_Management/Add_user'))
						extentTest.log(Status.PASS, 'Click on users')
				
				
	
	WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))
	extentTest.log(Status.PASS, 'Click on profile tab')
	WebUI.delay(2)
	
	WebUI.click(findTestObject('Landing_Page/ListItem_Logout'))
	extentTest.log(Status.PASS, 'Click on logout')
	WebUI.delay(2)
	
	WebUI.click(findTestObject('GenericObjects/btn_Yes'))
	extentTest.log(Status.PASS, 'Click on yes button')
	WebUI.delay(2)
	WebUI.delay(2)
	
	
	
	WebUI.click(findTestObject('AppComposer/Login'))
	extentTest.log(Status.PASS, 'Click on Login again')
	
	WebUI.setText(findTestObject('LoginPage/username_txtbx'),'pbsadmin')
	extentTest.log(Status.PASS, 'Enter username newuser ')
	
	WebUI.setText(findTestObject('LoginPage/password_txtbx'), 'pbsadmin')
	extentTest.log(Status.PASS, 'Enter  password  newuser')
	
	WebUI.click(findTestObject('LoginPage/login_btn'))
	extentTest.log(Status.PASS, 'Click on Login')

	WebUI.delay(2)
 filesTab =CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'),
		20,extentTest,'App def')

if (filesTab) {
	WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
}

extentTest.log(Status.PASS, 'Navigated to Files Tab')
//	WebUI.delay(2)
		WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 5)
	
				WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))
				
		
				WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), "shared")
				WebUI.sendKeys(findTestObject('FilesPage/FilesSearch_filter'), Keys.chord(Keys.ENTER))

	
			WebUI.delay(2)
		WebUI.doubleClick(findTestObject('Object Repository/ShareACopy/sharedfolder'))
		newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'data-automation-id', 'equals',"B1", true)
		WebUI.verifyElementPresent(newFileObj, 3, FailureHandling.STOP_ON_FAILURE)
		WebUI.delay(2)
		WebUI.click(newFileObj)
				
	
	
  
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

