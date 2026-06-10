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
 *
 * @author Sara
 */
public class KorisnikDto implements Dto {
	private Long korisnikId;

	@NotBlank(message = "Niste uneli ime!")
	@Size(min = 3, max = 50, message = "Ime mora imati izmedju 3 i 50 karaktera")
	private String ime;

	@Size(min = 3, max = 100, message = "Prezime mora imati izmedju 3 i 100 karaktera")
	private String prezime;

	@NotBlank(message = "Niste uneli korisnicko ime!")
	@Size(min = 3, max = 50, message = "Korisnicko ime mora imati izmedju 3 i 50 karaktera")
	private String korisnickoIme;

	@NotBlank(message = "Niste uneli email!")
	@Email(message = "Email nije u ispravnom formatu")
	private String email;

	private Uloga uloga = Uloga.USER;

	@Size(min = 5, max = 255, message = "Adresa mora imati izmedju 5 i 255 karaktera")
	private String adresa;

	private List<PorudzbinaDto> porudzbine;

	public KorisnikDto() {
	}

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
