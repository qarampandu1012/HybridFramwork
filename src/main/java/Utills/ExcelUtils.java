package Utills;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static FileInputStream fi;
	public static FileOutputStream fo;

	public static Workbook wb;
	public static Sheet sheet;
	public static Row row;
	public static Cell cell;
	public static CellStyle cellStyle;

	/// className objectname= new Classname();
	public static int getRowCount(String FileName, String sheet_Name) throws IOException {

		fi = new FileInputStream(FileName);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheet_Name);
		int rowcount = sheet.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}

	public static int getCellCount(String FileName, String sheet_Name, int rowNumber) throws IOException {

		fi = new FileInputStream(FileName);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheet_Name);
		sheet.getLastRowNum();
		row = sheet.getRow(rowNumber);
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
	}

	public static String getStringCellData(String FileName, String sheet_Name, int rowNumber, int cellNumber)
			throws IOException {

		fi = new FileInputStream(FileName);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheet_Name);
		sheet.getLastRowNum();
		row = sheet.getRow(rowNumber);
		cell = row.getCell(cellNumber);

		String Data = "";

		try {
			Data = cell.getStringCellValue();
		} catch (Exception e) {
			Data = "No Data";
		}

		wb.close();
		fi.close();
		return Data;
	}

	public static String getNumaricCellData(String FileName, String sheet_Name, int rowNumber, int cellNumber)
			throws IOException {

		fi = new FileInputStream(FileName);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheet_Name);
		sheet.getLastRowNum();
		row = sheet.getRow(rowNumber);
		cell = row.getCell(cellNumber);

		double Data = 0.0;
		String res = "";
		try {
			Data = cell.getNumericCellValue();
			res = String.valueOf(Data);
		} catch (Exception e) {
			res = "No Numaric cell data";
		}

		wb.close();
		fi.close();
		return res;
	}

	public static String getBooleancellData(String FileName, String sheet_Name, int rowNumber, int cellNumber)
			throws IOException {

		fi = new FileInputStream(FileName);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheet_Name);
		sheet.getLastRowNum();
		row = sheet.getRow(rowNumber);
		cell = row.getCell(cellNumber);

		boolean Data = false;
		String res = "";
		try {
			Data = cell.getBooleanCellValue();
			res = String.valueOf(Data);
		} catch (Exception e) {
			res = "No Boolean cell data";
		}

		wb.close();
		fi.close();
		return res;
	}

	public void setCellData(String ExcelFilePath, String testData, int rowNumber, int cellNumber, String Result)
			throws IOException {
		fi = new FileInputStream(ExcelFilePath);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(testData);
		row = sheet.getRow(rowNumber);
		cell = row.createCell(cellNumber);
		cell.setCellValue(Result);
		fo = new FileOutputStream(ExcelFilePath);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}

	public static void setGreeCellData(String FileName, String sheet_Name, int rowNumber, int cellNumber)
			throws IOException {

		fi = new FileInputStream(FileName);
		fo = new FileOutputStream(FileName);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheet_Name);
		sheet.getLastRowNum();
		row = sheet.getRow(rowNumber);
		cell = row.getCell(cellNumber);
		cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(cellStyle);
		wb.write(fo);
		fo.close();
		wb.close();
		fi.close();
	}

	public static void setRedCellData(String FileName, String sheet_Name, int rowNumber, int cellNumber)
			throws IOException {

		fi=new FileInputStream(FileName);
		wb= new XSSFWorkbook(fi);
		sheet=wb.getSheet(sheet_Name);
		row=sheet.getRow(rowNumber);		
		cell=row.getCell(cellNumber);
		cellStyle=wb.createCellStyle();
		cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(cellStyle);
		fo=new FileOutputStream(FileName);
		wb.write(fo);
		fo.close();
		wb.close();
		fi.close();
	
	}

	public static void setYelloeCellData(String FileName, String sheet_Name, int rowNumber, int cellNumber)
			throws IOException {

		fi=new FileInputStream(FileName);
		wb= new XSSFWorkbook(fi);
		sheet=wb.getSheet(sheet_Name);
		row=sheet.getRow(rowNumber);		
		cell=row.getCell(cellNumber);
		cellStyle=wb.createCellStyle();
		cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(cellStyle);
		fo=new FileOutputStream(FileName);
		wb.write(fo);
		fo.close();
		wb.close();
		fi.close();
	}

	public String getSheetName(String FileName, String sheet_Name) throws IOException {
		fi = new FileInputStream(FileName);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheet_Name);
		String sheetName = sheet.toString();
		wb.close();
		fi.close();
		return sheet_Name;
	}

}
