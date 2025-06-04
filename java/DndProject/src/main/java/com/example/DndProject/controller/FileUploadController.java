package com.example.DndProject.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileUploadController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){

        if(file.isEmpty()){
            return new ResponseEntity<>("Please select a file to upload.", HttpStatus.BAD_REQUEST);
        }

        try{
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

            String fileName = Objects.requireNonNull(file.getOriginalFilename());
            Path targetLocation = uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return new ResponseEntity<>("File uploaded successfuly: " + fileName, HttpStatus.OK);
        }catch (IOException ex) {
            return new ResponseEntity<>("Failed to upload file: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
