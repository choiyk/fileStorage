package com.sample.filestorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sample.filestorage.entity.Mytest;

@Mapper
public interface MytestMapper {

    @Select("SELECT * FROM mytest")
    List<Mytest> findAll();
    
}
