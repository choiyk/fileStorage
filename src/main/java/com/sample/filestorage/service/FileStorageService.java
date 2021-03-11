package com.sample.filestorage.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

import com.sample.filestorage.dto.FileInfo;

public interface FileStorageService {
    
    public void init();

    public FileInfo save(MultipartFile file);

    public Resource load(String filename);

    public void delete(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();

}
