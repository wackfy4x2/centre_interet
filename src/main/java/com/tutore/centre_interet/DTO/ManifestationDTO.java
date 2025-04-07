package com.tutore.centre_interet.DTO;

public class ManifestationDTO {
    private Long id;
    private Long iduser;
    private String libelle;

    public ManifestationDTO() {
    }


    public Long getId() {
        return this.id;
    }

    public Long getIduser() {
        return this.iduser;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ManifestationDTO)) return false;
        final ManifestationDTO other = (ManifestationDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$iduser = this.getIduser();
        final Object other$iduser = other.getIduser();
        if (this$iduser == null ? other$iduser != null : !this$iduser.equals(other$iduser)) return false;
        final Object this$libelle = this.getLibelle();
        final Object other$libelle = other.getLibelle();
        if (this$libelle == null ? other$libelle != null : !this$libelle.equals(other$libelle)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ManifestationDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $iduser = this.getIduser();
        result = result * PRIME + ($iduser == null ? 43 : $iduser.hashCode());
        final Object $libelle = this.getLibelle();
        result = result * PRIME + ($libelle == null ? 43 : $libelle.hashCode());
        return result;
    }

    public String toString() {
        return "ManifestationDTO(id=" + this.getId() + ", iduser=" + this.getIduser() + ", libelle=" + this.getLibelle() + ")";
    }
}
