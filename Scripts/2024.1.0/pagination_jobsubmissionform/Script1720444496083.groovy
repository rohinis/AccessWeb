import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver

import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status

import internal.GlobalVariable as GlobalVariable
//====================================================================================
WebDriver driver = DriverFactory.getWebDriver()
EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()
//RemoteWebDriver katalonWebDriver = ((wrappedWebDriver) as RemoteWebDriver)
RemoteWebDriver katalonWebDriver = (RemoteWebDriver) wrappedWebDriver
//==================================================================
def Browser = GlobalVariable.G_Browser
//===============================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=============================================================

println "*****************************************************"
println GlobalVariable.G_Platform
println "*****************************************************"

def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()
def location = (navLocation + '/JobsModule/')
println "*****************************************************"
println location
println "*****************************************************"


TestObject newFileObj=null

try {
	 WebUI.enableSmartWait()
	//def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/JobsTab_disabled'),
			//20, extentTest, 'Jobs Tab')

	//if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	//}
	extentTest.log(Status.PASS, 'Navigated Jobs Tab')
	//WebUI.delay(2)

	TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'), 'id', 'equals',
			AppName, true)
	WebUI.click(newAppObj)
	extentTest.log(Status.PASS, 'Navigated to Job Submission For for - ' + AppName)
	extentTest.log(Status.PASS,"AD-5213 -jobs section->job submission form->files section for items less than a page")
	extentTest.log(Status.PASS,"The user has less number of folders\files in files section")
	
	def res1=WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/Pagination/pagination_element1_disabled'),5)
	def res2=WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/Pagination/pagination_element2_disabled'),5)
	def res3=WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/Pagination/pagination_element3_disabled'),5)
	def res4=WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/Pagination/pagination_element4_disabled'),5)
	if(res1&&res2&&res3&&res4) {
		extentTest.log(Status.PASS, 'Verified that the page navigation buttons are disabled if the content is less than a page')
	}
	else {
		extentTest.log(Status.FAIL, 'failed to verify the pagination elements')
	}
	
	String pagination=WebUI.getText(findTestObject('Object Repository/FilesPage/Pagination/pagination_number_status'))
	println("pagination status"+ pagination)
	
	//WebUI.delay(2)

	
	
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

	






