
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

public class CreateExcelFromNet 
{
    final private String fileName;
    final private NetworkData net;
    
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
            
            ArrayList<String> nodeConnectorsList = new ArrayList<>();
            ArrayList<Integer> interfaceSpeedList = new ArrayList<>();
            ArrayList<String> upTimeList = new ArrayList<>();
            
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
            Cell cell = rowhead.createCell(0);
            cell.setCellValue("Nodes");
            cell.setCellStyle(boldStyle);
            
            //--------------------Body---------------------------
            HSSFRow row;
            
            //-----------------------Nodes-------------------------
            for (int i = 0; i<nodes.length; i++)
            {
                row = sheet.createRow((short)i+1);
                row.createCell(0).setCellValue(nodes[i]);
                
                //get node connectors of each node
                String[] nodeConnectors = net.getNodeConnectors(nodes[i]);
                
                //get values of each node connector to arraylists
                for (int j = 0; j < nodeConnectors.length; j++)
                {
                   nodeConnectorsList.add(nodeConnectors[j]);
                   interfaceSpeedList.add(net.getInterfaceSpeed(nodeConnectors[j]));
                   upTimeList.add(net.getNodeConSeconds(nodeConnectors[j]));
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
            
            styleToRow(row,boldStyle);           
            
            for(int i = 0; i<nodeConnectorsList.size(); i++)
            {
                row = sheet.createRow((short)sheet.getLastRowNum()+1);
                //node connector id
                row.createCell(0).setCellValue(nodeConnectorsList.get(i));                
                //interface speed
                row.createCell(1).setCellValue(interfaceSpeedList.get(i));
                //interface active time
                row.createCell(2).setCellValue(PublicStatics.formatSeconds(Integer.parseInt(upTimeList.get(i))));
            }
            //System.out.print(sheet.getLastRowNum());
            
            //size columns to text inserted
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);

            //create excel file
            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }
    
    private void styleToRow(HSSFRow row,HSSFCellStyle style){
        for(int i=0;i<row.getLastCellNum();i++)
                row.getCell(i).setCellStyle(style);
    }
}
