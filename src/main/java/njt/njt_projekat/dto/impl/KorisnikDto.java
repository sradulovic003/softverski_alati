/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.dto.impl;

import java.util.List;

import jakarta.validation.constraints.*;
import njt.njt_projekat.dto.Dto;
import njt.njt_projekat.entity.impl.Uloga;

/**
 * Predstavlja objekat za prenos podataka o korisniku.
 *
 * Koristi se za prijem i slanje podataka o korisniku izmedju
 * klijenta i servera. Sadrzi validaciona pravila za polja
 * koja korisnik unosi.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
public class KorisnikDto implements Dto {
	
	/**
     * Jedinstveni identifikator korisnika kao Long.
     */
	private Long korisnikId;

	/**
     * Ime korisnika kao String.
     * Ne sme biti prazno i mora imati izmedju 3 i 50 karaktera.
     */
	@NotBlank(message = "Niste uneli ime!")
	@Size(min = 3, max = 50, message = "Ime mora imati izmedju 3 i 50 karaktera")
	private String ime;

	/**
     * Prezime korisnika kao String.
     * Mora imati izmedju 3 i 100 karaktera.
     */
	@Size(min = 3, max = 100, message = "Prezime mora imati izmedju 3 i 100 karaktera")
	private String prezime;

	/**
     * Korisnicko ime korisnika kao String.
     * Ne sme biti prazno i mora imati izmedju 3 i 50 karaktera.
     * Mora biti jedinstveno u sistemu.
     */
	@NotBlank(message = "Niste uneli korisnicko ime!")
	@Size(min = 3, max = 50, message = "Korisnicko ime mora imati izmedju 3 i 50 karaktera")
	private String korisnickoIme;

	/**
     * Email adresa korisnika kao String.
     * Ne sme biti prazna i mora biti u ispravnom formatu.
     * Mora biti jedinstvena u sistemu.
     */
	@NotBlank(message = "Niste uneli email!")
	@Email(message = "Email nije u ispravnom formatu")
	private String email;

	/**
     * Uloga korisnika u sistemu kao enumeracija Uloga.
     * Podrazumevana vrednost je Uloga.USER.
     */
	private Uloga uloga = Uloga.USER;

	/**
     * Adresa korisnika kao String.
     * Mora imati izmedju 5 i 255 karaktera.
     */
	@Size(min = 5, max = 255, message = "Adresa mora imati izmedju 5 i 255 karaktera")
	private String adresa;

	/**
     * Lista porudzbina koje je korisnik kreirao.
     */
	private List<PorudzbinaDto> porudzbine;

	/**
     * Kreira objekat klase KorisnikDto sa podrazumevanim (null) vrednostima
     * svih atributa.
     */
	public KorisnikDto() {
	}

	/**
     * Kreira objekat klase KorisnikDto sa svim unetim vrednostima atributa.
     *
     * @param korisnikId Identifikator korisnika.
     * @param ime Ime korisnika. Ne sme biti prazno i mora imati izmedju 3 i 50 karaktera.
     * @param prezime Prezime korisnika. Mora imati izmedju 3 i 100 karaktera.
     * @param korisnickoIme Korisnicko ime korisnika. Ne sme biti prazno i mora imati izmedju 3 i 50 karaktera.
     * @param email Email adresa korisnika. Ne sme biti prazna i mora biti u ispravnom formatu.
     * @param adresa Adresa korisnika. Mora imati izmedju 5 i 255 karaktera.
     * @param uloga Uloga korisnika u sistemu.
     * @param porudzbine Lista porudzbina korisnika.
     */
	public KorisnikDto(Long korisnikId, String ime, String prezime, String korisnickoIme, String email, String adresa,
			Uloga uloga, List<PorudzbinaDto> porudzbine) {
		this.korisnikId = korisnikId;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.email = email;
		this.adresa = adresa;
		this.uloga = uloga;
		this.porudzbine = porudzbine;
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
     * Ime ne sme biti prazno i mora imati izmedju 3 i 50 karaktera.
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
     * Prezime mora imati izmedju 3 i 100 karaktera.
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
     * Korisnicko ime ne sme biti prazno i mora imati izmedju 3 i 50 karaktera.
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
     * Email ne sme biti prazan i mora biti u ispravnom formatu.
     *
     * @param email Nova email adresa korisnika.
     */
	public void setEmail(String email) {
		this.email = email;
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
     * Adresa mora imati izmedju 5 i 255 karaktera.
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
	public List<PorudzbinaDto> getPorudzbine() {
		return porudzbine;
	}

	/**
     * Postavlja listu porudzbina korisnika na unetu vrednost.
     *
     * @param porudzbine Nova lista porudzbina korisnika.
     */
	public void setPorudzbine(List<PorudzbinaDto> porudzbine) {
		this.porudzbine = porudzbine;
	}

}
