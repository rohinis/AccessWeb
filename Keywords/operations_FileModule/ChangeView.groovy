package operations_FileModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus
import com.kms.katalon.core.exception.StepErrorException as StepErrorException



public class ChangeView {
	@Keyword
	def changePageView(String TestCaseName,extentTest) {

		try {
			def tileView
			def listView

			TestObject viewIconTile = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title',
					'equals', 'Tile View', true)
			TestObject viewIconList = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title',
					'equals', 'List View', true)

			tileView = WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/SortLableTileView'), 3, FailureHandling.CONTINUE_ON_FAILURE)

			listView = WebUI.verifyElementPresent(findTestObject('Object Repository/JobMonitoringPage/setting_icon'), 3, FailureHandling.CONTINUE_ON_FAILURE)

			println('current view - tile view  - ' + tileView)
			println('current view - list view - ' + listView)

			if (tileView) {
				WebUI.click(viewIconList)
				extentTest.log(LogStatus.PASS, 'Changed View to ListView')
				WebUI.delay(2)
			}

			if (TestCaseName.contains('tile view')) {
				println("in tile wala if")
				WebUI.click(viewIconTile)
				extentTest.log(LogStatus.PASS, 'Changed View to Tile View as per the test case - ' +TestCaseName)
			}
		}
		catch (Exception  ex) {

			String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
			WebUI.takeScreenshot(screenShotPath)
			extentTest.log(LogStatus.FAIL,ex)
		}
		catch (StepErrorException  e) {

			String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
			WebUI.takeScreenshot(screenShotPath)
			extentTest.log(LogStatus.FAIL,e)
		}
	}
}
