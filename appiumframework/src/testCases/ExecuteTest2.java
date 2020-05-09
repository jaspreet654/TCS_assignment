package testCases;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import operation.ReadObject;
import operation.Keywords;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import excelExportAndFileIO.ReadExcelFile;

/**
 * THIS IS THE EXAMPLE OF KEYWORD DRIVEN TEST CASE
 *
 */
public class ExecuteTest2 {
	public static void main(String args[]) throws Exception {
		WebDriver driver;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", "Android");
		capabilities.setCapability("deviceName", "AndroidEmulator");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.ebay.mobile");
		// This package is name of your app (you can get it from apk info app)
		capabilities.setCapability("appActivity", "com.ebay.mobile.activities.MainActivity");
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		ReadExcelFile file = new ReadExcelFile();
		ReadObject object = new ReadObject();
		Properties allObjects = object.getObjectRepository();
		Keywords operation = new Keywords(driver);
		// Read keyword sheet
		Sheet jaspreetSheet = file.readExcel("C:\\seleniumproject\\appiumframework\\src\\TestScripts", "TestCase1.xlsx",
				"KeywordFramework");
		// Find number of rows in excel file
		int rowCount = jaspreetSheet.getLastRowNum() - jaspreetSheet.getFirstRowNum();
		System.out.println("rows:" + rowCount);
		// Create a loop over all the rows of excel file to read it
		for (int i = 1; i < rowCount + 1; i++) {
			// Loop over all the rows
			Row row = jaspreetSheet.getRow(i);
			// Check if the first cell contain a value, if yes, That means it is the new
			// testcase name
			if (row.getCell(0).toString().length() == 0) {
				// Print testcase detail on console
				System.out.println(row.getCell(1).toString() + "----" + row.getCell(2).toString() + "----"
						+ row.getCell(3).toString() + "----" + row.getCell(4).toString());
				// Call perform function to perform operation on UI
				operation.perform(allObjects, row.getCell(1).toString(), row.getCell(2).toString(),
						row.getCell(3).toString(), row.getCell(4).toString());
			} else {
				// Print the new testcase name when it started
				System.out.println("New Testcase->" + row.getCell(0).toString() + " Started");
			}
		}
	}

}
