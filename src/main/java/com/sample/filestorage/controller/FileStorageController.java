package com.sample.filestorage.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.sample.filestorage.dto.FileInfo;
import com.sample.filestorage.dto.ResponseMessage;
import com.sample.filestorage.service.FileStorageService;
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

    //@Autowired private FileStorageService fileStorageService;
    @Autowired private WinsamFileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file){
        String message = "";
        try{
            fileStorageService.save(file);
            message = file.getOriginalFilename() + " 파일을 업로드 했습니다.";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch(Exception e){
            message = file.getOriginalFilename() + " 파일을 업로드할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles(){
        List<FileInfo> fileInfos = fileStorageService.loadAll().map(path -> {
        String filename = path.getFileName().toString();
        String url = MvcUriComponentsBuilder.fromMethodName(FileStorageController.class, "getFile", path.getFileName().toString()).build().toString();  //url을 로컬 url이 아닌 파일을 다운받을 수 있는 url로 가공
        return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
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
