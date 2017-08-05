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
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jettison.json.JSONObject;

public class HttpGetJson
{
    HttpURLConnection connection;

    public JSONObject getRestconfInJson(String user, String password,String controllerIP,String baseURL) 
    {

        StringBuffer result = new StringBuffer();
        try {

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
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Basic "
                    + encodedAuthStr);
            connection.setRequestProperty("Accept", "application/json");

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
