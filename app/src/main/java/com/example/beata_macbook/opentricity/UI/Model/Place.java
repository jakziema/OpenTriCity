package com.example.beata_macbook.opentricity.UI.Model;

import org.parceler.Parcel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Beata-MacBook on 13.10.2016.
 */

/**
 * Klasa modelujaca nasze Miejsce
 */

@Parcel
public class Place {

    String opis;
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
    String el_wheelchair;
    String wheelchair;
    String crutch;
    String latitude;
    String longitude;
    String deaf;
    String blind;
    String midget;
    HashMap<String, Object> comments;

    //pusty konstruktor potrzebny bibliotece PICASSO
    public Place() {}

    public Place(
            String name, String website, String address, String phoneNumber,
            String imageURL, String opis, String elevator,
            String staff, String bar, String toilets, String podjazdy,
            String el_wheelchair, String wheelchair, String crutch,
            String latitude, String longitude, String blind, String midget,
            String deaf,  HashMap<String, Object> comments
    ) {
        this.name = name;
        this.website = website;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.imageURL = imageURL;
        this.opis = opis;
        this.elevator = elevator;
        this.staff = staff;
        this.bar = bar;
        this.toilets = toilets;
        this.podjazdy = podjazdy;
        this.el_wheelchair = el_wheelchair;
        this.wheelchair = wheelchair;
        this.crutch = crutch;
        this.latitude = latitude;
        this.longitude = longitude;
        this.blind = blind;
        this.deaf = deaf;
        this.midget = midget;
        this.comments = comments;
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

    public String getDescription() { return opis; }

    public String getElevator() { return elevator; }

    public String getStaff() {return staff; }

    public String getPodjazdy() {return podjazdy; }

    public String getBar() {return bar; }

    public String getToilets() {return toilets; }

    public void setDescription(String opis) {
        this.opis = opis;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setElevator(String elevator) {
        this.elevator = elevator;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public void setPodjazdy(String podjazdy) {
        this.podjazdy = podjazdy;
    }

    public void setToilets(String toilets) {
        this.toilets = toilets;
    }

    public String getLatitude() {return latitude;}

    public String getLongitude() {return longitude; }

    public HashMap<String, Object> getComments() {
        return comments;
    }

    public void setComments(HashMap<String, Object> comments) {
        this.comments = comments;
    }
}
