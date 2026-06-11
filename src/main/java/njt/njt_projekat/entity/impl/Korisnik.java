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
 * Predstavlja korisnika softverskog sistema za online narucivanje iz poslasticare.
 *
 * Svaki korisnik ima jedinstveni identifikator, ime, prezime, korisnicko ime,
 * email, lozinku, ulogu i adresu. Korisnicko ime i email su jedinstveni u sistemu.
 * Jedan korisnik moze kreirati vise porudzbina (0..*),
 * a jedna porudzbina pripada tacno jednom korisniku (1..1).
 * Jedan korisnik moze posedovati vise kupona (0..*),
 * a jedan kupon pripada tacno jednom korisniku (1..1).
 * Jedan korisnik moze napisati vise recenzija (0..*),
 * a jedna recenzija je napisana od strane tacno jednog korisnika (1..1).
 *
 * @author Sara Radulovic
 * @version 1.0
 */
@Entity
@Table(name = "korisnici",
        uniqueConstraints = {  //ne moze da postoji vise redova u tabeli koji imaju istu vr
            @UniqueConstraint(name = "uk_korisnik_korisnickoIme", columnNames = "korisnickoIme"),
            @UniqueConstraint(name = "uk_korisnik_email", columnNames = "email")})
public class Korisnik implements MyEntity {
	
	/**
     * Jedinstveni identifikator korisnika kao Long.
     * Vrednost se automatski generise od strane baze podataka.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long korisnikId;
    
    /**
     * Ime korisnika kao String.
     */
    private String ime;
    
    /**
     * Prezime korisnika kao String.
     */
    private String prezime;
    
    /**
     * Korisnicko ime korisnika kao String.
     * Mora biti jedinstveno u sistemu. Ne sme biti null
     * i moze imati najvise 50 karaktera.
     */
    @Column(nullable=false,length=50)
    private String korisnickoIme;
    
    /**
     * Email adresa korisnika kao String.
     * Mora biti jedinstvena u sistemu. Ne sme biti null
     * i moze imati najvise 120 karaktera.
     */
    @Column(nullable=false,length=120)
    private String email;
    
    /**
     * Hesovana lozinka korisnika kao String.
     * U bazi se cuva iskljucivo hesovana vrednost lozinke.
     * Ne sme biti null i moze imati najvise 200 karaktera.
     */
    @Column(nullable=false,length=200)
    private String lozinka;  //cuvamo hash
    
    /**
     * Uloga korisnika u sistemu kao enumeracija Uloga.
     * Podrazumevana vrednost je Uloga.USER.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=50)
    private Uloga uloga = Uloga.USER;
    
    /**
     * Adresa korisnika kao String.
     */
    private String adresa;

    /**
     * Lista porudzbina koje je korisnik kreirao.
     * Jedan korisnik moze kreirati vise porudzbina (0..*),
     * a jedna porudzbina pripada tacno jednom korisniku (1..1).
     */
    @OneToMany(mappedBy = "korisnik", cascade=CascadeType.ALL)
    private List<Porudzbina> porudzbine;
    
    /**
     * Status aktivacije naloga korisnika kao boolean.
     * Vrednost true oznacava da je nalog aktiviran,
     * a false da nalog jos nije aktiviran.
     * Podrazumevana vrednost je false.
     */
    @Column(nullable=false)
    private boolean enabled=false;

    /**
     * Kreira objekat klase Korisnik sa podrazumevanim (null) vrednostima
     * svih atributa.
     */
    public Korisnik() {
    }

    /**
     * Kreira objekat klase Korisnik sa unetim identifikatorom.
     * Koristi se za kreiranje reference na korisnika
     * bez ucitavanja svih podataka iz baze.
     *
     * @param korisnikId Identifikator korisnika.
     */
    public Korisnik(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    /**
     * Vraca identifikator korisnika.
     *
     * @return identifikator korisnika kao Long.
     */
    public Long getKorisnikId() {
        return korisnikId;
    }

    /**
     * Postavlja identifikator korisnika na unetu vrednost.
     *
     * @param korisnikId Novi identifikator korisnika.
     */
    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    /**
     * Vraca ime korisnika.
     *
     * @return ime korisnika kao String.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime korisnika na unetu vrednost.
     *
     * @param ime Novo ime korisnika.
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * Vraca prezime korisnika.
     *
     * @return prezime korisnika kao String.
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime korisnika na unetu vrednost.
     *
     * @param prezime Novo prezime korisnika.
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     * Vraca korisnicko ime korisnika.
     *
     * @return korisnicko ime korisnika kao String.
     */
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * Postavlja korisnicko ime korisnika na unetu vrednost.
     *
     * @param korisnickoIme Novo korisnicko ime korisnika.
     */
    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    /**
     * Vraca email adresu korisnika.
     *
     * @return email adresa korisnika kao String.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Postavlja email adresu korisnika na unetu vrednost.
     *
     * @param email Nova email adresa korisnika.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Vraca hesovanu lozinku korisnika.
     *
     * @return hesovana lozinka korisnika kao String.
     */
    public String getLozinka() {
        return lozinka;
    }

    /**
     * Postavlja hesovanu lozinku korisnika na unetu vrednost.
     *
     * @param lozinka Nova hesovana lozinka korisnika.
     */
    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    /**
     * Vraca ulogu korisnika u sistemu.
     *
     * @return uloga korisnika kao enumeracija Uloga.
     */
    public Uloga getUloga() {
        return uloga;
    }

    /**
     * Postavlja ulogu korisnika u sistemu na unetu vrednost.
     *
     * @param uloga Nova uloga korisnika.
     */
    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    /**
     * Vraca adresu korisnika.
     *
     * @return adresa korisnika kao String.
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * Postavlja adresu korisnika na unetu vrednost.
     *
     * @param adresa Nova adresa korisnika.
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    /**
     * Vraca listu porudzbina koje je korisnik kreirao.
     *
     * @return lista porudzbina korisnika.
     */
    public List<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    /**
     * Postavlja listu porudzbina korisnika na unetu vrednost.
     *
     * @param porudzbine Nova lista porudzbina korisnika.
     */
    public void setPorudzbine(List<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    /**
     * Vraca status aktivacije naloga korisnika.
     *
     * @return true ako je nalog aktiviran, false ako nije.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Postavlja status aktivacije naloga korisnika na unetu vrednost.
     *
     * @param enabled Novi status aktivacije naloga korisnika.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
