package com.sample.filestorage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.filestorage.entity.Mytest;
import com.sample.filestorage.mapper.MytestMapper;

@Service
public class MytestService {

    @Autowired private MytestMapper mytestMapper;

    public List<Mytest> getMytests(){
        return mytestMapper.findAll();
    }
    
}
