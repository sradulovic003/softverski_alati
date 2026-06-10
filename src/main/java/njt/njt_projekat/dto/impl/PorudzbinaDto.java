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
 *
 * @author Sara
 */
public class PorudzbinaDto implements Dto {

    private Long porudzbinaId;

    private Double ukupanIznos;

    private Date datum;

    private StatusPorudzbine status;

    @NotNull(message = "Nedostaje korisnik")
    private Long korisnikId;

    @Valid
    @NotEmpty(message = "Porudzbina mora da sadrzi bar jednu stavku")
    private List<StavkaPorudzbineDto> stavkePorudzbine;

    public PorudzbinaDto() {
    }

    public PorudzbinaDto(Long porudzbinaId, Double ukupanIznos, Date datum, StatusPorudzbine status, Long korisnikId, List<StavkaPorudzbineDto> stavkePorudzbine) {
        this.porudzbinaId = porudzbinaId;
        this.ukupanIznos = ukupanIznos;
        this.datum = datum;
        this.status = status;
        this.korisnikId = korisnikId;
        this.stavkePorudzbine = stavkePorudzbine;
    }

    public Long getPorudzbinaId() {
        return porudzbinaId;
    }

    public void setPorudzbinaId(Long porudzbinaId) {
        this.porudzbinaId = porudzbinaId;
    }

    public Double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(Double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public List<StavkaPorudzbineDto> getStavkePorudzbine() {
        return stavkePorudzbine;
    }

    public void setStavkePorudzbine(List<StavkaPorudzbineDto> stavkePorudzbine) {
        this.stavkePorudzbine = stavkePorudzbine;
    }

    public StatusPorudzbine getStatus() {
        return status;
    }

    public void setStatus(StatusPorudzbine status) {
        this.status = status;
    }

}
