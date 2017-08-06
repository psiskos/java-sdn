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
public class ControllerUrls 
{
    //Opendaylight
    final public static String TOPOLOGY_URL = ":8181/restconf/operational/network-topology:network-topology/";
    final public static String NODES_URL = ":8181/restconf/operational/opendaylight-inventory:nodes/node/";
    final public static String CONFIG_NODES_URL = ":8181/restconf/config/opendaylight-inventory:nodes/node/";
}
