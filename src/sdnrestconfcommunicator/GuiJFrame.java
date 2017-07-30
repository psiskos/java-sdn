/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import javax.swing.DefaultListModel;
import javax.swing.JList;


/**
 *
 * @author pas
 */

public class GuiJFrame extends javax.swing.JFrame 
{
    NetworkElements mNet;
    String[] nodes,flows;
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
            listModel.addElement(printArray[i]);
        }
        jList.setModel( listModel );
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
        getTopoMenuItm = new javax.swing.JMenuItem();
        getNodesMenuItm = new javax.swing.JMenuItem();
        getLinksMenuItm = new javax.swing.JMenuItem();
        flowsMenu = new javax.swing.JMenu();
        getFlowsMenuItm = new javax.swing.JMenuItem();
        dropFlowsMenuItm = new javax.swing.JMenuItem();
        inspectFlowsMenuItm = new javax.swing.JMenuItem();

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

        getTopoMenuItm.setText("GetTopology");
        getTopoMenuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getTopoMenuItmActionPerformed(evt);
            }
        });
        topoMenu.add(getTopoMenuItm);

        getNodesMenuItm.setText("Get Nodes");
        getNodesMenuItm.setEnabled(false);
        getNodesMenuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getNodesMenuItmActionPerformed(evt);
            }
        });
        topoMenu.add(getNodesMenuItm);

        getLinksMenuItm.setText("Get Links");
        getLinksMenuItm.setEnabled(false);
        getLinksMenuItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getLinksMenuItmActionPerformed(evt);
            }
        });
        topoMenu.add(getLinksMenuItm);

        jMenuBar1.add(topoMenu);

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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(controllerIpLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(usernameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(controllerIpTxtFld, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(usernameTxtFld)
                            .addComponent(passwordFld)))
                    .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(controllerIpLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(controllerIpTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tableTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tableLbl))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getNodesMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getNodesMenuItmActionPerformed
        // TODO add your handling code here:     
        printToJList(mNet.getTopoNodes(),jList);
    }//GEN-LAST:event_getNodesMenuItmActionPerformed

    private void getLinksMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getLinksMenuItmActionPerformed
        // TODO add your handling code here:
        printToJList(mNet.getTopoLinks(),jList);
    }//GEN-LAST:event_getLinksMenuItmActionPerformed

    private void jListComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jListComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jListComponentHidden

    private void getFlowsMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getFlowsMenuItmActionPerformed
        // TODO add your handling code here:
        //System.out.println(
        flows = mNet.getFlows(jList.getSelectedValue(),tableTxtFld.getText());
        printToJList(flows,jList);
    }//GEN-LAST:event_getFlowsMenuItmActionPerformed

    private void jListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListMouseClicked
        // TODO add your handling code here:
        nodes = mNet.getTopoNodes();
        
        //enables get flows,table label and textfield only if selected item is a node
        getFlowsMenuItm.setEnabled(false);
        dropFlowsMenuItm.setEnabled(false);
        inspectFlowsMenuItm.setEnabled(false);
        tableLbl.setVisible(false);
        tableTxtFld.setVisible(false);
        for (int i = 0; i < nodes.length; i++) 
        {
            if(nodes[i].equals(jList.getSelectedValue())){
                getFlowsMenuItm.setEnabled(true);
                tableLbl.setVisible(true);
                tableTxtFld.setVisible(true);
                break;
            }
        }
        if(flows!= null)
        {
            for (int i = 0; i < flows.length; i++) 
            {
                if(flows[i].equals(jList.getSelectedValue())){
                    dropFlowsMenuItm.setEnabled(true);
                    inspectFlowsMenuItm.setEnabled(true);
                    tableLbl.setVisible(true);
                    tableTxtFld.setVisible(true);
                    break;
                }
            }
        }
    }//GEN-LAST:event_jListMouseClicked

    private void getTopoMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getTopoMenuItmActionPerformed
        // TODO add your handling code here:
        //gets user values and creates network class
        mNet = new NetworkElements(usernameTxtFld.getText(),
                new String(passwordFld.getPassword()),
                controllerIpTxtFld.getText());
        
        getNodesMenuItm.setEnabled(false);
        getLinksMenuItm.setEnabled(false);
        //if exception occurs null is returned
        if(mNet.getTopoID() != null){
            getNodesMenuItm.setEnabled(true);
            getLinksMenuItm.setEnabled(true);
        }
            
        
    }//GEN-LAST:event_getTopoMenuItmActionPerformed

    private void getFlowsMenuItmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getFlowsMenuItmMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_getFlowsMenuItmMouseClicked

    private void tableTxtFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableTxtFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableTxtFldActionPerformed

    private void dropFlowsMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropFlowsMenuItmActionPerformed
        // TODO add your handling code here:
        mNet.dropFlows(mNet.switchUsedForFlows, tableTxtFld.getText(), jList.getSelectedValue());
    }//GEN-LAST:event_dropFlowsMenuItmActionPerformed

    private void inspectFlowsMenuItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inspectFlowsMenuItmActionPerformed
        // TODO add your handling code here:
        printToJList(mNet.getFlowsValues(jList.getSelectedValue()),jList);
    }//GEN-LAST:event_inspectFlowsMenuItmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel controllerIpLbl;
    private javax.swing.JTextField controllerIpTxtFld;
    private javax.swing.JMenuItem dropFlowsMenuItm;
    private javax.swing.JMenu flowsMenu;
    private javax.swing.JMenuItem getFlowsMenuItm;
    private javax.swing.JMenuItem getLinksMenuItm;
    private javax.swing.JMenuItem getNodesMenuItm;
    private javax.swing.JMenuItem getTopoMenuItm;
    private javax.swing.JMenuItem inspectFlowsMenuItm;
    private javax.swing.JList<String> jList;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JPasswordField passwordFld;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JLabel tableLbl;
    private javax.swing.JTextField tableTxtFld;
    private javax.swing.JMenu topoMenu;
    private javax.swing.JLabel usernameLbl;
    private javax.swing.JTextField usernameTxtFld;
    // End of variables declaration//GEN-END:variables
}
