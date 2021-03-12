package com.sample.filestorage.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Myfile {
    
    private int id;
    private String originName;
    private String fName;
    private long fSize;
    private String fType;
    private Timestamp fDatetime;
    private String storedPath;

    public Myfile(String originName, String fName, long fSize, String fType, String storedPath){
        this.originName = originName;
        this.fName = fName;
        this.fSize = fSize;
        this.fType = fType;
        this.storedPath = storedPath;
    }

}
