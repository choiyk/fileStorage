package com.sample.filestorage.service;

import java.util.List;

import com.sample.filestorage.entity.Myfile;

public interface MyfileService {

    public List<Myfile> getMyFiles();

    public Myfile getMyfile(int id);

    public int getCount();

    public void addMyfile(Myfile fileInfo);

    public void removeMyfile(int id);
    
}
