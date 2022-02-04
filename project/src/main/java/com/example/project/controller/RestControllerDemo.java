package com.example.project.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/rest_img")
public class RestControllerDemo {

    @GetMapping(
            value = "/static/image/{userPicture}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    byte[] getImageForEveryone(
            @PathVariable("userPicture") String userPicture
    ) {
        try{
            File file = new File("C:\\Users\\Ramazan\\Downloads\\" +
                    "project\\src\\main\\" +
                    "resources\\img\\" + userPicture);
            InputStream in = new FileInputStream(file);
            return IOUtils.toByteArray(in);
        } catch (FileNotFoundException fex){
            System.out.println("file not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
