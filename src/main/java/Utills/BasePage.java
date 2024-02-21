package Utills;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BasePage {
	public static WebDriver driver;

	String ChromeDriverPath = System.getProperty("user.dir") + "/BrowserDrivers/chromedriver.exe";
	String EdgeDriverPath = System.getProperty("user.dir") + "/BrowserDrivers/msedgedriver.exe";
	String appCongigFilPath = System.getProperty("user.dir") + "/appConfig.properties";
	static String Report = System.getProperty("user.dir") + "/TestReport";
	FileInputStream fi;
	Properties prop;

	public static ExtentReports extentReports;
	public static ExtentSparkReporter extentSparkReporter;
	public static ExtentTest extentTest;

	
	public static void startReport() {
		extentReports = new ExtentReports();
		extentSparkReporter = new ExtentSparkReporter(Report);
		extentReports.attachReporter(extentSparkReporter);

	}

	public void invokeBrowser() throws IOException {
		
		fi = new FileInputStream(appCongigFilPath);
		prop = new Properties();
		prop.load(fi);

		String driverFlag = prop.getProperty("Browser");
		String URL = prop.getProperty("URL");
		String chromeDriverPath = prop.getProperty("chromeDriverValue");
		String edgeDriverPath = prop.getProperty("edhgeDriverValue");

		switch (driverFlag) {

		case "Chrome":
			ChromeOptions options = new ChromeOptions();
			System.setProperty(chromeDriverPath, ChromeDriverPath);
			options.addArguments("incognito");
			driver = new ChromeDriver(options);

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();

			driver.get(URL);

			break;

		case "Edge":

			System.setProperty(edgeDriverPath, EdgeDriverPath);
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get(URL);
			break;

		default:

			System.out.println("No Browser Value For Driver Flag");
			break;

		}

	}

	public void closeBrowser() {
		driver.quit();
	}

	
	public static void closeReport() {
		extentReports.flush();
	}

}
