package com.example.bj.entity;

import javax.persistence.*;

/**
 * @author Jiangzhendong
 * @Description 备品入库实体类
 *
 */

@Entity
public class BeiPin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private int number;

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

    @Column
    private String supply;

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

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }

    public BeiPin(){}

    public BeiPin( int id,String name,  int number,String type,String brand,String serialnumber, String room,String supply, String date){
        this.id = id;
        this.name = name;
        this.number = number;
        this.type = type;
        this.brand = brand;
        this.serialnumber = serialnumber;
        this.room = room;
        this.supply = supply;
        this.date = date;
    }

    @Override
    public  String toString(){
        return "beipin{" +
                "name=" + name +
                "number=" + number +
                "type=" + type +
                "brand" + brand +
                "serialnumber" + serialnumber +
                "room=" + room +
                "supply=" + supply +
                "date=" + date;
    }

}
