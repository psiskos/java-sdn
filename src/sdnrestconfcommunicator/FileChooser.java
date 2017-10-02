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
 
import java.io.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
/*
 * FileChooserDemo.java uses these files:
 *   images/Open16.gif
 *   images/Save16.gif
 */
public class FileChooser extends JPanel
{
    JButton saveButton;
    JFileChooser fc;
    public static NetworkElements net;
 
    public FileChooser() 
    {
        super(new BorderLayout());

 
        //Create a file chooser
        fc = new JFileChooser();
        String timeStamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm").format(Calendar.getInstance().getTime());
        fc.setSelectedFile(new File(timeStamp+"_sdn_report.xls"));

        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
     
        int returnVal = fc.showSaveDialog(FileChooser.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) 
            {
                String file = fc.getSelectedFile().toString();
                CreateExcel mXL = new CreateExcel(file,net);
            } 
    }
    
 
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("FileChooser");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
        //Add content to the window.
        frame.add(new FileChooser());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
