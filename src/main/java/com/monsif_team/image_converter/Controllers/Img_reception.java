package com.monsif_team.image_converter.Controllers;

import com.monsif_team.image_converter.Services.Img_converter_service;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

@RestController
@RequestMapping(value = "/Img_converter")
public class Img_reception {

    private final Img_converter_service img_converter_service;
    private byte[] file;

    public Img_reception(Img_converter_service img_converter_service) {
        this.img_converter_service = img_converter_service;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    private ResponseEntity<?> converter(
            @RequestPart("file") MultipartFile file ,
            @RequestParam("convert_to") String convert_to,
            HttpServletResponse response
    ) throws IOException {
        byte[] convertedFile= img_converter_service.convertImage(file.getBytes() ,convert_to);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(convertedFile);
        this.file = convertedFile;
        outputStream.flush();
        outputStream.close();
        return ResponseEntity.status(HttpStatus.OK).body(
                null
        );

    }
    @GetMapping("/getImage")
    private ResponseEntity<?> getImage(HttpServletResponse response) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(file);
        outputStream.flush();
        outputStream.close();
        return null;
    }
}
