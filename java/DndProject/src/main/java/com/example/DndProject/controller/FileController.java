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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileController {

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

    @RequestMapping(path = "/backgrounds", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllBackgroundImages() {
        Path folderPath = Paths.get(uploadDir);

        if(!Files.exists(folderPath)  || !Files.isDirectory(folderPath)){
            System.err.println("Backgrounds folder does not exist or is not a directory" + uploadDir);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(List.of("Backgrounds folder not found of accessible"));
        }

        List<String> imageFileNames;
        try(Stream<Path> paths = Files.walk(folderPath, 1)) {
            imageFileNames = paths
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(name -> name.toLowerCase().endsWith(".jpg") ||
                                    name.toLowerCase().endsWith(".jpeg")||
                                    name.toLowerCase().endsWith(".png") ||
                                    name.toLowerCase().endsWith(".gif"))
                    .toList();
        }catch(IOException e){
            System.err.println("Error reading background images folder: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of("Error reading images."));
        }

        return ResponseEntity.ok(imageFileNames);
    }

}
