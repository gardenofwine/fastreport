package com.gardenofwine.www.fastreport.db.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ifeins on 2/23/15.
 */
@DatabaseTable(tableName = "streets")
public class Street {

    @DatabaseField(columnName = "_id", generatedId = true)
    int id;

    @DatabaseField(columnName = "street_name", canBeNull = false)
    String streetName;

    @DatabaseField(columnName = "english_street_name")
    String englishStreetName;

    @DatabaseField(columnName = "street_code", canBeNull = false, unique = true)
    int streetCode;

    @DatabaseField(columnName = "first_apartment_number")
    int firstApartmentNumber;

    @DatabaseField(columnName = "last_apartment_number")
    int lastApartmentNumber;

    public Street() {
        // needed for ORMLite
    }

    public Street(String streetName, String englishStreetName, int streetCode, int firstApartmentNumber, int lastApartmentNumber) {
        this.streetName = streetName;
        this.englishStreetName = englishStreetName;
        this.streetCode = streetCode;
        this.firstApartmentNumber = firstApartmentNumber;
        this.lastApartmentNumber = lastApartmentNumber;
    }

    public int getId() {
        return id;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getEnglishStreetName() {
        return englishStreetName;
    }

    public int getStreetCode() {
        return streetCode;
    }

    public int getFirstApartmentNumber() {
        return firstApartmentNumber;
    }

    public int getLastApartmentNumber() {
        return lastApartmentNumber;
    }
}
