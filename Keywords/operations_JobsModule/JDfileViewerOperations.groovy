package operations_JobsModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status

import internal.GlobalVariable

public class JDfileViewerOperations {


	@Keyword
	def executeFileOperations(WebDriver katalonWebDriver,String Operation,String TestCaseName ,extentTest) {
		boolean result=false

		println ("Control in Keyword --- "+ Operation )
		WebUI.delay(2)
		switch (Operation) {

			case 'Details':
				WebUI.click(findTestObject('Object Repository/JobDetailsPage/Icon_DetailsFile'))
				WebUI.delay(2)
				WebUI.verifyElementPresent(findTestObject('JobDetailsPage/Icon_DetailsFile'), 3, FailureHandling.STOP_ON_FAILURE)
				extentTest.log(Status.PASS, 'Clicked on file to view details')
				TestObject newFileNameDetails=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FileEditor/FileName_Text_Details'), 'text', 'equals', 'ToEdit.txt', true)
				TestObject newFileOwnerDetails=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FileEditor/FileOwner_Text_Details'), 'text', 'equals', GlobalVariable.G_userName, true)
				def isFileNamePresent=WebUI.verifyElementPresent(newFileNameDetails, 5)
				def isFileOwnerPresent=WebUI.verifyElementPresent(newFileOwnerDetails, 5)
				if(isFileNamePresent && isFileOwnerPresent) {
					extentTest.log(Status.PASS, 'File name and file owner verified')
					result=true
				}
				else {
					extentTest.log(Status.FAIL, 'File name and file owner not matched')
					result=false
				}

				WebUI.click(findTestObject('Object Repository/FilesPage/Close_DetailsPanel'))

				return result
				break


			case 'EditSave':
				def myXpath="//div[@id='ace-editor']//textarea"
				def myLineXpath='//div[@class="ace_content"]'
				WebUI.click(findTestObject('Object Repository/JobDetailsPage/Icon_EditFile'))
				extentTest.log(Status.PASS, 'Click on file edit icon')
				WebUI.delay(3)
				WebElement textEle=katalonWebDriver.findElement(By.xpath(myXpath))

				List<WebElement> linesBeforeEdit = katalonWebDriver.findElements(By.xpath(myLineXpath))
				def num=linesBeforeEdit.size()
			//	extentTest.log(Status.INFO, 'linesBeforeEdit - '+ num)

				RemoteWebElement ele = textEle
				ele.sendKeys("new line added")
				ele.sendKeys('\n')
				extentTest.log(Status.PASS, 'Added new line into file')
				WebUI.click(findTestObject('Object Repository/FilesPage/btn_Save_fileEditor'))
				extentTest.log(Status.PASS, 'Saved the file ')

				WebUI.click(findTestObject('Object Repository/JobDetailsPage/Icon_EditFile'))
				extentTest.log(Status.PASS, 'Click on file edit icon again to check added lines ')
				WebUI.delay(3)
				List<WebElement> linesAfterEdit = katalonWebDriver.findElements(By.xpath(myLineXpath))
				def num3=linesAfterEdit.size()

			//	extentTest.log(Status.INFO, 'linesAfterEdit - '+ num3)

				if(num3>num) {
					extentTest.log(Status.PASS, 'New  lines added in file')
					result=true
				}
				else {
					extentTest.log(Status.FAIL, 'New line not added in file')

					result=false
				}
				return result
				break


			case 'EditCancel':
				def myXpath="//div[@id='brace-editor']//textarea"
				def myLineXpath='//div[@class="ace_line_group"]'
				WebUI.click(findTestObject('Object Repository/JobDetailsPage/Icon_EditFile'))
				extentTest.log(Status.PASS, 'Click on file edit icon')
				WebUI.delay(3)
				WebElement textEle=katalonWebDriver.findElement(By.xpath(myXpath))

				List<WebElement> linesBeforeEdit = katalonWebDriver.findElements(By.xpath(myLineXpath))
				def num=linesBeforeEdit.size()
			//extentTest.log(Status.INFO, 'linesBeforeEdit - '+ num)

				RemoteWebElement ele = textEle
				ele.sendKeys("new line added")
				ele.sendKeys('\n')
				extentTest.log(Status.PASS, 'Added new line into file')
				WebUI.click(findTestObject('Object Repository/FilesPage/btn_Cancel_fileEditor'))
				extentTest.log(Status.PASS, 'Saved the file ')

				WebUI.click(findTestObject('Object Repository/JobDetailsPage/Icon_EditFile'))
				extentTest.log(Status.PASS, 'Click on file edit icon again to check added lines ')
				WebUI.delay(3)
				List<WebElement> linesAfterEdit = katalonWebDriver.findElements(By.xpath(myLineXpath))
				def num3=linesAfterEdit.size()

			//extentTest.log(Status.INFO, 'linesAfterEdit - '+ num3)

				if(num3>num) {
					extentTest.log(Status.FAIL, 'New lines added in file')
					result=false
				}
				else {
					extentTest.log(Status.PASS, 'New line not added in file')

					result=true
				}
				return result
				break

			case 'Download':

				WebUI.click(findTestObject('Object Repository/JobDetailsPage/Icon_DownloadFile'))
				extentTest.log(Status.PASS, 'Click on file to download')
				WebUI.delay(3)
				def downloadLoc=GlobalVariable.G_DownloadFolder
				File downloadFolder = new File("C://KatalonDownloads")

				List namesOfFiles = Arrays.asList(downloadFolder.list())
				println(namesOfFiles.size())
				if (namesOfFiles.contains('ToEdit.txt')) {
					println('success')
					//extentTest.log(Status.PASS, 'file to downloaded ')
					result=true
				} else {
					println('fail')
					result=false
				}

				return true
				break

			case 'Delete':

				WebUI.click(findTestObject('Object Repository/JobDetailsPage/Icon_DeleteFile'))
				extentTest.log(Status.PASS, 'Click on file to delete')
				result=true
				return result
				break

			case 'Tail':
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FileViewer_Operation'), 'title', 'equals', Operation, true)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Click on file to perform tail operation')
				result=true
				return result
				break
			case 'pagination':
				extentTest.log(Status.PASS,"output folder 's file openned in file viewer, contains content less than a page")
				result=WebUI.verifyElementClickable(findTestObject('Object Repository/FilesPage/Pagination/pagination_numberfield'))
				if(result)
					extentTest.log(Status.PASS, 'Verified the pagenumbers are active')
				def res1=WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/Pagination/pagination_element1_disabled'),5)
				def res2=WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/Pagination/pagination_element2_disabled'),5)
				def res3=WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/Pagination/pagination_element3_disabled'),5)
				def res4=WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/Pagination/pagination_element4_disabled'),5)
				if(res1&&res2&&res3&&res4) {
					extentTest.log(Status.PASS, 'Verified the pagination elements are disabled')
				}
				else {
					extentTest.log(Status.FAIL, 'failed to verify the pagination elements')
				}
				break
		}
	}
}