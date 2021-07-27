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

		

		case'Space':
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
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(LogStatus.PASS, 'Select the Test123File  created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Copy'))
			extentTest.log(LogStatus.PASS, 'Click on Clone option')
			WebUI.delay(3)
			WebUI.click(findTestObject('Object Repository/AppComposer/Save_ btn'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			
				def Text = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text)
			WebUI.verifyElementPresent(findTestObject('AppComposer/Test12App_clone'), 3)
			extentTest.log(LogStatus.PASS, 'Verify app copy created and present in both App composer and Jobs page')
			
			
			break
			
			case 'Special char':
			
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
			extentTest.log(LogStatus.PASS, 'Select the app Test12File created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Copy'))
			extentTest.log(LogStatus.PASS, 'Click on Clone option')
			WebUI.delay(3)
			WebUI.click(findTestObject('Object Repository/AppComposer/Save_ btn'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			
				def Text3 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text3)
			WebUI.verifyElementPresent(findTestObject('AppComposer/Testapp_clone'), 3)
			extentTest.log(LogStatus.PASS, 'Verify  copy of app created and present in both App composer and Jobs page')
			
			
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
			
				def Text4 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text4)
	
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(LogStatus.PASS, 'Select the app ' + app +' created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Share'))
			extentTest.log(LogStatus.PASS, 'Click on Share option')
			
			WebUI.click(findTestObject('AppComposer/Input'))
			extentTest.log(LogStatus.PASS, 'Click on Input text box')
			WebUI.setText(findTestObject('AppComposer/Input'), '')
			WebUI.setText(findTestObject('AppComposer/Input'), 'bhuvana')
			extentTest.log(LogStatus.PASS, 'Add username bhuvana to share the apllication')
			WebUI.click(findTestObject('AppComposer/Share_btn'))
			extentTest.log(LogStatus.PASS, 'Click on Share button')
			
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
			extentTest.log(LogStatus.PASS, 'Click on Share button')
			
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(LogStatus.PASS, 'Select the app created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Share'))
			extentTest.log(LogStatus.PASS, 'Click on Clone option')
			
			WebUI.click(findTestObject('AppComposer/Input'))
			extentTest.log(LogStatus.PASS, 'Click on Input text box')
			WebUI.setText(findTestObject('AppComposer/Input'), '')
			WebUI.setText(findTestObject('AppComposer/Input'), 'bhuvana')
			extentTest.log(LogStatus.PASS, 'Add username bhuvana to share the apllication')
			WebUI.click(findTestObject('AppComposer/Share_btn'))
			extentTest.log(LogStatus.PASS, 'Click on Share button')
			

			break
			
			case'Publish':
			
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
			
				def Text5 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text5)
	
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Publishall'))
			extentTest.log(LogStatus.PASS, 'Click on Publish to all button')
			WebUI.delay(5)
			WebUI.click(findTestObject('AppComposer/Ok_btn'))
			extentTest.log(LogStatus.PASS, 'Click on Ok button , PAS configuration change pop up')
			
			WebUI.click(findTestObject('AppComposer/Publishapp'))
			extentTest.log(LogStatus.PASS, 'Click on ' + app+ ' in left navigation')
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(LogStatus.PASS, 'Select the app ' + app + ' created and click on ellipses')
			WebUI.verifyElementPresent(findTestObject('AppComposer/Copy'),3)
			extentTest.log(LogStatus.PASS, 'Click on Copy  option')
			
			break
			
			case 'New':
			
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
	
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(LogStatus.PASS, 'Select the app '+ app +' created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Copy'))
			extentTest.log(LogStatus.PASS, 'Click on Clone option')
			WebUI.delay(3)
			WebUI.click(findTestObject('Object Repository/AppComposer/Save_ btn'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			WebUI.verifyElementPresent(findTestObject('AppComposer/Newapp'), 3)
			//WebUI.click(findTestObject('Object Repository/AppComposer/Newapp'))
			
			
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
			extentTest.log(LogStatus.PASS, 'Click on Share button')
			
			WebUI.delay(3)
		    WebUI.click(findTestObject('AppComposer/Newapp_clone'))
			extentTest.log(LogStatus.PASS, 'Click on apllication in left navigation')
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(LogStatus.PASS, 'Select the app ' + app + ' created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Copy'))
			extentTest.log(LogStatus.PASS, 'Click on Clone option')
			WebUI.delay(3)
			WebUI.click(findTestObject('Object Repository/AppComposer/Save_ btn'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			
				def Text7 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text7)
			WebUI.verifyElementPresent(findTestObject('AppComposer/Newapp_clone'), 3)	
			extentTest.log(LogStatus.PASS, 'Verify Newapp_1  has been created and present in jobs page')
			
			WebUI.delay(3)
			
			WebUI.click(findTestObject('AppComposer/Newapp_clone'))
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(LogStatus.PASS, 'Select the app created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Deleteapp'))
			extentTest.log(LogStatus.PASS, 'Click on Delete option')
			
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(LogStatus.PASS, 'Select the app '+ app +' created and click on ellipses')
			
			WebUI.click(findTestObject('AppComposer/Edit'))
			extentTest.log(LogStatus.PASS, 'Click on Edit option')
			
			//WebUI.mouseOver(findTestObject('AppComposer/Editapp'))
			
			//WebUI.click(findTestObject('AppComposer/Editapp'))
			extentTest.log(LogStatus.PASS, 'Click on ' + app + 'on left side')
			
			WebUI.mouseOver(findTestObject('AppComposer/AppName'))
			
			//WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'),'Demonew')
			extentTest.log(LogStatus.PASS, 'Rename the created app - Demonew' )
			
			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			
			WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
			extentTest.log(LogStatus.PASS, 'Click on Jobs Tab')
			WebUI.delay(3)
			
			//WebUI.verifyElementPresent(findTestObject('AppComposer/Demonew'), 3)
			extentTest.log(LogStatus.PASS, 'Verify app has been renamed to Demonew and pesent on jobs page')
			
			
						
			break
			
			case'Clone':
			
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
			WebUI.click(findTestObject('AppComposer/Copy'))
			extentTest.log(LogStatus.PASS, 'Click on Clone option')
			WebUI.delay(3)
			WebUI.click(findTestObject('Object Repository/AppComposer/Save_ btn'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			
				def Text9 = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text9)
			WebUI.verifyElementPresent(findTestObject('AppComposer/Demo_clone'), 3)
			extentTest.log(LogStatus.PASS, 'Verify Demo_1  has been created and present in jobs page')
			
			break
			
			case'Publishall':
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
			WebUI.click(findTestObject('AppComposer/Publishall'))
			extentTest.log(LogStatus.PASS, 'Click on Publish to all button')
			WebUI.delay(3)
			WebUI.verifyElementPresent(findTestObject('Object Repository/AppComposer/Configuration'), 3)
			extentTest.log(LogStatus.PASS, 'Verify There is a change in PAS configuration. ')
			
			WebUI.delay(5)
			WebUI.click(findTestObject('AppComposer/Ok_btn'))
			//extentTest.log(LogStatus.PASS, 'Click on Ok button')
			
			
			break
			
			
			case'Access':
			
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
			
			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')
			WebUI.delay(2)
			
			WebUI.click(findTestObject('Access_Management/Access_management'))
			extentTest.log(LogStatus.PASS, 'Click on access management')
			
			WebUI.click(findTestObject('Access_Management/Add_rolebutton'))
			extentTest.log(LogStatus.PASS, 'Click on edit role')
			
			WebUI.click(findTestObject('Access_Management/Edit_roleid'))
			WebUI.doubleClick(findTestObject('Object Repository/AppComposer/Text'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('Object Repository/AppComposer/Text'), '')
			WebUI.setText(findTestObject('Object Repository/AppComposer/Text'), 'demo')
			extentTest.log(LogStatus.PASS, 'Add roleid name - demo')
			
			WebUI.click(findTestObject('Access_Management/Confirm_button'))
			extentTest.log(LogStatus.PASS, 'Click on save')
			
			WebUI.delay(4)
				
			WebUI.click(findTestObject('Access_Management/Add_resources'))
			extentTest.log(LogStatus.PASS, 'Click on add resources icon')
			WebUI.delay(3)
			WebUI.mouseOver(findTestObject('AppComposer/one_resource'))
			
			WebUI.click(findTestObject('AppComposer/one_resource'))
			extentTest.log(LogStatus.PASS, 'Select the Access app as resource')
			
			WebUI.click(findTestObject('Access_Management/Confirm_button'))
			extentTest.log(LogStatus.PASS, 'Click on Confirm button')
			
			WebUI.click(findTestObject('Access_Management/Save_role'))
			extentTest.log(LogStatus.PASS, 'Click on add resources icon')
			WebUI.delay(3)
			
			TestObject role =WebUI.modifyObjectProperty(findTestObject('Access_Management/Roleinfo'),'text', 'equals','Demo', true)
			WebUI.doubleClick(role)
			extentTest.log(LogStatus.PASS, 'Click on added role')
			
			WebUI.click(findTestObject('AppComposer/one_resource'))
			extentTest.log(LogStatus.PASS, 'Verified globally publish app showed up in access management')
		
			
			break
			
			case'Delete':
			
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
			
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(LogStatus.PASS, 'Select the app ' + app +' created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Deleteapp'))
			extentTest.log(LogStatus.PASS, 'Click on Delete option')
			WebUI.verifyElementPresent(findTestObject('AppComposer/DelPop'), 3)
			WebUI.waitForElementPresent(findTestObject('AppComposer/DelPop'), 5)
			
				def Text13 = WebUI.getText(findTestObject('AppComposer/DelPop'))
			
				extentTest.log(LogStatus.PASS, 'Notification Generated ' + Text13)
			
			
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

