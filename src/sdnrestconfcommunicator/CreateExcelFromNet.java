
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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;

public class CreateExcelFromNet 
{
    final private String fileName;
    final private NetworkData net;
    final private static int MAX_NUMBER_OF_COLUMNS = 10;//used for autosize columns
    
    public CreateExcelFromNet(String fileName,NetworkData net)
    {
        this.fileName = fileName;
        this.net = net;
        createXL();
    }
    
    
    
    private void createXL()
    {
        try 
        {
            String[] nodes = net.getNodesNoHosts();
            String[] hosts = net.getHosts();

            //node connector values(id,interface speed,uptime,mac,interface name,configuration,
            //transmitted,received,util Tx, util Rx)
            ArrayList<String> nodeConnectorsList = new ArrayList<>();
            ArrayList<String[]> nodeConValuesList = new ArrayList<>();
            
            //Create Workbook and Sheet
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");
            
            //Create Bold style
            HSSFCellStyle boldStyle = workbook.createCellStyle();
            HSSFFont mFont=workbook.createFont();
            mFont.setBold(true);
            boldStyle.setFont(mFont);

             ///------------------------HEAD(1rst row)--------------------------
            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Nodes");
            rowhead.createCell(1).setCellValue("Hardware");
            rowhead.createCell(2).setCellValue("Software Version");
            rowhead.createCell(3).setCellValue("Serial");
            rowhead.createCell(4).setCellValue("Manufacturer");

            styleToRow(rowhead,boldStyle); 
            //--------------------Body---------------------------
            HSSFRow row;
            
            //-----------------------Nodes-------------------------
            for (int i = 0; i<nodes.length; i++)
            {
                //get node values(hardware,software,serial,manufacturer)
                String[] nodeValues = net.getNodeValues(nodes[i]);
                
                //create excel row with node data
                row = sheet.createRow((short)i+1);
                row.createCell(0).setCellValue(nodes[i]);//id
                row.createCell(1).setCellValue(nodeValues[0]);//hard
                row.createCell(2).setCellValue(nodeValues[1]);//soft
                row.createCell(3).setCellValue(nodeValues[2]);//serial
                row.createCell(4).setCellValue(nodeValues[3]);//manufacturer
                
                               
                //get node connectors of each node
                String[] nodeConIDs = net.getNodeConIDs(nodes[i]);
                
                //get values of each node connector to arraylist
                for (int j = 0; j < nodeConIDs.length; j++)
                {
                   nodeConnectorsList.add(nodeConIDs[j]);
                   nodeConValuesList.add(net.getnNodeConValues(nodeConIDs[j],nodes[i]));
                }                 
            }
            
            //----------------------Hosts--------------------------
            row = sheet.createRow((short)sheet.getLastRowNum()+2);

            row.createCell(0).setCellValue("Hosts");           
            row.createCell(1).setCellValue("Mac");//mac
            row.createCell(2).setCellValue("Ip");//ip
            
            styleToRow(row,boldStyle); 
            
            for(int i = 0; i<hosts.length; i++)
            {
                row = sheet.createRow((short)sheet.getLastRowNum()+1);
                
                row.createCell(0).setCellValue(hosts[i]);//id/name
                String[] hostValues = net.getHostValues(hosts[i]);//mac,ip
                row.createCell(1).setCellValue(hostValues[0]);//mac
                row.createCell(2).setCellValue(hostValues[1]);//ip
                
            }
            
            //----------------------Node Connectors/Interfaces-------------------
            row = sheet.createRow((short)sheet.getLastRowNum()+2);

            row.createCell(0).setCellValue("Node-Connectors/Interfaces");
            row.createCell(1).setCellValue("Interface Speed(kb/s)");
            row.createCell(2).setCellValue("Active Time");
            row.createCell(3).setCellValue("Mac Address");
            row.createCell(4).setCellValue("Interface Name");
            row.createCell(5).setCellValue("Configuration");
            row.createCell(6).setCellValue("Transmitted Bytes");
            row.createCell(7).setCellValue("Received Bytes");
            row.createCell(8).setCellValue("Tx Bytes/Sec");
            row.createCell(9).setCellValue("Rx Bytes/Sec");

            styleToRow(row,boldStyle);           
            
            for(int i = 0; i<nodeConnectorsList.size(); i++)
            {
                String[] nodeConValues = nodeConValuesList.get(i);
                
                row = sheet.createRow((short)sheet.getLastRowNum()+1);
                //node connector id
                row.createCell(0).setCellValue(nodeConnectorsList.get(i));
                
                //interface speed
                row.createCell(1).setCellValue(nodeConValues[0]);
                //interface active time
                row.createCell(2).setCellValue(PublicStatics.formatSeconds(Integer.parseInt(nodeConValues[1])));
                
                for (int j=2;j<nodeConValues.length;j++)
                    row.createCell(j+1).setCellValue(nodeConValues[j]);

            }
            
            //size columns to text size
            autosizeColumns(sheet);

            //create excel file
            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();

        }
        catch ( ArrayIndexOutOfBoundsException ex ) {
            ex.printStackTrace();
            System.out.println(" @create excel");
        }
        catch ( Exception ex ) {
            System.out.println(ex +" @create excel");
        }
    }
    
    private void styleToRow(HSSFRow row,HSSFCellStyle style){
        for(int i=0;i<row.getLastCellNum();i++)
                row.getCell(i).setCellStyle(style);
    }
    
    private void autosizeColumns(HSSFSheet sheet){
        for(int i=0;i<MAX_NUMBER_OF_COLUMNS;i++)
                sheet.autoSizeColumn(i);
    }
}
