/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.dto.impl;

/**
 *
 * @author Sara
 */
public class AuthResponse {  //od servera ka klijentu
    private String token;  //pristup, dozvola
    private KorisnikDto korisnik;

    public AuthResponse() {
    }

    public AuthResponse(String token, KorisnikDto korisnik) {
        this.token = token;
        this.korisnik = korisnik;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public KorisnikDto getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(KorisnikDto korisnik) {
        this.korisnik = korisnik;
    }
    
    
}
