/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

/**
 *
 * @author pas
 */
import java.io.FileOutputStream;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFCell;

public class CreateExcel 
{
    private String data,fileName;
    
    public CreateExcel(String data, String fileName)
    {
        this.data = data;
        this.fileName = fileName;
        
        createXL();
    }
    
    protected void createXL()
    {
        try {
            //String filename = "C:/NewExcelFile.xls" ;
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");  

            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("No.");
            rowhead.createCell(1).setCellValue("Name");
            rowhead.createCell(2).setCellValue("City");
            rowhead.createCell(3).setCellValue("University");

            HSSFRow row = sheet.createRow((short)1);
            row.createCell(0).setCellValue("1");
            row.createCell(1).setCellValue("Paschalis");
            row.createCell(2).setCellValue("Athens");
            row.createCell(3).setCellValue("University of Piraeus");

            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }
}
