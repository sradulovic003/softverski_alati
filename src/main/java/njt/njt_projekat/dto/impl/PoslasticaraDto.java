/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.dto.impl;

import jakarta.validation.constraints.*;
import java.util.List;
import njt.njt_projekat.dto.Dto;

/**
 *
 * @author Sara
 */
public class PoslasticaraDto implements Dto {

    private Long poslasticaraId;

    @NotBlank(message = "Niste uneli naziv!")
    @Size(max = 100, message = "Naziv ne sme da sadrzi više od 100 karaktera")
    private String naziv;

    @NotBlank(message = "Niste uneli adresu!")
    @Size(min = 2, max = 200, message = "Adresa mora imati između 2 i 200 karaktera")
    private String adresa;

    @NotBlank(message = "Niste uneli kontakt!")
    @Pattern(
            regexp = "^(?=(?:[^\\d]*\\d){6,15}[^\\d]*$)[+\\d\\s/\\-\\u2011]+$",
            message = "Broj telefona mora sadržati od 6 do 15 cifara, opcionalno sa +, ‑, / i razmacima."
    )
    private String kontakt;

    private List<ProizvodDto> proizvodi;

    public PoslasticaraDto() {
    }

    public PoslasticaraDto(Long poslasticaraId, String naziv, String adresa, String kontakt, List<ProizvodDto> proizvodi) {
        this.poslasticaraId = poslasticaraId;
        this.naziv = naziv;
        this.adresa = adresa;
        this.kontakt = kontakt;
        this.proizvodi = proizvodi;
    }

    public Long getPoslasticaraId() {
        return poslasticaraId;
    }

    public void setPoslasticaraId(Long poslasticaraId) {
        this.poslasticaraId = poslasticaraId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public List<ProizvodDto> getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(List<ProizvodDto> proizvodi) {
        this.proizvodi = proizvodi;
    }

}
