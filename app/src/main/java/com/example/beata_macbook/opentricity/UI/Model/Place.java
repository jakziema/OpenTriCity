package com.example.beata_macbook.opentricity.UI.Model;

import org.parceler.Parcel;

/**
 * Created by Beata-MacBook on 13.10.2016.
 */

@Parcel
public class Place {

    String description;
    String name;
    String website;
    String address;
    String phoneNumber;
    String imageURL;

    public Place() {}

    public Place(String name, String website, String address, String phoneNumber, String imageURL, String description) {
        this.name = name;
        this.website = website;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.imageURL = imageURL;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDescription() { return description; }
}
