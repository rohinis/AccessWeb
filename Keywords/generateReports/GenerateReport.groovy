package generateReports
import static com.aventstack.extentreports.reporter.configuration.Theme.DARK
import static com.aventstack.extentreports.reporter.configuration.Theme.STANDARD

import java.text.SimpleDateFormat

import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.reporter.ExtentSparkReporter
import com.aventstack.extentreports.reporter.configuration.ViewName
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration

import internal.GlobalVariable as GlobalVariable

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class GenerateReport {


	@Keyword
	def createSpark(String ReportName , String BrowserName , String BrowserVersion, String totalTime) {
		//String totalTime
		def date = new Date()
		def filePath = (RunConfiguration.getProjectDir() + '/ExtentReports/')
		def path = filePath+ReportName
		println ("********************* - "+path+" - *********************")
		//creating Extent spark reporter
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(path)
		extent.attachReporter(spark);


		//Creating Testsuite name for systemenv in reports
		String TestSuite=ReportName
		TestSuite=ReportName.toLowerCase()
		String[] Ts=TestSuite.split("_")
		String TestSuiteName = Ts[0];

		//creating system environments in extentreports dashboard
		extent.setSystemInfo("BrowserName",BrowserName)
		extent.setSystemInfo("BrowserVersion", BrowserVersion)
		extent.setSystemInfo("TestSuite", TestSuiteName )

		return extent
	}


	@Keyword
	def getDate() {
		def date = new Date()
		def sdf = new SimpleDateFormat("dd_MM_yyyy_HHmmss")
		def start_time = sdf.format(date)
		println start_time
	}
}
