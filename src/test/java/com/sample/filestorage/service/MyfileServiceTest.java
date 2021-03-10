package com.sample.filestorage.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sample.filestorage.entity.Myfile;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MyfileServiceTest {

    @Autowired private MyfileService myfileService;

    @Test
    public void getMyfiles(){
        List<Myfile> myfiles = myfileService.getMyFiles();
        System.out.println("test: getMyFiles");
        for(Myfile file : myfiles)
            System.out.println(file);
    }

    @Test
    public void getMyfile(){
        Myfile myfile = myfileService.getMyfile(1);
        System.out.println("test: getMyfile");
        System.out.println(myfile);
    }

    @Test
    public void count(){
        int num = myfileService.getCount();
        assertEquals(1, num);
    }

    @Test
    public void removeMyfile(){
        myfileService.removeMyfile(1);
        int num = myfileService.getCount();
        assertEquals(0, num);
    }

    @Test
    public void addMyfile(){
        Date date = new Date();
        String ts = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        Myfile myfile = new Myfile();
        myfile.setOriginName("test1");
        myfile.setFName("test1"+"_"+ts);
        myfile.setFSize(123);
        myfile.setFType("jpg");

        myfileService.addMyfile(myfile);
        getMyfiles();
    }
    
}
