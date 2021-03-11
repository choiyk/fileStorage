package com.sample.filestorage.service;

import java.util.List;

import com.sample.filestorage.entity.Myfile;
import com.sample.filestorage.mapper.MyfileMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyfileServiceImpl implements MyfileService{

    @Autowired private MyfileMapper myfileMapper;

    @Override
    public List<Myfile> getMyFiles(){
        return myfileMapper.findAll();
    }

    @Override
    public Myfile getMyfile(int id){
        return myfileMapper.findOne(id);
    }

    @Override
    public int getCount(){
        return myfileMapper.count();
    }

    @Override
    public void addMyfile(Myfile myfile){
        myfileMapper.insert(myfile);
    }

    @Override
    public void removeMyfile(int id){
        myfileMapper.delete(id);
    }
    
}
