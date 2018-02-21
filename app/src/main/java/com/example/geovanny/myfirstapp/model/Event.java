package com.example.geovanny.myfirstapp.model;

import com.example.geovanny.myfirstapp.R;

import java.io.Serializable;

/**
 * Created by geovanny on 18/02/18.
 */

public class Event implements Serializable{
    private int id;
    private String name;
    private String contactPhone;
    private String dayhour;
    private int price;
    private String place;
    private int imagen = R.mipmap.icparque;

    public Event(){}

    public Event(int id, String name, String contactPhone, String dayhour, int price, String place) {
        this.id = id;
        this.name = name;
        this.contactPhone = contactPhone;
        this.dayhour = dayhour;
        this.price = price;
        this.place = place;
    }

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

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
