package com.sample.filestorage.exception;

import com.sample.filestorage.dto.ResponseMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler{

    @ExceptionHandler(MaxUploadSizeExceededException.class)
        public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException e){
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(new ResponseMessage("파일의 사이즈가 너무 큽니다."));
        }
    
}
