package com.sample.filestorage.dto;

import java.sql.Timestamp;

import org.springframework.core.io.Resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ResponseFileInfo {

    private int id;
    private String name;
    private String url;
    private long size;
    private Timestamp date;
    private Resource file;

}
