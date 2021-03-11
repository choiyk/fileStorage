package com.sample.filestorage.dto;

import java.nio.file.Path;

import lombok.Data;

@Data
public class FileInfo {

    private final String originName;
    private final String reName;
    private final Path path;
    private final long size;
    private final String type;
    
}
