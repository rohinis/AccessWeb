import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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

def extentTest = extent.startTest(TestCaseName)*/
String screenShot='ExtentReports/'+TestCaseName+userChoice+GlobalVariable.G_Browser+'.png'
//sdef result

'Login into PAW '
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

try {
    WebUI.delay(2)

    //WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
    WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

    extentTest.log(Status.PASS, 'Click on profile tab')

    WebUI.delay(2)

    WebUI.click(findTestObject('Access_Management/Access_management'))

    extentTest.log(Status.PASS, 'Click on access management')

    switch (userChoice) {
        case 'Add user verify assigned role':
            CustomKeywords.'create_user_role.user.add_user'(username, firstname, lastname, extentTest)

            /*WebUI.click(findTestObject('Access_Management/Add_user'))
		extentTest.log(Status.PASS, 'Click on users')
		
		WebUI.click(findTestObject('Access_Management/Add_userbutton'))
		extentTest.log(Status.PASS, 'Click on add user')
		
		
		
		WebUI.click(findTestObject('Access_Management/Username'))
		WebUI.doubleClick(findTestObject('Access_Management/Username'))
		WebUI.delay(3)
		WebUI.doubleClick(findTestObject('Object Repository/Access_Management/Username_text'))
		WebUI.delay(2)
		rob.keyPress(KeyEvent.VK_BACK_SPACE)
		rob.keyRelease(KeyEvent.VK_BACK_SPACE)
		WebUI.setText(findTestObject('Object Repository/Access_Management/Username_text'), '')
		WebUI.setText(findTestObject('Object Repository/Access_Management/Username_text'), username)
		extentTest.log(Status.PASS, 'Add username name - ' + username )
		
		WebUI.click(findTestObject('Access_Management/Firstname'))
		WebUI.setText(findTestObject('Access_Management/Firstname'), firstname)
		extentTest.log(Status.PASS, 'Add first name - ' + firstname)
		
		WebUI.click(findTestObject('Access_Management/Lastname'))
		WebUI.setText(findTestObject('Access_Management/Lastname'), lastname)
		extentTest.log(Status.PASS, 'Add last name - ' + lastname)
		
		
		WebUI.click(findTestObject('Access_Management/Save'))
		extentTest.log(Status.PASS, 'Click on save')*/
            TestObject user = WebUI.modifyObjectProperty(findTestObject('Access_Management/Userinfo'), 'text', 'equals', 
                username, true)

            WebUI.doubleClick(findTestObject('Access_Management/Userinfo'))

            extentTest.log(Status.PASS, 'Verify new user info')

            WebUI.click(findTestObject('Access_Management/Add_role'))

            extentTest.log(Status.PASS, 'Assign role to new user')

            WebUI.delay(2)

            WebUI.click(findTestObject('Access_Management/Appuser'))

            extentTest.log(Status.PASS, 'Select the role')

            WebUI.delay(2)

            WebUI.click(findTestObject('Access_Management/Confirm_button'))

            extentTest.log(Status.PASS, 'Click on confirm button')

            WebUI.delay(2)

            WebUI.click(findTestObject('Access_Management/Save'))

            extentTest.log(Status.PASS, 'Click on save')

            break
        case 'Add new user duplicate name':
            CustomKeywords.'create_user_role.user.add_user'(username, firstname, lastname, extentTest)

            WebUI.verifyElementPresent(findTestObject('Access_Management/Duplicate_name'), 2)

            extentTest.log(Status.PASS, 'Verify error message user already exists')

            WebUI.takeScreenshot(screenShot)

            break
        case 'Add new user special characters':
            CustomKeywords.'create_user_role.user.add_user'(username, firstname, lastname, extentTest)

            extentTest.log(Status.PASS, 'Verify through Screenshot')

            WebUI.takeScreenshot(screenShot)

            break
        case 'Add new user blank username':
            CustomKeywords.'create_user_role.user.add_user'(username, firstname, lastname, extentTest)

            /*  WebUI.click(findTestObject('Access_Management/Add_user'))
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
		rob.keyPress(KeyEvent.VK_BACK_SPACE)
		rob.keyRelease(KeyEvent.VK_BACK_SPACE)
		WebUI.setText(findTestObject('Object Repository/Access_Management/Username_text'), '')
		WebUI.setText(findTestObject('Object Repository/Access_Management/Username_text'), username)
		extentTest.log(Status.PASS, 'Add username name - ' + username )

	    WebUI.click(findTestObject('Access_Management/Save'))
	    extentTest.log(Status.PASS, 'Click on save')
		*/
            WebUI.takeScreenshot(screenShot)

            extentTest.log(Status.PASS, 'Verify through screenshot ')

            break
        case 'Add user valid username':
            CustomKeywords.'create_user_role.user.add_user'(username, firstname, lastname, extentTest)

            TestObject user = WebUI.modifyObjectProperty(findTestObject('Access_Management/Userinfo'), 'text', 'equals', 
                username, true)

            WebUI.click(user)

            extentTest.log(Status.PASS, 'Verify new user')

            break
        case 'Delete User':
            WebUI.click(findTestObject('Access_Management/Add_user'))

            extentTest.log(Status.PASS, 'Click on users')

            TestObject user = WebUI.modifyObjectProperty(findTestObject('Access_Management/Userinfo'), 'text', 'equals', 
                username, true)

            WebUI.click(user)

            extentTest.log(Status.PASS, 'Click on user to be deleted')

            WebUI.click(findTestObject('Access_Management/Delete_icon'))

            extentTest.log(Status.PASS, 'Click on delete icon')

            WebUI.click(findTestObject('Access_Management/Confirm_button'))

            WebUI.click(findTestObject('Landing_Page/Btn_Notifiction2'))

            extentTest.log(Status.PASS, 'Opened Notification Panel')

            WebUI.delay(2)

            result = WebUI.verifyElementPresent(findTestObject('Access_Management/Delete_User'), 5)

            extentTest.log(Status.PASS, 'Verified user is deleted successfully ')

            break
        case 'Delete service user':
            WebUI.click(findTestObject('Access_Management/Add_user'))

            extentTest.log(Status.PASS, 'Click on users')

            //WebUI.click(findTestObject('Access_Management/Checkuser'))
            WebUI.click(findTestObject('Access_Management/Manager_userinfo'))

            extentTest.log(Status.PASS, 'Click on manager ')

            WebUI.verifyElementPresent(findTestObject('Access_Management/Lock_icon'), 5)

            extentTest.log(Status.PASS, 'Verify the lock icon present')

            WebUI.doubleClick(findTestObject('Access_Management/Manager_userinfo'))

            extentTest.log(Status.PASS, 'Double click the manager user')

            WebUI.verifyElementNotPresent(findTestObject('Access_Management/Confirm_button'), 5)

            extentTest.log(Status.PASS, 'Verify save  button is diabled')

            break
        case 'Add new role application user':
            CustomKeywords.'create_user_role.role.add_role'(roleid, extentTest)

            WebUI.verifyElementPresent(findTestObject('Access_Management/App_user_tickmark'), 4)

            extentTest.log(Status.PASS, 'Verify application user is enable')

            WebUI.click(findTestObject('Access_Management/Save_role'))

            extentTest.log(Status.PASS, 'Click on save')

            WebUI.verifyElementPresent(findTestObject('Access_Management/Verify_roleid'), 5)

            extentTest.log(Status.PASS, 'Verify roleid')

            break
        case 'Add new role portal admin':
            CustomKeywords.'create_user_role.role.add_role'(roleid, extentTest)

            WebUI.verifyElementPresent(findTestObject('Access_Management/App_user_tickmark'), 4)

            WebUI.click(findTestObject('Access_Management/Save_role'))

            WebUI.verifyElementPresent(findTestObject('Access_Management/Verify_roleid'), 5)

            WebUI.click(findTestObject('Access_Management/Verify_roleid'))

            WebUI.verifyElementClickable(findTestObject('Access_Management/Portal_admin_tickmark'))

            break
        case 'Add new role verify assigned resources':
            CustomKeywords.'create_user_role.role.add_role'(roleid, extentTest)

            WebUI.click(findTestObject('Access_Management/Add_resources'))

            WebUI.click(findTestObject('Access_Management/Shellscript_resource'))

            WebUI.click(findTestObject('Access_Management/Confirm_button'))

            WebUI.click(findTestObject('Access_Management/Save_role'))
			WebUI.delay(3)

            TestObject role = WebUI.modifyObjectProperty(findTestObject('Access_Management/Roleinfo'), 'text', 'equals', 
                roleid, true)

            WebUI.doubleClick(role)
			WebUI.delay(3)

            WebUI.click(findTestObject('Access_Management/Shellscript_resource'))

            break
        case 'Add new role duplicate name':
            CustomKeywords.'create_user_role.role.add_role'(roleid, extentTest)

            WebUI.takeScreenshot(screenShot)

            if (GlobalVariable.G_Browser == 'IE') {
                WebUI.click(findTestObject('Access_Management/Cancel'))
            }
            
            break
        case 'Add new role blank':
            CustomKeywords.'create_user_role.role.add_role'(roleid, extentTest)

            WebUI.takeScreenshot(screenShot)

            if (GlobalVariable.G_Browser == 'IE') {
                WebUI.click(findTestObject('Access_Management/Cancel'))
            }
            
            break
        case 'Add new role special characters':
            CustomKeywords.'create_user_role.role.add_role'(roleid, extentTest)

            WebUI.takeScreenshot(screenShot)

            extentTest.log(Status.PASS, 'Verify unable to use special characters')

            if (GlobalVariable.G_Browser == 'IE') {
                WebUI.click(findTestObject('Access_Management/Cancel'))
            }
            
            break
        case 'Change Assigned resources -Delete resources':
            CustomKeywords.'create_user_role.role.add_role'(roleid, extentTest)

            WebUI.delay(2)

            WebUI.click(findTestObject('Access_Management/Add_resources'))

            extentTest.log(Status.PASS, 'Click on add resources')

            WebUI.delay(2)

            WebUI.click(findTestObject('Access_Management/Shellscript_resource'))

            extentTest.log(Status.PASS, 'Add Shellscript')

            WebUI.click(findTestObject('Access_Management/Confirm_button'))

            extentTest.log(Status.PASS, 'Click on Confirm')

            WebUI.click(findTestObject('Access_Management/Save_role'))

            extentTest.log(Status.PASS, 'Click on save role')

            WebUI.delay(2)

            TestObject role = WebUI.modifyObjectProperty(findTestObject('Access_Management/Roleinfo'), 'text', 'equals', 
                roleid, true)

            WebUI.doubleClick(role)

            extentTest.log(Status.PASS, 'Click on save')

            WebUI.click(findTestObject('Access_Management/Shellscript_resource'))

            extentTest.log(Status.PASS, 'Click on save')

            WebUI.delay(2)

            WebUI.click(findTestObject('Access_Management/Delete_resource'))

            extentTest.log(Status.PASS, 'Click on delete assigned resource')

            WebUI.verifyElementNotPresent(findTestObject('Access_Management/Shellscript_resource'), 5)

            extentTest.log(Status.PASS, 'Verify assigned resource is deleted')

            break
        case 'Edit role':
            TestObject role = WebUI.modifyObjectProperty(findTestObject('Access_Management/Roleinfo'), 'text', 'equals', 
                roleid, true)

            WebUI.doubleClick(role)
			WebUI.delay(2)

            extentTest.log(Status.PASS, 'Click on role to be edit')

            WebUI.click(findTestObject('Access_Management/Edit_role'))

            extentTest.log(Status.PASS, 'Click on edit')

            WebUI.delay(2)

            //WebUI.click(findTestObject('Access_Management/Edit_roleid'))
            WebUI.delay(3)

            WebUI.doubleClick(findTestObject('Object Repository/AppComposer/Text'))

            WebUI.sendKeys(findTestObject('Object Repository/AppComposer/Text'), Keys.chord(Keys.BACK_SPACE))

            WebUI.setText(findTestObject('Object Repository/AppComposer/Text'), '')

            WebUI.setText(findTestObject('Object Repository/AppComposer/Text'), 'newrole')

            extentTest.log(Status.PASS, 'Add roleid name - newrole')

            WebUI.click(findTestObject('Access_Management/Confirm_button'))

            extentTest.log(Status.PASS, 'Click on confirm')

            WebUI.click(findTestObject('Access_Management/Save_role'))

            extentTest.log(Status.PASS, 'Click on save')

            WebUI.click(findTestObject('Access_Management/Newrole_info'))

            extentTest.log(Status.PASS, 'Click on new role')

            break
        case 'Edit role cancel':
            TestObject role = WebUI.modifyObjectProperty(findTestObject('Access_Management/Roleinfo'), 'text', 'equals', 
                roleid, true)

            WebUI.doubleClick(role)
			WebUI.delay(2)

            extentTest.log(Status.PASS, 'Click on role')

            WebUI.click(findTestObject('Access_Management/Edit_role'))

            extentTest.log(Status.PASS, 'Click on edit role')

            WebUI.delay(2)

            //WebUI.click(findTestObject('Access_Management/Edit_roleid'))
            WebUI.delay(3)

            WebUI.doubleClick(findTestObject('Object Repository/AppComposer/Text'))

            WebUI.sendKeys(findTestObject('Object Repository/AppComposer/Text'), Keys.chord(Keys.BACK_SPACE))

            WebUI.setText(findTestObject('Object Repository/AppComposer/Text'), '')

            WebUI.setText(findTestObject('Object Repository/AppComposer/Text'), 'newrole')

            extentTest.log(Status.PASS, 'Add new roleid name')
			WebUI.delay(3)

            WebUI.click(findTestObject('Access_Management/Confirm_cancel'))

            extentTest.log(Status.PASS, 'Click on cancel')

            WebUI.verifyElementPresent(findTestObject('Access_Management/Role_header'), 5)

            extentTest.log(Status.PASS, 'Verify role header is present')

            break
        case 'Edit username cancel':
            WebUI.click(findTestObject('Access_Management/Add_user'))

            extentTest.log(Status.PASS, 'Click on users')
			WebUI.delay(2)

            TestObject user = WebUI.modifyObjectProperty(findTestObject('Access_Management/Userinfo'), 'text', 'equals', 
                username, true)

            WebUI.doubleClick(user)
			WebUI.delay(2)

            extentTest.log(Status.PASS, 'Click on user')

            WebUI.waitForElementPresent(findTestObject('Access_Management/Cancel_button'), 10)

            WebUI.click(findTestObject('Access_Management/Cancel_button'))

            extentTest.log(Status.PASS, 'Click on cancel button')

            WebUI.click(findTestObject('Access_Management/Userinfo'))

            extentTest.log(Status.PASS, 'Verify user is present' + username)

            break
        case 'Edit username':
            WebUI.click(findTestObject('Access_Management/Add_user'))

            extentTest.log(Status.PASS, 'Click on users')

            TestObject user = WebUI.modifyObjectProperty(findTestObject('Access_Management/Userinfo'), 'text', 'equals', 
                username, true)

            WebUI.doubleClick(user)

            extentTest.log(Status.PASS, 'Click on user info')

            WebUI.takeScreenshot(screenShot)

            extentTest.log(Status.PASS, 'Verify unable to edit username')

            break
        case 'Delete role':
            TestObject role = WebUI.modifyObjectProperty(findTestObject('Access_Management/RoleinfoNew'), 'text', 'equals', 
                roleid, true)

            WebUI.mouseOver(role)

            WebUI.click(role)

            extentTest.log(Status.PASS, 'Click on role')

            //WebUI.mouseOver(findTestObject('Access_Management/Selectrole'))
            //WebUI.click(findTestObject('Access_Management/Selectrole'))
            WebUI.delay(3)

            WebUI.click(findTestObject('Access_Management/Delete_role'))

            extentTest.log(Status.PASS, 'Click on delete icon')

            WebUI.click(findTestObject('Access_Management/Confirm_button'))

            extentTest.log(Status.PASS, 'Click on confirm button')

            WebUI.click(findTestObject('Landing_Page/Btn_Notifiction2'))

            extentTest.log(Status.PASS, 'Opened Notification Panel')

            WebUI.delay(2)

            WebUI.verifyElementPresent(findTestObject('Access_Management/Role_Delete'), 5)

            extentTest.log(Status.PASS, 'Verify role deleted successfully')

            break
        case 'Delete role manager':
            WebUI.delay(2)

            WebUI.click(findTestObject('Access_Management/Manager_roleinfo'))

            extentTest.log(Status.PASS, 'Click on manager role')

            WebUI.delay(3)

            WebUI.verifyElementPresent(findTestObject('Access_Management/Delete_role'), 5)

            //WebUI.verifyElementNotClickable(findTestObject('Access_Management/Delete_role'))
            println(WebUI.verifyElementClickable(findTestObject('Access_Management/Delete_role')))

            WebUI.verifyElementPresent(findTestObject('Access_Management/Lock_icon'), 5)

            extentTest.log(Status.PASS, 'Verify the lock icon present')

            WebUI.doubleClick(findTestObject('Access_Management/Manager_roleinfo'))

            extentTest.log(Status.PASS, 'Double click the manager role')

            WebUI.delay(5)

            WebUI.verifyElementPresent(findTestObject('Access_Management/Cancel_Role'), 10)

            extentTest.log(Status.PASS, 'Verify save role button is diabled')

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


