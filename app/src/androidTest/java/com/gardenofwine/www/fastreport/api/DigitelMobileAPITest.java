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

import android.support.annotation.NonNull;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.gardenofwine.www.fastreport.testutils.AssetsTestUtils;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * TODO: Write Javadoc for DigitelMobileAPITest.
 *
 * @author ifeins
 */
@RunWith(AndroidJUnit4.class)
public class DigitelMobileAPITest extends InstrumentationTestCase {

    private static final int SERVER_PORT = 12345;
    private static final String BASE_URL = "http://localhost:" + SERVER_PORT;

    private DigitelMobileAPI mApi;
    private MockWebServer mServer;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        mServer = new MockWebServer();
        mServer.start(12345);
        mApi = new DigitelMobileAPI(BASE_URL);
    }

    @After
    public void tearDown() throws Exception {
        super.setUp();
        mServer.shutdown();
    }

    @Test
    public void testPostServiceRequest() throws Exception {
        String responseBody = AssetsTestUtils.readAsset(getInstrumentation().getContext().getAssets(), "success_response.xml");
        mServer.enqueue(new MockResponse().setBody(responseBody));
        mApi.postServiceRequest("blat");

        String expectedRequestBody = AssetsTestUtils.readAsset(getInstrumentation().getContext().getAssets(), "report_request.xml");
        RecordedRequest recordedRequest = mServer.takeRequest();
        assertEquals("/TouchPoints/IntegrationWsMobile/PdaGateWayWs.asmx/TestPdaGateWay", recordedRequest.getPath());

        String actualRequestBody = recordedRequest.getBody().readUtf8();
        XMLAssert.assertXMLEqual(cleanedRequestBody(expectedRequestBody), cleanedRequestBody(actualRequestBody));
    }

    private String cleanedRequestBody(String requestBody) {
        // there is some junk before the root element which is not valid xml
        return requestBody.substring(requestBody.indexOf("<root"));
    }
}
