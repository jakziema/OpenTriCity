package com.example.beata_macbook.opentricity.UI.Model;

/**
 * Created by Beata-MacBook on 13.10.2016.
 */

public class Place {
    String name;
    String website;
    String address;
    String phoneNumber;
    String imageURL;

    public Place() {

    }

    public Place(String name, String website, String address, String phoneNumber, String imageURL) {
        this.name = name;
        this.website = website;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.imageURL = imageURL;
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
}
