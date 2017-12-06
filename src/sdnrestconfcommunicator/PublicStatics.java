/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.codehaus.jettison.json.JSONObject;

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
    final public static long CONNECTION_REFRESH_TIME = 100;//seconds
    //times that thread waits before drawing if rates are 0
    final public static long NO_DRAW_THRESHOLD = 3;
    final public static double CONGESTION_THRESHOLD = 90;//percentage
    
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

    
    final static String queue = "{\n" +
"  \"flow\": {\n" +
"    \"id\": \"test\",\n" +
"    \"instructions\": {\n" +
"      \"instruction\": {\n" +
"        \"order\": \"0\",\n" +
"        \"apply-actions\": {\n" +
"          \"action\": [\n" +
"            {\n" +
"              \"order\": \"1\",\n" +
"              \"output-action\": {\n" +
"                \"output-node-connector\": \"NORMAL\",\n" +
"                \"max-length\": \"65535\"\n" +
"              }\n" +
"            },\n" +
"            {\n" +
"              \"order\": \"0\",\n" +
"              \"set-queue-action\": { \"queue-id\": \"0\" }\n" +
"            }\n" +
"          ]\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"barrier\": \"true\",\n" +
"    \"flow-name\": \"sdn_communicator_flow\",\n" +
"    \"match\": {\n" +
"      \"ethernet-match\": {\n" +
"        \"ethernet-type\": { \"type\": \"2048\" }\n" +
"      },\n" +
"      \"ipv4-source\": \"0.0.0.0/0\",\n" +
"      \"ipv4-destination\": \"0.0.0.0/0\",\n" +
"      \"ip-match\": { \"ip-protocol\": \"6\" }\n" +
"    },\n" +
"    \"hard-timeout\": \"0\",\n" +
"    \"priority\": \"20\",\n" +
"    \"table_id\": \"0\",\n" +
"    \"idle-timeout\": \"0\"\n" +
"  }\n" +
"}";
    
    final static String queueMonitorTest = "{\n" +
"  \"flow\": {\n" +
"    \"id\": \"q1\",\n" +
"    \"instructions\": {\n" +
"      \"instruction\": {\n" +
"        \"order\": \"0\",\n" +
"        \"apply-actions\": {\n" +
"          \"action\": [\n" +
"            {\n" +
"              \"order\": \"1\",\n" +
"              \"output-action\": {\n" +
"                \"output-node-connector\": \"NORMAL\",\n" +
"                \"max-length\": \"65535\"\n" +
"              }\n" +
"            },\n" +
"            {\n" +
"              \"order\": \"0\",\n" +
"              \"set-queue-action\": { \"queue-id\": \"2\" }\n" +
"            }\n" +
"          ]\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"barrier\": \"true\",\n" +
"    \"flow-name\": \"sdn_communicator_flow\",\n" +
"    \"match\": {\n" +
"      \"ethernet-match\": {\n" +
"        \"ethernet-type\": { \"type\": \"2048\" }\n" +
"      },\n" +
"      \"ipv4-source\": \"10.0.0.2/32\",\n" +
"      \"ipv4-destination\": \"10.0.0.1/32\",\n" +
"      \"ip-match\": { \"ip-protocol\": \"6\" }\n" +
"    },\n" +
"    \"hard-timeout\": \"100\",\n" +
"    \"priority\": \"20\",\n" +
"    \"table_id\": \"0\",\n" +
"    \"idle-timeout\": \"0\"\n" +
"  }\n" +
"}";
    
    final static String queueMonitorTest2 = "{\n" +
"  \"flow\": {\n" +
"    \"id\": \"q2\",\n" +
"    \"instructions\": {\n" +
"      \"instruction\": {\n" +
"        \"order\": \"0\",\n" +
"        \"apply-actions\": {\n" +
"          \"action\": [\n" +
"            {\n" +
"              \"order\": \"1\",\n" +
"              \"output-action\": {\n" +
"                \"output-node-connector\": \"NORMAL\",\n" +
"                \"max-length\": \"65535\"\n" +
"              }\n" +
"            },\n" +
"            {\n" +
"              \"order\": \"0\",\n" +
"              \"set-queue-action\": { \"queue-id\": \"1\" }\n" +
"            }\n" +
"          ]\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"barrier\": \"true\",\n" +
"    \"flow-name\": \"sdn_communicator_flow\",\n" +
"    \"match\": {\n" +
"      \"ethernet-match\": {\n" +
"        \"ethernet-type\": { \"type\": \"2048\" }\n" +
"      },\n" +
"      \"ipv4-source\": \"10.0.0.4/32\",\n" +
"      \"ipv4-destination\": \"10.0.0.1/32\",\n" +
"      \"ip-match\": { \"ip-protocol\": \"6\" }\n" +
"    },\n" +
"    \"hard-timeout\": \"100\",\n" +
"    \"priority\": \"20\",\n" +
"    \"table_id\": \"0\",\n" +
"    \"idle-timeout\": \"0\"\n" +
"  }\n" +
"}";
    
    final static String queueMonitorTest3 = "{\n" +
"  \"flow\": {\n" +
"    \"id\": \"q3\",\n" +
"    \"instructions\": {\n" +
"      \"instruction\": {\n" +
"        \"order\": \"0\",\n" +
"        \"apply-actions\": {\n" +
"          \"action\": [\n" +
"            {\n" +
"              \"order\": \"1\",\n" +
"              \"output-action\": {\n" +
"                \"output-node-connector\": \"NORMAL\",\n" +
"                \"max-length\": \"65535\"\n" +
"              }\n" +
"            },\n" +
"            {\n" +
"              \"order\": \"0\",\n" +
"              \"set-queue-action\": { \"queue-id\": \"1\" }\n" +
"            }\n" +
"          ]\n" +
"        }\n" +
"      }\n" +
"    },\n" +
"    \"barrier\": \"true\",\n" +
"    \"flow-name\": \"sdn_communicator_flow\",\n" +
"    \"match\": {\n" +
"      \"ethernet-match\": {\n" +
"        \"ethernet-type\": { \"type\": \"2048\" }\n" +
"      },\n" +
"      \"ipv4-source\": \"10.0.0.3/32\",\n" +
"      \"ipv4-destination\": \"10.0.0.1/32\",\n" +
"      \"ip-match\": { \"ip-protocol\": \"6\" }\n" +
"    },\n" +
"    \"hard-timeout\": \"100\",\n" +
"    \"priority\": \"20\",\n" +
"    \"table_id\": \"0\",\n" +
"    \"idle-timeout\": \"0\"\n" +
"  }\n" +
"}";
    
    public static ArrayList<String> priorityQueues = new ArrayList<>
        (Arrays.asList(queueMonitorTest, queueMonitorTest2,queueMonitorTest3));
    

}
