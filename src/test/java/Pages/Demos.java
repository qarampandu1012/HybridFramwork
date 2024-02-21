package Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Utills.AppUtills;
import dev.failsafe.internal.util.Assert;

public class Demos extends AppUtills {

	private static final By Dropable = By.xpath("//a[text()='Droppable']");
	private static final By DragElement = By.xpath("//div[@id='draggable']");
	private static final By DropableElement = By.xpath("//div[@id='droppable']");
	private static final By Frame = By.xpath("//iframe[@class='demo-frame']");
	private static final By Demos = By.xpath("//a[text()='Demos']");
	private static final By DatePickerCalenderTextBox = By.xpath("//input[@id='datepicker']");
	private static final By ActualYearText = By.xpath("//span[@class='ui-datepicker-year']");
	private static final By NextButton = By.xpath("//span[text()='Next']");
	private static final By ActualMonthText = By.xpath("//span[@class='ui-datepicker-month']");
	private static final By Table = By.xpath("//table[@class='ui-datepicker-calendar']");

	public boolean validatePageTitle() throws IOException {

		boolean res = false;
		String expectedvalue = "jquery";
		String ActualValue = getTitle();

		if (ActualValue.toLowerCase().contains(expectedvalue.toLowerCase())) {
			res = true;
			getScreenShotForSuccess("validatePageTitle");

			Assert.isTrue(res, "Success fully validated Page Title" + ActualValue);
		} else {
			res = false;
			getScreenShotForFailed("validatePageTitle");
			Assert.isTrue(res, "Success fully validated Page Title" + ActualValue);

		}

		return res;
	}

	public boolean validateDragAnDropFunctionality() throws IOException {
		boolean res;
		try {
			clickElement(Demos);
			clickElement(Dropable);
			switchToFrame(Frame);
			dragAndDrop(DragElement, DropableElement);
			res = true;
		} catch (Exception e) {
			res = false;
		}

		return res;
	}

	public boolean validateCalenderFunctinality() throws InterruptedException, IOException {
		boolean res = false;
		try {

			// Steps1
			String expectedDate = "April/28/2025";
			String[] temp = expectedDate.split("/");
			String ExpectedMonth = temp[0];
			String ExpectedDay = temp[1];
			String ExpectedYear = temp[2];
			clickElement(Demos);
			Thread.sleep(2000);
			clickElement(DatePickerCalenderTextBox);
			Thread.sleep(2000);
			switchToFrame(Frame);
			System.out.println("Successfully clicked on Date Text Box");

			// Selecting Year

			String ActualYear = getText(ActualYearText);

			Thread.sleep(2000);

			while (!ActualYear.equals(ExpectedYear)) {

				clickElement(NextButton);
				ActualYear = getText(ActualYearText);
			}
			System.out.println("Successfully Selected Year");

			// Selecting Month

			String ActualMonth = getText(ActualMonthText);
			while (!ActualMonth.equals(ExpectedMonth)) {
				clickElement(NextButton);
				ActualMonth = getText(ActualMonthText);
			}

			System.out.println("Successfully Selected Year");

			WebElement table = findElement(Table);

			List<WebElement> rows, col;

			rows = findElements(By.tagName("tr"));

			for (int i = 0; i < rows.size(); i++) {

				col = rows.get(i).findElements(By.xpath("td"));

				for (int j = 0; j < col.size(); j++) {
					String key = col.get(j).getText();

					if (key.equals(ExpectedDay)) {
						col.get(j).click();
						res = true;
						break;
					}
				}

			}

			org.testng.Assert.assertTrue(res);
			System.out.println("Successfully Selected Date");

		} catch (Exception e) {
			org.testng.Assert.assertTrue(false);
			getScreenShotForFailed("Calender validation");
		}

		return res;
	}
}
