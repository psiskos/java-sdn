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
    final public static String TOPOLOGY_URL = ":8181/restconf/operational/network-topology:network-topology/";
    final public static String NODES_URL = ":8181/restconf/operational/opendaylight-inventory:nodes/node/";
    final public static String CONFIG_NODES_URL = ":8181/restconf/config/opendaylight-inventory:nodes/node/";
    
    //global static vars
    final public static long REFRESH_TIMER = 1;//seconds
    //times that thread waits before drawing if rates are 0
    final public static long NO_DRAW_THRESHOLD = 3;
    final public static double CONGESTION_THRESHOLD = 85;//percentage
    
}
