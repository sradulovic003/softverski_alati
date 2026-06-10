/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.dto.impl;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Sara
 */
public class RegisterRequest {
    @NotBlank @Size(min=3,max=50, message="Ime mora da sadrzim minimum 3 a maksimum 50 karaktera")
    private String ime;
    
    @Size(min=3,max=100, message="Prezime mora da sadrzim minimum 3 a maksimum 100 karaktera")
    private String prezime;
    
    @NotBlank @Size(min=3, max=50, message="Korisničko ime mora da sadrzim minimum 3 a maksimum 50 karaktera")
    private String korisnickoIme;
    
    @NotBlank @Email(message= "Email nije u ispravnom formatu")
    private String email;
    
    @NotBlank @Size(min=6,max=100, message="Lozinka mora da sadrzim minimum 6 a maksimum 6 karaktera")
    private String lozinka;
    
    @NotBlank @Size(min = 5, max = 255, message="Adresa mora da sadrzim minimum 5 a maksimum 255 karaktera")
    private String adresa;

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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    
    
    
    
}
