package com.example.bj.mapper;


import com.example.bj.entity.Take;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TakeRepository extends JpaRepository<Take,String> {

    @Query(value = "select * from take where if(?1 !='',name=?1,1=1) and if(?2 !='',number=?2,1=1) and if(?3 !='',peopleName=?3,1=1)  and if(?4 !='',type=?4,1=1)  and if(?5 !='',brand=?5,1=1) and if(?6 !='',serialnumber=?6,1=1) and if(?7 !='',room=?7,1=1) and if(?8 !='',date=?8,1=1) ", nativeQuery = true)
    List<Take> select(String name, String number, String peopleName, String type,String brand, String serialnumber, String room,  String date) ;


}
