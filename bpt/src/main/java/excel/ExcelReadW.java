package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelReadW {
	
	public String[][] getExcelData(String excellocation , String sheetName){
		try{
			String dataSets[][] = null;
			FileInputStream file = new FileInputStream(new File(excellocation));
			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int totalRow = sheet.getLastRowNum();			
			int totalColumn = sheet.getRow(0).getLastCellNum();
			dataSets = new String[totalRow][totalColumn];
			
			Iterator<Row> rowIterator = sheet.iterator();
			int i=0;
			
			while(rowIterator.hasNext() ){

				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int j=0;
				
				while(cellIterator.hasNext()){
					
					Cell cell= cellIterator.next();
					if(cell.getStringCellValue().contains("username") ){
						break;
					}
					switch(cell.getCellType()){
					case Cell.CELL_TYPE_NUMERIC:
						dataSets[i-1][j++] = cell.getStringCellValue();
						System.out.println(cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						dataSets[i-1][j++] = cell.getStringCellValue();
						System.out.println(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						dataSets[i-1][j++] = cell.getStringCellValue();
						System.out.println(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						dataSets[i-1][j++] = cell.getStringCellValue();
						System.out.println(cell.getStringCellValue());
						break;
					}
					
				}
				
				
				i++;
			}
			file.close();
			return dataSets;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) throws IOException {
		String excellocation = System.getProperty("user.dir")+"\\excel\\login.xlsx";
		String sheetName = "Credentials";
		ExcelReadW excel = new ExcelReadW();
		String[][] data = excel.getExcelData(excellocation, sheetName);
//		updateResult(excellocation,sheetName,"logintestandLogout","PASS");
	}
	
	public static void updateResult(String excellocation, String sheetName,  String testCaseName,String testStatus) throws IOException {

		try {
			FileInputStream file = new FileInputStream(new File(excellocation));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			// count number of active tows
			int totalRow = sheet.getLastRowNum() + 1;
			// count number of active columns in row
			for (int i = 1; i < totalRow; i++) {
				XSSFRow r = sheet.getRow(i);
				String ce = r.getCell(1).getStringCellValue();

					r.createCell(2).setCellValue(testStatus);
					file.close();
					System.out.println("resule updated");
					FileOutputStream outFile = new FileOutputStream(new File(excellocation));
					workbook.write(outFile);
					outFile.close();
					break;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
