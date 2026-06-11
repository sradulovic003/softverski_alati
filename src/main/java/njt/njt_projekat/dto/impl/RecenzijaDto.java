package njt.njt_projekat.dto.impl;

import jakarta.validation.constraints.*;
import njt.njt_projekat.dto.Dto;

/**
 * Predstavlja objekat za prenos podataka o recenziji.
 *
 * Koristi se za prijem i slanje podataka o recenziji izmedju
 * klijenta i servera. Sadrzi validaciona pravila za polja
 * koja korisnik unosi.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
public class RecenzijaDto implements Dto {
	
	/**
     * Jedinstveni identifikator recenzije kao Long.
     */
	private Long recenzijaId;

	/**
     * Tekstualni komentar recenzije kao String.
     * Moze imati najvise 500 karaktera.
     */
	@Size(max = 500, message = "Komentar ne sme da sadrzi vise od 500 karaktera")
	private String komentar;

	/**
     * Ocena poslasticare kao Integer.
     * Obavezno polje. Mora biti u opsegu od 1 do 5.
     */
	@NotNull(message = "Niste uneli ocenu!")
	@Min(value = 1, message = "Ocena mora biti najmanje 1")
	@Max(value = 5, message = "Ocena moze biti najvise 5")
	private Integer ocena;

	/**
     * Identifikator korisnika koji je napisao recenziju kao Long.
     * Obavezno polje.
     */
	@NotNull(message = "Nedostaje korisnik")
	private Long korisnikId;

	/**
     * Identifikator poslasticare na koju se recenzija odnosi kao Long.
     * Obavezno polje.
     */
	@NotNull(message = "Nedostaje poslasticara")
	private Long poslasticaraId;

	/**
     * Kreira objekat klase RecenzijaDto sa podrazumevanim (null) vrednostima
     * svih atributa.
     */
	public RecenzijaDto() {
	}

	/**
     * Kreira objekat klase RecenzijaDto sa svim unetim vrednostima atributa.
     *
     * @param recenzijaId Identifikator recenzije.
     * @param komentar Tekstualni komentar recenzije. Moze imati najvise 500 karaktera.
     * @param ocena Ocena poslasticare. Mora biti u opsegu od 1 do 5.
     * @param korisnikId Identifikator korisnika koji je napisao recenziju.
     * @param poslasticaraId Identifikator poslasticare na koju se recenzija odnosi.
     */
	public RecenzijaDto(Long recenzijaId, String komentar, Integer ocena, Long korisnikId, Long poslasticaraId) {
		this.recenzijaId = recenzijaId;
		this.komentar = komentar;
		this.ocena = ocena;
		this.korisnikId = korisnikId;
		this.poslasticaraId = poslasticaraId;
	}

	/**
     * Vraca identifikator recenzije.
     *
     * @return identifikator recenzije kao Long.
     */
	public Long getRecenzijaId() {
		return recenzijaId;
	}

	/**
     * Postavlja identifikator recenzije na unetu vrednost.
     *
     * @param recenzijaId Novi identifikator recenzije.
     */
	public void setRecenzijaId(Long recenzijaId) {
		this.recenzijaId = recenzijaId;
	}

	/**
     * Vraca tekstualni komentar recenzije.
     *
     * @return komentar recenzije kao String.
     */
	public String getKomentar() {
		return komentar;
	}

	 /**
     * Postavlja tekstualni komentar recenzije na unetu vrednost.
     * Komentar moze imati najvise 500 karaktera.
     *
     * @param komentar Novi komentar recenzije.
     */
	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	/**
     * Vraca ocenu poslasticare.
     *
     * @return ocena poslasticare kao Integer.
     */
	public Integer getOcena() {
		return ocena;
	}

	/**
     * Postavlja ocenu poslasticare na unetu vrednost.
     * Ocena mora biti u opsegu od 1 do 5.
     *
     * @param ocena Nova ocena poslasticare.
     */
	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}

	/**
     * Vraca identifikator korisnika koji je napisao recenziju.
     *
     * @return identifikator korisnika kao Long.
     */
	public Long getKorisnikId() {
		return korisnikId;
	}

	/**
     * Postavlja identifikator korisnika koji je napisao recenziju na unetu vrednost.
     * Identifikator korisnika ne sme biti null.
     *
     * @param korisnikId Novi identifikator korisnika.
     */
	public void setKorisnikId(Long korisnikId) {
		this.korisnikId = korisnikId;
	}

	/**
     * Vraca identifikator poslasticare na koju se recenzija odnosi.
     *
     * @return identifikator poslasticare kao Long.
     */
	public Long getPoslasticaraId() {
		return poslasticaraId;
	}

	/**
     * Postavlja identifikator poslasticare na koju se recenzija odnosi na unetu vrednost.
     * Identifikator poslasticare ne sme biti null.
     *
     * @param poslasticaraId Novi identifikator poslasticare.
     */
	public void setPoslasticaraId(Long poslasticaraId) {
		this.poslasticaraId = poslasticaraId;
	}
	
	
}
