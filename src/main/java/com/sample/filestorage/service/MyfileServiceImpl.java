package com.sample.filestorage.service;

import java.util.List;

import com.sample.filestorage.entity.Myfile;
import com.sample.filestorage.mapper.MyfileMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyfileServiceImpl implements MyfileService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
    public void addMyfile(Myfile fileInfo){
        myfileMapper.insert(fileInfo);
        logger.debug("myfileMapper insert success!");
    }

    @Override
    public void removeMyfile(int id){
        myfileMapper.delete(id);
        logger.debug("myfileMapper delete success!");
    }

}
