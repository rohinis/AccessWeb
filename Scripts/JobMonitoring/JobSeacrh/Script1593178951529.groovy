import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.support.events.EventFiringWebDriver

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status

import internal.GlobalVariable as GlobalVariable

//====================================================================================
WebDriver driver = DriverFactory.getWebDriver()
EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()
RemoteWebDriver katalonWebDriver = (RemoteWebDriver) wrappedWebDriver

//==================================================================
def Browser = GlobalVariable.G_Browser
//===============================================================
def extentTest=GlobalVariable.G_ExtentTest
//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//================================================================================================


def result=false

WebUI.delay(2)

try {
	WebUI.delay(2)

	def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'),
		20,extentTest,'Jobs Tab')

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}	    
	
	extentTest.log(Status.PASS, 'Navigated to Jobs Tab')
	WebUI.delay(2)

	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))
	extentTest.log(Status.PASS, 'Filters reset')

	WebUI.click(findTestObject('JobMonitoringPage/JM_SearchBox'))
	extentTest.log(Status.PASS, 'Entering Search text - '+searchValue)
	WebUI.setText(findTestObject('JobMonitoringPage/JM_SearchBox'),searchValue)
	WebUI.sendKeys(findTestObject('JobMonitoringPage/JM_SearchBox'), Keys.chord(Keys.ENTER))

	if(GlobalVariable.G_Browser.equals('FireFox')) {
		WebUI.delay(5)
		extentTest.log(Status.PASS, 'Waiting for jobs table to load on FireFox')
	}

	String myXpath="//div[@col-id='jobId']"
	List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
	println listElement.size()


	switch (userChoice)
	{
		case "Valid":
			if(listElement.size()>=2)
			{
				for(int i =1;i<listElement.size();i++) {
					RemoteWebElement ele = listElement.get(i)
					String myText=ele.getText()
					println (ele.getText())
					extentTest.log(Status.PASS, 'Job Found for matching search criteria  - '+ searchWith + ' with job id  - '+myText)
				}
			}
			else
			{
				extentTest.log(Status.FAIL, 'No job found for search criteria - '+ searchWith)

			}
			break;

		case "Invalid":
			if(listElement.size()>=2)
			{
				for(int i =1;i<listElement.size();i++) {
					RemoteWebElement ele = listElement.get(i)
					String myText=ele.getText()
					println (ele.getText())
					extentTest.log(Status.FAIL, 'Job Found for invalid search criteria  - '+ searchWith + ' with job id  - '+myText)
			}

			}else
			{
				extentTest.log(Status.PASS, 'No job found for invalid search criteria - '+ searchWith)
				
			}
			break;
		case "NoJobs":
		def isMsgPresent=WebUI.verifyElementPresent(findTestObject('Object Repository/JobMonitoringPage/NoJobsMsg'), 5, FailureHandling.CONTINUE_ON_FAILURE)
		if(isMsgPresent)
		{
			extentTest.log(Status.PASS, 'No job found for user - '+GlobalVariable.G_userName)
			
			}
		else
		{
			extentTest.log(Status.PASS, 'Jobs found for user - '+GlobalVariable.G_userName)
			
			}
			break;
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