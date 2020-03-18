package com.example.bj.mapper;

import com.example.bj.entity.BeiPin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BeiPinRepository extends JpaRepository<BeiPin,String> {

    @Query(value = "select * from beipin where if(?1 !='',name=?1,1=1) and if(?2 !='',number=?2,1=1) and if(?3 !='',type=?3,1=1)  and if(?4 !='',brand=?4,1=1)  and if(?5 !='',serialnumber=?5,1=1) and if(?6 !='',room=?6,1=1) and if(?7 !='',supply=?7,1=1) and if(?8 !='',date=?8,1=1) ", nativeQuery = true)
    List<BeiPin> select(String name, String number,String type,String brand,String serialnumber, String room, String supply,String date) ;


}
