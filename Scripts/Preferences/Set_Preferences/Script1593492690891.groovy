import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.junit.After as After
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.exception.StepErrorException as StepErrorException
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.MediaEntityBuilder as MediaEntityBuilder
import com.aventstack.extentreports.Status as Status
import internal.GlobalVariable as GlobalVariable

//==================================================================
def Browser = GlobalVariable.G_Browser

//===============================================================
def extentTest = GlobalVariable.G_ExtentTest

//===========================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)

//=============================================================
WebUI.delay(2)

try {
	switch (userChoice) {
		case 'verify theme colour':
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))

			extentTest.log(Status.PASS, 'Click on preference')

			WebUI.verifyElementPresent(findTestObject('PageNavigation/Preferences/ThemeColour'), 5)

			extentTest.log(Status.PASS, 'Verify theme colur')

			break
		case 'verify font size':
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))

			extentTest.log(Status.PASS, 'Click on preference')

			WebUI.verifyElementPresent(findTestObject('PageNavigation/Preferences/Fontsize'), 5)

			extentTest.log(Status.PASS, 'Verify font size')

			break
		case 'Diagnosis':
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))

			extentTest.log(Status.PASS, 'Click on the  preference')

			TestObject prefer = WebUI.modifyObjectProperty(findTestObject('PageNavigation/Preferences/Choice'), 'text',
				'equals', preference, true)

			WebUI.click(prefer)

			WebUI.click(findTestObject('PageNavigation/Preferences/Reset'))

			extentTest.log(Status.PASS, 'Click on Reset all')

			WebUI.click(findTestObject('PageNavigation/Preferences/Confirm_button'))

			extentTest.log(Status.PASS, 'Click on yes button')

			result = WebUI.verifyElementPresent(findTestObject('PageNavigation/Preferences/Reset_popup'), 2)

			if (result) {
				extentTest.log(Status.PASS, 'Verify the Reset  pop-up ')
			} else {
				extentTest.log(Status.FAIL, 'failed to verify the popup')
			}
			
			break
		case 'Remote Session':
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))

			extentTest.log(Status.PASS, 'Click on preference')

			TestObject prefer = WebUI.modifyObjectProperty(findTestObject('PageNavigation/Preferences/Choice'), 'text',
				'equals', preference, true)

			WebUI.click(prefer)

			extentTest.log(Status.PASS, 'Click on preference')

			WebUI.delay(3)

			WebUI.verifyElementPresent(findTestObject('PageNavigation/Preferences/Remotesession'), 5)

			extentTest.log(Status.PASS, 'Verify Remote session resolution')

			break
		case 'Profile':
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))

			extentTest.log(Status.PASS, 'Click on preference')

			TestObject prefer = WebUI.modifyObjectProperty(findTestObject('PageNavigation/Preferences/Choice'), 'text',
				'equals', preference, true)

			WebUI.click(prefer)

			extentTest.log(Status.PASS, 'Click on preference')

			WebUI.click(findTestObject('PageNavigation/Preferences/Profilename'))

			WebUI.setText(findTestObject('PageNavigation/Preferences/Profilename'), 'samishtha')

			extentTest.log(Status.PASS, 'Add profile name')

			WebUI.click(findTestObject('PageNavigation/Preferences/Profile_email'))

			WebUI.setText(findTestObject('PageNavigation/Preferences/Profile_email'), 'samishtha.taneja@altair.com')

			extentTest.log(Status.PASS, 'Add email id')

			WebUI.click(findTestObject('PageNavigation/Preferences/Back'))

			extentTest.log(Status.PASS, 'Click on back')

			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))

			extentTest.log(Status.PASS, 'Click on preference')

			WebUI.click(findTestObject('PageNavigation/Preferences/Profile'))

			extentTest.log(Status.PASS, 'Click on profile')
		case 'Notifications On':
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))

			extentTest.log(Status.PASS, 'Click on preference')

			TestObject prefer = WebUI.modifyObjectProperty(findTestObject('PageNavigation/Preferences/Choice'), 'text',
				'equals', preference, true)

			WebUI.click(prefer)

			extentTest.log(Status.PASS, 'Click on preference')

			WebUI.verifyElementPresent(findTestObject('PageNavigation/Preferences/Tickmark'), 5)

			WebUI.click(findTestObject('PageNavigation/Preferences/Back'))

			extentTest.log(Status.PASS, 'Click on back')

			'Navigate to Files Tab\r\n'
			def isElemenetPresent = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Files'),
				5, extentTest, 'Title')

			if (isElemenetPresent) {
				WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))

				extentTest.log(Status.PASS, 'Navigated to Files Tab')
			}
			
			WebUI.delay(2)

			WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 5)

			'Click New File Button '
			WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))

			extentTest.log(Status.PASS, 'Clicked on New File Button')

			WebUI.click(findTestObject('FilesPage/ListItem_File'))

			WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))

			WebUI.waitForElementVisible(findTestObject('FilesPage/TextBx_CreateFile'), 5)

			WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))

			WebUI.setText(findTestObject('FilesPage/TextBx_CreateFile'), 'new.txt')

			extentTest.log(Status.PASS, 'Enterted File Name to create ')

			'Click save\r\n'
			WebUI.click(findTestObject('FilesPage/btn_Save'))

			extentTest.log(Status.PASS, 'Clicked on Save Button')

			result = WebUI.verifyElementPresent(findTestObject('PageNavigation/Preferences/Notification_popup'), 2)

			if (result) {
				extentTest.log(Status.PASS, 'Verified the Notification for file creation ')
			} else {
				extentTest.log(Status.FAIL, 'Failed to verify the notification')
			}
			
			break
		case 'Notifications Off':
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))

			extentTest.log(Status.PASS, 'Click on preference')

			TestObject prefer = WebUI.modifyObjectProperty(findTestObject('PageNavigation/Preferences/Choice'), 'text',
				'equals', preference, true)

			WebUI.click(prefer)

			extentTest.log(Status.PASS, 'Click on preference')

			WebUI.click(findTestObject('PageNavigation/Preferences/Tickmark'))

			WebUI.click(findTestObject('PageNavigation/Preferences/Back'))

			extentTest.log(Status.PASS, 'Click on back')

			'Navigate to Files Tab\r\n'
			def isElemenetPresent = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Files'),
				5, extentTest, 'Title')

			if (isElemenetPresent) {
				WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))

				extentTest.log(Status.PASS, 'Navigated to Files Tab')
			}
			
			WebUI.delay(2)

			WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 5)

			'Click New File Button '
			WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))

			extentTest.log(Status.PASS, 'Clicked on New File Button')

			WebUI.click(findTestObject('FilesPage/ListItem_File'))

			WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))

			WebUI.waitForElementVisible(findTestObject('FilesPage/TextBx_CreateFile'), 5)

			WebUI.click(findTestObject('FilesPage/TextBx_CreateFile'))

			WebUI.setText(findTestObject('FilesPage/TextBx_CreateFile'), fileName)

			extentTest.log(Status.PASS, 'Enterted File Name to create ')

			'Click save\r\n'
			WebUI.click(findTestObject('FilesPage/btn_Save'))

			extentTest.log(Status.PASS, 'Clicked on Save Button')

			WebUI.click(findTestObject('Landing_Page/Btn_Notifiction2'))

			extentTest.log(Status.PASS, 'Opened Notification Panel')

			WebUI.delay(2)

			result = WebUI.verifyElementPresent(findTestObject('Notificactions/Notification_FileCreation'), 5)

			result = WebUI.verifyElementNotPresent(findTestObject('PageNavigation/Preferences/Notification_popup'), 2)

			println('notification status - ' + result)

			if (result) {
				extentTest.log(Status.PASS, 'Verified notification for operation - ')
			} else {
				extentTest.log(Status.FAIL, 'Failed to verify')
			}
			
			break
		case 'Job Submission form open':
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))

			extentTest.log(Status.PASS, 'Click on preference')

			TestObject prefer = WebUI.modifyObjectProperty(findTestObject('PageNavigation/Preferences/Choice'), 'text',
				'equals', preference, true)

			WebUI.click(prefer)

			extentTest.log(Status.PASS, 'Click on preference')
			WebUI.click(findTestObject('PageNavigation/Preferences/Job_submission_tickmark'))
			
						extentTest.log(Status.PASS, 'Enable tick mark')

			WebUI.click(findTestObject('PageNavigation/Preferences/Back'))

			extentTest.log(Status.PASS, 'Click on back')

			TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'),
				'id', 'equals', AppName, true)

			WebUI.click(newAppObj)

			extentTest.log(Status.PASS, 'Navigated to Job Submission For for - ' + AppName)

			//	WebUI.doubleClick(newAppObj)
			WebUI.delay(2)

			def errorPanel = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/JS_ErrorModalPanel'),
				5, extentTest, '')

			if (errorPanel) {
				WebUI.click(findTestObject('Object Repository/JobSubmissionForm/button_Close'))
			}
			
			WebUI.click(findTestObject('Object Repository/LoginPage/NewJobPage/GenericProfile'))

			WebUI.delay(2)

			WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)

			WebUI.delay(3)

			if (ExecMode.equals('LocalForm')) {
				newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
			} else {
				if (ExecMode.equals('Local')) {
					newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
				} else {
					if (TestCaseName.contains('ShortCut')) {
						ExecMode = 'ShortCut'

						newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile,
							extentTest)
					} else {
						newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile,
							extentTest)
					}
				}
				
				WebUI.click(newFileObj)

				WebUI.rightClick(newFileObj)

				extentTest.log(Status.PASS, 'Right Clicked on Input file ' + InputFile)

				WebUI.delay(2)

				String idForCntxtMn = 'Add as ' + FileArg

				TestObject newRFBContextMnOption = WebUI.modifyObjectProperty(findTestObject('Object Repository/LoginPage/NewJobPage/ContextMenu_RFB_FilePicker'),
					'id', 'equals', idForCntxtMn, true)

				WebUI.click(newRFBContextMnOption)

				extentTest.log(Status.PASS, 'Clicked on context menu - ' + idForCntxtMn)
			}
			
			def submitBtn = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/button_Submit_Job'),
				10, extentTest, '')

			if (submitBtn) {
				WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))

				extentTest.log(Status.PASS, 'Clicked on Submit Button ')
			}
			
			result = WebUI.verifyElementPresent(findTestObject('JobSubmissionForm/button_Submit_Job'), 5)

			extentTest.log(Status.PASS, 'Verify submit button present')

			break
		case 'Job Submission form close':
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))

			extentTest.log(Status.PASS, 'Click on preference')

			TestObject prefer = WebUI.modifyObjectProperty(findTestObject('PageNavigation/Preferences/Choice'), 'text',
				'equals', preference, true)

			WebUI.click(prefer)

			extentTest.log(Status.PASS, 'Click on preference')

			WebUI.delay(2)

			WebUI.click(findTestObject('PageNavigation/Preferences/Job_submission_tickmark'))

			extentTest.log(Status.PASS, 'Enable tick mark')

			WebUI.click(findTestObject('PageNavigation/Preferences/Back'))

			extentTest.log(Status.PASS, 'Click on back')

			TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'),
				'id', 'equals', AppName, true)

			WebUI.click(newAppObj)

			extentTest.log(Status.PASS, 'Navigated to Job Submission For for - ' + AppName)

			//	WebUI.doubleClick(newAppObj)
			WebUI.delay(2)

			def errorPanel = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/JS_ErrorModalPanel'),
				5, extentTest, '')

			if (errorPanel) {
				WebUI.click(findTestObject('Object Repository/JobSubmissionForm/button_Close'))
			}
			
			WebUI.click(findTestObject('Object Repository/LoginPage/NewJobPage/GenericProfile'))

			WebUI.delay(2)

			WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)

			WebUI.delay(3)

			if (ExecMode.equals('LocalForm')) {
				newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
			} else {
				if (ExecMode.equals('Local')) {
					newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile, extentTest)
				} else {
					if (TestCaseName.contains('ShortCut')) {
						ExecMode = 'ShortCut'

						newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile,
							extentTest)
					} else {
						newFileObj = CustomKeywords.'operations_JobsModule.JobSubmissions.selectFile'(ExecMode, InputFile,
							extentTest)
					}
				}
				
				WebUI.click(newFileObj)

				WebUI.rightClick(newFileObj)

				extentTest.log(Status.PASS, 'Right Clicked on Input file ' + InputFile)

				WebUI.delay(2)

				String idForCntxtMn = 'Add as ' + FileArg

				TestObject newRFBContextMnOption = WebUI.modifyObjectProperty(findTestObject('Object Repository/LoginPage/NewJobPage/ContextMenu_RFB_FilePicker'),
					'id', 'equals', idForCntxtMn, true)

				WebUI.click(newRFBContextMnOption)

				extentTest.log(Status.PASS, 'Clicked on context menu - ' + idForCntxtMn)
			}
			
			def submitBtn = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/button_Submit_Job'),
				10, extentTest, '')

			if (submitBtn) {
				WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))

				extentTest.log(Status.PASS, 'Clicked on Submit Button ')
			}
			
			WebUI.verifyElementPresent(findTestObject('LoginPage/NewJobPage/AppList_ShellScript'), 5)

			extentTest.log(Status.PASS, 'Verify app def is present')

			break
		case 'Hidden Files Enable':
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))

			extentTest.log(Status.PASS, 'Click on preference')

			TestObject prefer = WebUI.modifyObjectProperty(findTestObject('PageNavigation/Preferences/Choice'), 'text',
				'equals', preference, true)

			WebUI.click(prefer)

			extentTest.log(Status.PASS, 'Click on preference')

			WebUI.click(findTestObject('PageNavigation/Preferences/Tickmark'))

			extentTest.log(Status.PASS, 'Enable hidden items')

			WebUI.click(findTestObject('PageNavigation/Preferences/Back'))

			extentTest.log(Status.PASS, 'Click on back')

			'Navigate to Files Tab\r\n'
			def isElemenetPresent = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Files'),
				5, extentTest, ' hidden')

			if (isElemenetPresent) {
				WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))

				extentTest.log(Status.PASS, 'Navigated to Files Tab')
			}
			
			def location = ('/stage/' + GlobalVariable.G_userName) + '/ForHidden'

			/*		WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
			

			WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)

			WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
			extentTest.log(Status.PASS, 'Navigated to /stage/JSUploads in RFB ')*/
			CustomKeywords.'generateFilePath.filePath.navlocation'(location, extentTest)

			WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))

			WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 2)

			println('.hidden')

			WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), 'hiddenfile')

			extentTest.log(Status.PASS, 'Looking for file to perfrom operation - ')

			WebUI.sendKeys(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), Keys.chord(Keys.ENTER))

			extentTest.log(Status.PASS, 'Clicked on File')

			result = WebUI.verifyElementPresent(findTestObject('PageNavigation/Preferences/HiddenFile'), 5)

			if (result) {
				extentTest.log(Status.PASS, 'Verify the hidden file is  present')
			}
			
			break
		case 'Hidden Files Disable':
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))

			TestObject prefer = WebUI.modifyObjectProperty(findTestObject('PageNavigation/Preferences/Choice'), 'text',
				'equals', preference, true)

			WebUI.click(prefer)

			extentTest.log(Status.PASS, 'Click on preference')

			WebUI.click(findTestObject('PageNavigation/Preferences/Tickmark'))

			extentTest.log(Status.PASS, 'Enable hidden items')

			WebUI.click(findTestObject('PageNavigation/Preferences/Back'))

			extentTest.log(Status.PASS, 'Click on back')

			'Navigate to Files Tab\r\n'
			def isElemenetPresent = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Files'),
				5, extentTest, ' ')

			if (isElemenetPresent) {
				WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))

				extentTest.log(Status.PASS, 'Navigated to Files Tab')
			}
			
			def location = '/stage/' + GlobalVariable.G_userName

			CustomKeywords.'generateFilePath.filePath.navlocation'(location, extentTest)

			/*	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
			

			WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)

			WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
			extentTest.log(Status.PASS, 'Navigated to /stage/JSUploads in RFB ')*/
			/*
		 WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))
		 WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 2)
		 println('.hidden')
		 WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), '.hidden')
		 extentTest.log(Status.PASS, 'Looking for file to perfrom operation - ')
		 WebUI.sendKeys(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), Keys.chord(Keys.ENTER))
		 extentTest.log(Status.PASS, 'Clicked on File')
		 prefer
		 */
			result = WebUI.verifyElementNotPresent(findTestObject('PageNavigation/Preferences/HiddenFile'), 5)

			if (result) {
				extentTest.log(Status.PASS, 'Verify the hidden file is not present  ' + result)
			}
			
			break /*
		 case 'Pagination':
		 WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))
		 extentTest.log(Status.PASS, 'Click on profile tab')
		 WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))
		 extentTest.log(Status.PASS, 'Click on preference')
		 TestObject	 prefer = WebUI.modifyObjectProperty(findTestObject('PageNavigation/Preferences/Choice'), 'text', 'equals',
		 preference, true)
		 WebUI.click(prefer)
		 extentTest.log(Status.PASS, 'Click on preference')
		 break
		 case 'Tail Frequency':
		 WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))
		 extentTest.log(Status.PASS, 'Click on profile tab')
		 WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))
		 extentTest.log(Status.PASS, 'Click on preference')
		 TestObject	 prefer = WebUI.modifyObjectProperty(findTestObject('PageNavigation/Preferences/Choice'), 'text', 'equals',
		 preference, true)
		 WebUI.click(prefer)
		 extentTest.log(Status.PASS, 'Click on preference')
		 break
		 */
		case 'Hidden Files Disable':
			/*WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))
		extentTest.log(Status.PASS, 'Click on profile tab')

		WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))
		WebUI.click(findTestObject('PageNavigation/Preferences/Tickmark'))
		extentTest.log(Status.PASS, 'Enable hidden items')


		TestObject	 prefer = WebUI.modifyObjectProperty(findTestObject('PageNavigation/Preferences/Choice'), 'text', 'equals',
				preference, true)
		WebUI.click(prefer)
		extentTest.log(Status.PASS, 'Click on preference')



		WebUI.click(findTestObject('PageNavigation/Preferences/Back'))
		extentTest.log(Status.PASS, 'Click on back')*/
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))

			extentTest.log(Status.PASS, 'Click on preference')

			TestObject prefer = WebUI.modifyObjectProperty(findTestObject('PageNavigation/Preferences/Choice'), 'text',
				'equals', preference, true)

			WebUI.click(prefer)

			extentTest.log(Status.PASS, 'Click on preference')

			WebUI.click(findTestObject('PageNavigation/Preferences/Back'))

			extentTest.log(Status.PASS, 'Click on back')

			'Navigate to Files Tab\r\n'
			def isElemenetPresent = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Files'),
				5)

			if (isElemenetPresent) {
				WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))

				extentTest.log(Status.PASS, 'Navigated to Files Tab')
			}
			
			WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))

			def location = '/stage/' + GlobalVariable.G_userName

			WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)

			WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))

			extentTest.log(Status.PASS, 'Navigated to /stage/JSUploads in RFB ')

			WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))

			WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 2)

			println('.hidden')

			WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), 'hidden')

			extentTest.log(Status.PASS, 'Looking for file to perfrom operation - ')

			WebUI.sendKeys(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), Keys.chord(Keys.ENTER))

			// extentTest.log(Status.PASS, 'Clicked on File')
			result = WebUI.verifyElementNotPresent(findTestObject('PageNavigation/Preferences/HiddenFile'), 5)

			if (result) {
				extentTest.log(Status.PASS, 'The hidden file is not present')
			}
			
			break
		case 'Persistence Off':
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))

			extentTest.log(Status.PASS, 'Click on preference')

			TestObject prefer = WebUI.modifyObjectProperty(findTestObject('PageNavigation/Preferences/Choice'), 'text',
				'equals', preference, true)

			WebUI.click(prefer)

			extentTest.log(Status.PASS, 'Click on preference')

			WebUI.click(findTestObject('PageNavigation/Preferences/Back'))

			extentTest.log(Status.PASS, 'Click on back')

			WebUI.delay(2)

			WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

			WebUI.delay(2)

			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))

			TestObject newJobFilter = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/label_jobState'), 'text',
				'equals', 'Completed', true)

			WebUI.click(newJobFilter)

			WebUI.delay(2)

			extentTest.log(Status.INFO, 'Clicked on job with state  - Completed')

			TestObject newJobRow = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/div_Completed'), 'title',
				'equals', 'Completed', true)

			WebUI.rightClick(newJobRow)

			WebUI.click(findTestObject('JobMonitoringPage/ViewDetails_Jobs'))

			WebUI.waitForElementVisible(findTestObject('JobMonitoringPage/OutputFolder_File'), 5)

			WebUI.click(findTestObject('PageNavigation/Preferences/Sessions'))

			WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

			WebUI.delay(2)

			WebUI.verifyElementPresent(findTestObject('PageNavigation/Preferences/Appdef'), 5)

			extentTest.log(Status.PASS, 'Verify app def is present')

			break
		case 'Persistence On':
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))

			extentTest.log(Status.PASS, 'Click on preference')

			TestObject prefer = WebUI.modifyObjectProperty(findTestObject('PageNavigation/Preferences/Choice'), 'text',
				'equals', preference, true)

			WebUI.click(prefer)

			extentTest.log(Status.PASS, 'Click on preference')

			WebUI.click(findTestObject('PageNavigation/Preferences/Tickmark'))

			extentTest.log(Status.PASS, 'Click on Tickmark')

			WebUI.click(findTestObject('PageNavigation/Preferences/Back'))

			extentTest.log(Status.PASS, 'Click on back')

			WebUI.delay(2)

			WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

			WebUI.delay(2)

			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))

			TestObject newJobFilter = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/label_jobState'), 'text',
				'equals', 'Completed', true)

			WebUI.click(newJobFilter)

			WebUI.delay(2)

			extentTest.log(Status.INFO, 'Clicked on job with state  - ')

			TestObject newJobRow = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/div_Completed'), 'title',
				'equals', 'Completed', true)

			WebUI.rightClick(newJobRow)

			WebUI.click(findTestObject('JobMonitoringPage/ViewDetails_Jobs'))

			WebUI.waitForElementVisible(findTestObject('JobMonitoringPage/OutputFolder_File'), 5)

			WebUI.click(findTestObject('PageNavigation/Preferences/Sessions'))

			WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))

			WebUI.delay(2)

			WebUI.verifyElementPresent(findTestObject('JobMonitoringPage/OutputFolder_File'), 5)

			break
		case 'RVS':
			WebUI.click(findTestObject('PageNavigation/Preferences/Profiletab'))

			extentTest.log(Status.PASS, 'Click on profile tab')

			WebUI.click(findTestObject('PageNavigation/Preferences/Preference'))

			TestObject prefer = WebUI.modifyObjectProperty(findTestObject('PageNavigation/Preferences/Choice'), 'text',
				'equals', preference, true)

			WebUI.click(prefer)

			extentTest.log(Status.PASS, 'Click on  the preference')

			result = WebUI.verifyElementPresent(findTestObject('PageNavigation/Preferences/Autorefreshtime'), 5)

			if (result) {
				extentTest.log(Status.PASS, 'Verify autorefresh time')
			} else {
				extentTest.log(Status.FAIL, 'failed to verify the  autorefresh time')
			}
			
			break
	}
	
	if (GlobalVariable.G_Browser == 'IE') {
		WebUI.callTestCase(findTestCase('XRepeated_TC/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
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