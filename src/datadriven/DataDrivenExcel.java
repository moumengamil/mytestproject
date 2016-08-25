package datadriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenExcel {
	
	
	public  Object[][]ReadData(String sheetname,String filepath) throws IOException 
	{
		
	File Excel=new File(filepath);
	
	  FileInputStream fs=new FileInputStream(Excel);
	
	XSSFWorkbook wb=new XSSFWorkbook(fs);
	XSSFSheet sheet=wb.getSheet(sheetname);
	
   
	int rownum=sheet.getLastRowNum()+1;
	int colnum=sheet.getRow(0).getLastCellNum();
	
	String [][] data=new String[rownum][colnum];
	 
	for(int i=0;i<rownum;i++)
	    {
		XSSFRow rw=sheet.getRow(i);
		for(int j=0;j<colnum;j++)
		{
		XSSFCell cell=rw.getCell(j);
		String value=celltostring(cell);
		data[i][j]=value;
		
			
		}
		
	}
	return data;
	
	
	
	
	
}



private static String celltostring(XSSFCell cell) {
	int type;
	Object result;
	type=cell.getCellType();
	
	switch (type){
	
	case 0:
		result=cell.getNumericCellValue();
		break;
		
	case 1:
		result=cell.getStringCellValue();
		break;
		
	default :
		throw new RuntimeException("EXCEPTION");
		
	}
		
		return result.toString();
		
		
		
    }

	
	
}

	
	
	
	
	
	 