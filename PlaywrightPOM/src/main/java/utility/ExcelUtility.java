package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	//this map stores the data which is to be stored in excel
	static Map<String, Object[]> data = new TreeMap<String, Object[]>();

	public static void Write(String sheet_Name,String path,Map<String, Object[]> data) throws IOException 
	{
			wb = new XSSFWorkbook();			
			//Create a blank sheet
			XSSFSheet sheet = wb.createSheet(sheet_Name);

			//Iterate over data and write to sheet
			Set<String> keyset = data.keySet();
			int rownum = 0;
			for (String key : keyset)
			{
				row = sheet.createRow(rownum++);
				Object [] objArr = data.get(key);
				int cellnum = 0;
				for (Object obj : objArr)
				{
					cell = row.createCell(cellnum++);
					if(obj instanceof String)
						cell.setCellValue((String)obj);
					else if(obj instanceof Integer)
						cell.setCellValue((Integer)obj);
				}
			}
			
				//Write the workbook in file system
				fos = new FileOutputStream(new File(path));
				wb.write(fos);
				fos.close();
				System.out.println("******File Write Successfull.******");
	}
	public static void Update(String path,int ID,String KEY,String VALUE) {
		
		try{
			fis = new FileInputStream(new File(path));

			//Create Workbook instance holding reference to .xlsx file
			wb = new XSSFWorkbook(fis);

			//Get first/desired sheet from the workbook
			sheet = wb.getSheetAt(wb.getActiveSheetIndex());

			ExcelUtility Up = new ExcelUtility(ID,KEY,VALUE);
			//Get the count in sheet
			int rowCount = sheet.getLastRowNum()+1;
			row = sheet.createRow(rowCount);
			System.out.println();
			Cell c1 = row.createCell(0);
			c1.setCellValue(Up.getId());
			Cell c2 = row.createCell(1);
			c2.setCellValue(Up.getFirstName());
			Cell c3 = row.createCell(2);
			c3.setCellValue(Up.getLastName());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		try
		{
			//Write the workbook in file system
			fos = new FileOutputStream(new 
					File(path));
			wb.write(fos);
			fos.close();
			System.out.println("******File Update Successfull******");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private int id;
	private String col1;
	private String col2;

	public ExcelUtility(){}

	public ExcelUtility(int id, String first, String second) {
		super();
		this.id = id;
		this.col1 = first;
		this.col2 = second;
	}

	public String getFirstName() {
		return col1;
	}
	public void setFirstName(String first) {
		this.col1 = first;
	}
	public String getLastName() {
		return col2;
	}
	public void setLastName(String second) {
		this.col2 = second;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static String getData(String xlfile,String xlsheet, int rowNum,int colnum) throws IOException {
		
		
		fis = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(xlsheet);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colnum);
		
		String data;
			try
			{
				DataFormatter formatter = new DataFormatter();
				String cellData = formatter.formatCellValue(cell);
				wb.close();
				return cellData;
			}
			catch(Exception e)
			{
				data="";
			}
		fis.close();
		return data;
	}
}