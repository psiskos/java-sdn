package sdnrestconfcommunicator;


public class Main 
{
    

    public static void main(String[] args) 
    {
        //initialize Swing Jframe
        GuiJFrame mGuiSwingForm = new GuiJFrame();
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() {
                mGuiSwingForm.setVisible(true);
            }
        });
        
        
    }
    
}


