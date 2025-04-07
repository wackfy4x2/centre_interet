package com.tutore.centre_interet.Services;

import com.tutore.centre_interet.DTO.ContactDTO;
import com.tutore.centre_interet.DTO.ImageDTO;
import com.tutore.centre_interet.Entities.Contact;
import com.tutore.centre_interet.Entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    public Image save(Image image);
    public Image update(Image image);
    public Image delete(Long id);
    public List<Image> findall();
    public String generateUniqueFilename(String originalfilename);
    public Image createandsave(MultipartFile multipartFile);
}
