package sdnrestconfcommunicator;


public class SdnRestconfCommunicator 
{
    public static String topoRestconfURL = ":8181/restconf/operational/network-topology:network-topology/";
    public static String operNodesRestconfURL = ":8181/restconf/operational/opendaylight-inventory:nodes/node/";
    public static String configNodesRestconfURL = ":8181/restconf/config/opendaylight-inventory:nodes/node/";

    public static void main(String[] args) {
        
        GuiJFrame mGuiSwingForm = new GuiJFrame();
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() {
                mGuiSwingForm.setVisible(true);
            }
        });
        
       
        
       


        
        
    }
    
}


