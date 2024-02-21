package run.config;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry;
import org.testng.annotations.Test;

import Pages.DownloadModule;
import Tests.DemosTest;
import Tests.DownloadModuleTest;
import Utills.BasePage;
import Utills.ExcelUtils;

public class TestRunner extends BasePage {

	static String KeywordDrivenFileName = System.getProperty("user.dir") + "/KeyWordDrivernFile/KewordDrivenFile.xlsx";
	static String TestModuleHome = "Home";
	static ExcelUtils excelUtils = new ExcelUtils();
	static DemosTest demosTest = new DemosTest();
	static DownloadModuleTest downloadModuleTest = new DownloadModuleTest();

	public static void main(String[] args) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		startReport();
		try {
			int TestModuleHomeRowcount = excelUtils.getRowCount(KeywordDrivenFileName, TestModuleHome);

			for (int i = 1; i < TestModuleHomeRowcount; i++) {

				String ModuleFlag = excelUtils.getStringCellData(KeywordDrivenFileName, TestModuleHome, i, 4);

				if (ModuleFlag.equals("Yes")) {

					String ModuleCellName = excelUtils.getStringCellData(KeywordDrivenFileName, TestModuleHome, i, 3);

					// checking module is available
					String ModuleSheetValue = excelUtils.getSheetName(KeywordDrivenFileName, ModuleCellName);

					switch (ModuleSheetValue) {
					case "Demos":
						demosTest.executeTestMethod(demosTest, ModuleSheetValue);
						break;
					case "Download":
						downloadModuleTest.executeDownloadModuleTestMethod(downloadModuleTest, ModuleSheetValue);
						break;
					default:
						break;

					}

				} else {

					excelUtils.setCellData(KeywordDrivenFileName, TestModuleHome, i, 5, "No Flag");
					excelUtils.setRedCellData(KeywordDrivenFileName, TestModuleHome, i, 5);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		closeReport();
	}

}
