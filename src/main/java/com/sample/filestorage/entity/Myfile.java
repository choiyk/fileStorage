package com.sample.filestorage.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Myfile {

    int id;
    String originName;
    String fName;
    int fSize;
    String fType;
    Timestamp fDatetime;
    
}
