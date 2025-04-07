package com.tutore.centre_interet.Services.ServicesImpl;

import com.tutore.centre_interet.Entities.Image;
import com.tutore.centre_interet.Repository.ImageRepository;
import com.tutore.centre_interet.Services.ImageService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ImageServiceImpl.class);
    private ImageRepository imageRepository;
    private final Path fileStorageLocation = Paths.get("centre_interet/public", "centreinteret");

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image update(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image delete(Long id) {
        return imageRepository.deleteImageById(id);
    }

    @Override
    public List<Image> findall() {
        return imageRepository.findAll();
    }

    @Override
    public String generateUniqueFilename(String originalfilename) {
        // Extraire l'extension du fichier
        int extensionIndex = originalfilename.lastIndexOf(".");
        String extension = extensionIndex > -1 ? originalfilename.substring(extensionIndex) : "";

        // Générer un nom de fichier unique avec un UUID
        String uniqueFilename = UUID.randomUUID().toString() + extension;
        return uniqueFilename;
    }

    @Override
    public Image createandsave(MultipartFile multipartFile) {
        Image image = new Image();
        try {
            if (multipartFile.isEmpty()) {
                log.info("aucune image selectionner");
                return image;
            }
            String uniqueFilename = generateUniqueFilename(multipartFile.getOriginalFilename());
            image.setSrc(uniqueFilename);
            Path path = fileStorageLocation.resolve(uniqueFilename);
            if (!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
                log.info("repertoire cree : ", fileStorageLocation);
            }
            log.info("save as :" + path.toAbsolutePath());
            Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            log.info("image enregistrer avec succes");
//            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            imageRepository.save(image);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            log.info("erreur lors de la sauvegarde");
            return image;
        }
    }
}
