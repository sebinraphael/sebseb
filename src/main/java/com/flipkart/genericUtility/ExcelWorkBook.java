package com.flipkart.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author daniel
 *
 */
public class ExcelWorkBook {
	private Workbook book;
	private FileInputStream fis;
	private FileOutputStream fio;

	/**
	 * This method is used to fetch test data from excel file
	 * 
	 * @author daniel
	 * @param path
	 * @param sheetName
	 * @param row
	 * @param cell
	 * @return
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */

	public String fetchTestData(String path, String sheetName, int row, int cell) throws EncryptedDocumentException, IOException {


		fis = new FileInputStream(path);
		book = WorkbookFactory.create(fis);
		DataFormatter df = new DataFormatter();
		String tesData = df.formatCellValue(book.getSheet(sheetName).getRow(row).getCell(cell));
		return tesData;
	}

	/**
	 * This method is for fetching data from Excel using validation
	 * 
	 * @param path
	 * @param sheetName1
	 * @param rowforCellCount
	 * @param expectedTestCase
	 * @param expectedkey
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */

	public String fetchValidTestData(String path, String sheetName1, int rowforCellCount, String expectedTestCase,
			String expectedkey)  {
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet1 = book.getSheet(sheetName1);
		int lastRowNumber = sheet1.getLastRowNum();
		short lastcellNumber = sheet1.getRow(rowforCellCount).getLastCellNum();
		String expectedTestCase1 = expectedTestCase;
		String expectedkey1 = expectedkey;
		String value = "";
		for (int i = 0; i < lastRowNumber; i++) {
			String actualTestCase = sheet1.getRow(i).getCell(0).getStringCellValue();
			if (actualTestCase.equals(expectedTestCase1)) {
				for (int j = 1; j < lastcellNumber - 1; j++) {
					String actualName = sheet1.getRow(i).getCell(j).getStringCellValue();
					if (actualName.equals(expectedkey1)) {
						value = sheet1.getRow(i + 1).getCell(j).getStringCellValue();
						break;
					}
				}

				break;
			}
		}
		return value;
	}


	/**
	 * This method is used to insert data to excel
	 * @param rowNumber
	 * @param cellNumber
	 * @param path
	 * @param sheetName1
	 * @param message
	 */
	public void insertDataToExcel(int rowNumber, int cellNumber,String path, String sheetName1,String message)  {

		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = book.getSheet(sheetName1);
		sheet.getRow(rowNumber).getCell(cellNumber).setCellValue(message);

		try {
			fio = new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book.write(fio);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to close the excel
	 * @throws IOException
	 */
	public void closeExcel() 
	{
		try {
			book.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * This method is used to write data to multiple column
	 * @param path
	 * @param sheetName
	 * @param NameRow1
	 * @param NameRow2
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeInMultipleColumn( String path,String sheetName, List<WebElement> NameRow1, List<WebElement> NameRow2 ) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(path);
		book = WorkbookFactory.create(fis);
		int size1 = NameRow1.size();
		Sheet sh = book.getSheet(sheetName);
		for (int i=1; i<size1;i++)
		{
			Row row = sh.createRow(i);
			for (int j=0; j<2;j++)
			{
				if(j==0)
				{
					Cell cell = row.createCell(0);
					cell.setCellValue(NameRow1.get(i).getText());
				}
				else if(j==1)
				{
					Cell cell = row.createCell(1);
					cell.setCellValue(NameRow2.get(i).getText());
				}
			}
		}

		FileOutputStream fio= new FileOutputStream("./src/test/resources/TestData.xlsx");
		book.write(fio);
	}
}
