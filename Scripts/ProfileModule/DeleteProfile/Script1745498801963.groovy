import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

import com.assertthat.selenium_shutterbug.utils.web.Browser
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable


WebDriver driver = DriverFactory.getWebDriver()
RemoteWebDriver katalonWebDriver = (RemoteWebDriver) driver
//=============================================================
def Browser = GlobalVariable.G_Browser
//=============================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=============================================================
def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()
def location = navLocation + '/FilesModule/FileOps/'
//=====================================================================================
def isElementPresnt


WebUI.enableSmartWait()
try
{

	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))


	TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'), 'id', 'equals', AppName, true)

	WebUI.click(newAppObj)
	extentTest.log(Status.PASS, 'Navigated to Job Submission form for - '+AppName )



	TestObject LeftNavAppIdentifier = CustomKeywords.'buildTestObj.CreateTestObjJobs.myLeftNavAppIdentifier'(proName)
	WebUI.click(findTestObject('Object Repository/LoginPage/NewJobPage/GenericProfile'))
	def isProfilePersent = WebUI.verifyElementPresent(LeftNavAppIdentifier, 5)
	WebUI.click(findTestObject('Object Repository/LoginPage/NewJobPage/GenericProfile'))

	WebUI.disableSmartWait()

	//navigating to manageProfile
	WebUI.click(findTestObject('Object Repository/ProfileOptions/manage_Profile_Icon'))
	WebUI.delay(2)



	WebElement profName= driver.findElement(By.xpath("//span[text()='"+proName+"']"))
	WebElement ele1 = driver.findElement(By.xpath("//span[text()='"+proName+"']/ancestor::div[@role='row']//div[@class='filelist-gridicon']"))
	JavascriptExecutor jse1 = (JavascriptExecutor)driver;
	jse1.executeScript("arguments[0].click()", ele1);


	WebUI.delay(2)
	WebUI.click(findTestObject('Object Repository/ProfileOptions/delete_Btn'))
	extentTest.log(Status.PASS, 'clicked on delete option for the - '+ proName)

	boolean delProf=WebUI.verifyElementPresent(findTestObject('Object Repository/ProfileOptions/delete_Profile'), 4)
	if(delProf) {
		WebUI.click(findTestObject('Object Repository/ProfileOptions/yes_Btn'))
		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/ProfileOptions/close_Btn'))

	}

	msg=proName+' has been deleted successfully'
	if	(WebUI.waitForElementPresent(findTestObject('Object Repository/GenericObjects/Notification-Popup'), 5))
					{
						String notificationMsg = (new operations_FileModule.notifications()).getNotificationsText(extentTest,katalonWebDriver)
						if(notificationMsg.contains(msg))
						{
	
							extentTest.log(Status.PASS,"Verified Notification Msg - "+notificationMsg)
						}
						else
						{
							extentTest.log(Status.PASS,"Verified Notification Msg - "+notificationMsg)
						}
					}
					else
					{
						extentTest.log(Status.PASS,"Notification Pop-Up not presnt")
					}
	


	if(ProfileType.equals('Cancel'))
	{
		WebUI.click(findTestObject('Object Repository/ProfileOptions/Save_this_Profile'))
		//boolean cancelBtn=WebUI.verifyElementPresent(findTestObject('Object Repository/LoginPage/NewJobPage/button_Cancel'), 2)
		//if(cancelBtn) {
		WebUI.click(findTestObject('Object Repository/LoginPage/NewJobPage/button_Cancel'))
		//}
		extentTest.log(Status.PASS, 'Clicked on Save As ')
		extentTest.log(Status.PASS, 'Entered profile name -  '+proName)
		extentTest.log(Status.PASS, 'Profile Creation Option Selected - '+ProfileType)
		/*def isProfilePersentProCan = WebUI.verifyElementPresent(LeftNavAppIdentifier, 3,FailureHandling.CONTINUE_ON_FAILURE)
		 if(isProfilePersentProCan)
		 {
		 extentTest.log(Status.PASS, 'Profile not created - '+ proName)
		 }
		 else
		 {
		 extentTest.log(Status.PASS, 'Profile not created - '+ proName)
		 }*/


		WebUI.click(findTestObject('Object Repository/LoginPage/NewJobPage/GenericProfile'))
		def result = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(LeftNavAppIdentifier,2,extentTest,'deletedProfile')

		WebUI.click(findTestObject('Object Repository/ProfileOptions/manage_Profile_Icon'))
		def result1 = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(LeftNavAppIdentifier,2,extentTest,'deletedProfile')
		//println(result)
		if (result || result1)
		{
			extentTest.log(Status.FAIL,'Profile not deleted')
			//extentTest.log(Status.FAIL, ( TestCaseName) + ' :: failed')

		}
		else {
			extentTest.log(Status.PASS, 'Deleted Profile - '+proName )
			extentTest.log(Status.PASS, ('Verified ::  ' + TestCaseName) + ' :: Sucessfully')

		}
	}


}catch (Exception ex) {
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




