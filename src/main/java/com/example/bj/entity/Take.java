package com.example.bj.entity;

import javax.persistence.*;

/**
 * @author Jiangzhendong
 * @Description 备品出库实体类
 *
 */
@Entity
public class Take {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private int number;

    @Column
    private String peopleName;

    @Column
    private String type;

    @Column
    private String brand;

    @Column
    private String serialnumber;

    @Column
    private String room;

    @Column
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Take(){}

    public Take(int id,String name,int number, String peopleName, String type,String brand,String serialnumber,String room, String date){
        this.id = id;
        this.name = name;
        this.number = number;
        this.peopleName = peopleName;
        this.type = type;
        this.brand = brand;
        this.serialnumber = serialnumber;
        this.room = room;
        this.date = date;
    }


}
