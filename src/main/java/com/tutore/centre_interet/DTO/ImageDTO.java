package com.tutore.centre_interet.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class ImageDTO {
    private Long id;
    private String src;

    public ImageDTO(Long id, String src) {
        this.id = id;
        this.src = src;
    }

    public ImageDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
