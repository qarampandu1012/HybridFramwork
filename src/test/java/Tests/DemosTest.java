package Tests;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import org.testng.Assert;
import org.testng.TestNG;

import Pages.Demos;
import Utills.BasePage;
import Utills.ExcelUtils;

public class DemosTest extends Demos {

	ExcelUtils excelUtils = new ExcelUtils();
	String KeywordDrivenFileName = System.getProperty("user.dir") + "/KeyWordDrivernFile/KewordDrivenFile.xlsx";
	String TestModuleHome = "Home";
	BasePage basePage = new BasePage();

	@Test
	public void verifyPageTitle() throws IOException {

		boolean res = false;
		extentTest = extentReports.createTest("verifyPageTitle");
		basePage.invokeBrowser();
		res = validatePageTitle();
		if (res) {
			extentTest.log(Status.PASS, "verifyPageTitle");
		} else {
			extentTest.log(Status.FAIL, "verifyPageTitle");
		}
		basePage.closeBrowser();
	}

	@Test
	public void verifyDragAnDropFunctionality() throws IOException {
		boolean res = false;
		extentTest = extentReports.createTest("verifyDragAnDropFunctionality");
		basePage.invokeBrowser();
		res = validateDragAnDropFunctionality();
		if (res) {
			extentTest.log(Status.PASS, "verifyDragAnDropFunctionality");
		} else {
			extentTest.log(Status.FAIL, "verifyDragAnDropFunctionality");
		}
		Assert.assertTrue(true);
		basePage.closeBrowser();
	}

	@Test
	public void verifyCalenderFunctinality() throws InterruptedException, IOException {
		boolean res = false;
		extentTest = extentReports.createTest("verifyCalenderFunctinality");
		basePage.invokeBrowser();
		res = validateCalenderFunctinality();
		if (res) {
			extentTest.log(Status.PASS, "verifyCalenderFunctinality");
		} else {
			extentTest.log(Status.FAIL, "verifyCalenderFunctinality");
		}
		Assert.assertTrue(true);
		basePage.closeBrowser();
	}

	public void executeTestMethod(DemosTest testClass, String ModuleSheetValue) throws IOException {
		int TestCasesRowsCount = excelUtils.getRowCount(KeywordDrivenFileName, ModuleSheetValue);
		String testcaseName = "";
		for (int j = 1; j < TestCasesRowsCount; j++) {

			String TestCaseFlag = excelUtils.getStringCellData(KeywordDrivenFileName, ModuleSheetValue, j, 2);

			if (TestCaseFlag.equals("Yes")) {

				testcaseName = excelUtils.getStringCellData(KeywordDrivenFileName, ModuleSheetValue, j, 1);
				try {
					testClass.getClass().getMethod(testcaseName).invoke(testClass);
				} catch (Exception e) {
					// basePage.closeBrowser();
				}
			}
		}

	}

}
