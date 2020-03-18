package com.example.bj.mapper;

import com.example.bj.entity.Stock;

import java.util.List;

public interface StockMapper {
    public void Insert(Stock stock);

    public void Update(Stock stock);

    public Stock get(String name);

    public List<Stock> list();


}
