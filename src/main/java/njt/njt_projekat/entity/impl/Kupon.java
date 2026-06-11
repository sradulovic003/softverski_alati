package njt.njt_projekat.entity.impl;

import jakarta.persistence.*;
import njt.njt_projekat.entity.MyEntity;

/**
 * Predstavlja kupon za popust koji korisnik moze da iskoristi pri placanju porudzbine.
 *
 * Svaki kupon ima jedinstveni identifikator, kod, procenat popusta i status iskoriscenja.
 * Kupon pripada tacno jednom korisniku (1..1), a moze biti iskoriscen
 * za najvise jednu porudzbinu (0..1).
 *
 * @author Sara Radulovic
 * @version 1.0
 */
@Entity
@Table(name = "kuponi")
public class Kupon implements MyEntity {

	 /**
     * Jedinstveni identifikator kupona kao Long.
     * Vrednost se automatski generise od strane baze podataka.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long kuponId;
	
	/**
     * Jedinstveni kod kupona kao String.
     */
	private String kod;
	
	/**
     * Procenat popusta koji kupon obezbeduje kao Double.
     */
	private Double popust;
	
	/**
     * Status iskoriscenja kupona kao boolean.
     * Vrednost true oznacava da je kupon vec iskoriscen,
     * a false da kupon jos nije iskoriscen.
     */
	private boolean iskoriscen;

	/**
	 * Korisnik kome kupon pripada.
	 * Jedan kupon pripada tacno jednom korisniku (1..1),
	 * a jedan korisnik moze posedovati vise kupona (0..*).
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "korisnikId", nullable = false)
	private Korisnik korisnik;

	/**
	 * Porudzbina za koju je kupon iskoriscen.
	 * Jedan kupon moze biti iskoriscen za najvise jednu porudzbinu (0..1),
	 * a jedna porudzbina moze imati najvise jedan kupon (0..1).
	 */
	@OneToOne
	@JoinColumn(name = "porudzbinaId")
	private Porudzbina porudzbina;

	
	/**
     * Kreira objekat klase Kupon sa podrazumevanim (null) vrednostima
     * svih atributa.
     */
	public Kupon() {
	}

	
	/**
     * Kreira objekat klase Kupon sa unetim identifikatorom.
     * Koristi se za kreiranje reference na kupon
     * bez ucitavanja svih podataka iz baze.
     *
     * @param kuponId Identifikator kupona.
     */
	public Kupon(Long kuponId) {
		super();
		this.kuponId = kuponId;
	}

	
	/**
     * Kreira objekat klase Kupon sa svim unetim vrednostima atributa.
     *
     * @param kuponId Identifikator kupona.
     * @param kod Jedinstveni kod kupona.
     * @param popust Procenat popusta koji kupon obezbeduje.
     * @param iskoriscen Status iskoriscenja kupona.
     * @param korisnik Korisnik kome kupon pripada.
     * @param porudzbina Porudzbina kojoj je kupon dodeljen.
     */
	public Kupon(Long kuponId, String kod, Double popust, boolean iskoriscen, Korisnik korisnik,
			Porudzbina porudzbina) {
		super();
		this.kuponId = kuponId;
		this.kod = kod;
		this.popust = popust;
		this.iskoriscen = iskoriscen;
		this.korisnik = korisnik;
		this.porudzbina = porudzbina;
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
     * Vraca korisnika kome kupon pripada.
     *
     * @return korisnik kome kupon pripada kao objekat klase Korisnik.
     */
	public Korisnik getKorisnik() {
		return korisnik;
	}

	/**
     * Postavlja korisnika kome kupon pripada na unetu vrednost.
     *
     * @param korisnik Korisnik kome kupon pripada.
     */
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	/**
     * Vraca porudzbinu kojoj je kupon dodeljen.
     *
     * @return porudzbina kojoj je kupon dodeljen kao objekat klase Porudzbina,
     * ili null ako kupon nije dodeljen ni jednoj porudzbini.
     */
	public Porudzbina getPorudzbina() {
		return porudzbina;
	}

	/**
     * Postavlja porudzbinu kojoj je kupon dodeljen na unetu vrednost.
     *
     * @param porudzbina Porudzbina kojoj je kupon dodeljen.
     */
	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}

}
