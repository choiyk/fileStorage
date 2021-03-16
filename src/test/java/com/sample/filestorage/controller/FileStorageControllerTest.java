package com.sample.filestorage.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sample.filestorage.service.MyfileService;
import com.sample.filestorage.service.WinsamFileStorageService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class FileStorageControllerTest {

    @Autowired MockMvc mvc;
    @Autowired private WinsamFileStorageService fileStorageService;
    @Autowired private MyfileService myfileService;

    @Test
    public void uploadTest() throws Exception{
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "hellp file".getBytes());
        this.mvc.perform(multipart("/upload").file(file)).andExpect(status().isOk());
    }

    // @Test
    // public void uploadTransactionTest() throws Exception{
    //     MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "hellp file".getBytes());
        
    //     String message;
    //     Myfile fileInfo = null;
    //     try{
    //         fileInfo = fileStorageService.save(file);
    //         System.out.println("파일 저장 완.");
    //     } catch(Exception e){
    //         message = fileInfo.getOriginName() + " 파일을 업로드할 수 없습니다.";
    //     }

    //     try{
    //         System.out.println("exception!!!!!!!!!!!!!!!");
    //         throw new RuntimeException();
    //     } catch(Exception e){
    //         fileStorageService.delete(fileInfo.getFName());
    //         System.out.println("파일 삭제함");
    //         message = fileInfo.getOriginName() + " 파일을 업로드할 수 없습니다.";
    //     }
    // }

    // @Test
    // public void deleteFileTest(){
    //     String message = "";
    //     Myfile fileInfo = myfileService.getMyfile(12);
    //     try{
    //         //DB 정보 삭제
    //         myfileService.removeMyfile(12);
    //         //파일 삭제
    //         fileStorageService.delete(fileInfo.getFName());
    //         message = fileInfo.getOriginName() + " 파일을 삭제했습니다.";
    //     }catch(Exception e){
    //         message = fileInfo.getOriginName() + " 파일을 삭제할 수 없습니다.";
    //     }
    // }
    
}
