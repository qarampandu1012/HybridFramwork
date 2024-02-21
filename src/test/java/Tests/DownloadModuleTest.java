package Tests;

import java.io.IOException;

import org.testng.annotations.Test;

import Pages.DownloadModule;
import Utills.BasePage;
import Utills.ExcelUtils;

public class DownloadModuleTest extends DownloadModule {
	
	ExcelUtils excelUtils = new ExcelUtils();
	String KeywordDrivenFileName = System.getProperty("user.dir") + "/KeyWordDrivernFile/KewordDrivenFile.xlsx";
	String TestModuleHome = "Home";
	BasePage basePage = new BasePage();

	@Test 

	public void verifyDownloadPage() throws IOException {
		validateDownloadPage();
	}

	@Test 
	public void verifyAllJqueryUiDownloadPage() throws IOException {
		validateAllJqueryUiDownloadPage();
	}
	

	public void executeDownloadModuleTestMethod(DownloadModuleTest testClass, String ModuleSheetValue) throws IOException {
		int TestCasesRowsCount = excelUtils.getRowCount(KeywordDrivenFileName, ModuleSheetValue);
		String testcaseName = "";
		for (int j = 1; j < TestCasesRowsCount; j++) {

			String TestCaseFlag = excelUtils.getStringCellData(KeywordDrivenFileName, ModuleSheetValue, j, 2);

			if (TestCaseFlag.equals("Yes")) {

				testcaseName = excelUtils.getStringCellData(KeywordDrivenFileName, ModuleSheetValue, j, 1);
				try {
					testClass.getClass().getMethod(testcaseName).invoke(testClass);
				} catch (Exception e) {
					//basePage.closeBrowser();
				}
			}
		}
		
	}
}
