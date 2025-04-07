package com.tutore.centre_interet.Controller;

import com.tutore.centre_interet.Entities.Image;
import com.tutore.centre_interet.Services.ServicesImpl.ImageServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/image")
public class ImageController {
    private ImageServiceImpl imageService;

    public ImageController(ImageServiceImpl imageService) {
        this.imageService = imageService;
    }

    @PostMapping("")
    public Map<String, Object> add(@RequestBody Image image) {
        Image image1 = imageService.save(image);
        Map<String, Object> response = new HashMap<>();
        if (image1 == null) {
            response.put("success", false);
        } else {
            response.put("success", true);
        }
        return response;
    }

    @PostMapping("create")
    public Map<String, Object> createandread(@RequestBody MultipartFile image) {
        Image image1 = imageService.createandsave(image);
        Map<String, Object> response = new HashMap<>();
        if (image1 == null) {
            response.put("success", false);
        } else {
            response.put("success", true);
        }
        return response;
    }

    @GetMapping("")
    public Map<String, Object> getAll() {
        List<Image> images = imageService.findall();
        Map<String, Object> response = new HashMap<>();
        if (response == null) {
            response.put("success", false);
        } else {
            response.put("success", true);
            response.put("list", images);
        }
        return response;
    }
}
