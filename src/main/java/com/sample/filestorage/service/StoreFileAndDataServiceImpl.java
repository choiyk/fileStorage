package com.sample.filestorage.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.sample.filestorage.dto.ResponseFileInfo;
import com.sample.filestorage.entity.Myfile;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StoreFileAndDataServiceImpl implements StoreFileAndDataService{

    @Autowired private WinsamFileStorageService fileStorageService;
    @Autowired private MyfileService myfileService;

    @Override
    @Transactional
    public void add(MultipartFile file){
        //파일 정보 DB 저장
        Myfile fileInfo = this.makeFileInfo(file);
        myfileService.addMyfile(fileInfo);

        //파일 저장
        fileStorageService.save(file, fileInfo.getFName());
    }

    @Override
    @Transactional
    public void remove(int id){
        //DB 정보 삭제
        Myfile fileInfo = myfileService.getMyfile(id);
        myfileService.removeMyfile(id);

        //파일 삭제
        fileStorageService.delete(fileInfo.getFName());
    }

    @Override
    public List<ResponseFileInfo> getFiles(){
        List<ResponseFileInfo> myfiles = myfileService.getMyFiles().stream().map(f -> {
            //String url = MvcUriComponentsBuilder.fromMethodName(FileStorageController.class, "getFile", f.getFName()).build().toString();  //파일을 다운받을 수 있는 url
            return new ResponseFileInfo(f.getId(), f.getOriginName(), "/files/"+f.getFName(), f.getFSize(), f.getFDatetime(), null);
        }).collect(Collectors.toList());

        return myfiles;
    }

    @Override
    public Resource getFile(String filename){
        return fileStorageService.load(filename);
    }

    @Override
    public ResponseFileInfo getFile(int id){
        Myfile fileInfo = myfileService.getMyfile(id);
        Resource file = fileStorageService.load(fileInfo.getFName());
        return new ResponseFileInfo(fileInfo.getId(), fileInfo.getOriginName(), "files/"+fileInfo.getFName(), fileInfo.getFSize(), fileInfo.getFDatetime(), file);
    }

    private Myfile makeFileInfo(MultipartFile file){
        Date date = new Date();
        String ts = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        String type = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = ts+"_"+random.nextInt(99)+"."+type;
        String storedPath = fileStorageService.getRoot();
        Myfile fileInfo = new Myfile(file.getOriginalFilename(), fileName, file.getSize(), type, storedPath);
        return fileInfo;
    }
    
}
