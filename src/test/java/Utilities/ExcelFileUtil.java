package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {

	Workbook wb;
	//constructor for reading excel path
	public ExcelFileUtil(String ExcelPath) throws Throwable
	{
	
		FileInputStream fi = new FileInputStream(ExcelPath);
		
		wb = WorkbookFactory.create(fi);
		
	}
	
	public int rowCount(String SheetName) 
	{
		
		return wb.getSheet(SheetName).getLastRowNum();
	}
	
	public String getCellData(String SheetName,int row , int column)
	{
		String data = "";
		if(wb.getSheet(SheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC) {
			int celldata = (int)wb.getSheet(SheetName).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(celldata);
			
			
		}else {
			data = wb.getSheet(SheetName).getRow(row).getCell(column).getStringCellValue();		
			}
	
	return data;
	}
//methor for set cell data
	public void setCelldata(String sheetName, int row, int column, String status,String WriteExcel) throws IOException 
	{
	//get sheet from wb
	Sheet ws = wb.getSheet(sheetName);
	
	Row rowNum = ws.getRow(row);
	
	Cell cell = rowNum.createCell(column);
	
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("Pass")) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		//colour
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		style.setFont(font);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		rowNum.getCell(column).setCellStyle(style);
		
		
		
	}else if(status.equalsIgnoreCase("Blocked"))
	{
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		//colour
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		style.setFont(font);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		rowNum.getCell(column).setCellStyle(style);
	}
	
	FileOutputStream fo = new FileOutputStream(WriteExcel);
	wb.write(fo);
	}

	public static void main(String[] args) throws Throwable {
		ExcelFileUtil xl = new ExcelFileUtil("D:/book1.xlsx");
		
		
		
	}
	
	
	

}
