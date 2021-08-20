package customWait

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.LogStatus

public class setPrefrenceHidden {

	@Keyword
	def setprefrence(def preValue, extentTest) {

		WebUI.click(findTestObject('Preferences/Profiletab'))
		extentTest.log(LogStatus.PASS, 'Click on profile tab')

		WebUI.click(findTestObject('Preferences/Preference'))
		extentTest.log(LogStatus.PASS, 'Click on preference menu item')

		TestObject	 prefer = WebUI.modifyObjectProperty(findTestObject('Preferences/Choice'), 'text', 'equals',"Files", true)
		WebUI.click(prefer)
		extentTest.log(LogStatus.PASS, 'Click on preference - Files')

		WebUI.delay(2)

		def result=WebUI.verifyElementChecked(findTestObject('Preferences/CheckBox_HiddenFiles'), 2,FailureHandling.CONTINUE_ON_FAILURE)

		extentTest.log(LogStatus.PASS, 'result value -- '+ result)
		extentTest.log(LogStatus.PASS, 'preValue value -- '+ preValue)


		if(preValue) {
			if(result) {
				extentTest.log(LogStatus.PASS, 'Enable hidden items is checked')
				extentTest.log(LogStatus.PASS, 'pref - true , res - true ')
			}
			else {
				WebUI.click(findTestObject('Preferences/Tickmark'))
				extentTest.log(LogStatus.PASS, ' Checked the Enable hidden items checkbox')
				extentTest.log(LogStatus.PASS, 'pref - true , res - false ')
			}
		}
		else {
			if(result) {
				extentTest.log(LogStatus.PASS, 'Enable hidden items is checked')
				WebUI.click(findTestObject('Preferences/Tickmark'))
				extentTest.log(LogStatus.PASS, ' UnChecked the Enable hidden items checkbox')
				extentTest.log(LogStatus.PASS, 'pref - false , res - true ')
			}
			else {
				extentTest.log(LogStatus.PASS, ' Enable hidden items checkbox is unchecked')
				extentTest.log(LogStatus.PASS, 'pref - false , res - fasle ')
			}
		}

		WebUI.click(findTestObject('Preferences/Back'))
		extentTest.log(LogStatus.PASS, 'Click on back')
	}

	@Keyword
	def checkHiddenItems(def preValue, TestCaseName, extentTest)
	{
		TestObject newFileObj
		TestObject newFolderObj
		def isFilePresent
		def isFolderPresent
		def result



		if (TestCaseName.contains('tile view')) {
			WebUI.delay(2)
			newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals', ".hiddenFile",true)
			newFolderObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_TileView'), 'title', 'equals',".hiddenFolder", true )

		} else {
			newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals', ".hiddenFile",		true)
			newFolderObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_ListView'), 'title','equals', ".hiddenFolder", true)

		}

		isFilePresent=WebUI.verifyElementPresent(newFileObj, 3, FailureHandling.CONTINUE_ON_FAILURE)
		isFolderPresent=WebUI.verifyElementPresent(newFolderObj, 3, FailureHandling.CONTINUE_ON_FAILURE)

		if(preValue)
		{
			if(isFilePresent && isFilePresent) {
				extentTest.log(LogStatus.PASS, 'Hidden File Present - '+isFilePresent+' Hidden Folder present - '+isFilePresent)
				result=true
			}
			else {
				extentTest.log(LogStatus.FAIL, 'Hidden File/Folder not listed ')
				result=false
			}

		}
		else
		{
			if(isFilePresent && isFilePresent) {
				extentTest.log(LogStatus.FAIL, 'Hidden File Present - '+isFilePresent+' Hidden Folder present - '+isFilePresent +"- for prefrece set to false" )
				result=false
			}
			else {
				extentTest.log(LogStatus.PASS, 'Hidden File/Folder not listed ')
				result=true
			}
		}

	}



}




