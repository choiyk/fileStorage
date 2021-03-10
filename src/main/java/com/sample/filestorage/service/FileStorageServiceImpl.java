package com.sample.filestorage.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

//TO-DO Error Logging
//TO-DO 파일 업로드 정보 DB 저장
@Service
public class FileStorageServiceImpl implements FileStorageService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Path root = Paths.get("D:/fileStorage_uploads");

    @Override
    public void init(){
        try{
            Files.createDirectory(root);
        } catch(IOException e){
            logger.error("Can not create directory. e: "+e.getMessage());
            throw new RuntimeException("업로드할 폴더를 만들 수 없습니다.");
        }
    }

    @Override
    public void save(MultipartFile file){
        try{
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch(Exception e){
            logger.error("Can not save file. e: "+e.getMessage());
            throw new RuntimeException("파일 저장 실패! e: "+e.getMessage());
        }
    }

    @Override
    public Resource load(String filename){
        try{
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());  //URL을 기준으로 리소스를 읽어들임

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
    public void deleteAll(){
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll(){
        try{
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch(Exception e){
            logger.error("Can not load files. e: "+e.getMessage());
            throw new RuntimeException("파일 불러오기 실패");
        }
    }

    @Override
    public Path getRoot(){
        return this.root;
    }
    
}
