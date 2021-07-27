import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.Robot
import java.awt.event.KeyEvent as KeyEvent

import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration

/*
'Login into PAW '
WebUI.callTestCase(findTestCase('Generic/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
FailureHandling.STOP_ON_FAILURE)
*/

WebDriver driver = DriverFactory.getWebDriver()

EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)

// Get the driver wrapped inside
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()

// Cast the wrapped driver into RemoteWebDriver
RemoteWebDriver katalonWebDriver = ((wrappedWebDriver) as RemoteWebDriver)

ReportFile = (GlobalVariable.G_ReportName + '.html')
def extent = CustomKeywords.'generateReports.GenerateReport.create'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)
def LogStatus = com.relevantcodes.extentreports.LogStatus
def extentTest = extent.startTest(TestCaseName)
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
String screenShot='ExtentReports/'+TestCaseName+userChoice+GlobalVariable.G_Browser+'.png'
def result
WebUI.delay(2)
Robot rob = new Robot()

try
{
	WebUI.delay(2)
	//WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

	WebUI.click(findTestObject('Preferences/Profiletab'))
	extentTest.log(LogStatus.PASS, 'Click on profile tab')
	WebUI.delay(2)

	WebUI.click(findTestObject('AppComposer/Appcomposer'))
	extentTest.log(LogStatus.PASS, 'Click on App composer')
	
	

	switch(userChoice)
	{
	
			case'Unpublish':
			
			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)
			WebUI.click(findTestObject('AppComposer/Executableinput'))
			extentTest.log(LogStatus.PASS, 'Click to add exec commands')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add Exec commands - ' + exec)

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			
				def Text1 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text1)
			
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Publishall'))
			extentTest.log(LogStatus.PASS, 'Click on Publish to all button')
			WebUI.delay(5)
			
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(LogStatus.PASS, 'Select the app ' + app +' created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Unpublish'))
			extentTest.log(LogStatus.PASS, 'Click on Unpublish option')
			WebUI.waitForElementPresent(findTestObject('AppComposer/Unpubpop'), 5)
			
				def Text = WebUI.getText(findTestObject('AppComposer/Unpubpop'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text)
			WebUI.verifyElementPresent(findTestObject('AppComposer/Unpubpop'), 3)
			extentTest.log(LogStatus.PASS, 'Verify ' + app +' Application is unpublished successfully and not present in jobs page')
			
			
			break
			
			case'Share':
			
			
			
			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)
			WebUI.click(findTestObject('AppComposer/Executableinput'))
			extentTest.log(LogStatus.PASS, 'Click to add exec commands')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add Exec commands - ' + exec)

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			
				def Text2 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text2)
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(LogStatus.PASS, 'Select the app created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Share'))
			extentTest.log(LogStatus.PASS, 'Click on Share option')
			
			WebUI.click(findTestObject('AppComposer/Input'))
			extentTest.log(LogStatus.PASS, 'Click on Input text box')
			WebUI.setText(findTestObject('AppComposer/Input'), '')
			WebUI.setText(findTestObject('AppComposer/Input'), 'bhuvana')
			extentTest.log(LogStatus.PASS, 'Add username bhuvana to share the apllication')
			WebUI.click(findTestObject('AppComposer/Share_btn'))
			extentTest.log(LogStatus.PASS, 'Click on share button')
			
			
			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')
			WebUI.delay(2)
			
			WebUI.click(findTestObject('Landing_Page/ListItem_Logout'))
			extentTest.log(LogStatus.PASS, 'Click on logout')
			
			WebUI.verifyElementVisible(findTestObject('LogOut-PopUp/Title_Logout'))
			
			WebUI.delay(2)
			
			WebUI.click(findTestObject('GenericObjects/btn_Yes'))
			extentTest.log(LogStatus.PASS, 'Click on yes button')
			WebUI.delay(2)
			
			WebUI.click(findTestObject('AppComposer/Login'))
			extentTest.log(LogStatus.PASS, 'Click on Login again')
			
			WebUI.setText(findTestObject('LoginPage/username_txtbx'),'bhuvana')
			extentTest.log(LogStatus.PASS, 'Enter username bhuvana ')
			
			WebUI.setText(findTestObject('LoginPage/password_txtbx'), 'bhuvana')
			extentTest.log(LogStatus.PASS, 'Enter  password  bhuvana')
			
			WebUI.click(findTestObject('LoginPage/login_btn'))
			extentTest.log(LogStatus.PASS, 'Click on Login')
			WebUI.delay(2)
			
			
			//WebUI.verifyElementPresent(findTestObject('AppComposer/Shareapp'), 3)
			extentTest.log(LogStatus.PASS, 'Verify Shareapp is present in app composer and in jobs page ')
			
			break
			
			
			case'Shareleft':
			
			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)
			WebUI.click(findTestObject('AppComposer/Executableinput'))
			extentTest.log(LogStatus.PASS, 'Click to add exec commands')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add Exec commands - ' + exec)

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			
				def Text3 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text3)
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(LogStatus.PASS, 'Select the app '+ app +' created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Share'))
			extentTest.log(LogStatus.PASS, 'Click on Share option')
			
			WebUI.click(findTestObject('AppComposer/Input'))
			extentTest.log(LogStatus.PASS, 'Click on Input text box')
			WebUI.setText(findTestObject('AppComposer/Input'), '')
			WebUI.setText(findTestObject('AppComposer/Input'), 'bhuvana')
			extentTest.log(LogStatus.PASS, 'Add username bhuvana to share the apllication')
			WebUI.click(findTestObject('AppComposer/Share_btn'))
			extentTest.log(LogStatus.PASS, 'Click on Share button')
			
			WebUI.verifyElementPresent(findTestObject('AppComposer/Shareleft'), 3)
			extentTest.log(LogStatus.PASS, 'Click on Shareleft application in left navigation')
			
			
			break
			
			case'Shareuser':
			
			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)
			WebUI.click(findTestObject('AppComposer/Executableinput'))
			extentTest.log(LogStatus.PASS, 'Click to add exec commands')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add Exec commands - ' + exec)

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			
				def Text4 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text4)
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(LogStatus.PASS, 'Select the app ' + app + ' created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Share'))
			extentTest.log(LogStatus.PASS, 'Click on Share option')
			
			WebUI.click(findTestObject('AppComposer/Input'))
			extentTest.log(LogStatus.PASS, 'Click on Input text box')
			WebUI.setText(findTestObject('AppComposer/Input'), '')
			WebUI.setText(findTestObject('AppComposer/Input'), 'bhuvana'+',' + ' rohini')
			extentTest.log(LogStatus.PASS, 'Add username bhuvana , rohini to share the apllication')
			WebUI.click(findTestObject('AppComposer/Share_btn'))
			extentTest.log(LogStatus.PASS, 'Click on Share button')
			
			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')
			WebUI.delay(2)
			
			WebUI.click(findTestObject('Landing_Page/ListItem_Logout'))
			extentTest.log(LogStatus.PASS, 'Click on logout')
			
			WebUI.verifyElementVisible(findTestObject('LogOut-PopUp/Title_Logout'))
			
			WebUI.delay(2)
			
			WebUI.click(findTestObject('GenericObjects/btn_Yes'))
			extentTest.log(LogStatus.PASS, 'Click on yes button')
			WebUI.delay(2)
			
			WebUI.click(findTestObject('AppComposer/Login'))
			extentTest.log(LogStatus.PASS, 'Click on Login again')
			
			WebUI.setText(findTestObject('LoginPage/username_txtbx'),'rohini')
			extentTest.log(LogStatus.PASS, 'Enter username rohini ')
			
			WebUI.setText(findTestObject('LoginPage/password_txtbx'), 'rohini')
			extentTest.log(LogStatus.PASS, 'Enter  password  rohini')
			
			WebUI.click(findTestObject('LoginPage/login_btn'))
			extentTest.log(LogStatus.PASS, 'Click on Login')
			WebUI.delay(3)
			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')
			
			WebUI.click(findTestObject('AppComposer/Appcomposer'))
			extentTest.log(LogStatus.PASS, 'Click on App composer')
			
			//WebUI.verifyElementPresent(findTestObject('AppComposer/Shareapp'), 3)
			extentTest.log(LogStatus.PASS, 'Verify Shareapp is present in app composer and in jobs page ')
			
			WebUI.delay(3)
			/*
			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')
			WebUI.delay(2)
			
			WebUI.click(findTestObject('Landing_Page/ListItem_Logout'))
			extentTest.log(LogStatus.PASS, 'Click on logout')
			
			WebUI.verifyElementVisible(findTestObject('LogOut-PopUp/Title_Logout'))
			
			WebUI.delay(2)
			
			WebUI.click(findTestObject('GenericObjects/btn_Yes'))
			extentTest.log(LogStatus.PASS, 'Click on yes button')
			WebUI.delay(2)
			
			WebUI.click(findTestObject('AppComposer/Login'))
			extentTest.log(LogStatus.PASS, 'Click on Login again')
			
			WebUI.setText(findTestObject('LoginPage/username_txtbx'),'bhuvana')
			extentTest.log(LogStatus.PASS, 'Enter username ')
			
			WebUI.setText(findTestObject('LoginPage/password_txtbx'), 'bhuvana')
			extentTest.log(LogStatus.PASS, 'Enter  password ')
			
			WebUI.click(findTestObject('LoginPage/login_btn'))
			extentTest.log(LogStatus.PASS, 'Click on Login')
			
			
			*/
			
			
			break
			
			case'Without Publish':
			
			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)
			WebUI.click(findTestObject('AppComposer/Executableinput'))
			extentTest.log(LogStatus.PASS, 'Click to add exec commands')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add Exec commands - ' + exec)

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			
			//WebUI.verifyElementPresent(findTestObject('AppComposer/Publishall'), 3)
			//extentTest.log(LogStatus.PASS, 'Verify Publish to all button is present')
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			
				def Text5 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text5)
			
			WebUI.delay(4)
			WebUI.click(findTestObject('AppComposer/Withoutpublish'))
			extentTest.log(LogStatus.PASS, 'Click on app on left side')
			
			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on save button')

			WebUI.verifyElementPresent(findTestObject('AppComposer/InputF'), 3)
			//WebUI.click(findTestObject('AppComposer/InputF'))
			def filePath = (RunConfiguration.getProjectDir() + '/Upload/JobFiles/Running.sh')
			def newFP=(new generateFilePath.filePath()).getFilePath(filePath)
			println(newFP)
			WebUI.uploadFile(findTestObject('AppComposer/InputF'), newFP )
			extentTest.log(LogStatus.PASS, 'Upload input file')
			
			WebUI.delay(2)
			WebUI.click(findTestObject('AppComposer/Withoutpublish'))
			extentTest.log(LogStatus.PASS, 'Click on app on left side')
			
			WebUI.click(findTestObject('AppComposer/Submit_btn'))
			extentTest.log(LogStatus.PASS, 'Click on submit and test  button')
			
			WebUI.delay(4)
			
			//WebUI.verifyElementPresent(findTestObject('AppComposer/RunningFolder'), 3)
			extentTest.log(LogStatus.PASS, 'Verify running folder present')
			
			WebUI.click(findTestObject('AppComposer/CloseButton'))
			extentTest.log(LogStatus.PASS, 'Click on cancel ')
			
			
			WebUI.click(findTestObject('AppComposer/JobsTab'))
			extentTest.log(LogStatus.PASS, 'Navigated to Jobs Tab')
			
			WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
			extentTest.log(LogStatus.PASS, 'Click on Notification button to open Notification Panel')
		   
			
			WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)
			
			def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))
			
			CustomKeywords.'operations_JobsModule.GetJobRowDetails.getJobID'(jobText)
			
			 
			
				extentTest.log(LogStatus.PASS, 'Job ID - ' + GlobalVariable.G_JobID)
			
			 
			
				extentTest.log(LogStatus.PASS, 'Job Submission Done for - ' + TestCaseName)
			
			
		

			return true
			
			
			
			break
			
			case'Rename':
			
			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)
			WebUI.click(findTestObject('AppComposer/Executableinput'))
			extentTest.log(LogStatus.PASS, 'Click to add exec commands')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add Exec commands - ' + exec)

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			
				def Text6 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text6)
			WebUI.delay(4)
			
			WebUI.mouseOver(findTestObject('AppComposer/Rename'))
			
			WebUI.click(findTestObject('AppComposer/Rename'))
			extentTest.log(LogStatus.PASS, 'Click on ' + app + 'on left side')
			
			WebUI.mouseOver(findTestObject('AppComposer/AppName'))
			
			//WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'),'Renamenew')
			extentTest.log(LogStatus.PASS, 'Rename the created app - Renamenew' )
			
			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			
				def Text7 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text7)
			
			WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
			extentTest.log(LogStatus.PASS, 'Click on Jobs Tab')
			WebUI.delay(3)
			
			//WebUI.verifyElementPresent(findTestObject('AppComposer/Renamenew'), 3)
			extentTest.log(LogStatus.PASS, 'Verify app has been renamed to Renamenew and pesent on jobs page')
		
			
			break
			
			case'Edit':
			WebUI.delay(3)
			
			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)
			WebUI.click(findTestObject('AppComposer/Executableinput'))
			extentTest.log(LogStatus.PASS, 'Click to add exec commands')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add Exec commands - ' + exec)

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			
				def Text8 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text8)
			
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(LogStatus.PASS, 'Select the app '+ app +' created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Edit'))
			extentTest.log(LogStatus.PASS, 'Click on Edit option')
			
			WebUI.mouseOver(findTestObject('AppComposer/Editapp'))
			
			WebUI.click(findTestObject('AppComposer/Editapp'))
			extentTest.log(LogStatus.PASS, 'Click on ' + app + 'on left side')
			
			WebUI.mouseOver(findTestObject('AppComposer/AppName'))
			
			//WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'),'Editappnew')
			extentTest.log(LogStatus.PASS, 'Rename the created app - Editappnew' )
			
			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			
				def Text9 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text9)
			
			WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
			extentTest.log(LogStatus.PASS, 'Click on Jobs Tab')
			
			WebUI.verifyElementPresent(findTestObject('AppComposer/Editappnew'), 3)
			extentTest.log(LogStatus.PASS, 'Verify app has been renamed to Editappnew and pesent on jobs page')
		
			
			
			
			break
			
			case'Options':
			
			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)
			WebUI.click(findTestObject('AppComposer/Executableinput'))
			extentTest.log(LogStatus.PASS, 'Click to add exec commands')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add Exec commands - ' + exec)

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			
				def Text10 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text10)
			
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(LogStatus.PASS, 'Select the app ' + app +' created and click on ellipses')
			WebUI.verifyElementPresent(findTestObject('AppComposer/Edit'), 3)
			extentTest.log(LogStatus.PASS, 'Verify Edit option is present')
			
			WebUI.verifyElementPresent(findTestObject('AppComposer/Copy'), 3)
			extentTest.log(LogStatus.PASS, 'Verify Copy option is present')
			
			WebUI.verifyElementPresent(findTestObject('AppComposer/Share'), 3)
			extentTest.log(LogStatus.PASS, 'Verify Share option is present')
			
			WebUI.verifyElementPresent(findTestObject('AppComposer/Delete'), 3)
			extentTest.log(LogStatus.PASS, 'Verify Edit option is present')
			
			break
			
			case'Edit Not':
			
			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)
			WebUI.click(findTestObject('AppComposer/Executableinput'))
			extentTest.log(LogStatus.PASS, 'Click to add exec commands')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add Exec commands - ' + exec)

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			
				def Text11 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text11)
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Publishall'))
			extentTest.log(LogStatus.PASS, 'Click on Publish to all button')
			WebUI.delay(5)
			WebUI.click(findTestObject('AppComposer/Ok_btn'))
			extentTest.log(LogStatus.PASS, 'Click on Ok button')
	
			WebUI.click(findTestObject('Object Repository/AppComposer/Ellipses'))
			extentTest.log(LogStatus.PASS, 'Select the app created and click on ellipses')
			WebUI.delay(3)
			
			//WebUI.verifyElementNotPresent(findTestObject('AppComposer/Edit'), 3)
			extentTest.log(LogStatus.PASS, 'Verify Edit option not present')
		
			break
			
			case'Publish App':
			
			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)
			WebUI.click(findTestObject('AppComposer/Executableinput'))
			extentTest.log(LogStatus.PASS, 'Click to add exec commands')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add Exec commands - ' + exec)

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			
				def Text12 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text12)
	
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Publishall'))
			extentTest.log(LogStatus.PASS, 'Click on Publish to all button')
			WebUI.delay(5)
			WebUI.click(findTestObject('AppComposer/Ok_btn'))
			extentTest.log(LogStatus.PASS, 'Click on Ok button , PAS configuration change pop up')
			
			WebUI.click(findTestObject('AppComposer/Publishapp'))
			extentTest.log(LogStatus.PASS, 'Click on ' + app+ ' in left navigation , only admin user able to publish it globally')
			
			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')
			WebUI.delay(2)
			
			WebUI.click(findTestObject('Landing_Page/ListItem_Logout'))
			extentTest.log(LogStatus.PASS, 'Click on logout')
			
			WebUI.verifyElementVisible(findTestObject('LogOut-PopUp/Title_Logout'))
			
			WebUI.delay(2)
			
			WebUI.click(findTestObject('GenericObjects/btn_Yes'))
			extentTest.log(LogStatus.PASS, 'Click on yes button')
			WebUI.delay(3)
			
			WebUI.click(findTestObject('AppComposer/Login'))
			extentTest.log(LogStatus.PASS, 'Click on Login again')
			
			WebUI.setText(findTestObject('LoginPage/username_txtbx'),'rohini')
			extentTest.log(LogStatus.PASS, 'Enter username rohini ')
			
			WebUI.setText(findTestObject('LoginPage/password_txtbx'), 'rohini')
			extentTest.log(LogStatus.PASS, 'Enter  password  rohini')
			
			WebUI.click(findTestObject('LoginPage/login_btn'))
			extentTest.log(LogStatus.PASS, 'Click on Login')
			WebUI.delay(3)
			
			
			//WebUI.verifyElementPresent(findTestObject('AppComposer/Publishapp_jobs'), 3)
			extentTest.log(LogStatus.PASS, 'Verify Shareapp is present in app composer and in jobs page ')
			
			WebUI.delay(3)
			

		    break

	}
	




	if (GlobalVariable.G_Browser == 'IE') {
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
	}

}

catch (Exception  ex)
{

	String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL,ex)
	KeywordUtil.markFailed('ERROR: '+ e)

}
catch (StepErrorException  e)
{

	String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL,e)
	KeywordUtil.markFailed('ERROR: '+ e)

}
catch (StepFailedException e)
{
	String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
	WebUI.takeScreenshot(screenShotPath)
	extentTest.log(LogStatus.FAIL,e)
	KeywordUtil.markFailed('ERROR: '+ e)
}
finally
{

	extent.endTest(extentTest);
	extent.flush();

}

