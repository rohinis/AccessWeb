import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import com.kms.katalon.core.configuration.RunConfiguration
import internal.GlobalVariable as GlobalVariable
//==================================================================
def Browser = GlobalVariable.G_Browser
//===============================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=============================================================
try {
WebUI.delay(2)


    //click on the user menu
    WebUI.click(findTestObject('Object Repository/change_password/unityuser'))

    //click on the change passwd option
    WebUI.click(findTestObject('Object Repository/change_password/changepass_menu'))

    extentTest.log(Status.PASS, 'Clicked on the change password icon')

    //verify the title
    println(WebUI.verifyElementPresent(findTestObject('Object Repository/change_password/title'), 20))

    String title = WebUI.getText(findTestObject('Object Repository/change_password/title'))

    println('Error message******' + title)

    extentTest.log(Status.PASS, ('Verify the Title ' + title) + ' is present')

    //click on the close option
    WebUI.click(findTestObject('Object Repository/change_password/close_btn'))

    extentTest.log(Status.PASS, 'Clicked on the close icon ')

    //verify the change password window has gone
    println(WebUI.verifyElementNotPresent(findTestObject('Object Repository/change_password/title'), 20))

    extentTest.log(Status.PASS, ('Verify the change password window has disappeared ::Title ' + title) + ' is  not present')
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

    



