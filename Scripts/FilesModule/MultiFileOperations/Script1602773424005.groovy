import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver

import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status

import internal.GlobalVariable as GlobalVariable

//==================================================================
def Browser = GlobalVariable.G_Browser
//===============================================================
def extentTest=GlobalVariable.G_ExtentTest
//==============================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=============================================================



//Updaing test case name to represent multifile operations and icon operations
TestCaseName='MultiFileOperation -'+TestCaseName
if (Operation.contains('icon')) {
	TestCaseName = (TestCaseName + ' thorugh top menu icons')
}

WebDriver driver = DriverFactory.getWebDriver()
EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()
RemoteWebDriver katalonWebDriver = (RemoteWebDriver) wrappedWebDriver

//updating location to navigate as per module

def result=false
def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()
def location=null
def pasteLocation=null

TestObject newFileObj=null

if (Operation.contains('icon'))
{
	location = navLocation + '/MultipleFilesIcons'
}
else
{
	location = navLocation + '/MultipleFiles'
}
if (TestCaseName.contains('tile view'))
{
	if(TestCaseName.contains('Cut')||TestCaseName.contains('Copy'))
	{
		location = (location + '/TileViewCut')
	}
	else
	{
		location = (location + '/TileView')
	}
	newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals', 'five.txt',
			true)
}
else
{
	pasteLocation = (location + '/ToPasteLV')
	if(TestCaseName.contains('Cut')||TestCaseName.contains('Copy'))
	{
		location = (location + '/ListViewCut')
	}
	else
	{
		location = (location + '/ListView')
	}
	newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'data-automation-id', 'equals', 'five.txt',
			true)
}
println("================================================")
println("navlocation - "+ navLocation)
println("================================================")
println("location - "+location)
println("================================================")
println("pastelocation - "+pasteLocation)
println("================================================")

try {
	WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
	extentTest.log(Status.PASS, 'Navigated to Files Tab')
	//WebUI.delay(2)
	WebUI.enableSmartWait()

/*	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
	extentTest.log(Status.PASS, 'Navigated to - ' + location)*/
	CustomKeywords.'generateFilePath.filePath.navlocation'(location, extentTest)

	CustomKeywords.'operations_FileModule.ChangeView.changePageView'('list view', extentTest)

	// checking the first row element - if two.txt sorting by name to get five.txt at top
	String myXpath="//div[@col-id='name']"
	List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
	println("================================================")
	println(listElement[1].getText())
	println("================================================")

	extentTest.log(Status.INFO,listElement[1].getText())
	if(listElement[1].getText().equals('five.txt'))
	{
		println("in order")
	}
	else{
		println("need to sort")
		WebUI.click(findTestObject('Object Repository/FilesPage/Label-FilesTable-Name'))
	}

	WebUI.click(findTestObject('FilesPage/SelectallFiles'))
	extentTest.log(Status.PASS, 'Clicked on Select All header check box ')

	//Changing view as per test case
	CustomKeywords.'operations_FileModule.ChangeView.changePageView'(TestCaseName, extentTest)

	//Calling keyword as pre operation - icon or context menu
	if (Operation.contains('icon'))
	{
		result = CustomKeywords.'operations_FileModule.multifileOps.multiFileOperationsIcons'(Operation, TestCaseName, extentTest,
				katalonWebDriver)
	}
	else
	{
		def fileItem = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newFileObj, 20, extentTest, 'five.txt')
		println(fileItem)
		if (fileItem) {
			WebUI.waitForElementPresent(newFileObj, 10)
			WebUI.scrollToElement(newFileObj, 5)
			WebUI.delay(3)
			WebUI.rightClick(newFileObj)
			extentTest.log(Status.PASS, 'Right Clicked File to invoke context menu on  ')
			WebUI.delay(2)
		}
		result = CustomKeywords.'operations_FileModule.multifileOpsCntxtMn.multiFileOperations'(Operation, TestCaseName,
				extentTest, katalonWebDriver)
	}
	WebUI.disableSmartWait()
	
	if (result)
	{
		extentTest.log(Status.PASS, ('Verified - ' + TestCaseName) + '  Sucessfully')
	} else {
		extentTest.log(Status.FAIL, TestCaseName + ' - failed')
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