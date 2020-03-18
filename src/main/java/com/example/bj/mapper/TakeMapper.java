package com.example.bj.mapper;

import com.example.bj.entity.BeiPin;
import com.example.bj.entity.Take;

public interface TakeMapper {
    public void Insert(Take take);

    public void Delete(int id);

    public Take Select(int id);
}
