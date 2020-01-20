package Functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelFile {
	
	
	public String readExcel(String filePath, String fileName, String sheetName, int rownum, int columnnum) throws IOException {
		
		int rownm = rownum;
		int colnm = columnnum;
		
		File file = new File(filePath+"\\"+fileName);
		FileInputStream fis = new FileInputStream(file);
		Workbook wbk = null;
		
		DataFormatter formatter = new DataFormatter();
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")) {
			wbk = new XSSFWorkbook(fis);
		}
		else if(fileExtensionName.equals(".xls")){
			wbk = new HSSFWorkbook(fis);
		}
		else {
			System.out.println("Not a valid File Type. Please use file with extenion .xls or .xlsx");
		}
		Sheet sheet =wbk.getSheet(sheetName);
		Row row = sheet.getRow(rownm);
		Cell cell=row.getCell(colnm);
		String cellValue = formatter.formatCellValue(cell);
		return cellValue;
	}
	
	
	public int getTotalRowColumn(String filePath, String fileName, String sheetName) throws IOException {
		File file = new File(filePath+"\\"+fileName);
		FileInputStream fis = new FileInputStream(file);
		Workbook wbk = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		int rowMax = 0;
		int colMax = 0;
		int[] rowCol= {0,0};
		if(fileExtensionName.equals(".xlsx")) {
			wbk = new XSSFWorkbook(fis);
		}
		else if(fileExtensionName.equals(".xls")){
			wbk = new HSSFWorkbook(fis);
		}
		else {
			System.out.println("Not a valid File Type. Please use file with extenion .xls or .xlsx");
		}
		Sheet sheet = wbk.getSheet(sheetName);
		rowMax = sheet.getLastRowNum()-sheet.getFirstRowNum();
		for (int i = 0 ;i<rowMax;i++) {
			Row row = sheet.getRow(i);
			colMax = row.getLastCellNum()-row.getFirstCellNum();
		}
		rowCol[0] = rowMax;
		rowCol[1] = colMax;
		return rowMax;
	}
}
