import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject



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

import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import  org.openqa.selenium.Keys

import internal.GlobalVariable as GlobalVariable



'Login into PAW '
/*
WebUI.callTestCase(findTestCase('XRepeated_TC/Login'), [('username') : GlobalVariable.G_AdminUser, ('password') : GlobalVariable.G_AdminPasswd],
FailureHandling.STOP_ON_FAILURE)

WebDriver driver = DriverFactory.getWebDriver()

EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)

// Get the driver wrapped inside
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()

// Cast the wrapped driver into RemoteWebDriver
RemoteWebDriver katalonWebDriver = ((wrappedWebDriver) as RemoteWebDriver)

String ReportFile=GlobalVariable.G_ReportName+".html"

def extent=CustomKeywords.'generateReports.GenerateReport.create'(ReportFile,GlobalVariable.G_Browser,GlobalVariable.G_BrowserVersion)
def Status = com.relevantcodes.extentreports.Status;

def extentTest = extent.startTest(TestCaseName)
String screenShot='ExtentReports/'+TestCaseName+userChoice+GlobalVariable.G_Browser+'.png'
def result
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
try
{
	WebUI.delay(2)
	//WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	
	WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))
	extentTest.log(Status.PASS, 'Click on profile tab')
	WebUI.delay(2)
	
	WebUI.click(findTestObject('Access_Management/Access_management'))
	extentTest.log(Status.PASS, 'Click on access management')
	
	switch(userChoice)
	{
		case 'user':
		
		WebUI.click(findTestObject('Access_Management/Add_rolebutton'))
		extentTest.log(Status.PASS, 'Click on add role')
	
		WebUI.click(findTestObject('Access_Management/Edit_role'))
		extentTest.log(Status.PASS, 'Click on edit role')
	
		WebUI.delay(2)
		
			//WebUI.click(findTestObject('Access_Management/Edit_roleid'))
			WebUI.delay(3)
				WebUI.doubleClick(findTestObject('Object Repository/AppComposer/Text'))
			 WebUI.sendKeys(findTestObject('Object Repository/AppComposer/Text'), Keys.chord(Keys.CONTROL,'a'))

				WebUI.setText(findTestObject('Object Repository/AppComposer/Text'), '')
				WebUI.setText(findTestObject('Object Repository/AppComposer/Text'), roleid)
				extentTest.log(Status.PASS, 'Add roleid name -' + roleid)
				
				WebUI.click(findTestObject('Access_Management/Confirm_button'))
				extentTest.log(Status.PASS, 'Click on save')
				
				WebUI.click(findTestObject('Object Repository/Access_Management/Security_tickmark'))
				extentTest.log(Status.PASS, 'Uncheck Security tickmark')
				
				WebUI.click(findTestObject('Object Repository/Access_Management/Audit_tickmark'))
				extentTest.log(Status.PASS, 'Uncheck Audit tickmark')
				
				WebUI.click(findTestObject('Object Repository/Access_Management/Application_tickmark'))
				extentTest.log(Status.PASS, 'Uncheck Application tickmark')
				
				WebUI.click(findTestObject('Access_Management/Save_role'))
				extentTest.log(Status.PASS, 'Click on Save')
				
				WebUI.delay(3)
				
				WebUI.click(findTestObject('Access_Management/Add_user'))
				extentTest.log(Status.PASS, 'Click on users')
		
				WebUI.click(findTestObject('Access_Management/Add_userbutton'))
				extentTest.log(Status.PASS, 'Click on add user')
		
				WebUI.click(findTestObject('Access_Management/Firstname'))
				WebUI.setText(findTestObject('Access_Management/Firstname'), firstname)
				extentTest.log(Status.PASS, 'Add first name - ' + firstname)
		
				WebUI.click(findTestObject('Access_Management/Lastname'))
				WebUI.setText(findTestObject('Access_Management/Lastname'), lastname)
				extentTest.log(Status.PASS, 'Add last name - ' + lastname)
		
				WebUI.click(findTestObject('Access_Management/Username'))
				WebUI.doubleClick(findTestObject('Access_Management/Username'))
				WebUI.delay(3)
				WebUI.doubleClick(findTestObject('Object Repository/Access_Management/Username_text'))
				WebUI.delay(2)
				WebUI.sendKeys(findTestObject('Object Repository/Access_Management/Username_text'),  Keys.chord(Keys.BACK_SPACE))
				
				WebUI.setText(findTestObject('Object Repository/Access_Management/Username_text'), '')
				WebUI.setText(findTestObject('Object Repository/Access_Management/Username_text'), username)
				extentTest.log(Status.PASS, 'Add username name - ' + username )
				
				WebUI.click(findTestObject('Access_Management/Add_role'))
				extentTest.log(Status.PASS, 'Click on add role')
				
				WebUI.click(findTestObject('Object Repository/Access_Management/userrole'))
				extentTest.log(Status.PASS, 'Click on userrole role ')
				
				WebUI.delay(2)
				
				//WebUI.click(findTestObject('Object Repository/Access_Management/Manager_role'))
		
				WebUI.click(findTestObject('Object Repository/AppComposer/Ok_btn'))
				extentTest.log(Status.PASS, 'Click on Ok button')
				WebUI.delay(2)
				WebUI.click(findTestObject('Object Repository/Access_Management/Save_btn'))
				extentTest.log(Status.PASS, 'Click on Save button')
				
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
				
				WebUI.setText(findTestObject('LoginPage/username_txtbx'),'newuser')
				extentTest.log(Status.PASS, 'Enter username newuser ')
				
				WebUI.setText(findTestObject('LoginPage/password_txtbx'), 'newuser')
				extentTest.log(Status.PASS, 'Enter  password  newuser')
				
				WebUI.click(findTestObject('LoginPage/login_btn'))
				extentTest.log(Status.PASS, 'Click on Login')
			
				WebUI.delay(2)
				WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))
				extentTest.log(Status.PASS, 'Click on profile tab')
				WebUI.delay(2)
				
				WebUI.click(findTestObject('Access_Management/Access_management'))
				extentTest.log(Status.PASS, 'Click on access management')
				
				WebUI.click(findTestObject('Access_Management/Add_user'))
				extentTest.log(Status.PASS, 'Click on users')
				
				WebUI.click(findTestObject('Access_Management/Add_userbutton'))
				extentTest.log(Status.PASS, 'Click on add user')
		
				WebUI.click(findTestObject('Access_Management/Firstname'))
				WebUI.setText(findTestObject('Access_Management/Firstname'),'samishtha')
				extentTest.log(Status.PASS, 'Add first name - samishtha' )
		
				WebUI.click(findTestObject('Access_Management/Lastname'))
				WebUI.setText(findTestObject('Access_Management/Lastname'), 'taneja')
				extentTest.log(Status.PASS, 'Add last name - taneja')
		
				WebUI.click(findTestObject('Access_Management/Username'))
				WebUI.doubleClick(findTestObject('Access_Management/Username'))
				WebUI.delay(3)
				WebUI.doubleClick(findTestObject('Object Repository/Access_Management/Username_text'))
				WebUI.delay(2)
								WebUI.sendKeys(findTestObject('Object Repository/Access_Management/Username_text'), Keys.chord(Keys.BACK_SPACE))

				WebUI.setText(findTestObject('Object Repository/Access_Management/Username_text'), '')
				WebUI.setText(findTestObject('Object Repository/Access_Management/Username_text'), 'abcd')
				extentTest.log(Status.PASS, 'Add username name - abcd' )
				
				WebUI.click(findTestObject('Object Repository/Access_Management/Save_btn'))
				extentTest.log(Status.PASS, 'Click on Save button')
				
				TestObject user =WebUI.modifyObjectProperty(findTestObject('Access_Management/Userinfo'),'text', 'equals', 'abcd', true)
				WebUI.click(user)
				extentTest.log(Status.PASS, 'Verify new user abcd created')
				
				WebUI.doubleClick(findTestObject('Object Repository/Access_Management/Secuser'))
				extentTest.log(Status.PASS, 'Double click on secuser to modify')
				
				WebUI.doubleClick(findTestObject('Access_Management/Firstname'))
				WebUI.delay(2)
				WebUI.sendKeys(findTestObject('Object Repository/Access_Management/Firstname'), Keys.chord(Keys.CONTROL,'a'))

				WebUI.setText(findTestObject('Access_Management/Firstname'), '')
				WebUI.setText(findTestObject('Access_Management/Firstname'), 'sami')
				extentTest.log(Status.PASS, 'First name of secuser modiefied ' )
				
				WebUI.click(findTestObject('Object Repository/Access_Management/Save_btn'))
				extentTest.log(Status.PASS, 'Click on Save button')
				
				WebUI.doubleClick(findTestObject('Object Repository/Access_Management/Secuser'))
				extentTest.log(Status.PASS, 'Double click on secuser to modify')
				
				WebUI.waitForElementPresent(findTestObject('Object Repository/Access_Management/Firstname'), 5)
				
					def Text1 = WebUI.getText(findTestObject('Object Repository/Access_Management/Firstname'))
				
					extentTest.log(Status.PASS, 'First name of secuser ' + Text1)
					
					WebUI.click(findTestObject('Object Repository/Access_Management/Save_btn'))
					extentTest.log(Status.PASS, 'Click on Save button')
				
				WebUI.click(findTestObject('Object Repository/Access_Management/secdelete'))
				extentTest.log(Status.PASS, 'Click on secuser to delete')
				
				WebUI.click(findTestObject('Access_Management/Delete_icon'))
				extentTest.log(Status.PASS, 'Click on delete icon')
				
				WebUI.click(findTestObject('Access_Management/Confirm_button'))
				
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction2'))
				extentTest.log(Status.PASS, "Opened Notification Panel" )
				
				WebUI.delay(2)
				result = WebUI.verifyElementPresent(findTestObject('Access_Management/Delete_User'),5)
				if(result)
				extentTest.log(Status.PASS, "Verified user is deleted successfully ")
				
				
					
				
		break
		
		case'role':
		
		WebUI.click(findTestObject('Access_Management/Add_rolebutton'))
		extentTest.log(Status.PASS, 'Click on add role')
	
		WebUI.click(findTestObject('Access_Management/Edit_role'))
		extentTest.log(Status.PASS, 'Click on edit role')
	
		WebUI.delay(2)
		
			//WebUI.click(findTestObject('Access_Management/Edit_roleid'))
			WebUI.delay(3)
				WebUI.doubleClick(findTestObject('Object Repository/AppComposer/Text'))
				WebUI.sendKeys(findTestObject('Object Repository/AppComposer/Text'), Keys.chord(Keys.CONTROL,'a'))
				WebUI.setText(findTestObject('Object Repository/AppComposer/Text'), '')
				WebUI.setText(findTestObject('Object Repository/AppComposer/Text'), roleid)
				extentTest.log(Status.PASS, 'Add roleid name -' + roleid)
				
				WebUI.click(findTestObject('Access_Management/Confirm_button'))
				extentTest.log(Status.PASS, 'Click on save')
				
				WebUI.click(findTestObject('Object Repository/Access_Management/System_tickmark'))
				extentTest.log(Status.PASS, 'Uncheck System tickmark')
				
				WebUI.click(findTestObject('Object Repository/Access_Management/Audit_tickmark'))
				extentTest.log(Status.PASS, 'Uncheck Audit tickmark')
				
				WebUI.click(findTestObject('Object Repository/Access_Management/Application_tickmark'))
				extentTest.log(Status.PASS, 'Uncheck application tickmark')
				
				WebUI.click(findTestObject('Access_Management/Save_role'))
				extentTest.log(Status.PASS, 'Click on Save button')
				
				WebUI.delay(3)
				//CustomKeywords.'create_user_role.user.add_user'(username, firstname, lastname,extentTest)
				
				
				WebUI.click(findTestObject('Access_Management/Add_user'))
				extentTest.log(Status.PASS, 'Click on users')
		
				WebUI.click(findTestObject('Access_Management/Add_userbutton'))
				extentTest.log(Status.PASS, 'Click on add user')
		
				WebUI.click(findTestObject('Access_Management/Firstname'))
				WebUI.setText(findTestObject('Access_Management/Firstname'), firstname)
				extentTest.log(Status.PASS, 'Add first name - ' + firstname)
		
				WebUI.click(findTestObject('Access_Management/Lastname'))
				WebUI.setText(findTestObject('Access_Management/Lastname'), lastname)
				extentTest.log(Status.PASS, 'Add last name - ' + lastname)
		
				WebUI.click(findTestObject('Access_Management/Username'))
				WebUI.doubleClick(findTestObject('Access_Management/Username'))
				WebUI.delay(3)
				WebUI.doubleClick(findTestObject('Object Repository/Access_Management/Username_text'))
				WebUI.delay(2)
					    WebUI.sendKeys(findTestObject('Object Repository/Access_Management/Username_text'),  Keys.chord(Keys.BACK_SPACE))
				WebUI.setText(findTestObject('Object Repository/Access_Management/Username_text'), '')
				WebUI.setText(findTestObject('Object Repository/Access_Management/Username_text'), username)
				extentTest.log(Status.PASS, 'Add username name - ' + username )
				
				WebUI.delay(2)
				
				WebUI.click(findTestObject('Access_Management/Add_role'))
				extentTest.log(Status.PASS, 'Click on add role')
				WebUI.delay(2)
				
				WebUI.click(findTestObject('Object Repository/Access_Management/demorole'))
				extentTest.log(Status.PASS, 'Click on demorole role to be added')
				WebUI.delay(2)
				
				//WebUI.click(findTestObject('Object Repository/Access_Management/Manager_role'))
		
				WebUI.click(findTestObject('Object Repository/AppComposer/Ok_btn'))
				extentTest.log(Status.PASS, 'Click on Ok button')
				
				WebUI.click(findTestObject('Object Repository/Access_Management/Save_btn'))
				extentTest.log(Status.PASS, 'Click on Save button')
				
				
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
				
				
				WebUI.setText(findTestObject('LoginPage/username_txtbx'),'demouser')
				extentTest.log(Status.PASS, 'Enter username demouser ')
				
				WebUI.setText(findTestObject('LoginPage/password_txtbx'), 'demouser')
				extentTest.log(Status.PASS, 'Enter  password  demouser')
			
				
				WebUI.click(findTestObject('Object Repository/LoginPage/login_btn'))
				extentTest.log(Status.PASS, 'Click on Login')
				WebUI.delay(2)
				WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))
				extentTest.log(Status.PASS, 'Click on profile tab')
				WebUI.delay(2)
				
				WebUI.click(findTestObject('Access_Management/Access_management'))
				extentTest.log(Status.PASS, 'Click on access management')
				
				WebUI.click(findTestObject('Access_Management/Add_rolebutton'))
				extentTest.log(Status.PASS, 'Click on add role')
			
				WebUI.click(findTestObject('Access_Management/Edit_role'))
				extentTest.log(Status.PASS, 'Click on edit role')
			
				WebUI.delay(2)
				
					//WebUI.click(findTestObject('Access_Management/Edit_roleid'))
					WebUI.delay(3)
						WebUI.doubleClick(findTestObject('Object Repository/AppComposer/Text'))
							    WebUI.sendKeys(findTestObject('Object Repository/AppComposer/Text'), Keys.chord(Keys.CONTROL,'a'))
						WebUI.setText(findTestObject('Object Repository/AppComposer/Text'), '')
						WebUI.setText(findTestObject('Object Repository/AppComposer/Text'), 'secabcd')
						extentTest.log(Status.PASS, 'Add roleid name - secabcd')
						
						WebUI.click(findTestObject('Access_Management/Confirm_button'))
						extentTest.log(Status.PASS, 'Click on save')
						
						WebUI.click(findTestObject('Access_Management/Save_role'))
						extentTest.log(Status.PASS, 'Click on Save button')
						
						WebUI.verifyElementPresent(findTestObject('Access_Management/secabcd'),3)
						extentTest.log(Status.PASS, 'Verify new role - secabcd is created')
						
						WebUI.doubleClick(findTestObject('Access_Management/secabcd'))
						
						WebUI.click(findTestObject('Access_Management/Edit_role'))
						extentTest.log(Status.PASS, 'Click on edit role')
					
						WebUI.delay(2)
						
							//WebUI.click(findTestObject('Access_Management/Edit_roleid'))
							WebUI.delay(3)
								WebUI.doubleClick(findTestObject('Object Repository/AppComposer/Text'))
								    WebUI.sendKeys(findTestObject('Object Repository/AppComposer/Text'), Keys.chord(Keys.CONTROL,'a'))
								WebUI.setText(findTestObject('Object Repository/AppComposer/Text'), '')
								WebUI.setText(findTestObject('Object Repository/AppComposer/Text'), 'test')
								extentTest.log(Status.PASS, 'Add roleid name - test')
								
								WebUI.click(findTestObject('Access_Management/Confirm_button'))
								extentTest.log(Status.PASS, 'Click on save')
								
								WebUI.click(findTestObject('Access_Management/Save_role'))
								extentTest.log(Status.PASS, 'Click on Save button')
								
							WebUI.verifyElementPresent(findTestObject('Access_Management/test'),3)
								extentTest.log(Status.PASS, 'verify security role modified to test')
								
								TestObject role =WebUI.modifyObjectProperty(findTestObject('Access_Management/Roleinfo'),'text', 'equals', roleid, true)
								WebUI.mouseOver(role)
								WebUI.click(role)
								extentTest.log(Status.PASS, "Click on test role to delete" )
								
								//WebUI.mouseOver(findTestObject('Access_Management/Selectrole'))
								//WebUI.click(findTestObject('Access_Management/Selectrole'))
								
								WebUI.delay(3)
								WebUI.click(findTestObject('Access_Management/Delete_role'))
								extentTest.log(Status.PASS, "Click on delete icon" )
								
								WebUI.click(findTestObject('Access_Management/Confirm_button'))
								extentTest.log(Status.PASS, "Click on confirm button" )
								
								WebUI.click(findTestObject('Landing_Page/Btn_Notifiction2'))
								extentTest.log(Status.PASS, "Opened Notification Panel" )
								WebUI.delay(2)
								WebUI.verifyElementPresent(findTestObject('Access_Management/Role_Delete'), 5)
								extentTest.log(Status.PASS, "Verify role deleted successfully")
								
								
				
				
		
		break
		
		
		
	
	case'Verify':
	
	        WebUI.click(findTestObject('Access_Management/Add_rolebutton'))
	        extentTest.log(Status.PASS, 'Click on add role')

	        WebUI.delay(3)
			
			WebUI.verifyElementPresent(findTestObject('Object Repository/Access_Management/Security_tickmark'), 3)
			extentTest.log(Status.PASS, 'Verify Security Administrator option available')
			
			WebUI.delay(3)
			
			WebUI.verifyElementPresent(findTestObject('Object Repository/Access_Management/System_tickmark'), 3)
			extentTest.log(Status.PASS, 'Verify System Administrator option available')
			
			WebUI.delay(3)
			
			WebUI.verifyElementPresent(findTestObject('Object Repository/Access_Management/Audit_tickmark'), 3)
			extentTest.log(Status.PASS, 'Verify Audit Administrator option available')
			
			WebUI.delay(3)
			
			WebUI.verifyElementPresent(findTestObject('Object Repository/Access_Management/Application_tickmark'), 3)
			extentTest.log(Status.PASS, 'Verify Application Administrator option available')
			
			WebUI.delay(3)
	        
	
			break


		
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

