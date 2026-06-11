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
 * Predstavlja objekat za prenos podataka o proizvodu.
 *
 * Koristi se za prijem i slanje podataka o proizvodu izmedju
 * klijenta i servera. Sadrzi validaciona pravila za polja
 * koja korisnik unosi.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
public class ProizvodDto implements Dto{
	
	/**
     * Jedinstveni identifikator proizvoda kao Long.
     * Vrednost se automatski generise od strane baze podataka.
     */
    private Long proizvodId;
    
    /**
     * Naziv proizvoda kao String.
     * Ne sme biti prazno i mora imati izmedju 2 i 100 karaktera.
     */
    @NotBlank(message="Niste uneli naziv!")
    @Size(min = 2, max = 100, message = "Naziv mora imati između 2 i 100 karaktera")
    private String naziv;
    
    /**
     * Cena proizvoda kao Double.
     * Obavezno polje. Mora biti pozitivan broj.
     */
    @NotNull(message="Niste uneli cenu!")
    @Positive(message="Cena mora biti pozitivan broj")
    private Double cena;
    
    /**
     * URL adresa slike proizvoda kao String.
     * Mora biti ispravna URL adresa.
     */
    @URL(message="Slika mora biti link")
    private String imageUrl;
    
    /**
     * Kategorija proizvoda kao enumeracija Kategorija.
     * Obavezno polje.
     */
    @NotNull(message = "Kategorija mora biti izabrana")
    private Kategorija kategorija;
    
    /**
     * Opis proizvoda kao String.
     * Moze imati najvise 500 karaktera.
     */
    @Size(max = 500, message = "Opis moze imati najvise 500 karaktera")
    private String opis;
    
    /**
     * Identifikator poslasticare kojoj proizvod pripada kao Long.
     * Vrednost se postavlja od strane sistema.
     */
    private Long poslasticaraId;
    

    /**
     * Kreira objekat klase ProizvodDto sa podrazumevanim (null) vrednostima
     * svih atributa.
     */
    public ProizvodDto() {
    }

    /**
     * Kreira objekat klase ProizvodDto sa svim unetim vrednostima atributa.
     *
     * @param proizvodId Identifikator proizvoda.
     * @param naziv Naziv proizvoda. Ne sme biti prazno i mora imati izmedju 2 i 100 karaktera.
     * @param cena Cena proizvoda. Mora biti pozitivan broj.
     * @param imageUrl URL adresa slike proizvoda. Mora biti ispravna URL adresa.
     * @param kategorija Kategorija proizvoda.
     * @param opis Opis proizvoda. Moze imati najvise 500 karaktera.
     * @param poslasticaraId Identifikator poslasticare kojoj proizvod pripada.
     */
    public ProizvodDto(Long proizvodId, String naziv, Double cena, String imageUrl, Kategorija kategorija, String opis, Long poslasticaraId) {
        this.proizvodId = proizvodId;
        this.naziv = naziv;
        this.cena = cena;
        this.imageUrl = imageUrl;
        this.kategorija = kategorija;
        this.opis = opis;
        this.poslasticaraId = poslasticaraId;
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
     * Naziv ne sme biti prazno i mora imati izmedju 2 i 100 karaktera.
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
     * Cena mora biti pozitivan broj.
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
     * URL adresa mora biti ispravna.
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
     * Kategorija ne sme biti null.
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
     * Opis moze imati najvise 500 karaktera.
     *
     * @param opis Novi opis proizvoda.
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * Vraca identifikator poslasticare kojoj proizvod pripada.
     *
     * @return identifikator poslasticare kao Long.
     */
    public Long getPoslasticaraId() {
        return poslasticaraId;
    }

    /**
     * Postavlja identifikator poslasticare kojoj proizvod pripada na unetu vrednost.
     *
     * @param poslasticaraId Novi identifikator poslasticare.
     */
    public void setPoslasticaraId(Long poslasticaraId) {
        this.poslasticaraId = poslasticaraId;
    }

  

    
    
    
}
