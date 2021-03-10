package com.sample.filestorage;

//import javax.annotation.Resource;

//import com.sample.filestorage.service.FileStorageService;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// public class FilestorageApplication implements CommandLineRunner{

// 	@Resource FileStorageService fileStorageService;

// 	public static void main(String[] args) {
// 		SpringApplication.run(FilestorageApplication.class, args);
// 	}

// 	@Override
// 	public void run(String... args) throws Exception{
// 		fileStorageService.deleteAll();
// 		fileStorageService.init();
// 	}

// }

@SpringBootApplication
public class FilestorageApplication{

	//@Resource FileStorageService fileStorageService;

	public static void main(String[] args) {
		SpringApplication.run(FilestorageApplication.class, args);
	}

}