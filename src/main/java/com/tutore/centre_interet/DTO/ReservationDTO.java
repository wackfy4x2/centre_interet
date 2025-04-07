package com.tutore.centre_interet.DTO;

import java.util.Date;

public class ReservationDTO {
    private Long id;
    private Long iduser;
    private Date datereservation;
    private Date dateprevu;
    private String service;

    public ReservationDTO() {
    }


    public Long getId() {
        return this.id;
    }

    public Long getIduser() {
        return this.iduser;
    }

    public Date getDatereservation() {
        return this.datereservation;
    }

    public Date getDateprevu() {
        return this.dateprevu;
    }

    public String getService() {
        return this.service;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public void setDatereservation(Date datereservation) {
        this.datereservation = datereservation;
    }

    public void setDateprevu(Date dateprevu) {
        this.dateprevu = dateprevu;
    }

    public void setService(String service) {
        this.service = service;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ReservationDTO)) return false;
        final ReservationDTO other = (ReservationDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$iduser = this.getIduser();
        final Object other$iduser = other.getIduser();
        if (this$iduser == null ? other$iduser != null : !this$iduser.equals(other$iduser)) return false;
        final Object this$datereservation = this.getDatereservation();
        final Object other$datereservation = other.getDatereservation();
        if (this$datereservation == null ? other$datereservation != null : !this$datereservation.equals(other$datereservation))
            return false;
        final Object this$dateprevu = this.getDateprevu();
        final Object other$dateprevu = other.getDateprevu();
        if (this$dateprevu == null ? other$dateprevu != null : !this$dateprevu.equals(other$dateprevu)) return false;
        final Object this$service = this.getService();
        final Object other$service = other.getService();
        if (this$service == null ? other$service != null : !this$service.equals(other$service)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ReservationDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $iduser = this.getIduser();
        result = result * PRIME + ($iduser == null ? 43 : $iduser.hashCode());
        final Object $datereservation = this.getDatereservation();
        result = result * PRIME + ($datereservation == null ? 43 : $datereservation.hashCode());
        final Object $dateprevu = this.getDateprevu();
        result = result * PRIME + ($dateprevu == null ? 43 : $dateprevu.hashCode());
        final Object $service = this.getService();
        result = result * PRIME + ($service == null ? 43 : $service.hashCode());
        return result;
    }

    public String toString() {
        return "ReservationDTO(id=" + this.getId() + ", iduser=" + this.getIduser() + ", datereservation=" + this.getDatereservation() + ", dateprevu=" + this.getDateprevu() + ", service=" + this.getService() + ")";
    }
}
