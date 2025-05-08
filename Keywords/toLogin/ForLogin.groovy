package toLogin

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class ForLogin {

	@Keyword
	def Login(extentTest) {
		def errScreenShot = (RunConfiguration.getProjectDir() + '/ExtentReports/LoginError')
		
		def Browser = GlobalVariable.G_Browser
		//extentTest.log(Status.PASS, 'Navigated to Acces Instance - '+GlobalVariable.G_BaseUrl)


		if (Browser == 'Edge Chromium') {
			WebUI.click(findTestObject('Object Repository/GenericObjects/EdgeChromium_Details_link'))
			WebUI.delay(3)
			WebUI.click(findTestObject('Object Repository/GenericObjects/EdgeChromium_proceed_link'))
			WebUI.delay(3)
			WebUI.deleteAllCookies()
		}
		try {
			WebUI.waitForElementPresent(findTestObject('LoginPage/username_txtbx'), 5)
			extentTest.log(Status.PASS, 'Navigated to Acces Instance - '+GlobalVariable.G_BaseUrl)
			WebUI.setText(findTestObject('LoginPage/username_txtbx'), GlobalVariable.G_userName)
			WebUI.setText(findTestObject('LoginPage/password_txtbx'), GlobalVariable.G_Password)
			WebUI.click(findTestObject('LoginPage/login_btn'))
			extentTest.log(Status.PASS, 'Entered Creds - username - '+GlobalVariable.G_userName +' password - '+GlobalVariable.G_Password)
			extentTest.log(Status.PASS, 'Clicked on Login Button ')
			WebUI.click(findTestObject('Object Repository/Landing_Page/LandigPage_AltairAccess_Link'))
			extentTest.log(Status.PASS, 'Verified AltairAccess Logo post login ')

		}
catch (Exception ex) {
	println('From TC - ' + GlobalVariable.G_ReportFolder)
	String screenShotPath = (errScreenShot+ GlobalVariable.G_Browser) + '.png'
	WebUI.takeScreenshot(screenShotPath)
	String p = ('LoginError' + GlobalVariable.G_Browser) + '.png'
	extentTest.log(Status.FAIL, ex)
	extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(p).build())
}
catch (StepErrorException e) {
	String screenShotPath = (errScreenShot+ GlobalVariable.G_Browser) + '.png'
	WebUI.takeScreenshot(screenShotPath)
	String p = ('LoginError'  + GlobalVariable.G_Browser) + '.png'
	extentTest.log(Status.FAIL, ex)
	extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(p).build())
}

		}

}
