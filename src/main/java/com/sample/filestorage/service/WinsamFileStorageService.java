package com.sample.filestorage.service;

import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class WinsamFileStorageService extends FileStorageServiceImpl{

    @PostConstruct
    public void init(){
        this.setPath();
        super.init();
    }

    @Override
    public void setPath(){
        String root = super.getRoot();
        super.path = Paths.get(root+"winsam");
    }
    
}
