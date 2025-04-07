package com.tutore.centre_interet.Repository;

import com.tutore.centre_interet.Entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    public Image findImageById(Long id);
    public Image findImageBySrc(String src);
    public Image deleteImageById(Long id);
}
