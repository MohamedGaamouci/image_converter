package com.monsif_team.image_converter.Services;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class Img_converter_service {
    public byte[] convertImage(byte[] inputImageBytes, String outputFormat) throws IOException {
        // Load the input image
        BufferedImage inputImage = ImageIO.read(new ByteArrayInputStream(inputImageBytes));

        // Create a ByteArrayOutputStream to hold the converted image bytes
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Write the converted image to the ByteArrayOutputStream
        ImageIO.write(inputImage, outputFormat, byteArrayOutputStream);

        // Return the converted image bytes
        return byteArrayOutputStream.toByteArray();
    }
}
