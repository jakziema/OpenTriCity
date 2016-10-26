package com.example.beata_macbook.opentricity.UI.Model;

import org.parceler.Parcel;

/**
 * Created by Beata-MacBook on 13.10.2016.
 */

/**
 * Klasa modelujaca nasze Miejsce
 */

@Parcel
public class Place {

    String description;
    String name;
    String website;
    String address;
    String phoneNumber;
    String imageURL;
    String elevator;
    String bar;
    String staff;
    String podjazdy;
    String toilets;

    //pusty konstruktor potrzebny bibliotece PICASSO
    public Place() {}

    public Place(String name, String website, String address, String phoneNumber, String imageURL, String description, String elevator) {
        this.name = name;
        this.website = website;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.imageURL = imageURL;
        this.description = description;
        this.elevator = elevator;
        this.staff = staff;
        this.bar = bar;
        this.toilets = toilets;
        this.podjazdy = podjazdy;
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

    public String getElevator() { return elevator; }

    public String getStaff() {return staff; }

    public String getPodjazdy() {return podjazdy; }

    public String getBar() {return bar; }

    public String getToilets() {return toilets; }
}
