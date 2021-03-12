package com.sample.filestorage.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.sample.filestorage.dto.ResponseFileInfo;
import com.sample.filestorage.dto.ResponseMessage;
import com.sample.filestorage.entity.Myfile;
import com.sample.filestorage.service.MyfileService;
import com.sample.filestorage.service.WinsamFileStorageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Controller
public class FileStorageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private WinsamFileStorageService fileStorageService;
    @Autowired private MyfileService myfileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file){
        String message;
        Myfile fileInfo = null;
        //파일 저장
        try{
            fileInfo = fileStorageService.save(file);
            logger.debug("파일 저장 완료.");
        } catch(Exception e){
            message = fileInfo.getOriginName() + " 파일을 업로드할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
        //파일 정보 DB 저장
        try{
            myfileService.addMyfile(fileInfo);
            logger.debug("파일 DB 저장 완료.");
            message = fileInfo.getOriginName() + " 파일을 업로드 했습니다.";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch(Exception e){
            //DB 에러 시 저장했던 파일 삭제
            fileStorageService.delete(fileInfo.getFName());
            message = fileInfo.getOriginName() + " 파일을 업로드할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
        
    }
    
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFileInfo>> getListFiles(){
        List<ResponseFileInfo> myfiles = myfileService.getMyFiles().stream().map(f -> {
            String url = MvcUriComponentsBuilder.fromMethodName(FileStorageController.class, "getFile", f.getFName()).build().toString();  //파일을 다운받을 수 있는 url
            return new ResponseFileInfo(f.getId(), f.getOriginName(), url, f.getFSize(), f.getFDatetime());
        }).collect(Collectors.toList());
        
        return ResponseEntity.status(HttpStatus.OK).body(myfiles);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        try{
            Resource file = fileStorageService.load(filename);
            return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"").body(file);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
}
