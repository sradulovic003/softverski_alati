package njt.njt_projekat.dto.impl;

import jakarta.validation.constraints.*;
import njt.njt_projekat.dto.Dto;

/**
 * Predstavlja objekat za prenos podataka o kuponu.
 *
 * Koristi se za prijem i slanje podataka o kuponu izmedju
 * klijenta i servera. Sadrzi validaciona pravila za polja
 * koja korisnik unosi.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
public class KuponDto implements Dto {

	/**
     * Jedinstveni identifikator kupona kao Long.
     */
	private Long kuponId;

	/**
     * Jedinstveni kod kupona kao String.
     * Ne sme biti prazno i moze imati najvise 30 karaktera.
     */
	@NotBlank(message = "Niste uneli kod kupona!")
	@Size(max = 30, message = "Kod ne sme da sadrzi vise od 30 karaktera")
	private String kod;

	/**
     * Procenat popusta koji kupon obezbeduje kao Double.
     * Obavezno polje. Mora biti pozitivan broj.
     */
	@NotNull(message = "Niste uneli popust!")
	@Positive(message = "Popust mora biti pozitivan broj")
	private Double popust;

	/**
     * Status iskoriscenja kupona kao boolean.
     * Vrednost true oznacava da je kupon vec iskoriscen,
     * a false da kupon jos nije iskoriscen.
     */
	private boolean iskoriscen;

	/**
     * Identifikator korisnika kome kupon pripada kao Long.
     * Obavezno polje.
     */
	@NotNull(message = "Nedostaje korisnik")
	private Long korisnikId;

	/**
     * Identifikator porudzbine kojoj je kupon dodeljen kao Long.
     * Moze biti null ako kupon nije dodeljen ni jednoj porudzbini.
     */
	private Long porudzbinaId;

	/**
     * Kreira objekat klase KuponDto sa podrazumevanim (null) vrednostima
     * svih atributa.
     */
	public KuponDto() {
	}

	/**
     * Kreira objekat klase KuponDto sa svim unetim vrednostima atributa.
     *
     * @param kuponId Identifikator kupona.
     * @param kod Jedinstveni kod kupona. Ne sme biti prazno i moze imati najvise 30 karaktera.
     * @param popust Procenat popusta koji kupon obezbeduje. Mora biti pozitivan broj.
     * @param iskoriscen Status iskoriscenja kupona.
     * @param korisnikId Identifikator korisnika kome kupon pripada.
     * @param porudzbinaId Identifikator porudzbine kojoj je kupon dodeljen,
     * ili null ako kupon nije dodeljen ni jednoj porudzbini.
     */
	public KuponDto(Long kuponId, String kod, Double popust, boolean iskoriscen, Long korisnikId, Long porudzbinaId) {
		this.kuponId = kuponId;
		this.kod = kod;
		this.popust = popust;
		this.iskoriscen = iskoriscen;
		this.korisnikId = korisnikId;
		this.porudzbinaId = porudzbinaId;
	}

	/**
     * Vraca identifikator kupona.
     *
     * @return identifikator kupona kao Long.
     */
	public Long getKuponId() {
		return kuponId;
	}

	/**
     * Postavlja identifikator kupona na unetu vrednost.
     *
     * @param kuponId Novi identifikator kupona.
     */
	public void setKuponId(Long kuponId) {
		this.kuponId = kuponId;
	}

	/**
     * Vraca kod kupona.
     *
     * @return kod kupona kao String.
     */
	public String getKod() {
		return kod;
	}

	/**
     * Postavlja kod kupona na unetu vrednost.
     * Kod ne sme biti prazan i moze imati najvise 30 karaktera.
     *
     * @param kod Novi kod kupona.
     */
	public void setKod(String kod) {
		this.kod = kod;
	}

	/**
     * Vraca procenat popusta koji kupon obezbeduje.
     *
     * @return procenat popusta kao Double.
     */
	public Double getPopust() {
		return popust;
	}

	/**
     * Postavlja procenat popusta kupona na unetu vrednost.
     * Popust mora biti pozitivan broj.
     *
     * @param popust Novi procenat popusta.
     */
	public void setPopust(Double popust) {
		this.popust = popust;
	}

	/**
     * Vraca status iskoriscenja kupona.
     *
     * @return true ako je kupon iskoriscen, false ako nije.
     */
	public boolean isIskoriscen() {
		return iskoriscen;
	}

	/**
     * Postavlja status iskoriscenja kupona na unetu vrednost.
     *
     * @param iskoriscen Novi status iskoriscenja kupona.
     */
	public void setIskoriscen(boolean iskoriscen) {
		this.iskoriscen = iskoriscen;
	}

	/**
     * Vraca identifikator korisnika kome kupon pripada.
     *
     * @return identifikator korisnika kao Long.
     */
	public Long getKorisnikId() {
		return korisnikId;
	}

	/**
     * Postavlja identifikator korisnika kome kupon pripada na unetu vrednost.
     * Identifikator korisnika ne sme biti null.
     *
     * @param korisnikId Novi identifikator korisnika.
     */
	public void setKorisnikId(Long korisnikId) {
		this.korisnikId = korisnikId;
	}

	/**
     * Vraca identifikator porudzbine kojoj je kupon dodeljen.
     *
     * @return identifikator porudzbine kao Long,
     * ili null ako kupon nije dodeljen ni jednoj porudzbini.
     */
	public Long getPorudzbinaId() {
		return porudzbinaId;
	}

	/**
     * Postavlja identifikator porudzbine kojoj je kupon dodeljen na unetu vrednost.
     *
     * @param porudzbinaId Novi identifikator porudzbine,
     * ili null ako se veza uklanja.
     */
	public void setPorudzbinaId(Long porudzbinaId) {
		this.porudzbinaId = porudzbinaId;
	}

}
