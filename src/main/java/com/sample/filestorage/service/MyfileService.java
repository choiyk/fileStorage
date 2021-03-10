package com.sample.filestorage.service;

import java.util.List;

import com.sample.filestorage.mapper.MyfileMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.filestorage.entity.Myfile;

@Service
public class MyfileService {

    @Autowired private MyfileMapper myfileMapper;

    public List<Myfile> getMyFiles(){
        return myfileMapper.findAll();
    }

    public Myfile getMyfile(int id){
        return myfileMapper.findOne(id);
    }

    public int getCount(){
        return myfileMapper.count();
    }

    public void addMyfile(Myfile myfile){
        myfileMapper.insert(myfile);
    }

    public void removeMyfile(int id){
        myfileMapper.delete(id);
    }
    
}
