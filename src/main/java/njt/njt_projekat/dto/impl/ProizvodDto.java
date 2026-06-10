/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.dto.impl;

import jakarta.validation.constraints.*;
import njt.njt_projekat.dto.Dto;
import njt.njt_projekat.entity.impl.Kategorija;
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author Sara
 */
public class ProizvodDto implements Dto{
    private Long proizvodId;
    
    @NotBlank(message="Niste uneli naziv!")
    @Size(min = 2, max = 100, message = "Naziv mora imati između 2 i 100 karaktera")
    private String naziv;
    
    @NotNull(message="Niste uneli cenu!")
    @Positive(message="Cena mora biti pozitivan broj")
    private Double cena;
    
    @URL(message="Slika mora biti link")
    private String imageUrl;
    
    @NotNull(message = "Kategorija mora biti izabrana")
    private Kategorija kategorija;
    
    @Size(max = 500, message = "Opis moze imati najvise 500 karaktera")
    private String opis;
    
    private Long poslasticaraId;
    

    public ProizvodDto() {
    }

    public ProizvodDto(Long proizvodId, String naziv, Double cena, String imageUrl, Kategorija kategorija, String opis, Long poslasticaraId) {
        this.proizvodId = proizvodId;
        this.naziv = naziv;
        this.cena = cena;
        this.imageUrl = imageUrl;
        this.kategorija = kategorija;
        this.opis = opis;
        this.poslasticaraId = poslasticaraId;
    }

   
 

    public Long getProizvodId() {
        return proizvodId;
    }

    public void setProizvodId(Long proizvodId) {
        this.proizvodId = proizvodId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Long getPoslasticaraId() {
        return poslasticaraId;
    }

    public void setPoslasticaraId(Long poslasticaraId) {
        this.poslasticaraId = poslasticaraId;
    }

  

    
    
    
}
