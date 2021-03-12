package com.sample.filestorage.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

import com.sample.filestorage.entity.Myfile;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

public abstract class FileStorageServiceImpl implements FileStorageService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String root = "D:/spring-project/fileStorage_uploads/";
    protected Path path;

    @Override
    public void init(){
        try{
            if(!Files.exists(path))
                Files.createDirectory(path);
        } catch(IOException e){
            logger.error("Can not create directory. e: "+e.getMessage());
            throw new RuntimeException("업로드할 폴더를 만들 수 없습니다.");
        }
    }

    @Override
    public Myfile save(MultipartFile file){
        try{
            Myfile fileInfo = this.makeFileInfo(file);
            Path storedPath = this.path.resolve(fileInfo.getFName());
            Files.copy(file.getInputStream(), storedPath);
            return fileInfo;
        } catch(Exception e){
            logger.error("Can not save file. e: "+e.getMessage());
            throw new RuntimeException("파일 저장 실패! e: "+e.getMessage());
        }
    }

    @Override
    public Resource load(String filename){
        try{
            Path file = path.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return resource;
            }
            else{
                logger.error("Resource doesn't exists or can't read.");
                throw new RuntimeException("파일 불러오기 실패");
            } 
        } catch(MalformedURLException e){
            logger.error("Can not load file. e: "+e.getMessage());
            throw new RuntimeException("e: "+e.getMessage());
        }
    }

    @Override
    public void delete(String filename) {
        try{
            Path file = path.resolve(filename);
            if(Files.exists(file)){
                Files.delete(file);
            }
            else{
                logger.error("File doesn't exist.");
            }
        } catch(Exception e){
            logger.error("Can not delete file. e: "+e.getMessage());
            throw new RuntimeException("e: "+e.getMessage());
        }

    }

    @Override
    public void deleteAll(){
        FileSystemUtils.deleteRecursively(path.toFile());
    }

    @Override
    public Stream<Path> loadAll(){
        try{
            return Files.walk(this.path, 1).filter(path -> !path.equals(this.path)).map(this.path::relativize);
        } catch(Exception e){
            logger.error("Can not load files. e: "+e.getMessage());
            throw new RuntimeException("파일 불러오기 실패");
        }
    }

    abstract public void setPath();

    protected String getRoot(){
        return this.root;
    }

    private Myfile makeFileInfo(MultipartFile file) throws Exception{
        Date date = new Date();
        String ts = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        String type = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = ts+"_"+random.nextInt(99)+"."+type;
        String storedPath = this.path.toString();
        Myfile fileInfo = new Myfile(file.getOriginalFilename(), fileName, file.getSize(), type, storedPath);
        return fileInfo;
    }
    
}
