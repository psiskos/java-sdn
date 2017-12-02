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
public class HttpJsonRequest 
{
    HttpURLConnection connection;
    int callStatus;
    
    public boolean putRestconfInJson(String user, String password,String controllerIP,String baseURL, String flowString) 
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
            connection.setDoInput(true);
            connection.setDoOutput(true);           
            
            OutputStream out = connection.getOutputStream();
            out.write(flowString.getBytes("UTF-8"));
            callStatus = connection.getResponseCode();

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
          if (connection != null)
            connection.disconnect();
        }
        
        //true if ok, false if not
        return (callStatus == 201 || callStatus == 200); 
        
    }
    
    public boolean deleteRestconfInJson(String user, String password,String controllerIP,String baseURL) 
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
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Authorization", "Basic "
                    + encodedAuthStr);
            connection.setRequestProperty("Accept", "application/json");
            callStatus = connection.getResponseCode();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            connection.disconnect();
        }
         //true if ok, false if not
        return (callStatus == 201 || callStatus == 200);

    }
    
    public JSONObject getRestconfInJson(String user, String password,String controllerIP,String baseURL) 
    {

        StringBuffer result = new StringBuffer();
        try {

            if (!controllerIP.contains("http")) {
                controllerIP = "http://" + controllerIP;
            }
            baseURL = controllerIP + baseURL;

            URL url = new URL(baseURL);

            // Create authentication string and encode it to Base64
            String authStr = user + ":" + password;
            String encodedAuthStr = Base64.encodeBase64String(authStr
                    .getBytes());

            // Create Http connection
            connection = (HttpURLConnection) url
                    .openConnection();

            // Set connection properties
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Basic "
                    + encodedAuthStr);
            connection.setRequestProperty("Accept", "application/json");
            callStatus = connection.getResponseCode();

            // Get the response from connection's inputStream
            InputStream content = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    content));
            String line = "";
            while ((line = in.readLine()) != null) {
                result.append(line);
            }

            JSONObject nodes = new JSONObject(result.toString());
            return nodes;
        } catch (Exception e) {
            e.printStackTrace();
        }
          finally{
            if (connection != null)
                connection.disconnect();
        }

        return null;
    }
}
