import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver

import com.assertthat.selenium_shutterbug.utils.web.Browser
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable


//====================================================================================
WebDriver driver = DriverFactory.getWebDriver()
RemoteWebDriver katalonWebDriver = (RemoteWebDriver) driver
//==================================================================
def Browser = GlobalVariable.G_Browser
def errScreenShot = (RunConfiguration.getProjectDir() + '/ExtentReports/'+TestCaseName)
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=============================================================

def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()
def location = navLocation + '/ForProfiles/InputDeck/'
def result=false
//=====================================================================================

String proElement
TestObject newProObj
TestObject newFileObj


//WebUI.delay(2)
WebUI.enableSmartWait()

try {

		WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))

	CustomKeywords.'operations_FileModule.ChangeView.changePageView'(userChoice, extentTest)
	if (TestCaseName.contains('tile view')) {
		//WebUI.delay(2)
		newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'data-automation-id', 'equals', fileName, true)
	}
	else
	{
		newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'data-automation-id', 'equals', fileName, true)
	}

	if(AppName.contains('InComplete'))
	{
		proElement = AppName + '-' + proName+' GUI'
		newProObj = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/CntxtMenu-SubMenu-ProfileItem'),'id', 'contains', proElement, true)
	}
	else
	{
		proElement = AppName + '-' + proName
		newProObj = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/CntxtMenu-SubMenu-ProfileItem'),'id', 'contains', proElement, true)
	findTestObject('Object Repository/ProfileOptions/textBoxGoToFolder')}
	
	CustomKeywords.'generateFilePath.filePath.navlocation'(location, extentTest)

	extentTest.log(Status.PASS, 'Navigated to - ' + location)

	WebUI.disableSmartWait()
	
	def istextBoxPresent = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/textBx_file_filter'),5,extentTest,'TextBox')

	if(istextBoxPresent)
	{
		WebUI.setText(findTestObject('JobSubmissionForm/textBx_file_filter'), fileName)
		WebUI.sendKeys(findTestObject('JobSubmissionForm/textBx_file_filter'), Keys.chord(Keys.ENTER))
	}

	WebUI.delay(2)
	boolean isNewFileExist=WebUI.verifyElementPresent(newFileObj, 10)
   if(isNewFileExist) {
	
	WebUI.rightClick(newFileObj)

	}
	
	WebUI.enableSmartWait()
	
	def isMenuPresent = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('FilesPage/ContextMn_Process With'),5,extentTest,'ProcessWithMenu')

	if(isMenuPresent)
	{
		extentTest.log(Status.PASS, 'Right Clicked on file  - ' + fileName)
		extentTest.log(Status.PASS, 'Sub menu present')
		String screenShotforSubMenu = 'ExtentReports/ProfileScreenShots/' + TestCaseName +GlobalVariable.G_Browser+'SubMenu'+ '.png'

		WebUI.takeScreenshot(screenShotforSubMenu)
		screenShotforSubMenu= 'ProfileScreenShots/' + TestCaseName +GlobalVariable.G_Browser+'SubMenu'+ '.png'

		WebUI.verifyElementVisible(findTestObject('Object Repository/FilesPage/ContextMn_Process With'))
	//	WebUI.mouseOver(findTestObject('FilesPage/ContextMn_Process With'))

		WebUI.click(findTestObject('FilesPage/ContextMn_Process With'))
	
		extentTest.log(Status.PASS, 'Clicked on Process With ')
	}


	switch(submissionType)
	{
		case 'Direct':
			WebUI.click(newProObj)
			String screenShotforProfileMenu = 'ProfileScreenShots/' + TestCaseName +GlobalVariable.G_Browser+'ProfileMenu'+ '.png'
			String ScreenShotNamePM=TestCaseName +GlobalVariable.G_Browser+'SubMenu'+ '.png'
			WebUI.takeScreenshot(ScreenShotNamePM)
			//extentTest.log(Status.PASS,extentTest.addScreenCapture(screenShotforProfileMenu))
			extentTest.log(Status.PASS, 'Clicked on Profile Menu item - ' +proElement)
		break
		case 'AllProfiles':
			def isElemenetPresent = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/AllProfiles/span_All Profiles'),
			3,extentTest,'AllProfilesMenu')
			println("sub menu present - "+ isElemenetPresent)
			if (isElemenetPresent) {
				//WebUI.delay(1)
				//WebUI.mouseOver(findTestObject('Object Repository/AllProfiles/span_All Profiles'))
				//WebUI.delay(1)
				WebUI.click(findTestObject('Object Repository/AllProfiles/span_All Profiles'))
				String screenShotforSubMenuAll = 'ProfileScreenShots/' + TestCaseName +GlobalVariable.G_Browser+'All'+ '.png'
				String ScreenShotNameAll=TestCaseName +GlobalVariable.G_Browser+'All'+ '.png'
				WebUI.takeScreenshot(screenShotforSubMenuAll)
				//extentTest.log(Status.PASS,extentTest.addScreenCapture(screenShotforSubMenuAll))
				WebUI.click(findTestObject('Object Repository/AllProfiles/SelectProfile_pop-up-SearchBox'))
				WebUI.sendKeys(findTestObject('Object Repository/AllProfiles/SelectProfile_pop-up-SearchBox'), proName)
				TestObject newProLabel = CustomKeywords.'buildTestObj.CreateTestObjFiles.ProLabelIdentifier'(proName,AppName)
				WebUI.click(newProLabel)
				//WebUI.delay(2)
				String screenShotforPopup = 'ProfileScreenShots/' + TestCaseName +GlobalVariable.G_Browser+'popUp'+ '.png'
				String ScreenShotPopup=TestCaseName +GlobalVariable.G_Browser+'popUp'+ '.png'
				WebUI.takeScreenshot(screenShotforPopup)
				//extentTest.log(Status.PASS,extentTest.addScreenCapture(screenShotforPopup))
				WebUI.click(findTestObject('Object Repository/AllProfiles/button_Select'))
				//WebUI.delay(2)
				extentTest.log(Status.PASS, 'Clicked on - ' + proElement)
			}
			else {
				extentTest.log(Status.PASS, 'Profile Conetext-menu item not found - ' + proElement)
			}

			break


	}
	if(AppName.contains('InComplete'))
	{
		
		WebUI.click(findTestObject('Object Repository/JobSubmissionForm/TxtBox_ReqFiled_ToFill'))
		WebUI.setText(findTestObject('Object Repository/JobSubmissionForm/TxtBox_ReqFiled_ToFill'), 'testString')
		extentTest.log(Status.PASS, 'Entered text for required field')
		String screenShotforJS = 'ProfileScreenShots/' + TestCaseName +GlobalVariable.G_Browser+'JS'+ '.png'
		String ScreenShotJS=TestCaseName +GlobalVariable.G_Browser+'JS'+ '.png'
		WebUI.takeScreenshot(ScreenShotJS)
		//extentTest.log(Status.PASS,extentTest.addScreenCapture(screenShotforJS))
	/*	def submitBtn = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/button_Submit_Job'),20,extentTest,'SubmitButton')
		if (submitBtn) {
			//WebUI.delay(2)
			WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))
			extentTest.log(Status.PASS, 'Clicked on Submit Button ')
		}*/
		
		
		WebElement submitBtn= driver.findElement(By.xpath("//button[@id = 'submission_submit_btn']"))
		JavascriptExecutor jse1 = (JavascriptExecutor)driver;
		jse1.executeScript("arguments[0].click()", submitBtn);
		extentTest.log(Status.PASS, 'Clicked on Submit Button ')
	}

	WebUI.disableSmartWait()
	
	msg='Job has been submitted successfully on'
	String notificationMsg
	if	(WebUI.waitForElementPresent(findTestObject('Object Repository/GenericObjects/Notification-Popup'), 5))
					{
						 notificationMsg = (new operations_FileModule.notifications()).getNotificationsText(extentTest,katalonWebDriver)
						if(notificationMsg.contains(msg))
						{
							extentTest.log(Status.PASS,"Verified Notification Msg - "+notificationMsg)
							result=true
						}
						else
						{
							extentTest.log(Status.PASS,"Notification Msg - "+notificationMsg)
						}
					}
					else
					{
						extentTest.log(Status.PASS,"Notification Pop-Up not presnt")
					}
					
	
	if (result)
	{
		GlobalVariable.G_JobID=CustomKeywords.'operations_JobsModule.GetJobRowDetails.getJobID'(notificationMsg)
		extentTest.log(Status.PASS, 'Job Submitted through profile, initiated from files page, JobID - ' + GlobalVariable.G_JobID)
		println('********************************************')
		extentTest.log(Status.PASS, ('Verified ::  ' + TestCaseName) + ' :: Sucessfully')
	}
	else
	{
		extentTest.log(Status.FAIL,'Job Submission notification not generated')
		extentTest.log(Status.FAIL,  TestCaseName + ' :: failed')
	}
	
}

catch (Exception ex) {
	println('From TC - ' + GlobalVariable.G_ReportFolder)
	String screenShotPath = (errScreenShot+ GlobalVariable.G_Browser) + '.png'
	WebUI.takeScreenshot(screenShotPath)
	String p = (TestCaseName + GlobalVariable.G_Browser) + '.png'
	extentTest.log(Status.FAIL, ex)
	extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(p).build())
}
catch (StepErrorException e) {
	String screenShotPath = (errScreenShot+ GlobalVariable.G_Browser) + '.png'
	WebUI.takeScreenshot(screenShotPath)
	String p = (TestCaseName + GlobalVariable.G_Browser) + '.png'
	extentTest.log(Status.FAIL, ex)
	extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(p).build())
}
finally {
	extentTest.log(Status.PASS, 'Closing the browser after executinge test case - ' + TestCaseName)
}
