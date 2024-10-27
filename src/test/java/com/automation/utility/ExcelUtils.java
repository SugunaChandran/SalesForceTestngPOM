package com.automation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.log4testng.Logger;


public class ExcelUtils {
	static Logger mylog=Logger.getLogger(ExcelUtils.class);

	public static String readDataFromXL(String filePath,String sheetname,int rowIndex,int cellIndex)throws IOException{
		mylog.info("started to read single data from"+filePath);
		FileInputStream file=new FileInputStream(new File(filePath));
		Workbook  workbook=new XSSFWorkbook(file);
		Sheet sheet=workbook.getSheet(sheetname);		
		return sheet.getRow(rowIndex).getCell(cellIndex).toString();
	}



	public static List<List<String>> readAllDataFromXL(String filePath, String sheetName) {
		List<List<String>> allData = new ArrayList<>();

		try (FileInputStream file = new FileInputStream(new File(filePath))) {
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheet(sheetName);


			for (Row row : sheet) {
				List<String> rowDataList = new ArrayList<>();

				for (Cell cell : row) {
					rowDataList.add(cell.toString());
				}
				allData.add(rowDataList);
			}

			workbook.close();  
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allData;
	}


	public static String[][] readAllDataFromXLFromXLToArray(String filePath,String sheetName){
		String[][] data=null;		
		Workbook workbook=null;
		FileInputStream file=null;
		try {
			file=new FileInputStream(new File(filePath));
			workbook=WorkbookFactory.create(file);
			Sheet sheet=workbook.getSheet(sheetName);
			int rows=sheet.getPhysicalNumberOfRows();
			int cols=sheet.getRow(0).getLastCellNum();
			data=new String[rows][cols];
			for(int i=0;i<rows;i++) {
				Row row=sheet.getRow(i);
				for(int j=0;j<cols;j++) {
					Cell cell=row.getCell(j);
					data[i][j]=cell.toString();
				}

			}
		}
		catch(Exception e) {
			System.out.println("Exception while reading data");
			e.printStackTrace();
		}
		finally {try {
			workbook.close();
			file.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		}
		return data;
	}


}








