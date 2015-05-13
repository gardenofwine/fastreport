/*
 * Copyright (c) 2015 PayPal, Inc.
 *
 * All rights reserved.
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 */

package com.gardenofwine.www.fastreport.api;

import com.jamesmurty.utils.XMLBuilder2;

import java.util.HashMap;
import java.util.Map;

/**
 * Serializes a DigitelRequest to XML format expected by the Digitel mobile API
 *
 * @author ifeins
 */
public class DigitelRequestSerializer {

    public static String toXml(DigitelRequest request) {
        XMLBuilder2 builder = XMLBuilder2.create("root").namespace("")
                .elem("datasources")
                    .elem("request").attr("user_uniqe_id", request.getUserId()).attr("app_id", "238").attr("app_type", "455").attr("app_code", "default").attr("campaign_key", "").attr("platform", "32").attr("client_version", "1.0").up()
                    .elem("vars").up()
                    .elem("controls")
                    .elem("control");


        Map<String, String> listControls = new HashMap<>();
        listControls.put("firstName", request.getFirstName());
        listControls.put("lastName", request.getLastName());
        listControls.put("displayAddress", request.getStreetName());
        listControls.put("streetCode", request.getStreetCode());
        listControls.put("houseNumber", request.getStreetNumber());
        listControls.put("pelPrefix", request.getPhonePrefix());
        listControls.put("pelNumber", request.getPhoneNumber());
        listControls.put("zehut", "undefined");
        listControls.put("description", request.getDescription());
        listControls.put("numberOfImagesTransferred", "0");
        listControls.put("deviceToken", "Alpha100");
        listControls.put("enableSMS", "false");
        listControls.put("userID", request.getUserId());
        listControls.put("pictureName", request.getEncodedPicture());
        listControls.put("mokedID", "3");
        listControls.put("mokedMail", null);

        // <control><listcontrol>...</listcontrol><listcontrol>...</listcontrol>...</control>
        for (Map.Entry<String, String> entry : listControls.entrySet()) {
            addListControl(builder, entry.getKey(), entry.getValue());
        }

        // <dss>...</dss>
        builder.up().up()
                .elem("dss")
                    .elem("ds").attr("id", "")
                        .elem("Type").text("0").up()
                        .elem("TableSourceId").text("116").up()
                        .elem("FieldSourceId").up()
                    .up()
                .up()
            .up();

        long currentTime = System.currentTimeMillis() / 1000L;

        // <monitor>...</monitor>
        builder.elem("monitor")
                    .elem("monitor_TableSourceId").text("112").up()
                    .elem("monitor_Layir").up()
                    .elem("monitor_HomePage").up()
                    .elem("monitor_Request_time").text(Long.toString(currentTime) + "159").up()
                    .elem("monitor_Response_time").text(Long.toString(currentTime) + "450").up()
                    .elem("monitor_HTML_time").text(Long.toString(currentTime) + "490").up()
                    .elem("monitor_os").text("Linux armv7l").up()
                    .elem("monitor_appCodeName").text("Mozilla").up()
                    .elem("monitor_appVersion").text("5.0 (Linux; Android 4.4.4; Nexus 5 Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36").up()
                    .elem("monitor_real_html").text("157").up()
                .up();

        return builder.asString();
    }

    public static void addListControl(XMLBuilder2 builder, String controlName, String controlValue) {
        builder.elem("listcontrol")
                .elem("codekey").text(controlName).up()
                .elem("value").text(String.format(" %s ", controlValue)).up()
                .up();
    }
}
