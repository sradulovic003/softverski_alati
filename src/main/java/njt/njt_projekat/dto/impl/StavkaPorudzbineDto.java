/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.dto.impl;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import njt.njt_projekat.dto.Dto;

/**
 * Predstavlja objekat za prenos podataka o stavci porudzbine.
 *
 * Koristi se za prijem i slanje podataka o stavci porudzbine izmedju
 * klijenta i servera. Sadrzi validaciona pravila za polja
 * koja korisnik unosi.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
public class StavkaPorudzbineDto implements Dto{
	
	/**
     * Jedinstveni identifikator stavke porudzbine kao Long.
     * Vrednost se automatski generise od strane baze podataka.
     */
    private Long rb;
    
    /**
     * Cena proizvoda u trenutku kreiranja porudzbine kao Double.
     * Racuna se automatski od strane servisa na osnovu
     * trenutne cene proizvoda.
     */
    private Double cena;
    
    /**
     * Kolicina proizvoda u stavci porudzbine kao Double.
     * Obavezno polje. Mora biti pozitivan broj.
     */
    @NotNull(message="Niste uneli kolicinu!")
    @Positive(message="Kolicina mora biti pozitivan broj")
    private Double kolicina;
    
    /**
     * Ukupan iznos stavke porudzbine kao Double.
     * Racuna se automatski od strane servisa
     * kao proizvod cene i kolicine.
     */
    private Double iznos;
    
    /**
     * Identifikator porudzbine kojoj stavka pripada kao Long.
     * Postavlja se od strane servisa pri kreiranju porudzbine.
     */
    private Long porudzbinaId;
    
    /**
     * Identifikator proizvoda na koji se stavka odnosi kao Long.
     * Obavezno polje.
     */
    @NotNull(message="Nedostaje proizvod")
    private Long proizvodId; 

    /**
     * Kreira objekat klase StavkaPorudzbineDto sa podrazumevanim (null) vrednostima
     * svih atributa.
     */
    public StavkaPorudzbineDto() {
    }

    /**
     * Kreira objekat klase StavkaPorudzbineDto sa svim unetim vrednostima atributa.
     *
     * @param rb Identifikator stavke porudzbine.
     * @param cena Cena proizvoda u trenutku kreiranja porudzbine.
     * @param kolicina Kolicina proizvoda u stavci porudzbine. Mora biti pozitivan broj.
     * @param iznos Ukupan iznos stavke porudzbine.
     * @param porudzbinaId Identifikator porudzbine kojoj stavka pripada.
     * @param proizvodId Identifikator proizvoda na koji se stavka odnosi.
     */
    public StavkaPorudzbineDto(Long rb, Double cena, Double kolicina, Double iznos, Long porudzbinaId, Long proizvodId) {
        this.rb = rb;
        this.cena = cena;
        this.kolicina = kolicina;
        this.iznos = iznos;
        this.porudzbinaId = porudzbinaId;
        this.proizvodId = proizvodId;
    }

    /**
     * Vraca identifikator stavke porudzbine.
     *
     * @return identifikator stavke porudzbine kao Long.
     */
    public Long getRb() {
        return rb;
    }

    /**
     * Postavlja identifikator stavke porudzbine na unetu vrednost.
     *
     * @param rb Novi identifikator stavke porudzbine.
     */
    public void setRb(Long rb) {
        this.rb = rb;
    }

    /**
     * Vraca cenu proizvoda u trenutku kreiranja porudzbine.
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
     * Vraca kolicinu proizvoda u stavci porudzbine.
     *
     * @return kolicina proizvoda kao Double.
     */
    public Double getKolicina() {
        return kolicina;
    }

    /**
     * Postavlja kolicinu proizvoda na unetu vrednost.
     * Kolicina mora biti pozitivan broj.
     *
     * @param kolicina Nova kolicina proizvoda.
     */
    public void setKolicina(Double kolicina) {
        this.kolicina = kolicina;
    }

    /**
     * Vraca ukupan iznos stavke porudzbine.
     *
     * @return ukupan iznos stavke porudzbine kao Double.
     */
    public Double getIznos() {
        return iznos;
    }

    /**
     * Postavlja ukupan iznos stavke porudzbine na unetu vrednost.
     *
     * @param iznos Novi ukupan iznos stavke porudzbine.
     */
    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    /**
     * Vraca identifikator porudzbine kojoj stavka pripada.
     *
     * @return identifikator porudzbine kao Long.
     */
    public Long getPorudzbinaId() {
        return porudzbinaId;
    }

    /**
     * Postavlja identifikator porudzbine kojoj stavka pripada na unetu vrednost.
     *
     * @param porudzbinaId Novi identifikator porudzbine.
     */
    public void setPorudzbinaId(Long porudzbinaId) {
        this.porudzbinaId = porudzbinaId;
    }

    /**
     * Vraca identifikator proizvoda na koji se stavka odnosi.
     *
     * @return identifikator proizvoda kao Long.
     */
    public Long getProizvodId() {
        return proizvodId;
    }

    /**
     * Postavlja identifikator proizvoda na koji se stavka odnosi na unetu vrednost.
     * Identifikator proizvoda ne sme biti null.
     *
     * @param proizvodId Novi identifikator proizvoda.
     */
    public void setProizvodId(Long proizvodId) {
        this.proizvodId = proizvodId;
    }
    
    
}
