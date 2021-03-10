package com.sample.filestorage.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.filestorage.entity.Mytest;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MytestServiceTest {
    
    @Autowired private MytestService mytestService;

    @Test
    public void getMytests(){
        System.out.println("test");
        List<Mytest> mytests = mytestService.getMytests();
        for(Mytest test : mytests)
            System.out.println(test);
    }

}
