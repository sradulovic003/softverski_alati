/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.entity.impl;

import jakarta.persistence.*;


/**
 *
 * @author Sara
 */
@Entity
@Table(name="stavke_porudzbine")
public class StavkaPorudzbine {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long rb;
    
    @Column(nullable=false)
    private Double cena;
    
    @Column(nullable=false)
    private Double kolicina;
    
    @Column(nullable=false)
    private Double iznos;
    
    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="porudzbinaId")
    private Porudzbina porudzbina;
    
    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="proizvodId")
    private Proizvod proizvod; 

    public StavkaPorudzbine() {
    }

    public StavkaPorudzbine(Long rb, Double cena, Double kolicina, Double iznos, Porudzbina porudzbina, Proizvod proizvod) {
        this.rb = rb;
        this.cena = cena;
        this.kolicina = kolicina;
        this.iznos = iznos;
        this.porudzbina = porudzbina;
        this.proizvod = proizvod;
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

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }
    
    
}
