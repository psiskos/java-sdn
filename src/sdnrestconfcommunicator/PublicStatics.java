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
public class PublicStatics 
{
    //Opendaylight
    final public static String TOPOLOGY_URL = "/restconf/operational/network-topology:network-topology/";
    final public static String NODES_URL = "/restconf/operational/opendaylight-inventory:nodes/";
    final public static String NODE_CONNECTOR_URL = "/restconf/operational/opendaylight-inventory:nodes/node/";
    final public static String CONFIG_NODE_CONNECTOR_URL = "/restconf/config/opendaylight-inventory:nodes/node/";
    
    //global static vars
    final public static long GRAPH_REFRESH_TIME = 1;//seconds
    final public static long CONNECTION_REFRESH_TIME = 30;//seconds
    //times that thread waits before drawing if rates are 0
    final public static long NO_DRAW_THRESHOLD = 3;
    final public static double CONGESTION_THRESHOLD = 85;//percentage
    
    public static String formatSeconds(int seconds)
    {
        String timeFormatted = null;
        if (seconds > 3600)//hours
        {
            int hours = seconds / 3600;
            int mins = (seconds % 3600) / 60;
            int remSec = (seconds % 3600) % 60;
            timeFormatted = hours + " hours " + mins + " minutes " + remSec + " seconds";
        }
        else if (seconds > 60)//minutes
        {
            int mins = seconds/60;
            int remSec = seconds % 60;
            timeFormatted = mins + " minutes " + remSec + " seconds";
        }
        else
            timeFormatted = seconds + " seconds";
        return timeFormatted;
    }
    
}
