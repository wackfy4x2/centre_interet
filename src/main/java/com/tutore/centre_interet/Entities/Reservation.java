package com.tutore.centre_interet.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
//@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long iduser;
    @ManyToOne
    @JoinColumn(name = "servicereservation")
    @JsonBackReference
    private Serviceent serviceent;
    private Date datereservation;
    private Date dateprevu;

    public Reservation(Date dateprevu, Date datereservation, Serviceent serviceent, Long iduser, Long id) {
        this.dateprevu = dateprevu;
        this.datereservation = datereservation;
        this.serviceent = serviceent;
        this.iduser = iduser;
        this.id = id;
    }

    public Reservation() {
    }

    public Date getDateprevu() {
        return dateprevu;
    }

    public void setDateprevu(Date dateprevu) {
        this.dateprevu = dateprevu;
    }

    public Date getDatereservation() {
        return datereservation;
    }

    public void setDatereservation(Date datereservation) {
        this.datereservation = datereservation;
    }

    public Serviceent getServiceent() {
        return serviceent;
    }

    public void setServiceent(Serviceent serviceent) {
        this.serviceent = serviceent;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
