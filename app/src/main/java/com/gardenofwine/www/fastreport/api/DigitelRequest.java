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

/**
 * TODO: Write Javadoc for DigitelRequestBuilder.
 *
 * @author ifeins
 */
public class DigitelRequest {

    private String firstName;
    private String lastName;
    private String streetName;
    private String streetNumber;
    private String streetCode;
    private String phonePrefix;
    private String phoneNumber;
    private String description;
    private String userId;
    private String encodedPicture = "";

    private DigitelRequest() {
        // prevent instantiation
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public String getPhonePrefix() {
        return phonePrefix;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getUserId() {
        return userId;
    }

    public String getEncodedPicture() {
        return encodedPicture;
    }

    public static class Builder {

        private DigitelRequest request;

        public Builder() {
            request = new DigitelRequest();
        }

        public Builder withName(String firstName, String lastName) {
            request.firstName = firstName;
            request.lastName = lastName;
            return this;
        }

        public Builder withStreet(String streetName, String streetNumber, String streetCode) {
            request.streetName = streetName;
            request.streetNumber = streetNumber;
            request.streetCode = streetCode;
            return this;
        }

        public Builder withPhone(String phoneNumber) {
            String strippedPhone = phoneNumber.replaceAll("-", "");
            request.phonePrefix = strippedPhone.substring(0, 3);
            request.phoneNumber = strippedPhone.substring(3);
            return this;
        }

        public Builder withDescription(String description) {
            request.description = description;
            return this;
        }

        public Builder withUserId(String userId) {
            request.userId = userId;
            return this;
        }

        public DigitelRequest build() {
            DigitelRequest tempRequest = request;
            this.request = null;
            return tempRequest;
        }

    }

}
