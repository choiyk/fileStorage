package com.sample.filestorage.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FileStorageServiceTest {

    @Autowired private WinsamFileStorageService fileStorageService;

    @Test
    public void howWorkFilesWork(){
        //Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        final Path root = Paths.get("uploads");
        System.out.println("root path: "+root.toUri().toString());
        try{
            System.out.println("여기까진 오긴 하는거니..?");
            Files.walk(root, 1).forEach(path -> System.out.println("1. file: "+path.toUri().toString()));
            Files.walk(root, 1).filter(path -> !path.equals(root)).forEach(path -> System.out.println("2. file: "+path.toUri().toString()));  //결과에 root와 같은 url가 포함되어 있음. 걜 빼기 위해서 filtering
            Files.walk(root, 1).filter(path -> !path.equals(root)).map(root::relativize).forEach(path -> System.out.println("3. file: "+path.toUri().toString()));  //url이 /uploads/파일명 -> /파일명 으로 바뀜
        } catch(Exception e){
            throw new RuntimeException("파일을 불러올 수 없습니다.");
        }
    }

    @Test
    public void delete(){
        // this.setPath();
        // super.init();
        fileStorageService.delete("test.txt");
    }

}
