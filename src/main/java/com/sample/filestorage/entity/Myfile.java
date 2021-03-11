package com.sample.filestorage.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Myfile {

    private int id;
    private final String originName;
    private final String fName;
    private final int fSize;
    private final String fType;
    private Timestamp fDatetime;
    private final String storedPath;
    
}
