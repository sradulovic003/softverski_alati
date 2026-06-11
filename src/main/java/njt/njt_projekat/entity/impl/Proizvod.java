/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.entity.impl;

import jakarta.persistence.*;
import njt.njt_projekat.entity.MyEntity;

/**
 * Predstavlja proizvod koji se nalazi u ponudi poslasticare.
 *
 * Svaki proizvod ima jedinstveni identifikator, naziv, cenu, URL slike,
 * kategoriju i opis. Jedan proizvod pripada tacno jednoj poslasticari (1..1),
 * a jedna poslasticara moze imati vise proizvoda (0..*).
 * Jedan proizvod moze se nalaziti u vise stavki porudzbine (0..*),
 * a jedna stavka porudzbine odnosi se na tacno jedan proizvod (1..1).
 *
 * @author Sara Radulovic
 * @version 1.0
 */
@Entity
@Table(name="proizvodi")
public class Proizvod implements MyEntity{
	
	/**
     * Jedinstveni identifikator proizvoda kao Long.
     * Vrednost se automatski generise od strane baze podataka.
     */
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long proizvodId;
    
    /**
     * Naziv proizvoda kao String.
     */
    private String naziv;
    
    /**
     * Cena proizvoda kao Double.
     */
    private Double cena;
    
    /**
     * URL adresa slike proizvoda kao String.
     */
    private String imageUrl;
    
    /**
     * Kategorija proizvoda kao enumeracija Kategorija.
     */
    @Enumerated(EnumType.STRING)
    private Kategorija kategorija;
    
    /**
     * Opis proizvoda kao String.
     */
    private String opis;
    
    /**
     * Poslasticara kojoj proizvod pripada.
     * Jedan proizvod pripada tacno jednoj poslasticari (1..1),
     * a jedna poslasticara moze imati vise proizvoda (0..*).
     */
    @ManyToOne
    @JoinColumn(name="poslasticaraId")
    private Poslasticara poslasticara;
    

    /**
     * Kreira objekat klase Proizvod sa podrazumevanim (null) vrednostima
     * svih atributa.
     */
    public Proizvod() {
    }

    /**
     * Kreira objekat klase Proizvod sa svim unetim vrednostima atributa.
     *
     * @param proizvodId Identifikator proizvoda.
     * @param naziv Naziv proizvoda.
     * @param cena Cena proizvoda.
     * @param imageUrl URL adresa slike proizvoda.
     * @param kategorija Kategorija proizvoda.
     * @param opis Opis proizvoda.
     * @param poslasticara Poslasticara kojoj proizvod pripada.
     */
    public Proizvod(Long proizvodId, String naziv, Double cena, String imageUrl, Kategorija kategorija, String opis, Poslasticara poslasticara) {
        this.proizvodId = proizvodId;
        this.naziv = naziv;
        this.cena = cena;
        this.imageUrl = imageUrl;
        this.kategorija = kategorija;
        this.opis = opis;
        this.poslasticara = poslasticara;
    }

    /**
     * Kreira objekat klase Proizvod sa unetim identifikatorom.
     * Koristi se za kreiranje reference na proizvod
     * bez ucitavanja svih podataka iz baze.
     *
     * @param proizvodId Identifikator proizvoda.
     */
    public Proizvod(Long proizvodId) {
        this.proizvodId=proizvodId;
    }

    /**
     * Vraca identifikator proizvoda.
     *
     * @return identifikator proizvoda kao Long.
     */
    public Long getProizvodId() {
        return proizvodId;
    }

    /**
     * Postavlja identifikator proizvoda na unetu vrednost.
     *
     * @param proizvodId Novi identifikator proizvoda.
     */
    public void setProizvodId(Long proizvodId) {
        this.proizvodId = proizvodId;
    }

    /**
     * Vraca naziv proizvoda.
     *
     * @return naziv proizvoda kao String.
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv proizvoda na unetu vrednost.
     *
     * @param naziv Novi naziv proizvoda.
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * Vraca cenu proizvoda.
     *
     * @return cena proizvoda kao Double.
     */
    public Double getCena() {
        return cena;
    }

    /**
     * Postavlja cenu proizvoda na unetu vrednost.
     *
     * @param cena Nova cena proizvoda.
     */
    public void setCena(Double cena) {
        this.cena = cena;
    }

    /**
     * Vraca URL adresu slike proizvoda.
     *
     * @return URL adresa slike proizvoda kao String.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Postavlja URL adresu slike proizvoda na unetu vrednost.
     *
     * @param imageUrl Nova URL adresa slike proizvoda.
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Vraca kategoriju proizvoda.
     *
     * @return kategorija proizvoda kao enumeracija Kategorija.
     */
    public Kategorija getKategorija() {
        return kategorija;
    }

    /**
     * Postavlja kategoriju proizvoda na unetu vrednost.
     *
     * @param kategorija Nova kategorija proizvoda.
     */
    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    /**
     * Vraca opis proizvoda.
     *
     * @return opis proizvoda kao String.
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Postavlja opis proizvoda na unetu vrednost.
     *
     * @param opis Novi opis proizvoda.
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * Vraca poslasticaru kojoj proizvod pripada.
     *
     * @return poslasticara kojoj proizvod pripada kao objekat klase Poslasticara.
     */
    public Poslasticara getPoslasticara() {
        return poslasticara;
    }

    /**
     * Postavlja poslasticaru kojoj proizvod pripada na unetu vrednost.
     *
     * @param poslasticara Poslasticara kojoj proizvod pripada.
     */
    public void setPoslasticara(Poslasticara poslasticara) {
        this.poslasticara = poslasticara;
    }    
}
