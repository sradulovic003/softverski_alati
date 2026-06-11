/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.dto.impl;

import jakarta.validation.constraints.*;
import java.util.List;
import njt.njt_projekat.dto.Dto;

/**
 * Predstavlja objekat za prenos podataka o poslasticari.
 *
 * Koristi se za prijem i slanje podataka o poslasticari izmedju
 * klijenta i servera. Sadrzi validaciona pravila za polja
 * koja korisnik unosi.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
public class PoslasticaraDto implements Dto {

	/**
     * Jedinstveni identifikator poslasticare kao Long.
     * Vrednost se automatski generise od strane baze podataka.
     */
    private Long poslasticaraId;

    /**
     * Naziv poslasticare kao String.
     * Ne sme biti prazno i mora imati izmedju 2 i 100 karaktera.
     */
    @NotBlank(message = "Niste uneli naziv!")
    @Size(min = 2, max = 100, message = "Naziv mora imati izmedju 2 i 100 karaktera")
    private String naziv;

    /**
     * Adresa poslasticare kao String.
     * Ne sme biti prazna i mora imati izmedju 2 i 200 karaktera.
     */
    @NotBlank(message = "Niste uneli adresu!")
    @Size(min = 2, max = 200, message = "Adresa mora imati između 2 i 200 karaktera")
    private String adresa;

    /**
     * Kontakt podatak poslasticare kao String.
     * Ne sme biti prazno. Mora sadrzati od 6 do 15 cifara,
     * opcionalno sa +, -, / i razmacima.
     */
    @NotBlank(message = "Niste uneli kontakt!")
    @Pattern(
            regexp = "^(?=(?:[^\\d]*\\d){6,15}[^\\d]*$)[+\\d\\s/\\-\\u2011]+$",
            message = "Broj telefona mora sadržati od 6 do 15 cifara, opcionalno sa +, -, / i razmacima."
    )
    private String kontakt;

    /**
     * Lista proizvoda koje poslasticara nudi.
     * Jedna poslasticara moze imati vise proizvoda (0..*),
     * a jedan proizvod pripada tacno jednoj poslasticari (1..1).
     */
    private List<ProizvodDto> proizvodi;

    /**
     * Kreira objekat klase PoslasticaraDto sa podrazumevanim (null) vrednostima
     * svih atributa.
     */
    public PoslasticaraDto() {
    }

    /**
     * Kreira objekat klase PoslasticaraDto sa svim unetim vrednostima atributa.
     *
     * @param poslasticaraId Identifikator poslasticare.
     * @param naziv Naziv poslasticare. Ne sme biti prazno i moze imati najvise 100 karaktera.
     * @param adresa Adresa poslasticare. Ne sme biti prazna i mora imati izmedju 2 i 200 karaktera.
     * @param kontakt Kontakt podatak poslasticare. Mora sadrzati od 6 do 15 cifara,
     * opcionalno sa +, -, / i razmacima.
     * @param proizvodi Lista proizvoda koje poslasticara nudi.
     */
    public PoslasticaraDto(Long poslasticaraId, String naziv, String adresa, String kontakt, List<ProizvodDto> proizvodi) {
        this.poslasticaraId = poslasticaraId;
        this.naziv = naziv;
        this.adresa = adresa;
        this.kontakt = kontakt;
        this.proizvodi = proizvodi;
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
     * Naziv ne sme biti prazno i mora imati izmedju 2 i 100 karaktera.
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
     * Adresa ne sme biti prazna i mora imati izmedju 2 i 200 karaktera.
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
     * Kontakt mora sadrzati od 6 do 15 cifara,
     * opcionalno sa +, -, / i razmacima.
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
    public List<ProizvodDto> getProizvodi() {
        return proizvodi;
    }

    /**
     * Postavlja listu proizvoda poslasticare na unetu vrednost.
     *
     * @param proizvodi Nova lista proizvoda poslasticare.
     */
    public void setProizvodi(List<ProizvodDto> proizvodi) {
        this.proizvodi = proizvodi;
    }

}
