package com.gardenofwine.www.fastreport;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by benny on 2/18/15.
 */
public class DigitelMobileAPI {

    public void postServiceRequest(String description){

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        // Construct the URL for the OpenWeatherMap query
        // Possible parameters are available at OWM's forecast API page, at
        // http://openweathermap.org/API#forecast
        URL url = null;
        try {
            url = new URL("http://digitelmobile.tel-aviv.gov.il/TouchPoints/IntegrationWsMobile/PdaGateWayWs.asmx/TestPdaGateWay");

            String body = requestBody(description);
            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("X-Requested-With", "com.muni.telaviv");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setDoOutput(true);
            urlConnection.setFixedLengthStreamingMode(body.length());
            urlConnection.setRequestMethod("POST");

            DataOutputStream wr = new DataOutputStream (
                    urlConnection.getOutputStream ());
            wr.writeBytes (body);
            wr.flush ();
            wr.close ();

            //Get Response
            InputStream is = urlConnection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            Log.i("**==", response.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String requestBody(String description) {
        String BENNY_NEXUS_5_USER_ID = "8c41f6b3b72198a5";
        String RANDOM_NEXUS_5_USER_ID = "9c41fab3b7255885";

        String body = requestBody.replace(TOKEN_USER_ID, RANDOM_NEXUS_5_USER_ID);
        body = body.replace(TOKEN_FIRST_NAME, "ירון");
        body = body.replace(TOKEN_LAST_NAME, "כהן");
        body = body.replace(TOKEN_PHONE_NUMBER_PREFIX, "050");
        body = body.replace(TOKEN_PHONE_NUMBER, "1234567");
        body = body.replace(TOKEN_CURRENT_TIME, "" + System.currentTimeMillis() / 1000L);

        body = body.replace(TOKEN_STREET_NAME, "רוטשילד");
        body = body.replace(TOKEN_STREET_CODE, "8");
        body = body.replace(TOKEN_STREET_NUMBER, "100");
        body = body.replace(TOKEN_DESCRIPTION, "בדיקה נא להתעלם");
        body = body.replace(TOKEN_PICTURE_BASE64, "");
        body = body.replace(TOKEN_NUMBER_OF_IMAGES, "0");

        return body;
    }

    final String TOKEN_USER_ID = "%USER_ID%";
    final String TOKEN_FIRST_NAME = "%FIRST_NAME%";
    final String TOKEN_LAST_NAME = "%LAST_NAME%";
    final String TOKEN_PHONE_NUMBER_PREFIX = "%PHONE_PREFIX%";
    final String TOKEN_PHONE_NUMBER = "%PHONE_NUMBER%";
    final String TOKEN_CURRENT_TIME = "%TIME%";

    final String TOKEN_STREET_NAME = "%STREET_NAME%";
    final String TOKEN_STREET_CODE = "%STREET_CODE%";
    final String TOKEN_STREET_NUMBER = "%STREET_NUMBER%";
    final String TOKEN_DESCRIPTION = "%DESCRIPTION%";
    final String TOKEN_PICTURE_BASE64 = "%PICTURE%";
    final String TOKEN_NUMBER_OF_IMAGES = "%NUMBER_OF_PICTURES%";


    final String requestBody = "functionId=47&strXml=<root xmlns=\"\">\n" +
            "   <datasources>\n" +
            "      <request user_uniqe_id=\"" + TOKEN_USER_ID + "\" app_id=\"238\" app_type=\"455\" app_code=\"default\" campaign_key=\"\" platform=\"32\" client_version=\"1.0\" />\n" +
            "      <vars />\n" +
            "      <controls>\n" +
            "         <control>\n" +
            "            <listcontrol>\n" +
            "               <codekey>firstName</codekey>\n" +
            "               <value>"+ TOKEN_FIRST_NAME + "</value>\n" +
            "            </listcontrol>\n" +
            "            <listcontrol>\n" +
            "               <codekey>lastName</codekey>\n" +
            "               <value>" + TOKEN_LAST_NAME + "</value>\n" +
            "            </listcontrol>\n" +
            "            <listcontrol>\n" +
            "               <codekey>displayAddress</codekey>\n" +
            "               <value>" + TOKEN_STREET_NAME + "</value>\n" +
            "            </listcontrol>\n" +
            "            <listcontrol>\n" +
            "               <codekey>streetCode</codekey>\n" +
            "               <value>" + TOKEN_STREET_CODE + "</value>\n" +
            "            </listcontrol>\n" +
            "            <listcontrol>\n" +
            "               <codekey>houseNumber</codekey>\n" +
            "               <value>" + TOKEN_STREET_NUMBER + "</value>\n" +
            "            </listcontrol>\n" +
            "            <listcontrol>\n" +
            "               <codekey>pelPrefix</codekey>\n" +
            "               <value>" + TOKEN_PHONE_NUMBER_PREFIX + "</value>\n" +
            "            </listcontrol>\n" +
            "            <listcontrol>\n" +
            "               <codekey>pelNumber</codekey>\n" +
            "               <value>" + TOKEN_PHONE_NUMBER + "</value>\n" +
            "            </listcontrol>\n" +
            "            <listcontrol>\n" +
            "               <codekey>zehut</codekey>\n" +
            "               <value>undefined</value>\n" +
            "            </listcontrol>\n" +
            "            <listcontrol>\n" +
            "               <codekey>description</codekey>\n" +
            "               <value>" + TOKEN_DESCRIPTION + "</value>\n" +
            "            </listcontrol>\n" +
            "            <listcontrol>\n" +
            "               <codekey>numberOfImagesTransferred</codekey>\n" +
            "               <value>" + TOKEN_NUMBER_OF_IMAGES + "</value>\n" +
            "            </listcontrol>\n" +
            "            <listcontrol>\n" +
            "               <codekey>deviceToken</codekey>\n" +
            "               <value>Alpha100</value>\n" +
            "            </listcontrol>\n" +
            "            <listcontrol>\n" +
            "               <codekey>enableSMS</codekey>\n" +
            "               <value>false</value>\n" +
            "            </listcontrol>\n" +
            "            <listcontrol>\n" +
            "               <codekey>userID</codekey>\n" +
            "               <value>" + TOKEN_USER_ID + "</value>\n" +
            "            </listcontrol>\n" +
            "            <listcontrol>\n" +
            "               <codekey>pictureName</codekey>\n" +
            "               <value>" + TOKEN_PICTURE_BASE64 + "</value>\n" +
            "            </listcontrol>\n" +
            "            <listcontrol>\n" +
            "               <codekey>mokedID</codekey>\n" +
            "               <value>3</value>\n" +
            "            </listcontrol>\n" +
            "            <listcontrol>\n" +
            "               <codekey>mokedMail</codekey>\n" +
            "               <value />\n" +
            "            </listcontrol>\n" +
            "         </control>\n" +
            "      </controls>\n" +
            "      <dss>\n" +
            "         <ds id=\"\">\n" +
            "            <Type>0</Type>\n" +
            "            <TableSourceId>116</TableSourceId>\n" +
            "            <FieldSourceId />\n" +
            "         </ds>\n" +
            "      </dss>\n" +
            "   </datasources>\n" +
            "   <monitor>\n" +
            "      <monitor_TableSourceId>112</monitor_TableSourceId>\n" +
            "      <monitor_Layir />\n" +
            "      <monitor_HomePage />\n" +
            "      <monitor_Request_time>" + TOKEN_CURRENT_TIME + "159</monitor_Request_time>\n" +
            "      <monitor_Response_time>" + TOKEN_CURRENT_TIME + "450</monitor_Response_time>\n" +
            "      <monitor_HTML_time>" + TOKEN_CURRENT_TIME + "490</monitor_HTML_time>\n" +
            "      <monitor_os>Linux armv7l</monitor_os>\n" +
            "      <monitor_appCodeName>Mozilla</monitor_appCodeName>\n" +
            "      <monitor_appVersion>5.0 (Linux; Android 4.4.4; Nexus 5 Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36</monitor_appVersion>\n" +
            "      <monitor_real_html>157</monitor_real_html>\n" +
            "   </monitor>\n" +
            "</root>";
}
