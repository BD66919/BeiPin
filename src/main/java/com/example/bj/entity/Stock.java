package com.example.bj.entity;

import javax.persistence.Column;



/**
 * @author Jiangzhendong
 * @Description 当前库存实体类
 *
 */
public class Stock {
    @Column
    String name;

    @Column
    int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Stock(String name, int number){
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString(){ return "name="+name+"number="+number;}

}
