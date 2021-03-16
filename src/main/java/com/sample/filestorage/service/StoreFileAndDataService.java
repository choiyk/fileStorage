package com.sample.filestorage.service;

import java.util.List;

import com.sample.filestorage.dto.ResponseFileInfo;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StoreFileAndDataService{

    public void add(MultipartFile file);

    public void remove(int id);

    public List<ResponseFileInfo> getFiles();

    public Resource getFile(String filename);

    public ResponseFileInfo getFile(int id);
    
}
