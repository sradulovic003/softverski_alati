/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.entity.impl;

import java.util.List;
import jakarta.persistence.*;
import njt.njt_projekat.entity.MyEntity;

/**
 * Predstavlja poslasticaru u sistemu za online narucivanje.
 *
 * Svaka poslasticara ima jedinstveni identifikator, naziv, adresu i kontakt.
 * Jedna poslasticara moze imati vise proizvoda (0..*),
 * a jedan proizvod pripada tacno jednoj poslasticari (1..1).
 * Jedna poslasticara moze imati vise recenzija (0..*),
 * a jedna recenzija se odnosi na tacno jednu poslasticaru (1..1).
 *
 * @author Sara Radulovic
 * @version 1.0
 */
@Entity
@Table(name="poslasticare")
public class Poslasticara implements MyEntity{
	
	/**
     * Jedinstveni identifikator poslasticare kao Long.
     * Vrednost se automatski generise od strane baze podataka.
     */
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long poslasticaraId;
    
    /**
     * Naziv poslasticare kao String.
     */
    private String naziv;
    
    /**
     * Adresa poslasticare kao String.
     */
    private String adresa;
    
    /**
     * Kontakt podatak poslasticare kao String.
     */
    private String kontakt;
    
    /**
     * Lista proizvoda koje poslasticara nudi.
     * Jedna poslasticara moze imati vise proizvoda (0..*),
     * a jedan proizvod pripada tacno jednoj poslasticari (1..1).
     * Sve operacije (dodavanje, izmena, brisanje) se automatski
     * primenjuju i na proizvode poslasticare (CascadeType.ALL).
     */
    @OneToMany(mappedBy = "poslasticara", cascade=CascadeType.ALL)  
    private List<Proizvod> proizvodi;

    /**
     * Kreira objekat klase Poslasticara sa podrazumevanim (null) vrednostima
     * svih atributa.
     */
    public Poslasticara() {
    }

    /**
     * Kreira objekat klase Poslasticara sa svim unetim vrednostima atributa.
     *
     * @param poslasticaraId Identifikator poslasticare.
     * @param naziv Naziv poslasticare.
     * @param adresa Adresa poslasticare.
     * @param kontakt Kontakt podatak poslasticare.
     * @param proizvodi Lista proizvoda koje poslasticara nudi.
     */
    public Poslasticara(Long poslasticaraId, String naziv, String adresa, String kontakt, List<Proizvod> proizvodi) {
        this.poslasticaraId = poslasticaraId;
        this.naziv = naziv;
        this.adresa = adresa;
        this.kontakt = kontakt;
        this.proizvodi = proizvodi;
    }

    /**
     * Kreira objekat klase Poslasticara sa unetim identifikatorom.
     * Koristi se za kreiranje reference na poslasticaru
     * bez ucitavanja svih podataka iz baze.
     *
     * @param poslasticaraId Identifikator poslasticare.
     */
    public Poslasticara(Long poslasticaraId) {
        this.poslasticaraId =poslasticaraId;
    }
   
    /**
     * Vraca identifikator poslasticare.
     *
     * @return identifikator poslasticare kao Long.
     */
    public Long getPoslasticaraId() {
        return poslasticaraId;
    }

    /**
     * Postavlja identifikator poslasticare na unetu vrednost.
     *
     * @param poslasticaraId Novi identifikator poslasticare.
     */
    public void setPoslasticaraId(Long poslasticaraId) {
        this.poslasticaraId = poslasticaraId;
    }

    /**
     * Vraca naziv poslasticare.
     *
     * @return naziv poslasticare kao String.
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv poslasticare na unetu vrednost.
     *
     * @param naziv Novi naziv poslasticare.
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * Vraca adresu poslasticare.
     *
     * @return adresa poslasticare kao String.
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * Postavlja adresu poslasticare na unetu vrednost.
     *
     * @param adresa Nova adresa poslasticare.
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    /**
     * Vraca kontakt podatak poslasticare.
     *
     * @return kontakt podatak poslasticare kao String.
     */
    public String getKontakt() {
        return kontakt;
    }

    /**
     * Postavlja kontakt podatak poslasticare na unetu vrednost.
     *
     * @param kontakt Novi kontakt podatak poslasticare.
     */
    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    /**
     * Vraca listu proizvoda koje poslasticara nudi.
     *
     * @return lista proizvoda poslasticare.
     */
    public List<Proizvod> getProizvodi() {
        return proizvodi;
    }

    /**
     * Postavlja listu proizvoda poslasticare na unetu vrednost.
     *
     * @param proizvodi Nova lista proizvoda poslasticare.
     */
    public void setProizvodi(List<Proizvod> proizvodi) {
        this.proizvodi = proizvodi;
    }
    
    
}
