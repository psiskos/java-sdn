/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author pas
 */
public class ParseJsonReply 
{
    final private JSONObject reply;
    
    ParseJsonReply(JSONObject reply)
    {
        this.reply = reply;        
    }
    
    protected String getTopologyID()
    {
        String topologyId = "";
        
        try
        {
            JSONObject netTopo = reply.getJSONObject("network-topology");
            JSONArray topo = netTopo.getJSONArray("topology");
            JSONObject obj = topo.getJSONObject(0);
            topologyId = obj.getString("topology-id");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return topologyId;
    }
    
    protected JSONObject getFullControllerTopo()
    {
        JSONObject netTopo = null;
        try
        {
            netTopo = reply.getJSONObject("network-topology");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return netTopo;
    }
    
    protected String[] getNodes()
    {
        String[] nodes = null;
        try
        {
            JSONObject netTopo = reply.getJSONObject("network-topology");
            JSONArray topo = netTopo.getJSONArray("topology");
            JSONObject obj = topo.getJSONObject(0);
            nodes[0] = obj.getString("node");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return nodes;
    }
}
