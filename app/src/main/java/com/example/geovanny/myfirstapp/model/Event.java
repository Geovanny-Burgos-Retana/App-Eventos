package com.example.geovanny.myfirstapp.model;

/**
 * Created by geovanny on 18/02/18.
 */

public class Event {
    private int id;
    private String name;
    private String contactPhone;
    private String dayhour;
    private int price;
    private String place;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getDayhour() {
        return dayhour;
    }

    public void setDayhour(String dayhour) {
        this.dayhour = dayhour;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
