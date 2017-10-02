
package sdnrestconfcommunicator;

/**
 *
 * @author pas
 */
import java.io.FileOutputStream;
import java.util.ArrayList;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class CreateExcel 
{
    final private String fileName;
    final private NetworkElements net;
    
    public CreateExcel(String fileName,NetworkElements net)
    {
        this.fileName = fileName;
        this.net = net;
        createXL();
    }
    
    protected void createXL()
    {
        try 
        {
            String[] nodes = net.getTopoNodes();
            String[] links;
            
            ArrayList<String> linksList = new ArrayList<>();
            
            ///------------------------HEAD(1rst row)--------------------------
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");
            
            HSSFCellStyle boldStyle = workbook.createCellStyle();
            HSSFFont mFont=workbook.createFont();
            mFont.setBold(true);
            boldStyle.setFont(mFont);


            HSSFRow rowhead = sheet.createRow((short)0);
            //rowhead.createCell(0).setCellValue("Nodes");
            Cell cell = rowhead.createCell(0);
            cell.setCellValue("Nodes");
            cell.setCellStyle(boldStyle);
            
            //--------------------Body---------------------------
            HSSFRow row;
            for (int i = 0; i<nodes.length; i++)
            {
                row = sheet.createRow((short)i+1);
                row.createCell(0).setCellValue(nodes[i]);
                
                if(!nodes[i].contains("host"))
                {
                    links = net.getNodeConnectors(nodes[i]);
                    for (int j = 0; j < links.length; j++)
                    {
                       linksList.add(links[j]); 
                    }
                }
            }
            
            row = sheet.createRow((short)sheet.getLastRowNum()+2);
            //row.createCell(0).setCellValue("Node-Connectors/Links");
            cell = row.createCell(0);
            cell.setCellValue("Node-Connectors/Links");
            cell.setCellStyle(boldStyle);
            
            for(int i = 0; i<linksList.size(); i++)
            {
                row = sheet.createRow((short)sheet.getLastRowNum()+1);
                row.createCell(0).setCellValue(linksList.get(i));
            }
            //System.out.print(sheet.getLastRowNum());
            
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);

            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }
}
