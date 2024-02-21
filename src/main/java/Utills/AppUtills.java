package Utills;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


import dev.failsafe.internal.util.Assert;

public class AppUtills extends BasePage {

	
	
	
	
	
	public void getScreenShotForSuccess(String TestCases) throws IOException {

		String DateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyy_HH_mm_ss"));
		String SuccessScreenShotPath = System.getProperty("user.dir") + "/Screenshots/Success/" + DateTime
				+ "/Screenshot_" + DateTime + ".png";
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scr, new File(SuccessScreenShotPath));

	}

	public void getScreenShotForFailed(String TestCases) throws IOException {
		String DateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyy_HH_mm_ss"));
		String SuccessScreenShotPath = System.getProperty("user.dir") + "/Screenshots/Failed/" + DateTime
				+ "/Screenshot_" + DateTime + ".png";
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scr, new File(SuccessScreenShotPath));

	}

	public void clickElement(By by) throws IOException {
		boolean res = false;
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			element.click();
			getScreenShotForSuccess(by.toString());
			Assert.isTrue(true, "Element is  visible");
		} else {
			getScreenShotForFailed(by.toString());

			Assert.isTrue(false, "Element is Not  visible");
			;
		}
	}

	public void enterText(By by, String TextValue) throws IOException {
		boolean res = false;
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			element.sendKeys(TextValue);
			getScreenShotForSuccess(by.toString());
			Assert.isTrue(true, "Successfully Enter Text " + TextValue);
		} else {
			getScreenShotForFailed(by.toString());

			Assert.isTrue(false, "Unable to Enter Text " + TextValue);

		}
	}

	public void userShouldseeElement(By by) throws IOException {
		boolean res = false;
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			getScreenShotForSuccess(by.toString());

			Assert.isTrue(true, "User Should see " + by);
		} else {
			getScreenShotForFailed(by.toString());

			Assert.isTrue(false, "User Should not see " + by);

		}
	}

	public void switchToFrame(By by) throws IOException {
		boolean res = false;
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			driver.switchTo().frame(element);
			getScreenShotForSuccess(by.toString());
			Assert.isTrue(true, "User Should switch to Frame" + by);
		} else {
			getScreenShotForFailed(by.toString());

			Assert.isTrue(false, "User Should not switch to Frame" + by);

		}
	}

	public void dragAndDrop(By elemenet1, By element2) throws IOException {
		boolean res = false;
		Actions act = new Actions(driver);
		WebElement sourceElement = driver.findElement(elemenet1);
		WebElement TargaetElement = driver.findElement(element2);
		if (sourceElement.isDisplayed() || TargaetElement.isDisplayed()) {
			act.dragAndDrop(sourceElement, TargaetElement).build().perform();
			getScreenShotForSuccess(elemenet1.toString());

			Assert.isTrue(true, "User Should switch to Frame" + TargaetElement + " and " + sourceElement);
		} else {
			getScreenShotForFailed(elemenet1.toString());
			Assert.isTrue(true, "User Should switch to Frame" + TargaetElement + " and " + sourceElement);

		}
	}

	public String getText(By by) throws IOException {
		boolean res = false;
		String key = "";
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			key = element.getText();
			Assert.isTrue(true, "Element is  visible");
			getScreenShotForSuccess(by.toString());

		} else {
			getScreenShotForFailed(by.toString());
			Assert.isTrue(false, "Element is Not  visible");
		}
		return key;
	}

	public WebElement findElement(By by) throws IOException {
		boolean res = false;
		String key = "";
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			Assert.isTrue(true, "Element is  visible");
			getScreenShotForSuccess(by.toString());
		} else {
			getScreenShotForFailed(by.toString());
			Assert.isTrue(false, "Element is Not  visible");
		}
		return element;
	}

	public List<WebElement> findElements(By by) {
		boolean res = false;
		String key = "";
		List<WebElement> element = driver.findElements(by);
		return element;
	}

	public String getTitle() {
		boolean res = false;
		String title = driver.getTitle();
		return title;
	}

	public void selctByValueUsingText(By by, String ExpectedValue) {
		boolean res = false;
		WebElement element = driver.findElement(by);
		Select dropDown = new Select(element);
		dropDown.selectByVisibleText(ExpectedValue);
		String Actualvalue = dropDown.getFirstSelectedOption().getText();

//		if (ExpectedValue.equals(Actualvalue)) {
//			res = true;
//			org.testng.Assert.assertTrue(res, "User Expecting Selected value is : " + ExpectedValue);
//		} else {
//			org.testng.Assert.assertTrue(res, "User unable to select value : " + ExpectedValue);
//
//		}

	}

	public void selctByValueUsingValue(By by, String ExpectedValue) {
		boolean res = false;
		WebElement element = driver.findElement(by);
		Select dropDown = new Select(element);
		dropDown.selectByValue(ExpectedValue);
		String Actualvalue = dropDown.getFirstSelectedOption().getText();

		if (ExpectedValue.equals(Actualvalue)) {
			res = true;
			org.testng.Assert.assertTrue(res, "User Expecting Selected value is : " + ExpectedValue);
		} else {
			org.testng.Assert.assertTrue(res, "User unable to select value : " + ExpectedValue);

		}

	}

	public void selctByValueUsingIndex(By by, String ExpectedValue) {
		boolean res = false;
		WebElement element = driver.findElement(by);
		long value = Integer.parseInt(ExpectedValue);
		Select dropDown = new Select(element);
		dropDown.selectByIndex((int) value);
		String Actualvalue = dropDown.getFirstSelectedOption().getText();

//		if (ExpectedValue.equals(Actualvalue)) {
//			res = true;
//			org.testng.Assert.assertTrue(res, "User Expecting Selected value is : " + ExpectedValue);
//		} else {
//			org.testng.Assert.assertTrue(res, "User unable to select value : " + ExpectedValue);

		// }

	}

}
