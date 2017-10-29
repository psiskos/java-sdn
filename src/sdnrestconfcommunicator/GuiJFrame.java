/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import org.jfree.ui.RefineryUtilities;
import static sdnrestconfcommunicator.PublicStatics.REFRESH_TIMER;

/**
 *
 * @author pas
 */

public class GuiJFrame extends javax.swing.JFrame 
{
    NetworkData mNet;
    String[] nodes,flows,nodeConnectors;
    /**
     * Creates new form GuiJFrame
     */
    public GuiJFrame() {
        initComponents();
        tableLbl.setVisible(false);
        tableTxtFld.setVisible(false);
        
       
    }
    
    private void printToJList(String[] printArray,JList jList)
    {
        
        DefaultListModel listModel =  new DefaultListModel();
        listModel.removeAllElements();
        
        for (int i = 0; i < printArray.length; i++) 
        {
            //System.out.println(nodes[i]);
            String [] splitCommas = printArray[i].split(",");
            for (int j = 0; j < splitCommas.length; j++) 
                listModel.addElement(splitCommas[j]);
        }
        jList.setModel( listModel );
    }
    
    private boolean isItemSelected(String itemClicked,String[] values)
    {
        for (int i = 0; i < values.length; i++) 
        {
            //if node is selected enable get flow,install flow
            if(values[i].equals(itemClicked))
                return true;
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controllerIpTxtFld = new javax.swing.JTextField();
        portTxtFld = new javax.swing.JTextField();
        usernameTxtFld = new javax.swing.JTextField();
        passwordFld = new javax.swing.JPasswordField();
        controllerIpLbl = new javax.swing.JLabel();
        portLbl = new javax.swing.JLabel();
        usernameLbl = new javax.swing.JLabel();
        passwordLbl = new javax.swing.JLabel();
        tableLbl = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        jList = new javax.swing.JList<>();
        tableTxtFld = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        topoMenu = new javax.swing.JMenu();
        getNodesMenuItm = new javax.swing.JMenuItem();
        linksMenu = new javax.swing.JMenu();
        getLinksMenuItm = new javax.swing.JMenuItem();
        getLinkTrafficMenuItm = new javax.swing.JMenuItem();
        getUtilMenuItm = new javax.swing.JMenuItem();
        flowsMenu = new javax.swing.JMenu();
        getFlowsMenuItm = new javax.swing.JMenuItem();
        dropFlowsMenuItm = new javax.swing.JMenuItem();
        inspectFlowsMenuItm = new javax.swing.JMenuItem();
        installFlowsMenuItm = new javax.swing.JMenuItem();
        reportMenu = new javax.swing.JMenu();
        xlRepMenuItm = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        controllerIpTxtFld.setText("127.0.0.1");

        portTxtFld.setText("8181");

        usernameTxtFld.setText("admin");

        passwordFld.setText("admin");

        controllerIpLbl.setText("Controller IP");

        portLbl.setText("Port");

        usernameLbl.setText("Username");

        passwordLbl.setText("Password");

        tableLbl.setText("Table:");
        tableLbl.setEnabled(false);

        jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListMouseClicked(evt);
            }
        });
        jList.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jListComponentHidden(evt);
            }
        });
        jScrollPane.setViewportView(jList);

        tableTxtFld.setText("0");
        tableTxtFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableTxtFldActionPerformed(evt);
            }
        });

        topoMenu.setText("Topology");

        getNodesMenuItm.setText("Get Nodes");
        getNodesMenuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getNodesMenuItmActionPerformed(evt);
            }
        });
        topoMenu.add(getNodesMenuItm);

        jMenuBar1.add(topoMenu);

        linksMenu.setText("Links");

        getLinksMenuItm.setText("Get Node Links");
        getLinksMenuItm.setEnabled(false);
        getLinksMenuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getLinksMenuItmActionPerformed(evt);
            }
        });
        linksMenu.add(getLinksMenuItm);

        getLinkTrafficMenuItm.setText("Get Link Traffic");
        getLinkTrafficMenuItm.setEnabled(false);
        getLinkTrafficMenuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getLinkTrafficMenuItmActionPerformed(evt);
            }
        });
        linksMenu.add(getLinkTrafficMenuItm);

        getUtilMenuItm.setText("Get Node Utilization");
        getUtilMenuItm.setEnabled(false);
        getUtilMenuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getUtilMenuItmActionPerformed(evt);
            }
        });
        linksMenu.add(getUtilMenuItm);

        jMenuBar1.add(linksMenu);

        flowsMenu.setText("Flows");

        getFlowsMenuItm.setText("Get Flows");
        getFlowsMenuItm.setEnabled(false);
        getFlowsMenuItm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getFlowsMenuItmMouseClicked(evt);
            }
        });
        getFlowsMenuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getFlowsMenuItmActionPerformed(evt);
            }
        });
        flowsMenu.add(getFlowsMenuItm);

        dropFlowsMenuItm.setText("Drop Flow");
        dropFlowsMenuItm.setEnabled(false);
        dropFlowsMenuItm.setRequestFocusEnabled(false);
        dropFlowsMenuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropFlowsMenuItmActionPerformed(evt);
            }
        });
        flowsMenu.add(dropFlowsMenuItm);

        inspectFlowsMenuItm.setText("Inspect Flow");
        inspectFlowsMenuItm.setEnabled(false);
        inspectFlowsMenuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inspectFlowsMenuItmActionPerformed(evt);
            }
        });
        flowsMenu.add(inspectFlowsMenuItm);

        installFlowsMenuItm.setText("Install Flow");
        installFlowsMenuItm.setEnabled(false);
        installFlowsMenuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                installFlowsMenuItmActionPerformed(evt);
            }
        });
        flowsMenu.add(installFlowsMenuItm);

        jMenuBar1.add(flowsMenu);

        reportMenu.setText("Reports");

        xlRepMenuItm.setText("Excel Report");
        xlRepMenuItm.setEnabled(false);
        xlRepMenuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xlRepMenuItmActionPerformed(evt);
            }
        });
        reportMenu.add(xlRepMenuItm);

        jMenuBar1.add(reportMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(controllerIpLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(portLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(153, 153, 153))
                                .addComponent(usernameLbl))
                            .addComponent(passwordLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(passwordFld, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameTxtFld, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(controllerIpTxtFld, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(portTxtFld)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tableLbl)
                        .addGap(9, 9, 9)
                        .addComponent(tableTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(controllerIpLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(controllerIpTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tableTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tableLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getNodesMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getNodesMenuItmActionPerformed
        // TODO add your handling code here:    

        mNet = new NetworkData(usernameTxtFld.getText(),
                new String(passwordFld.getPassword()),
                controllerIpTxtFld.getText(),
                portTxtFld.getText());            
        
        if(mNet.getTopoNodes() != null)
        {
            //enables excel report button
            xlRepMenuItm.setEnabled(true);
            printToJList(mNet.getTopoNodes(),jList);
        }
    }//GEN-LAST:event_getNodesMenuItmActionPerformed

    private void jListComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jListComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jListComponentHidden

    private void getFlowsMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getFlowsMenuItmActionPerformed
        // TODO add your handling code here:
        if (isItemSelected(jList.getSelectedValue(),nodes))
        {
            flows = mNet.getFlowIDs(jList.getSelectedValue(),tableTxtFld.getText());
            printToJList(flows,jList);
        }
        else
            JOptionPane.showMessageDialog(null, "Select a node!!");
        
    }//GEN-LAST:event_getFlowsMenuItmActionPerformed

    private void jListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListMouseClicked
        // TODO add your handling code here:
        nodes = mNet.getTopoNodes();
        
        //enables get flows,table label and textfield only if selected item is a node
        getFlowsMenuItm.setEnabled(false);
        installFlowsMenuItm.setEnabled(false);
        dropFlowsMenuItm.setEnabled(false);
        inspectFlowsMenuItm.setEnabled(false);
        getLinksMenuItm.setEnabled(false);
        getLinkTrafficMenuItm.setEnabled(false);
        getUtilMenuItm.setEnabled(false);
        
        tableLbl.setVisible(false);
        tableTxtFld.setVisible(false);

        
        //is node selected
        if(isItemSelected(jList.getSelectedValue(),nodes)){
                getFlowsMenuItm.setEnabled(true);
                installFlowsMenuItm.setEnabled(true);
                tableLbl.setVisible(true);
                tableTxtFld.setVisible(true);
                getLinksMenuItm.setEnabled(true);
                getUtilMenuItm.setEnabled(true);
        }

        //is flow selected
        if(flows!= null)
        {
            //is flow selected
            if(isItemSelected(jList.getSelectedValue(),flows))
            {
                dropFlowsMenuItm.setEnabled(true);
                inspectFlowsMenuItm.setEnabled(true);
                tableLbl.setVisible(true);
                tableTxtFld.setVisible(true);        
            }                  
        }
        //is node-connector/link selected
        if(nodeConnectors!= null)
        {
            //is flow selected
            if(isItemSelected(jList.getSelectedValue(),nodeConnectors))
            {
                getLinkTrafficMenuItm.setEnabled(true);
            }                  
        }
    }//GEN-LAST:event_jListMouseClicked

    private void getFlowsMenuItmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getFlowsMenuItmMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_getFlowsMenuItmMouseClicked

    private void tableTxtFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableTxtFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableTxtFldActionPerformed

    private void dropFlowsMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropFlowsMenuItmActionPerformed
        // TODO add your handling code here:
        if (isItemSelected(jList.getSelectedValue(),flows))
            mNet.dropFlows(mNet.elemUsedForFlows, tableTxtFld.getText(), jList.getSelectedValue());
        else
            JOptionPane.showMessageDialog(null, "Select a flow!!");      
    }//GEN-LAST:event_dropFlowsMenuItmActionPerformed

    private void inspectFlowsMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inspectFlowsMenuItmActionPerformed
        // TODO add your handling code here:
        if (isItemSelected(jList.getSelectedValue(),flows))
        {
            String [] flowValues = mNet.getFlowsValues(jList.getSelectedValue());
            //removes []{} symbols
            for (int i=0; i<flowValues.length; i++)
               flowValues[i] = flowValues[i].replaceAll("[\\{\\]\\[\"\\}]", "");      
            printToJList((flowValues),jList);
        }
        else
            JOptionPane.showMessageDialog(null, "Select a flow!!");  
        
    }//GEN-LAST:event_inspectFlowsMenuItmActionPerformed

    private void installFlowsMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_installFlowsMenuItmActionPerformed
        // TODO add your handling code here:    
        if (isItemSelected(jList.getSelectedValue(),nodes))
            new InstallFlowJFrame(jList.getSelectedValue(), mNet.username, mNet.password, mNet.controllerIp).setVisible(true);
        else
            JOptionPane.showMessageDialog(null, "Select a node!!");
        
    }//GEN-LAST:event_installFlowsMenuItmActionPerformed

    private void getLinksMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getLinksMenuItmActionPerformed
        // TODO add your handling code here:
        //if selected item is a node
        if(isItemSelected(jList.getSelectedValue(),nodes))
        {
            nodeConnectors = mNet.getNodeConIDs(jList.getSelectedValue());
            printToJList(nodeConnectors,jList);
        }
        else
            JOptionPane.showMessageDialog(null, "Select a node!!");
    }//GEN-LAST:event_getLinksMenuItmActionPerformed

    private void getLinkTrafficMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getLinkTrafficMenuItmActionPerformed
        // TODO add your handling code here:
        
        
        if(isItemSelected(jList.getSelectedValue(),nodeConnectors))
        {
            String selectedNode = jList.getSelectedValue();
            String[] traRecBytesArray = mNet.getNodeConBytes(selectedNode);
            int interfaceSpeed = mNet.getNodeConInterSpeed(jList.getSelectedValue());//kbps
            
            TrafficChart  mChart = new TrafficChart(Double.parseDouble(traRecBytesArray[0]),
                    Double.parseDouble(traRecBytesArray[1]), interfaceSpeed);
            mChart.setVisible(true);

            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            Runnable updateRunnable = new Runnable() {
                public void run() 
                {
                    String[] tmp = mNet.getNodeConBytes(selectedNode);
//                    System.out.println(tmp[0]);
//                    System.out.println(tmp[1]);
                    mChart.updateGraph(Double.parseDouble(tmp[0]),Double.parseDouble(tmp[1]),interfaceSpeed);
                    if(!mChart.isVisible()){
                        executor.shutdown();
                    }
                }
            };            
            
            executor.scheduleAtFixedRate(updateRunnable, 0, REFRESH_TIMER, TimeUnit.SECONDS);

            
        }
        else
            JOptionPane.showMessageDialog(null, "Select a port/node-connector!!");
    }//GEN-LAST:event_getLinkTrafficMenuItmActionPerformed

    private void getUtilMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getUtilMenuItmActionPerformed
        // TODO add your handling code here:
        printToJList(mNet.getNodeUtil(jList.getSelectedValue()),jList);
    }//GEN-LAST:event_getUtilMenuItmActionPerformed

    private void xlRepMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xlRepMenuItmActionPerformed
        // TODO add your handling code here:
        //mNet has the network data for the excel, passed through static var
        FileChooser.net = mNet;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                FileChooser.createAndShowGUI();
            }
        });
    }//GEN-LAST:event_xlRepMenuItmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel controllerIpLbl;
    private javax.swing.JTextField controllerIpTxtFld;
    private javax.swing.JMenuItem dropFlowsMenuItm;
    private javax.swing.JMenu flowsMenu;
    private javax.swing.JMenuItem getFlowsMenuItm;
    private javax.swing.JMenuItem getLinkTrafficMenuItm;
    private javax.swing.JMenuItem getLinksMenuItm;
    private javax.swing.JMenuItem getNodesMenuItm;
    private javax.swing.JMenuItem getUtilMenuItm;
    private javax.swing.JMenuItem inspectFlowsMenuItm;
    private javax.swing.JMenuItem installFlowsMenuItm;
    private javax.swing.JList<String> jList;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JMenu linksMenu;
    private javax.swing.JPasswordField passwordFld;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JLabel portLbl;
    private javax.swing.JTextField portTxtFld;
    private javax.swing.JMenu reportMenu;
    private javax.swing.JLabel tableLbl;
    private javax.swing.JTextField tableTxtFld;
    private javax.swing.JMenu topoMenu;
    private javax.swing.JLabel usernameLbl;
    private javax.swing.JTextField usernameTxtFld;
    private javax.swing.JMenuItem xlRepMenuItm;
    // End of variables declaration//GEN-END:variables
}
