package com.sample.filestorage.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import com.sample.filestorage.dto.ResponseFileInfo;
import com.sample.filestorage.dto.ResponseMessage;
import com.sample.filestorage.service.StoreFileAndDataService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileStorageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private StoreFileAndDataService storeFileAndDataService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file){
        String message;
        try{
            storeFileAndDataService.add(file);
            message = "파일을 업로드 했습니다.";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch(Exception e){
            message = "파일을 업로드할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }        
    }
    
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFileInfo>> getListFiles(){
        try{
            List<ResponseFileInfo> myfiles = storeFileAndDataService.getFiles();
            return ResponseEntity.status(HttpStatus.OK).body(myfiles);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        try{
            Resource file = storeFileAndDataService.getFile(filename);
            return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+filename+"\"").body(file);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/files/f/{id}")
    @ResponseBody
    public ResponseEntity<Resource> getFilebyId(@PathVariable int id){
        try{
            ResponseFileInfo result = storeFileAndDataService.getFile(id);
            ContentDisposition cd = ContentDisposition.builder("attachment").filename(result.getName(), StandardCharsets.UTF_8).build();
            return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CONTENT_DISPOSITION, cd.toString()).body(result.getFile());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/files/f/{id}")
    public ResponseEntity<ResponseMessage> deleteFile(@PathVariable int id){
        String message = "";
        try{
            storeFileAndDataService.remove(id);
            message = "파일을 삭제했습니다.";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch(Exception e){
            message = "파일을 삭제할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

}
