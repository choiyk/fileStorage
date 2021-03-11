package com.sample.filestorage.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sample.filestorage.dto.FileInfo;
import com.sample.filestorage.entity.Myfile;
import com.sample.filestorage.mapper.MyfileMapper;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public void addMyfile(FileInfo fileInfo){
        Myfile f = new Myfile(fileInfo.getOriginName(), fileInfo.getReName(), fileInfo.getSize(), fileInfo.getType(), fileInfo.getPath().toString());
        myfileMapper.insert(f);
    }

    @Override
    public void removeMyfile(int id){
        myfileMapper.delete(id);
    }

    private long convertByteToLong(byte[] temp){
        String s = temp.toString();
        Long l = Long.parseLong(s);
        return l.longValue();
    }
    
}
