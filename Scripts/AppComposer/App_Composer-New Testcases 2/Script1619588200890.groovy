import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status


import org.openqa.selenium.Keys

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


import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration

/*
 'Login into PAW '
 WebUI.callTestCase(findTestCase('XRepeated_TC/Login'), [('username') : GlobalVariable.G_userName, ('password') : GlobalVariable.G_Password],
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
def result
WebUI.delay(2)

try
{
	WebUI.delay(2)

	WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))
	extentTest.log(Status.PASS, 'Click on profile tab')
	WebUI.delay(2)

	WebUI.click(findTestObject('AppComposer/Appcomposer'))
	extentTest.log(Status.PASS, 'Click on App composer')
	
	WebUI.delay(2)
	
	boolean hpcclusterTmp=	WebUI.waitForElementVisible(findTestObject('Object Repository/AppComposer/hpcluster_temp'), 10)
	if(hpcclusterTmp) {
		
		WebUI.click(findTestObject('Object Repository/AppComposer/hpcluster_temp'))
		WebUI.mouseOver(findTestObject('Object Repository/AppComposer/hpcluster_temp'))
		//WebUI.delay(2)
		
		boolean newAppTemp=WebUI.verifyElementPresent(findTestObject('Object Repository/AppComposer/NewApp_temp'), 5)
		if(newAppTemp) {
		WebUI.click(findTestObject('Object Repository/AppComposer/NewApp_temp'))
		extentTest.log(Status.PASS, 'Clicked on new Appcomposer')}
	}
	switch(userChoice)
	{

		/*case'Unpublish':
		

		
			WebUI.click(findTestObject('AppComposer/Publishall'))
			extentTest.log(Status.PASS, 'Click on Publish to all button')
			WebUI.delay(2)
			WebUI.waitForElementVisible(findTestObject('AppComposer/Ok_btn'),10)
			WebUI.click(findTestObject('AppComposer/Ok_btn'))
			WebUI.delay(5)
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(Status.PASS, 'Select the app ' + app +' created and click on ellipses')
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Unpublish'))
			extentTest.log(Status.PASS, 'Click on Unpublish option')
			//WebUI.waitForElementPresent(findTestObject('AppComposer/Unpubpop'), 5)

			
			
			result=WebUI.verifyElementPresent(findTestObject('AppComposer/Unpubpop'), 10)
			println("result +++++"+result)
			if(result)
							
				extentTest.log(Status.PASS, 'Verify ' + app +' Application is unpublished successfully and not present in jobs page')
			else
				extentTest.log(Status.FAIL, 'Failed to verify ')


			break*/

		case'Share':

		CustomKeywords.'appcomposer.Create_NewApp.create_app'(app,exec, extentTest)
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(Status.PASS, 'Select the app created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Share'))
			extentTest.log(Status.PASS, 'Click on Share option')

			WebUI.click(findTestObject('AppComposer/Input'))
			extentTest.log(Status.PASS, 'Click on Input text box')
			WebUI.setText(findTestObject('AppComposer/Input'), '')
			WebUI.setText(findTestObject('AppComposer/Input'), 'rohini')
			extentTest.log(Status.PASS, 'Add username rohini to share the apllication')
			WebUI.click(findTestObject('AppComposer/Share_btn'))
			extentTest.log(Status.PASS, 'Click on share button')

			
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))
			extentTest.log(Status.PASS, 'Click on profile tab')
			WebUI.delay(2)

			WebUI.click(findTestObject('Landing_Page/ListItem_Logout'))
			extentTest.log(Status.PASS, 'Click on logout')

			WebUI.verifyElementVisible(findTestObject('LogOut-PopUp/Title_Logout'))

			WebUI.delay(2)

			WebUI.click(findTestObject('GenericObjects/btn_Yes'))
			extentTest.log(Status.PASS, 'Click on yes button')
			WebUI.delay(2)

			WebUI.click(findTestObject('AppComposer/Login'))
			extentTest.log(Status.PASS, 'Click on Login again')

			WebUI.setText(findTestObject('LoginPage/username_txtbx'),'rohini')
			extentTest.log(Status.PASS, 'Enter username rohini ')

			WebUI.setText(findTestObject('LoginPage/password_txtbx'), 'rohini')
			extentTest.log(Status.PASS, 'Enter  password  rohini')

			WebUI.click(findTestObject('LoginPage/login_btn'))
			extentTest.log(Status.PASS, 'Click on Login')
		
			WebUI.delay(3)
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))
			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('AppComposer/Appcomposer'))
			extentTest.log(Status.PASS, 'Click on App composer')
			WebUI.delay(3)
		

			testobj = WebUI.modifyObjectProperty(findTestObject('AppComposer/application_name'), 'data-automation-id', 'equals', app,true)
			println(testobj)
		     def sharedapp=WebUI.verifyElementPresent(testobj, 5)
			 
			
			 extentTest.log(Status.PASS, 'click on the share app')
			 WebUI.click(testobj)
			 WebUI.delay(3)
			 WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)
			 
					 def notification = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))
			 
					 extentTest.log(Status.PASS, 'Notification Generated ' + notification)
					
			 WebUI.click(findTestObject('AppComposer/JobsTab'))
			 
			 extentTest.log(Status.PASS, 'Navigated to Jobs Tab')
			 WebUI.delay(3)
			
			testobj = WebUI.modifyObjectProperty(findTestObject('AppComposer/App_name_jobspage'), 'data-automation-id', 'equals', app,true)
			def shareappjobspage=WebUI.verifyElementPresent(testobj, 6)
		
			if(sharedapp&&shareappjobspage) {
			extentTest.log(Status.PASS, 'Verify Shareapp is present in app composer and in jobs page ')
			result=true
			}
			else
				extentTest.log(Status.FAIL, 'Failed to verify the app  ')
				
				

			break


		case'Shareleft':
		CustomKeywords.'appcomposer.Create_NewApp.create_app'(app,exec, extentTest)

			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(Status.PASS, 'Select the app '+ app +' created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Share'))
			extentTest.log(Status.PASS, 'Click on Share option')

			WebUI.click(findTestObject('AppComposer/Input'))
			extentTest.log(Status.PASS, 'Click on Input text box')
			WebUI.setText(findTestObject('AppComposer/Input'), '')
			WebUI.setText(findTestObject('AppComposer/Input'), 'bhuvana')
			extentTest.log(Status.PASS, 'Add username bhuvana to share the apllication')
			WebUI.click(findTestObject('AppComposer/Share_btn'))
			extentTest.log(Status.PASS, 'Click on Share button')

			result=WebUI.verifyElementPresent(findTestObject('AppComposer/Shareleft'), 6)
			if(result)
			extentTest.log(Status.PASS, 'verified the  Shareleft application in left side of appcomposer')
			else
				
				extentTest.log(Status.FAIL, 'failed to verify the application')

			break

		case'Shareuser':

		
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(Status.PASS, 'Select the app ' + app + ' created and click on ellipses')
			WebUI.delay(2)
			WebUI.click(findTestObject('AppComposer/Share'))
			extentTest.log(Status.PASS, 'Click on Share option')

			WebUI.click(findTestObject('AppComposer/Input'))
			extentTest.log(Status.PASS, 'Click on Input text box')
			WebUI.setText(findTestObject('AppComposer/Input'), '')
			WebUI.setText(findTestObject('AppComposer/Input'), 'bhuvana'+',' + ' rohini')
			extentTest.log(Status.PASS, 'Add username bhuvana , rohini to share the apllication')
			WebUI.click(findTestObject('AppComposer/Share_btn'))
			extentTest.log(Status.PASS, 'Click on Share button')
			WebUI.delay(2)
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))
			extentTest.log(Status.PASS, 'Click on profile tab')
			WebUI.delay(2)

			WebUI.click(findTestObject('Landing_Page/ListItem_Logout'))
			extentTest.log(Status.PASS, 'Click on logout')

			WebUI.verifyElementVisible(findTestObject('LogOut-PopUp/Title_Logout'))

			WebUI.delay(2)

			WebUI.click(findTestObject('GenericObjects/btn_Yes'))
			extentTest.log(Status.PASS, 'Click on yes button')
			WebUI.delay(2)

			WebUI.click(findTestObject('AppComposer/Login'))
			extentTest.log(Status.PASS, 'Click on Login again')

			WebUI.setText(findTestObject('LoginPage/username_txtbx'),'rohini')
			extentTest.log(Status.PASS, 'Enter username rohini ')

			WebUI.setText(findTestObject('LoginPage/password_txtbx'), 'rohini')
			extentTest.log(Status.PASS, 'Enter  password  rohini')

			WebUI.click(findTestObject('LoginPage/login_btn'))
			extentTest.log(Status.PASS, 'Click on Login')
			WebUI.delay(3)
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))
			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('AppComposer/Appcomposer'))
			extentTest.log(Status.PASS, 'Click on App composer')
			WebUI.delay(3)
		
			testobjapp = WebUI.modifyObjectProperty(findTestObject('AppComposer/application_name'), 'data-automation-id', 'equals', app,true)
		     def shareapp=WebUI.verifyElementPresent(testobjapp,8)
		

           if(shareapp)
           {
			   extentTest.log(Status.PASS, 'Verify Shareapp is present only in the Appcomposer page ')
			   result=true
           }
		   else {
			   extentTest.log(Status.FAIL, 'Failed to verify the shareapp ')
			   result=false
		   }
	
			break

		case'Without Publish':

		
		CustomKeywords.'appcomposer.Create_NewApp.create_app'(app,exec, extentTest)
			WebUI.click(findTestObject('AppComposer/Withoutpublish'))
			extentTest.log(Status.PASS, 'Click on app on left side')

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(Status.PASS, 'Click on save button')
			WebUI.delay(3)

			WebUI.verifyElementPresent(findTestObject('AppComposer/InputF'), 10)
		//WebUI.click(findTestObject('AppComposer/InputF'))
			WebUI.delay(4)
			def filePath = (RunConfiguration.getProjectDir() + '/Upload/JobFiles/Running.sh')
			def newFP=(new generateFilePath.filePath()).getFilePath(filePath)
			println(newFP)
			WebUI.uploadFile(findTestObject('AppComposer/InputF'), newFP )
			extentTest.log(Status.PASS, 'Upload input file')

			WebUI.delay(2)
			WebUI.click(findTestObject('AppComposer/Withoutpublish'))
			extentTest.log(Status.PASS, 'Click on app on left side')

			WebUI.click(findTestObject('AppComposer/Submit_btn'))
			extentTest.log(Status.PASS, 'Click on submit and test  button')

		
			
/*
           WebUI.verifyElementPresent(findTestObject('AppComposer/RunningFolder'),10)
			extentTest.log(Status.PASS, 'Verify running folder present')
			def jobidpopup=WebUI.getText(findTestObject('AppComposer/Jobid_popup'))
			

			WebUI.click(findTestObject('AppComposer/CloseButton'))
			extentTest.log(Status.PASS, 'Click on cancel ')


			WebUI.click(findTestObject('AppComposer/JobsTab'))
			extentTest.log(Status.PASS, 'Navigated to Jobs Tab')

			WebUI.click(findTestObject('Landing_Page/Btn_Notifiction2'))
			extentTest.log(Status.PASS, 'Click on Notification button to open Notification Panel')*/


			WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 10)

			def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))

			def jobid=CustomKeywords.'operations_JobsModule.GetJobRowDetails.getJobID'(jobText)
		 result=WebUI.verifyElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)
			
			if(result)
				extentTest.log(Status.PASS, 'Job Submission Done:: jobid is    ' + jobid)
				else
					extentTest.log(Status.FAIL, 'Failed to verify the jobid ')
	
	
				break
			
		/*	
			if(jobid== jobidpopup) {
				extentTest.log(Status.PASS, 'Job Submission Done:: jobid verified   ' + jobid)
			   result=true
			}
			else {
				extentTest.log(Status.FAIL, 'Failed to verify the jobid ' + jobid)
				result=false
			}*/
			
			
			
			
			
             break




		case'Rename':

		
		//CustomKeywords.'appcomposer.Create_NewApp.create_app'(app,exec, extentTest)
		//TestObject appdefname = WebUI.modifyObjectProperty(findTestObject('Object Repository/AppComposer/application_name'),'data-automation-id', 'equals', app, true)
		WebUI.click(findTestObject('Object Repository/AppComposer/application_name'))
		extentTest.log(Status.PASS, 'Select the Existing app def '+ app)
		WebUI.delay(3)
	
			WebUI.mouseOver(findTestObject('Object Repository/AppComposer/application_name'))

			WebUI.click(findTestObject('Object Repository/AppComposer/application_name'))
			extentTest.log(Status.PASS, 'Click on ' + app + 'on left side')

			WebUI.mouseOver(findTestObject('AppComposer/AppName'))


			extentTest.log(Status.PASS, 'Click to Entered App name')
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
	
			WebUI.sendKeys(findTestObject('AppComposer/AppName'), Keys.chord(Keys.CONTROL,'a'))
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'),'Renamenew')
			extentTest.log(Status.PASS, 'Rename the created app - Renamenew' )
			WebUI.click(findTestObject('AppComposer/Executableinput'))
			
			extentTest.log(Status.PASS, 'Click to add exec commands')
			
			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			
		   extentTest.log(Status.PASS, 'Add Exec commands - ' + exec)
			
			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(Status.PASS, 'Click on Save button')
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)

			def notifn_rename = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))

			extentTest.log(Status.PASS, 'Notification Generated ' + notifn_rename)
				WebUI.delay(3)

			WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
			extentTest.log(Status.PASS, 'Click on Jobs Tab')
			

			result=WebUI.verifyElementPresent(findTestObject('AppComposer/Renamenew'), 10)
			if(result)
			extentTest.log(Status.PASS, 'Verify app has been renamed to Renamenew and pesent on jobs page')
			else
				extentTest.log(Status.FAIL, 'Failed to verify the renamed app')


			break

		case'Edit':
		   
		CustomKeywords.'appcomposer.Create_NewApp.create_app'(app,exec, extentTest)
	
			
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(Status.PASS, 'Select the app '+ app +' created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Edit'))
			extentTest.log(Status.PASS, 'Click on Edit option')

			WebUI.mouseOver(findTestObject('AppComposer/Editapp'))

			WebUI.click(findTestObject('AppComposer/Editapp'))
			extentTest.log(Status.PASS, 'Click on ' + app + 'on left side')

			//WebUI.mouseOver(findTestObject('AppComposer/AppName'))

		WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(Status.PASS, 'Click to Entered App name')
			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
		//	rob.keyPress(KeyEvent.VK_BACK_SPACE)
		//	rob.keyRelease(KeyEvent.VK_BACK_SPACE)
			WebUI.sendKeys(findTestObject('AppComposer/AppName'), Keys.chord(Keys.CONTROL,'a'))
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'),'Editappnew')
			extentTest.log(Status.PASS, 'Rename the created app - Editappnew' )

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(Status.PASS, 'Click on Save button')
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)

			def editnotification = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))

			extentTest.log(Status.PASS, 'Notification Generated ' + editnotification)
			WebUI.delay(3)

			WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
			extentTest.log(Status.PASS, 'Click on Jobs Tab')

			result=WebUI.verifyElementPresent(findTestObject('AppComposer/Editappnew'), 3)
		
			  if(result)
				extentTest.log(Status.PASS, 'Verify app has been renamed to Editappnew and present on jobs page')
				else
			  extentTest.log(Status.FAIL, 'Failed to verify the renamed app')


			break

		case'Options':

		testobjapp2 = WebUI.modifyObjectProperty(findTestObject('AppComposer/application_name'), 'data-automation-id', 'equals', app,true)
		def withOutPublish=WebUI.verifyElementPresent(testobjapp2,5)
		if(withOutPublish) {
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(Status.PASS, 'Select the app ' + app +' created and click on ellipses')
			}
			def iseditpresent=WebUI.verifyElementPresent(findTestObject('AppComposer/Edit'), 3)
		    def iscopypresent=WebUI.verifyElementPresent(findTestObject('AppComposer/Copy'), 3)
			def issharepresent=WebUI.verifyElementPresent(findTestObject('AppComposer/Share'), 3)
		    def isdeletepresent=WebUI.verifyElementPresent(findTestObject('AppComposer/Delete'), 3)
			
			if(iscopypresent &&issharepresent && isdeletepresent &&iseditpresent) {
				extentTest.log(Status.PASS, 'Verify Edit option is present'+iseditpresent)
				extentTest.log(Status.PASS, 'Verify Copy option is present'+iscopypresent)
				extentTest.log(Status.PASS, 'Verify Share option is present'+issharepresent)
				extentTest.log(Status.PASS, 'Verify Delete option is present'+isdeletepresent)
				result=true
			}
			else {
				extentTest.log(Status.PASS, 'Failed to verify Edit option '+iseditpresent )
				extentTest.log(Status.PASS, 'Failed to verify Copy option '+iscopypresent )
				extentTest.log(Status.PASS, 'Failed to verify Share option '+issharepresent )
				extentTest.log(Status.PASS, 'Failed to verify Delete option '+isdeletepresent )
				result=false
			}

			break
			case 'Delete':
			CustomKeywords.'appcomposer.Create_NewApp.create_app'(app,exec, extentTest)
			
			
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(Status.PASS, 'Select the app ' + app +' created and click on ellipses')
			WebUI.click(findTestObject('AppComposer/Deleteapp'))
			extentTest.log(Status.PASS, 'Click on Delete option')
			WebUI.verifyElementPresent(findTestObject('AppComposer/DelPop'), 3)
			WebUI.waitForElementPresent(findTestObject('AppComposer/DelPop'), 5)
			
				def deletemsgnotfn = WebUI.getText(findTestObject('AppComposer/DelPop'))
				deleteobj = WebUI.modifyObjectProperty(findTestObject('AppComposer/application_name'), 'title', 'equals', app,true)
				result=WebUI.verifyElementNotPresent(deleteobj, 5)
				
				if(result)
			
				  extentTest.log(Status.PASS, 'Notification Generated ' + deletemsgnotfn)
				else
					extentTest.log(Status.FAIL, 'failed to deleted the app')
				
			
			
			break

		case'Edit Not':
		CustomKeywords.'appcomposer.Create_NewApp.create_app'(app,exec, extentTest)
			WebUI.click(findTestObject('AppComposer/Publishall'))
			extentTest.log(Status.PASS, 'Click on Publish to all button')
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Ok_btn'))
			extentTest.log(Status.PASS, 'Click on Ok button')
			WebUI.delay(2)
			WebUI.click(findTestObject('Object Repository/AppComposer/Ellipses'))
			extentTest.log(Status.PASS, 'Select the app created and click on ellipses')
			WebUI.delay(2)

			result=WebUI.verifyElementNotPresent(findTestObject('AppComposer/Edit_globalPublishapp'), 10)
			if(result)
			extentTest.log(Status.PASS, 'Verify Edit option not present')
			else
				extentTest.log(Status.FAIL, 'Failed to verfy')

			break

		case'Publish App':
		CustomKeywords.'appcomposer.Create_NewApp.create_app'(app,exec, extentTest)


			WebUI.click(findTestObject('AppComposer/Publishall'))
			extentTest.log(Status.PASS, 'Click on Publish to all button')
			WebUI.delay(5)
			WebUI.click(findTestObject('AppComposer/Ok_btn'))
			extentTest.log(Status.PASS, 'Click on Ok button , PAS configuration change pop up')
			WebUI.delay(2)

			WebUI.click(findTestObject('AppComposer/Publishapp'))
			extentTest.log(Status.PASS, 'Click on ' + app+ ' in left navigation , only admin user able to publish it globally')

			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))
			extentTest.log(Status.PASS, 'Click on profile tab')
			WebUI.delay(2)

			WebUI.click(findTestObject('Landing_Page/ListItem_Logout'))
			extentTest.log(Status.PASS, 'Click on logout')

			WebUI.verifyElementVisible(findTestObject('LogOut-PopUp/Title_Logout'))

			WebUI.delay(2)

			WebUI.click(findTestObject('GenericObjects/btn_Yes'))
			extentTest.log(Status.PASS, 'Click on yes button')
			WebUI.delay(3)

			WebUI.click(findTestObject('AppComposer/Login'))
			extentTest.log(Status.PASS, 'Click on Login again')

			WebUI.setText(findTestObject('LoginPage/username_txtbx'),'rohini')
			extentTest.log(Status.PASS, 'Enter username rohini ')

			WebUI.setText(findTestObject('LoginPage/password_txtbx'), 'rohini')
			extentTest.log(Status.PASS, 'Enter  password  rohini')

			WebUI.click(findTestObject('LoginPage/login_btn'))
			extentTest.log(Status.PASS, 'Click on Login')
			WebUI.delay(3)
		
			
			extentTest.log(Status.PASS, 'Navigated to Jobs Tab')
			result=WebUI.verifyElementPresent(findTestObject('AppComposer/Publishapp_jobs'), 8)
			if(result)
				extentTest.log(Status.PASS, 'Verify Publishapp is present in the  jobs page ')
				else
					extentTest.log(Status.FAIL, 'Failed to verify the publishapp')
					
			
			WebUI.delay(3)


			break

	}

	if (result)
	{
		extentTest.log(Status.PASS, ('Verified ::  ' + TestCaseName) + ' :: Sucessfully')
	} else {
		extentTest.log(Status.FAIL, TestCaseName + ' :: failed')
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


