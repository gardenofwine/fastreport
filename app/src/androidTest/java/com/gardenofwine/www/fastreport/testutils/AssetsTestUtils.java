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

package com.gardenofwine.www.fastreport.testutils;

import android.content.res.AssetManager;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * TODO: Write Javadoc for AssetsUtils.
 *
 * @author ifeins
 */
public class AssetsTestUtils {

    public static String readAsset(AssetManager assetManager, String assetName) {
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(assetName);
            return IOUtils.toString(inputStream, "utf8");
        } catch (IOException e) {
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
