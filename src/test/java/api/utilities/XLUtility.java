package api.utilities;
//File -> workbook -> worksheet -> row -> cell

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public XSSFRow rows;
	public XSSFCell cells;
	public XSSFCellStyle style;
	public String path;

	public XLUtility(String path) {
		this.path = path;

	}

	public int getCountRows(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);

		int count_rows = sheet.getLastRowNum();
		wb.close();
		fi.close();
		return count_rows;
	}

	public int getCountCols(String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);

		int count_cols = sheet.getRow(0).getLastCellNum();
		wb.close();
		fi.close();
		return count_cols;
	}

	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);

		rows = sheet.getRow(rownum);
		cells = rows.getCell(colnum);
		String data;

		DataFormatter dataFormater = new DataFormatter();
		try {
			data = dataFormater.formatCellValue(cells);

		} catch (Exception e) {

			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}

	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {

		File exFile = new File(path);

		if (!exFile.exists()) {
			wb = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			wb.write(fo);
		}

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		if (wb.getSheetIndex(sheetName) == -1) {
			sheet = wb.createSheet(sheetName);
			rows = sheet.getRow(rownum);
			cells = rows.getCell(colnum);
			cells.setCellValue(data);
			fo = new FileOutputStream(path);
			wb.write(fo);
			wb.close();
			fi.close();
			fo.close();
		}

	}

	public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);

		rows = sheet.getRow(rownum);
		cells = rows.getCell(colnum);

		style = wb.createCellStyle();

		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cells.setCellStyle(style);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}

	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);

		rows = sheet.getRow(rownum);
		cells = rows.getCell(colnum);

		style = wb.createCellStyle();

		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cells.setCellStyle(style);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}
}
