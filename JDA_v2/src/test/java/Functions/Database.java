package Functions;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Database {
	
	public void dbConnection(String Query) throws SQLException, IOException
	{
		InputStream input = new FileInputStream("./Environment\\Environment.properties");
		Properties prop = new Properties();
		prop.load(input);
		String testDataFilePath = prop.getProperty("testDataFilePath")+"\\"+prop.getProperty("inputDatafromDBFileName");
		String connectionURL=prop.getProperty("ConnectionURL");
		String userName=prop.getProperty("DBUserName");
		String pass= prop.getProperty("DBPass");
		//String abc=prop.put(key, value)
        
		Connection con=DriverManager.getConnection(connectionURL,userName,pass);
		Statement stmt=con.createStatement();
		
		ResultSet resSet = stmt.executeQuery(Query);
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("InputDataFromDB");
		Row rowhead = sheet.createRow((short) 0);
		rowhead.createCell((short) 0).setCellValue("SGWS_ITEM_NUMBER");
		rowhead.createCell((short) 1).setCellValue("REPLACED_BY_ID");
		rowhead.createCell((short) 2).setCellValue("REPLACEMENT_ITEM_EFF_DT");
		rowhead.createCell((short) 3).setCellValue("ULTIMATE_PARENT");
		rowhead.createCell((short) 4).setCellValue("LEVEL_ID");
		rowhead.createCell((short) 5).setCellValue("RUN_DATE");
		rowhead.createCell((short) 6).setCellValue("ITEM");
		rowhead.createCell((short) 7).setCellValue("ALTITEM");
		rowhead.createCell((short) 8).setCellValue("LOC");
		rowhead.createCell((short) 9).setCellValue("EFF");
		rowhead.createCell((short) 10).setCellValue("DISC");
		rowhead.createCell((short) 11).setCellValue("U_UNDO");
		rowhead.createCell((short) 12).setCellValue("U_UPDATE_DT");
		rowhead.createCell((short) 13).setCellValue("U_CREATE_DT");
		
		int i=1;
		while (resSet.next())
		{		
			
			Row row = sheet.createRow((short) i++);
			row.createCell((short) 0).setCellValue(resSet.getString("SGWS_ITEM_NUMBER"));
			row.createCell((short) 1).setCellValue(resSet.getString("REPLACED_BY_ID"));
			row.createCell((short) 2).setCellValue(resSet.getString("REPLACEMENT_ITEM_EFF_DT"));
			row.createCell((short) 3).setCellValue(resSet.getString("ULTIMATE_PARENT"));
			row.createCell((short) 4).setCellValue(resSet.getString("LEVEL_ID"));
			row.createCell((short) 5).setCellValue(resSet.getString("RUN_DATE"));
			row.createCell((short) 6).setCellValue(resSet.getString("ITEM"));
			row.createCell((short) 7).setCellValue(resSet.getString("ALTITEM"));
			row.createCell((short) 8).setCellValue(resSet.getString("LOC"));
			row.createCell((short) 9).setCellValue(resSet.getString("EFF"));
			row.createCell((short) 10).setCellValue(resSet.getString("DISC"));
			row.createCell((short) 11).setCellValue(resSet.getString("U_UNDO"));
			row.createCell((short) 12).setCellValue(resSet.getString("U_UPDATE_DT"));
			row.createCell((short) 13).setCellValue(resSet.getString("U_CREATE_DT"));
			
			}
		
		


		FileOutputStream fileOut = new FileOutputStream(testDataFilePath);
		workbook.write(fileOut);
		workbook.close();
		fileOut.close();
		con.close();
		
		
	}
}


