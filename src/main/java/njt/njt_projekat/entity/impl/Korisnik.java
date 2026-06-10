/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import java.util.List;
import njt.njt_projekat.entity.MyEntity;

/**
 *
 * @author Sara
 */
@Entity
@Table(name = "korisnici",
        uniqueConstraints = {  //ne moze da postoji vise redova u tabeli koji imaju istu vr
            @UniqueConstraint(name = "uk_korisnik_korisnickoIme", columnNames = "korisnickoIme"),
            @UniqueConstraint(name = "uk_korisnik_email", columnNames = "email")})
public class Korisnik implements MyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long korisnikId;
    
    private String ime;
    private String prezime;
    
    @Column(nullable=false,length=50)
    private String korisnickoIme;
    
    @Column(nullable=false,length=120)
    private String email;
    
    @Column(nullable=false,length=200)
    private String lozinka;  //cuvamo hash
    
    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=50)
    private Uloga uloga = Uloga.USER;
    
    private String adresa;

    @OneToMany(mappedBy = "korisnik", cascade=CascadeType.ALL)
    private List<Porudzbina> porudzbine;
    
    @Column(nullable=false)
    private boolean enabled=false;

    public Korisnik() {
    }

    public Korisnik(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public List<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(List<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
