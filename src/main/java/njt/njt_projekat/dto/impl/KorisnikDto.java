/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.dto.impl;

import java.util.List;
import njt.njt_projekat.dto.Dto;
import njt.njt_projekat.entity.impl.Uloga;

/**
 *
 * @author Sara
 */
public class KorisnikDto implements Dto {
    private Long korisnikId;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String email; 
    private Uloga uloga = Uloga.USER;
    private String adresa;

    private List<PorudzbinaDto> porudzbine;

    public KorisnikDto() {
    }

    public KorisnikDto(Long korisnikId, String ime, String prezime, String korisnickoIme, String email, String adresa,Uloga uloga, List<PorudzbinaDto> porudzbine) {
        this.korisnikId = korisnikId;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.email = email;
        this.adresa = adresa;
        this.uloga=uloga;
        this.porudzbine = porudzbine;
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

    public List<PorudzbinaDto> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(List<PorudzbinaDto> porudzbine) {
        this.porudzbine = porudzbine;
    }

    

   
    
    
}
