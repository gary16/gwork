
package com.gwork.app.springdemo.basic.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gwork.app.springdemo.basic.entity.Phone;

@Repository
public interface PhoneRepository {

    int selectAllCount();

    List<Phone> selectPhonesByPname(@Param("pnames") String[] pnames);

    int updateByTid(Phone phone);
}
