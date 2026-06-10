/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.dto.impl;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import njt.njt_projekat.dto.Dto;

/**
 *
 * @author Sara
 */
public class StavkaPorudzbineDto implements Dto{
    private Long rb;
    
    private Double cena;
    
    @NotNull(message="Niste uneli kolicinu!")
    @Positive(message="Kolicina mora biti pozitivan broj")
    private Double kolicina;
    
    private Double iznos;
    
    //@NotNull(message="Nedostaje porudzbina")
    private Long porudzbinaId;
    
    @NotNull(message="Nedostaje proizvod")
    private Long proizvodId; 

    public StavkaPorudzbineDto() {
    }

    public StavkaPorudzbineDto(Long rb, Double cena, Double kolicina, Double iznos, Long porudzbinaId, Long proizvodId) {
        this.rb = rb;
        this.cena = cena;
        this.kolicina = kolicina;
        this.iznos = iznos;
        this.porudzbinaId = porudzbinaId;
        this.proizvodId = proizvodId;
    }

    public Long getRb() {
        return rb;
    }

    public void setRb(Long rb) {
        this.rb = rb;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Double getKolicina() {
        return kolicina;
    }

    public void setKolicina(Double kolicina) {
        this.kolicina = kolicina;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    public Long getPorudzbinaId() {
        return porudzbinaId;
    }

    public void setPorudzbinaId(Long porudzbinaId) {
        this.porudzbinaId = porudzbinaId;
    }

    public Long getProizvodId() {
        return proizvodId;
    }

    public void setProizvodId(Long proizvodId) {
        this.proizvodId = proizvodId;
    }
    
    
}
