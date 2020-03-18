package com.example.bj.mapper;

import com.example.bj.entity.BeiPin;

public interface BeiPinMapper {
    public void Insert(BeiPin beipin);

    public void Delete(int id);

    public BeiPin Select(int id);
}
