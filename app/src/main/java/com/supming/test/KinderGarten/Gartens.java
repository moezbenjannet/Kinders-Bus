package com.supming.test.KinderGarten;

import java.sql.Blob;

/**
 * Created by Omar Bouhamed on 15/11/2017.
 */

public class Gartens {

    private int id ;
    private String name ;
    private String city ;
    private long contact ;
    private byte[] image ;
    private int favorite ;



    public Gartens(int id, String name, String city, long contact, byte[] image, int favorite) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.contact = contact;
        this.image = image;
        this.favorite = favorite ;
    }
    public Gartens(int id, String name, String city, long contact , int favorite) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.contact = contact;
        this.favorite = favorite ;
    }

    public Gartens(){}


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public long getContact() {
        return contact;
    }

    public byte[] getImage() {
        return image;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setId(int id) {this.id = id;}

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setFavorite(int favorite) {this.favorite = favorite;}

}
