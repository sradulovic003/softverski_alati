/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.dto.impl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import njt.njt_projekat.dto.Dto;
import njt.njt_projekat.entity.impl.StatusPorudzbine;

/**
 * Predstavlja objekat za prenos podataka o porudzbini.
 *
 * Koristi se za prijem i slanje podataka o porudzbini izmedju
 * klijenta i servera. Sadrzi validaciona pravila za polja
 * koja korisnik unosi.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
public class PorudzbinaDto implements Dto {

	/**
     * Jedinstveni identifikator porudzbine kao Long.
     * Vrednost se automatski generise od strane baze podataka.
     */
    private Long porudzbinaId;

    /**
     * Ukupan iznos porudzbine kao Double.
     * Racuna se automatski od strane servisa na osnovu stavki porudzbine.
     */
    private Double ukupanIznos;

    /**
     * Datum kreiranja porudzbine kao Date.
     * Automatski se postavlja na trenutni datum pri kreiranju porudzbine.
     */
    private Date datum;

    /**
     * Status porudzbine kao enumeracija StatusPorudzbine.
     * Podrazumevana vrednost je StatusPorudzbine.KREIRANA.
     * Postavlja se automatski od strane servisa pri kreiranju porudzbine.
     */
    private StatusPorudzbine status;

    /**
     * Identifikator korisnika koji je kreirao porudzbinu kao Long.
     * Obavezno polje.
     */
    @NotNull(message = "Nedostaje korisnik")
    private Long korisnikId;

    /**
     * Lista stavki porudzbine.
     * Obavezno polje. Porudzbina mora da sadrzi bar jednu stavku.
     * Svaka stavka se validira na osnovu pravila definisanih
     * u klasi StavkaPorudzbineDto (@Valid).
     */
    @Valid
    @NotEmpty(message = "Porudzbina mora da sadrzi bar jednu stavku")
    private List<StavkaPorudzbineDto> stavkePorudzbine;

    /**
     * Kreira objekat klase PorudzbinaDto sa podrazumevanim (null) vrednostima
     * svih atributa.
     */
    public PorudzbinaDto() {
    }

    /**
     * Kreira objekat klase PorudzbinaDto sa svim unetim vrednostima atributa.
     *
     * @param porudzbinaId Identifikator porudzbine.
     * @param ukupanIznos Ukupan iznos porudzbine.
     * @param datum Datum kreiranja porudzbine.
     * @param status Status porudzbine.
     * @param korisnikId Identifikator korisnika koji je kreirao porudzbinu.
     * @param stavkePorudzbine Lista stavki porudzbine. Mora sadrzati bar jednu stavku.
     */
    public PorudzbinaDto(Long porudzbinaId, Double ukupanIznos, Date datum, StatusPorudzbine status, Long korisnikId, List<StavkaPorudzbineDto> stavkePorudzbine) {
        this.porudzbinaId = porudzbinaId;
        this.ukupanIznos = ukupanIznos;
        this.datum = datum;
        this.status = status;
        this.korisnikId = korisnikId;
        this.stavkePorudzbine = stavkePorudzbine;
    }

    /**
     * Vraca identifikator porudzbine.
     *
     * @return identifikator porudzbine kao Long.
     */
    public Long getPorudzbinaId() {
        return porudzbinaId;
    }

    /**
     * Postavlja identifikator porudzbine na unetu vrednost.
     *
     * @param porudzbinaId Novi identifikator porudzbine.
     */
    public void setPorudzbinaId(Long porudzbinaId) {
        this.porudzbinaId = porudzbinaId;
    }

    /**
     * Vraca ukupan iznos porudzbine.
     *
     * @return ukupan iznos porudzbine kao Double.
     */
    public Double getUkupanIznos() {
        return ukupanIznos;
    }

    /**
     * Postavlja ukupan iznos porudzbine na unetu vrednost.
     *
     * @param ukupanIznos Novi ukupan iznos porudzbine.
     */
    public void setUkupanIznos(Double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    /**
     * Vraca datum kreiranja porudzbine.
     *
     * @return datum kreiranja porudzbine kao Date.
     */
    public Date getDatum() {
        return datum;
    }

    /**
     * Postavlja datum kreiranja porudzbine na unetu vrednost.
     *
     * @param datum Novi datum kreiranja porudzbine.
     */
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    /**
     * Vraca identifikator korisnika koji je kreirao porudzbinu.
     *
     * @return identifikator korisnika kao Long.
     */
    public Long getKorisnikId() {
        return korisnikId;
    }

    /**
     * Postavlja identifikator korisnika koji je kreirao porudzbinu na unetu vrednost.
     * Identifikator korisnika ne sme biti null.
     *
     * @param korisnikId Novi identifikator korisnika.
     */
    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    /**
     * Vraca listu stavki porudzbine.
     *
     * @return lista stavki porudzbine.
     */
    public List<StavkaPorudzbineDto> getStavkePorudzbine() {
        return stavkePorudzbine;
    }

    /**
     * Postavlja listu stavki porudzbine na unetu vrednost.
     * Lista mora sadrzati bar jednu stavku.
     *
     * @param stavkePorudzbine Nova lista stavki porudzbine.
     */
    public void setStavkePorudzbine(List<StavkaPorudzbineDto> stavkePorudzbine) {
        this.stavkePorudzbine = stavkePorudzbine;
    }

    /**
     * Vraca status porudzbine.
     *
     * @return status porudzbine kao enumeracija StatusPorudzbine.
     */
    public StatusPorudzbine getStatus() {
        return status;
    }

    /**
     * Postavlja status porudzbine na unetu vrednost.
     *
     * @param status Novi status porudzbine.
     */
    public void setStatus(StatusPorudzbine status) {
        this.status = status;
    }

}
