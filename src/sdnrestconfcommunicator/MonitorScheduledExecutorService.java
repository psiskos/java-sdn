/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import static sdnrestconfcommunicator.PublicStatics.priorityQueues;

/**
 *
 * @author pas
 */
public class MonitorScheduledExecutorService
{
    private ScheduledExecutorService executor;
    private String nodeConnector,node,flowId;
    private JCheckBox box;
    private int monitorTimer, queueId;
    private double threshold,tmp;
    private NetworkData mNet;
    
    public MonitorScheduledExecutorService(String nodeConnector,String node,String flowId,JCheckBox box,
            int monitorTimer,int queueId,double thresholdByUser,NetworkData mNet)
    {
        this.nodeConnector = nodeConnector;
        this.node = node;
        this.flowId = flowId;
        this.box = box;
        this.monitorTimer = monitorTimer;
        this.queueId = queueId;
        this.threshold = getThreshold(thresholdByUser);
        this.mNet = mNet;
       
        String[] traRecBytesArray = mNet.getNodeConBytes(nodeConnector,node);
        tmp = Double.parseDouble(traRecBytesArray[0]);
        
        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(mRun, 0, monitorTimer, TimeUnit.SECONDS);
    }
    
    Runnable mRun = new Runnable() 
    {
        public void run()
        {
            Double bytesTrDifference;
            if(!box.isSelected())
                executor.shutdown();


                    String[] traRecBytesArray = mNet.getNodeConBytes(nodeConnector,node);
                    System.out.println("Tr bytes: " + traRecBytesArray[0]);

                    bytesTrDifference = (Double.parseDouble(traRecBytesArray[0]) - tmp)/monitorTimer;
                    tmp = Double.parseDouble(traRecBytesArray[0]);
                    System.out.println("Bytes Difference: " + bytesTrDifference);
                    System.out.println("Threshold: " + threshold);

                    if(bytesTrDifference > threshold)
                    {  
                        JOptionPane.showMessageDialog(null, "Link Congestion!!");
                        String baseUrl = PublicStatics.CONFIG_NODE_CONNECTOR_URL + node +
                                "/table/0" +
                                //gets selected item name as flowId
                                "/flow/" + flowId;
                        HttpJsonRequest installMonitorFlowRequest = new HttpJsonRequest();
                        //after timeout flow is removed from switch but not from controller
                        //flow is first deleted and then installed
                        installMonitorFlowRequest.deleteRestconfInJson(mNet.username, mNet.password, 
                                mNet.controllerIp, baseUrl);
                        installMonitorFlowRequest.putRestconfInJson(mNet.username, mNet.password, 
                                mNet.controllerIp, baseUrl, 
                                //if the first item from combobox is selected, then it gets the first item from priorityQueues
                                priorityQueues.get(queueId));
                        //JOptionPane.showMessageDialog(null, installMonitorFlowRequest.callStatus);
                    }
        }
    };
    
    private double getThreshold(double thresholdByUser)
    {
        return thresholdByUser * 125;//* 1000 / 8
    }
    
}
