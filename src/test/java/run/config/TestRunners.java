package run.config;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import Utills.ExcelUtils;

public class TestRunners {

	static String KeywordDrivenFileName = System.getProperty("user.dir") + "/KeyWordDrivernFile/KewordDrivenFile.xlsx";
	static String TestModuleHome = "Home";
	static ExcelUtils excelUtils = new ExcelUtils();

	public static void main(String[] args) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		// TODO Auto-generated method stub
		int TestModuleHomeRowcount = excelUtils.getRowCount(KeywordDrivenFileName, TestModuleHome);

		for (int i = 1; i < TestModuleHomeRowcount; i++) {

			String ModuleFlag = excelUtils.getStringCellData(KeywordDrivenFileName, TestModuleHome, i, 4);

			if (ModuleFlag.equals("Yes")) {

				String ModuleCellName = excelUtils.getStringCellData(KeywordDrivenFileName, TestModuleHome, i, 3);

				// checking module is available
				String ModuleSheetValue = excelUtils.getSheetName(KeywordDrivenFileName, ModuleCellName);

				int TestCasesRowsCount = excelUtils.getRowCount(KeywordDrivenFileName, ModuleSheetValue);

				for (int j = 1; j < TestCasesRowsCount; j++) {

					String TestCaseFlag = excelUtils.getStringCellData(KeywordDrivenFileName, ModuleSheetValue, j, 2);

					if (TestCaseFlag.equals("Yes")) {

						String testcaseName = excelUtils.getStringCellData(KeywordDrivenFileName, ModuleSheetValue, j,
								1);
						String testClassName = excelUtils.getStringCellData(KeywordDrivenFileName, TestModuleHome, i,
								2);
					
						executeTest(testcaseName, testcaseName);

					}
				}

			} else {

				excelUtils.setCellData(KeywordDrivenFileName, TestModuleHome, i, 4, "No Flag");
				excelUtils.setRedCellData(KeywordDrivenFileName, TestModuleHome, i, 4);
			}

		}

	}

	public static void executeTest( Object className, String methode) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		className.getClass().getMethod(methode).invoke(className);

	}

}
