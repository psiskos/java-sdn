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
import javax.swing.Timer;
import org.jfree.ui.RefineryUtilities;
import static sdnrestconfcommunicator.ControllerUrls.REFRESH_TIMER;

/**
 *
 * @author pas
 */

public class GuiJFrame extends javax.swing.JFrame 
{
    NetworkElements mNet;
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
        usernameTxtFld = new javax.swing.JTextField();
        controllerIpLbl = new javax.swing.JLabel();
        usernameLbl = new javax.swing.JLabel();
        passwordLbl = new javax.swing.JLabel();
        passwordFld = new javax.swing.JPasswordField();
        jScrollPane = new javax.swing.JScrollPane();
        jList = new javax.swing.JList<>();
        tableTxtFld = new javax.swing.JTextField();
        tableLbl = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        topoMenu = new javax.swing.JMenu();
        getNodesMenuItm = new javax.swing.JMenuItem();
        linksMenu = new javax.swing.JMenu();
        getLinksMenuItm = new javax.swing.JMenuItem();
        getLinkTrafficMenuItm = new javax.swing.JMenuItem();
        flowsMenu = new javax.swing.JMenu();
        getFlowsMenuItm = new javax.swing.JMenuItem();
        dropFlowsMenuItm = new javax.swing.JMenuItem();
        inspectFlowsMenuItm = new javax.swing.JMenuItem();
        installFlowsMenuItm = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        controllerIpTxtFld.setText("127.0.0.1");

        usernameTxtFld.setText("admin");

        controllerIpLbl.setText("Controller IP");

        usernameLbl.setText("Username");

        passwordLbl.setText("Password");

        passwordFld.setText("admin");

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

        tableLbl.setText("Table:");
        tableLbl.setEnabled(false);

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

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tableLbl)
                        .addGap(9, 9, 9)
                        .addComponent(tableTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(controllerIpLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(usernameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(controllerIpTxtFld, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(usernameTxtFld)
                            .addComponent(passwordFld))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(controllerIpLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(controllerIpTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tableTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tableLbl))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getNodesMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getNodesMenuItmActionPerformed
        // TODO add your handling code here:    
        if(mNet == null)
        {
            mNet = new NetworkElements(usernameTxtFld.getText(),
                new String(passwordFld.getPassword()),
                controllerIpTxtFld.getText());
        }
        
        printToJList(mNet.getTopoNodes(),jList);
    }//GEN-LAST:event_getNodesMenuItmActionPerformed

    private void jListComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jListComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jListComponentHidden

    private void getFlowsMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getFlowsMenuItmActionPerformed
        // TODO add your handling code here:
        if (isItemSelected(jList.getSelectedValue(),nodes))
        {
            flows = mNet.getFlows(jList.getSelectedValue(),tableTxtFld.getText());
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
        tableLbl.setVisible(false);
        tableTxtFld.setVisible(false);

        
        //is node selected
        if(isItemSelected(jList.getSelectedValue(),nodes)){
                getFlowsMenuItm.setEnabled(true);
                installFlowsMenuItm.setEnabled(true);
                tableLbl.setVisible(true);
                tableTxtFld.setVisible(true);
                getLinksMenuItm.setEnabled(true);
        }

        
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
            nodeConnectors = mNet.getNodeConnectors(jList.getSelectedValue());
            printToJList(nodeConnectors,jList);
        }
        else
            JOptionPane.showMessageDialog(null, "Select a node!!");
    }//GEN-LAST:event_getLinksMenuItmActionPerformed

    private void getLinkTrafficMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getLinkTrafficMenuItmActionPerformed
        // TODO add your handling code here:
        
        
        if(isItemSelected(jList.getSelectedValue(),nodeConnectors))
        {
            
            String[] traRecBytesArray = mNet.getNodeConBytes(jList.getSelectedValue());
            TrafficChart  mChart = new TrafficChart(Double.parseDouble(traRecBytesArray[0]),
                    Double.parseDouble(traRecBytesArray[1]));
            mChart.setVisible(true);

            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            Runnable updateRunnable = new Runnable() {
                public void run() 
                {
                    String[] tmp = mNet.getNodeConBytes(jList.getSelectedValue());
//                    System.out.println(tmp[0]);
//                    System.out.println(tmp[1]);
                    mChart.updateGraph(Double.parseDouble(tmp[0]),Double.parseDouble(tmp[1]));
                    if(!mChart.isVisible()){
                        executor.shutdown();
                    }
                }
            };            
            
            executor.scheduleAtFixedRate(updateRunnable, 0, REFRESH_TIMER, TimeUnit.MILLISECONDS);

            
        }
        else
            JOptionPane.showMessageDialog(null, "Select a port/node-connector!!");
    }//GEN-LAST:event_getLinkTrafficMenuItmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel controllerIpLbl;
    private javax.swing.JTextField controllerIpTxtFld;
    private javax.swing.JMenuItem dropFlowsMenuItm;
    private javax.swing.JMenu flowsMenu;
    private javax.swing.JMenuItem getFlowsMenuItm;
    private javax.swing.JMenuItem getLinkTrafficMenuItm;
    private javax.swing.JMenuItem getLinksMenuItm;
    private javax.swing.JMenuItem getNodesMenuItm;
    private javax.swing.JMenuItem inspectFlowsMenuItm;
    private javax.swing.JMenuItem installFlowsMenuItm;
    private javax.swing.JList<String> jList;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JMenu linksMenu;
    private javax.swing.JPasswordField passwordFld;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JLabel tableLbl;
    private javax.swing.JTextField tableTxtFld;
    private javax.swing.JMenu topoMenu;
    private javax.swing.JLabel usernameLbl;
    private javax.swing.JTextField usernameTxtFld;
    // End of variables declaration//GEN-END:variables
}
