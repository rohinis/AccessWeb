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

		case'New App':

		//WebUI.click(findTestObject('AppComposer/DropDown'))
		//WebUI.click(findTestObject('AppComposer/Basic-SMP'))
			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name - ' + app)

			WebUI.click(findTestObject('AppComposer/Executableinput'))
			extentTest.log(LogStatus.PASS, 'Click to add exec commands')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add Exec commands - ' + exec)
			
			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			

			WebUI.delay(3)

			WebUI.click(findTestObject('AppComposer/JobsTab'))
			extentTest.log(LogStatus.PASS, 'Navigated to Jobs Tab')

			WebUI.verifyElementPresent(findTestObject('AppComposer/TestApp'), 3)
			extentTest.log(LogStatus.PASS, 'Verify app ' + app + ' on Jobs page')
			

			break

		case'Empty Fields':

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button ')
			
			WebUI.verifyElementPresent(findTestObject('AppComposer/Error'), 3)
			extentTest.log(LogStatus.PASS, 'Error message pop up  ')
			
			WebUI.takeScreenshot(screenShot)
			extentTest.log(LogStatus.PASS, 'Verify through screenshot ')

			break
			
		case'Untitled':
		
		    WebUI.click(findTestObject('AppComposer/Executableinput'))
		    extentTest.log(LogStatus.PASS, 'Click to add exec commands')
		    WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
		    WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add Exec commands - ' + exec)
		
		    WebUI.click(findTestObject('AppComposer/Save'))
		    extentTest.log(LogStatus.PASS, 'Click on Save button')
			
			
			//WebUI.mouseOver(findTestObject('GenericObjects/TitleLink_Jobs'))
			//WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
			
			WebUI.verifyElementPresent(findTestObject('AppComposer/Untitled_app'), 3)
			extentTest.log(LogStatus.PASS, 'Verify app created with name untitled')
			
			
		    break
			
		

		case'Duplicate App':

			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to  App name')
			
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name of an existing app -' + app)
			WebUI.click(findTestObject('AppComposer/Executableinput'))
			extentTest.log(LogStatus.PASS, 'Click to add Exec commands')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add Exec commands - ' + exec)

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')
			//WebUI.verifyElementPresent(findTestObject('AppComposer/AppExist'), 3)
			extentTest.log(LogStatus.PASS, 'Allow to create app with duplicate name')


			break

		case'Special char':
		    
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
			

			WebUI.delay(3)

			WebUI.click(findTestObject('AppComposer/JobsTab'))
			extentTest.log(LogStatus.PASS, 'Navigated to Jobs Tab')

			WebUI.verifyElementPresent(findTestObject('AppComposer/Test12App'), 3)
			extentTest.log(LogStatus.PASS, 'Verify app ' + app +' on Jobs page')
			break

		case'Empty Exec':

			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)
			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')

			WebUI.verifyElementPresent(findTestObject('AppComposer/Error'), 3)
			extentTest.log(LogStatus.PASS, 'Verify pop up exec commands required')

			break
			
		case'Input type':
		
		     //WebUI.click(findTestObject('AppComposer/Basic-SMP'))
			 
             WebUI.verifyElementPresent(findTestObject('AppComposer/Basic-SMP'), 3)
			 extentTest.log(LogStatus.PASS, 'Verify by default input type is Basic-SMP')
		       
		     WebUI.verifyElementPresent(findTestObject('AppComposer/Cores_pre'), 3)
			 extentTest.log(LogStatus.PASS, 'Verify cores present in pre window')
		
		     
		     break
			 
			 
	    case'Space':
		
		      WebUI.click(findTestObject('AppComposer/JobsTab'))
		      extentTest.log(LogStatus.PASS, 'Navigated to Jobs Tab')
			  
			  WebUI.verifyElementNotPresent(findTestObject('AppComposer/TestSpace'), 3)
			  extentTest.log(LogStatus.PASS, 'Verify app ' + app+ ' not present in Jobs page')
  
			  WebUI.click(findTestObject('Preferences/Profiletab'))
			  extentTest.log(LogStatus.PASS, 'Click on profile tab')
			  WebUI.delay(2)
  
			  WebUI.click(findTestObject('AppComposer/Appcomposer'))
			  extentTest.log(LogStatus.PASS, 'Click on App composer')
		
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
			  
			  WebUI.delay(3)
			  
		      WebUI.click(findTestObject('AppComposer/JobsTab'))
		      extentTest.log(LogStatus.PASS, 'Navigated to Jobs Tab')
			  
			  WebUI.verifyElementPresent(findTestObject('AppComposer/TestSpace'), 3)
			  extentTest.log(LogStatus.PASS, 'Verify app '+ app +' on Jobs page')
		
		
		     break

		case'Delete App':

			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)

			WebUI.click(findTestObject('AppComposer/InputFile'))
			WebUI.setText(findTestObject('AppComposer/InputFile'), input)
			extentTest.log(LogStatus.PASS, 'Add Input file -' + input)

			WebUI.click(findTestObject('AppComposer/Executableinput'))
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add exec commands')

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save button')

			WebUI.delay(3)

			WebUI.click(findTestObject('AppComposer/JobsTab'))
			extentTest.log(LogStatus.PASS, 'Navigated to Jobs Tab')

			WebUI.verifyElementPresent(findTestObject('AppComposer/TestApp'), 3)
			extentTest.log(LogStatus.PASS, 'Verify app present in Jobs page')

			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(LogStatus.PASS, 'Click on profile tab')
			WebUI.delay(2)

			WebUI.click(findTestObject('AppComposer/Appcomposer'))
			extentTest.log(LogStatus.PASS, 'Click on App composer')
			
			WebUI.delay(4)

			WebUI.click(findTestObject('AppComposer/LeftIcon'))
			extentTest.log(LogStatus.PASS, 'Click on left icon Test App')

			WebUI.click(findTestObject('AppComposer/Delete'))
			extentTest.log(LogStatus.PASS, 'Click on delete button')
			
			
			WebUI.verifyElementPresent(findTestObject('AppComposer/DeletePopUp'), 3)
			extentTest.log(LogStatus.PASS, 'Verify delete app notification')
			
			WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
			extentTest.log(LogStatus.PASS, 'Click on Notification button to open Notification Panel')
		   
			String msg = 'Deleted Test application successfully'
	   
			WebUI.delay(2)
			result = WebUI.verifyElementPresent(findTestObject('Object Repository/AppComposer/DeletePopUp'),5)
			println("notification status - "+result)
						   
		   
			if (result)
			{
				extentTest.log(LogStatus.PASS, ' Deleted application and verified notification')
				extentTest.log(LogStatus.PASS, ('Notification with msg - "' + msg) + '" is listed')
			}
			else
			{
				//extentTest.log(LogStatus.PASS, fileToCheck + ' - Not pasted')
				extentTest.log(LogStatus.FAIL)
			}
			WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
			extentTest.log(LogStatus.PASS, 'Click on Notification button to close Notification Panel')
		   

			break

		case'Manage Para':

			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)

			WebUI.click(findTestObject('AppComposer/InputFile'))
			WebUI.setText(findTestObject('AppComposer/InputFile'), input)
			extentTest.log(LogStatus.PASS, 'Click on Input file -' + input)

			WebUI.click(findTestObject('AppComposer/Executableinput'))
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add exec commands - ' + exec)

			WebUI.click(findTestObject('AppComposer/WallTime_arrow'))
			extentTest.log(LogStatus.PASS, 'Click on wall time arrow')
			WebUI.verifyElementPresent(findTestObject('AppComposer/Tickmark'), 3)

			WebUI.click(findTestObject('AppComposer/Manage_Parameter'))
			extentTest.log(LogStatus.PASS, 'Click on Manage parameter')

			WebUI.click(findTestObject('AppComposer/Project_lbl'))
			extentTest.log(LogStatus.PASS, 'Select project parameter')

			WebUI.click(findTestObject('AppComposer/Single_arrow'))
			extentTest.log(LogStatus.PASS, 'Click on single arrow')

			WebUI.click(findTestObject('AppComposer/Done_btn'))
			extentTest.log(LogStatus.PASS, 'Click on Done button')

			//WebUI.click(findTestObject('AppComposer/Dotmark'))
			//extentTest.log(LogStatus.PASS, 'Click on all fields in preview and test window')

			WebUI.verifyElementPresent(findTestObject('AppComposer/Project_pre'), 3)
			extentTest.log(LogStatus.PASS, 'Verify project label present')


			break

		case'Preview':
			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click on app name')
			
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)

			WebUI.click(findTestObject('AppComposer/InputFile'))
			WebUI.setText(findTestObject('AppComposer/InputFile'), input)
			extentTest.log(LogStatus.PASS, 'Click on Input File -' + input)

			WebUI.click(findTestObject('AppComposer/Executableinput'))
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add exec commands - ' + exec)

			WebUI.click(findTestObject('AppComposer/WallTime_arrow'))
			extentTest.log(LogStatus.PASS, 'Click on wall time arrow')
		//WebUI.verifyElementPresent(findTestObject('AppComposer/Tickmark'), 3)

			String wall=WebUI.getAttribute(findTestObject('AppComposer/WallTime_input'), 'value')

			//println ("Value --- "+ wall)
		    extentTest.log(LogStatus.PASS, 'Print wall time value -' + wall)
			
			
			//extentTest.log(LogStatus.PASS, 'Print wall time value')
			
			WebUI.click(findTestObject('AppComposer/WallTime_pre'))
		//WebUI.verifyElementPresent(findTestObject('AppComposer/Tickmark'), 3)
			extentTest.log(LogStatus.PASS, 'Click on wall time in preview and test window')

			String wall1=WebUI.getAttribute(findTestObject('AppComposer/WallTime_pre'), 'value')

			//println ("Value --- "+ wall1)
			
			
			extentTest.log(LogStatus.PASS, 'Print wall time value in preview and test window-' + wall1)
			
			if(wall == wall1){

				println('pass')
				extentTest.log(LogStatus.PASS, 'Both values matched hence passed')
			}
			else
			{
				println('failed')
				extentTest.log(LogStatus.FAIL, 'Both values did not match hence fail')
			}
			/*
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Cores_arrow'))
			extentTest.log(LogStatus.PASS, 'Click on no of cores arrow')
            
			
			WebUI.click(findTestObject('AppComposer/Number of Cores'))
			WebUI.delay(2)
			String core=WebUI.getAttribute(findTestObject('Object Repository/AppComposer/cores_vlaue'), 'value')

			println ("Value --- "+ core)
			extentTest.log(LogStatus.PASS, 'Print no of cores value')
			
			WebUI.click(findTestObject('AppComposer/Cores_pre'))
		//WebUI.verifyElementPresent(findTestObject('AppComposer/Tickmark'), 3)

			String core1=WebUI.getAttribute(findTestObject('AppComposer/Cores_pre'), 'value')

			println ("Value --- "+ core1)
			extentTest.log(LogStatus.PASS, 'Print no of cores value in prview and test window')

			if(core == core1){

				println('pass')
				extentTest.log(LogStatus.PASS, 'If both the values  match test case will pass')
			}
			else
			{
				println('failed')
				extentTest.log(LogStatus.FAIL, 'If both the values not match test case will fail')
			}
 
			WebUI.click(findTestObject('AppComposer/AddFiles_arrow'))
			extentTest.log(LogStatus.PASS, 'Click on add files')
			
			WebUI.click(findTestObject('AppComposer/AddFiles_tick'))
			extentTest.log(LogStatus.PASS, 'Click on add file arrow')
			
			WebUI.verifyElementPresent(findTestObject('AppComposer/AddFiles_label'), 3)
			extentTest.log(LogStatus.PASS, 'Verify add files label is present')
			
			*/
			break

		case'JM pop up':

			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(LogStatus.PASS, 'Click to Entered App name')
			
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)

			WebUI.click(findTestObject('AppComposer/Executableinput'))
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add exec commands -' + exec)

			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on save button')
			
			//WebUI.takeScreenshot(screenShot)
			//WebUI.delay(4)
			
			WebUI.delay(4)
			WebUI.click(findTestObject('AppComposer/TestApp_left'))
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
			WebUI.click(findTestObject('AppComposer/TestJm_left'))
			extentTest.log(LogStatus.PASS, 'Click on app on left side')
			
			WebUI.click(findTestObject('AppComposer/Submit_btn'))
			extentTest.log(LogStatus.PASS, 'Click on submit and test  button')
			
			WebUI.delay(4)
			
			//WebUI.verifyElementPresent(findTestObject('AppComposer/RunningFolder'), 3)
			extentTest.log(LogStatus.PASS, 'Verify running folder present')
			
			WebUI.delay(3)
			
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
			
		case'Saved App':
		     
		    WebUI.click(findTestObject('AppComposer/AppName'))
		    extentTest.log(LogStatus.PASS, 'Click to Entered App name')
		
		    WebUI.doubleClick(findTestObject('AppComposer/AppName'))
		    rob.keyPress(KeyEvent.VK_BACK_SPACE)
		    rob.keyRelease(KeyEvent.VK_BACK_SPACE)
		    WebUI.setText(findTestObject('AppComposer/AppName'), '')
		    WebUI.setText(findTestObject('AppComposer/AppName'), app)
		    extentTest.log(LogStatus.PASS, 'Entered App name -' + app)

		    WebUI.click(findTestObject('AppComposer/Executableinput'))
		    WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
		    WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
		    extentTest.log(LogStatus.PASS, 'Add exec commands -' + exec)

		    WebUI.delay(3)
		    WebUI.click(findTestObject('AppComposer/Save'))
		    extentTest.log(LogStatus.PASS, 'Click on save button')
			
			WebUI.delay(4)
			WebUI.click(findTestObject('AppComposer/TestAppSaved'))
			extentTest.log(LogStatus.PASS, 'Click on TestSaved app on left side')
			
			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on save button')
			
			WebUI.verifyElementPresent(findTestObject('AppComposer/InputF'), 3)
			//WebUI.click(findTestObject('AppComposer/InputF'))
			def filePath = (RunConfiguration.getProjectDir() + '/Upload/JobFiles/Running.sh')
			def newFP=(new generateFilePath.filePath()).getFilePath(filePath)
			println(newFP)
			WebUI.uploadFile(findTestObject('AppComposer/InputF'), newFP )
			extentTest.log(LogStatus.PASS, 'Upload input file')
			
			WebUI.delay(4)
			WebUI.click(findTestObject('AppComposer/TestAppSaved'))
			extentTest.log(LogStatus.PASS, 'Click on TestSaved app on left side')
			
			
			WebUI.click(findTestObject('AppComposer/Submit_btn'))
			extentTest.log(LogStatus.PASS, 'Click on submit and test  button')
			
			WebUI.click(findTestObject('AppComposer/CloseButton'))
			extentTest.log(LogStatus.PASS, 'Click on cancel ')
			
			
			WebUI.click(findTestObject('AppComposer/JobsTab'))
			extentTest.log(LogStatus.PASS, 'Navigated to Jobs Tab')
			
			WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)
			
			def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))
			
			CustomKeywords.'operations_JobsModule.GetJobRowDetails.getJobID'(jobText)
			
			 
			
				extentTest.log(LogStatus.PASS, 'Job ID - ' + GlobalVariable.G_JobID)
			
			
		
		    break

		case'No inputfile':

			WebUI.click(findTestObject('AppComposer/AppName'))
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)

			WebUI.click(findTestObject('AppComposer/Executableinput'))
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add exec commands -' + exec)
			

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save ')
			 
			WebUI.verifyElementPresent(findTestObject('AppComposer/InputF'), 3)
			extentTest.log(LogStatus.PASS, 'Verify input file is not present in preview window' )
			

			WebUI.click(findTestObject('AppComposer/Submit_btn'))
			extentTest.log(LogStatus.PASS, 'Click on Submit and test button')
			
			WebUI.takeScreenshot(screenShot)
			extentTest.log(LogStatus.PASS, 'Verify through screenshot ')

			break

		case'Queued state':
			WebUI.click(findTestObject('AppComposer/AppName'))
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(LogStatus.PASS, 'Entered App name -' + app)

			WebUI.click(findTestObject('AppComposer/Executableinput'))
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(LogStatus.PASS, 'Add exec commands -' + exec)

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save')
			
			WebUI.delay(2)
			WebUI.click(findTestObject('AppComposer/DemoApp'))
			extentTest.log(LogStatus.PASS, 'Click on app on left side')
			
			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(LogStatus.PASS, 'Click on Save')

			WebUI.delay(3)
			WebUI.doubleClick(findTestObject('AppComposer/Cores_pre'))
			rob.keyPress(KeyEvent.VK_BACK_SPACE)
			rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			//WebUI.setText(findTestObject('AppComposer/Cores_pre'), '')
			WebUI.setText(findTestObject('AppComposer/Cores_pre'),cores)
			extentTest.log(LogStatus.PASS, 'Add no of cores in preview window')

           

			WebUI.verifyElementPresent(findTestObject('AppComposer/InputF'), 3)
			//WebUI.click(findTestObject('AppComposer/InputF'))
			def filePath = (RunConfiguration.getProjectDir() + '/Upload/JobFiles/Running.sh')
			def newFP=(new generateFilePath.filePath()).getFilePath(filePath)
			println(newFP)
			WebUI.uploadFile(findTestObject('AppComposer/InputF'), newFP )
			extentTest.log(LogStatus.PASS, 'Upload input file')
			
			
			
			WebUI.click(findTestObject('AppComposer/Submit_btn'))
			extentTest.log(LogStatus.PASS, 'Click on submit button')
			
	       // WebUI.verifyElementPresent(findTestObject('AppComposer/JobStatus'), 3)
			extentTest.log(LogStatus.PASS, 'Verify Job status notification')
			
			WebUI.delay(4)
			//WebUI.verifyElementPresent(findTestObject('AppCompser/Close'), 3)
			println(WebUI.verifyElementPresent(findTestObject('AppComposer/Close'), 3))
			
			WebUI.click(findTestObject('AppComposer/Close'))
			extentTest.log(LogStatus.PASS, 'Click on close notification')
			
			/*
			
			WebUI.click(findTestObject('AppComposer/JobsTab'))
			extentTest.log(LogStatus.PASS, 'Navigated to Jobs Tab')
			
			
			WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)
			
			def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))
			
			CustomKeywords.'operations_JobsModule.GetJobRowDetails.getJobID'(jobText)
			
			 
			
				extentTest.log(LogStatus.PASS, 'Job ID - ' + GlobalVariable.G_JobID)
	        
*/
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

