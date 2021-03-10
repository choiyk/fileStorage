package com.sample.filestorage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoggingTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Test
    public void ErrorLoggingTest(){
        logger.info("info log test");
        logger.error("test");
    }

}
