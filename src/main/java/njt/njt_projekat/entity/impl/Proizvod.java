/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.entity.impl;

import jakarta.persistence.*;
import njt.njt_projekat.entity.MyEntity;

/**
 *
 * @author Sara
 */
@Entity
@Table(name="proizvodi")
public class Proizvod implements MyEntity{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long proizvodId;
    private String naziv;
    private Double cena;
    private String imageUrl;
    
    @Enumerated(EnumType.STRING)
    private Kategorija kategorija;
    private String opis;
    
    @ManyToOne
    @JoinColumn(name="poslasticaraId")
    private Poslasticara poslasticara;
    

    public Proizvod() {
    }

    public Proizvod(Long proizvodId, String naziv, Double cena, String imageUrl, Kategorija kategorija, String opis, Poslasticara poslasticara) {
        this.proizvodId = proizvodId;
        this.naziv = naziv;
        this.cena = cena;
        this.imageUrl = imageUrl;
        this.kategorija = kategorija;
        this.opis = opis;
        this.poslasticara = poslasticara;
    }

    public Proizvod(Long proizvodId) {
        this.proizvodId=proizvodId;
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

    public Poslasticara getPoslasticara() {
        return poslasticara;
    }

    public void setPoslasticara(Poslasticara poslasticara) {
        this.poslasticara = poslasticara;
    }    
}
