package com.sample.filestorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.sample.filestorage.entity.Myfile;

@Mapper
public interface MyfileMapper {

    @Select("SELECT * FROM myfile")
    List<Myfile> findAll();

    @Select("SELECT * FROM myfile WHERE id=#{id}")
    Myfile findOne(int id);

    @Select("SELECT COUNT(*) FROM myfile")
    int count();

    @Insert("INSERT INTO myfile (originName, fName, fSize, fType, storedPath) VALUES (#{originName}, #{fName}, #{fSize}, #{fType}, #{storedPath})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Myfile myfile);

    @Delete("DELETE FROM myfile WHERE id=#{id}")
    void delete(int id);
    
}
