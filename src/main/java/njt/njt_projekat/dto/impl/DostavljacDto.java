package njt.njt_projekat.dto.impl;

import jakarta.validation.constraints.*;
import njt.njt_projekat.dto.Dto;

/**
 * Predstavlja objekat za prenos podataka o dostavljacu.
 *
 * Koristi se za prijem i slanje podataka o dostavljacu izmedju
 * klijenta i servera. Sadrzi validaciona pravila za polja
 * koja korisnik unosi.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
public class DostavljacDto implements Dto {

	/**
     * Jedinstveni identifikator dostavljaca kao Long.
     */
	private Long dostavljacId;

	/**
     * Ime dostavljaca kao String.
     * Ne sme biti prazno i moze imati najvise 50 karaktera.
     */
	@NotBlank(message = "Niste uneli ime!")
	@Size(max = 50, message = "Ime ne sme da sadrzi vise od 50 karaktera")
	private String ime;

	/**
     * Prezime dostavljaca kao String.
     * Ne sme biti prazno i moze imati najvise 50 karaktera.
     */
	@NotBlank(message = "Niste uneli prezime!")
	@Size(max = 50, message = "Prezime ne sme da sadrzi vise od 50 karaktera")
	private String prezime;

	/**
     * Broj telefona dostavljaca kao String.
     * Ne sme biti prazno, mora imati 6 do 20 karaktera i moze sadrzati
     * cifre, znak +, -, / i razmak.
     */
	@NotBlank(message = "Niste uneli telefon!")
	@Pattern(regexp = "^[+\\d\\s/\\-]{6,20}$", message = "Telefon mora imati 6 do 20 karaktera (cifre, +, -, /, razmak)")
	private String telefon;

	/**
     * Kreira objekat klase DostavljacDto sa podrazumevanim (null) vrednostima
     * svih atributa.
     */
	public DostavljacDto() {
	}

	/**
     * Kreira objekat klase DostavljacDto sa svim unetim vrednostima atributa.
     *
     * @param dostavljacId Identifikator dostavljaca.
     * @param ime Ime dostavljaca. Ne sme biti prazno i moze imati najvise 50 karaktera.
     * @param prezime Prezime dostavljaca. Ne sme biti prazno i moze imati najvise 50 karaktera.
     * @param telefon Broj telefona dostavljaca. Ne sme biti prazno, mora imati 6 do 20 karaktera
     * i moze sadrzati cifre, znak +, -, / i razmak.
     */
	public DostavljacDto(Long dostavljacId, String ime, String prezime, String telefon) {
		this.dostavljacId = dostavljacId;
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
	}

	/**
     * Vraca identifikator dostavljaca.
     *
     * @return identifikator dostavljaca kao Long.
     */
	public Long getDostavljacId() {
		return dostavljacId;
	}

	/**
     * Postavlja identifikator dostavljaca na unetu vrednost.
     *
     * @param dostavljacId Novi identifikator dostavljaca.
     */
	public void setDostavljacId(Long dostavljacId) {
		this.dostavljacId = dostavljacId;
	}

	/**
     * Vraca ime dostavljaca.
     *
     * @return ime dostavljaca kao String.
     */
	public String getIme() {
		return ime;
	}

	/**
     * Postavlja ime dostavljaca na unetu vrednost.
     * Ime ne sme biti prazno i moze imati najvise 50 karaktera.
     *
     * @param ime Novo ime dostavljaca.
     */
	public void setIme(String ime) {
		this.ime = ime;
	}

	/**
     * Vraca prezime dostavljaca.
     *
     * @return prezime dostavljaca kao String.
     */
	public String getPrezime() {
		return prezime;
	}

	/**
     * Postavlja prezime dostavljaca na unetu vrednost.
     * Prezime ne sme biti prazno i moze imati najvise 50 karaktera.
     *
     * @param prezime Novo prezime dostavljaca.
     */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	/**
     * Vraca broj telefona dostavljaca.
     *
     * @return broj telefona dostavljaca kao String.
     */
	public String getTelefon() {
		return telefon;
	}

	/**
     * Postavlja broj telefona dostavljaca na unetu vrednost.
     * Telefon ne sme biti prazno, mora imati 6 do 20 karaktera i moze
     * sadrzati cifre, znak +, -, / i razmak.
     *
     * @param telefon Novi broj telefona dostavljaca.
     */
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

}
