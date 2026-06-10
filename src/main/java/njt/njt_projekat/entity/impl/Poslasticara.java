/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.entity.impl;

import java.util.List;
import jakarta.persistence.*;
import njt.njt_projekat.entity.MyEntity;
/**
 *
 * @author Sara
 */
@Entity
@Table(name="poslasticare")
public class Poslasticara implements MyEntity{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long poslasticaraId;
    private String naziv;
    private String adresa;
    private String kontakt;
    
    @OneToMany(mappedBy = "poslasticara", cascade=CascadeType.ALL)  //ime atr iz druge kl koji pravi vezu ka ovoj
    private List<Proizvod> proizvodi;

    public Poslasticara() {
    }

    public Poslasticara(Long poslasticaraId, String naziv, String adresa, String kontakt, List<Proizvod> proizvodi) {
        this.poslasticaraId = poslasticaraId;
        this.naziv = naziv;
        this.adresa = adresa;
        this.kontakt = kontakt;
        this.proizvodi = proizvodi;
    }

    public Poslasticara(Long poslasticaraId) {
        this.poslasticaraId =poslasticaraId;
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

    public List<Proizvod> getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(List<Proizvod> proizvodi) {
        this.proizvodi = proizvodi;
    }
    
    
}
