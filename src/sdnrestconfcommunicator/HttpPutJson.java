/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdnrestconfcommunicator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.codec.binary.Base64;
import org.codehaus.jettison.json.JSONObject;


/**
 *
 * @author pas
 */
public class HttpPutJson 
{
    HttpURLConnection connection;
    JSONObject putResult;
    int callStatus;
    public boolean putRestconfInJson(String user, String password,String controllerIP,String baseURL) 
    {

        try 
        {

            if (!controllerIP.contains("http")) {
                controllerIP = "http://" + controllerIP;
            }
            baseURL = controllerIP + baseURL;

            // Create URL = base URL + container
            URL url = new URL(baseURL);

            // Create authentication string and encode it to Base64
            String authStr = user + ":" + password;
            String encodedAuthStr = Base64.encodeBase64String(authStr
                   .getBytes());
            

            // Create Http connection
            connection = (HttpURLConnection) url
                    .openConnection();

            // Set connection properties
            
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Basic "
                    + encodedAuthStr);
            //connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            
            String test = "{\n" +
"  \"flow\": {\n" +
"    \"strict\": \"false\",\n" +
"    \"instructions\": {\n" +
"      \"instruction\": {\n" +
"        \"order\": \"0\",\n" +
"        \"go-to-table\": { \"table_id\": \"1\" }\n" +
"      }\n" +
"    },\n" +
"    \"table_id\": \"0\",\n" +
"    \"id\": \"2\",\n" +
"    \"cookie_mask\": \"255\",\n" +
"    \"installHw\": \"false\",\n" +
"    \"match\": {\n" +
"      \"ethernet-match\": {\n" +
"        \"ethernet-type\": { \"type\": \"2048\" }\n" +
"      },\n" +
"      \"ipv4-source\": \"10.0.0.0/8\",\n" +
"      \"ipv4-destination\": \"10.0.0.0/8\",\n" +
"      \"ip-match\": { \"ip-protocol\": \"6\" }\n" +
"    },\n" +
"    \"hard-timeout\": \"0\",\n" +
"    \"cookie\": \"4\",\n" +
"    \"idle-timeout\": \"0\",\n" +
"    \"flow-name\": \"IP FLOW\",\n" +
"    \"priority\": \"17\",\n" +
"    \"barrier\": \"false\"\n" +
"  }\n" +
"}";
            OutputStream out = connection.getOutputStream();
            out.write(test.getBytes("UTF-8"));
            callStatus = connection.getResponseCode();
            

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
          if (connection != null)
            connection.disconnect();
        }
        
        if (callStatus == 201 || callStatus == 200)//ok 
        {		
            System.out.println("Flow installed Successfully");
            return true;
	} 
        else 
        {
            System.err.println("Failed to install flow.. " + callStatus);
            return false;	
        }
    }
}
