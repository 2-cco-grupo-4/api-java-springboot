package com.example.picmejava.controller;

import com.example.picmejava.service.s3.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    @Autowired
    private S3Service service;

    @PostMapping("/putObject")
    public ResponseEntity<Void> putObject(@RequestParam("file") MultipartFile file){
        service.putObject("picme-s3", file.getName());
        return ResponseEntity.status(200).build();
    }
}
