package njt.njt_projekat.entity.impl;

import jakarta.persistence.*;
import njt.njt_projekat.entity.MyEntity;

/**
 * Predstavlja recenziju koju korisnik ostavlja za poslasticaru.
 *
 * Svaka recenzija ima jedinstveni identifikator, komentar i ocenu.
 * Recenzija je napisana od strane tacno jednog korisnika (1..1),
 * a jedan korisnik moze napisati vise recenzija (0..*).
 * Recenzija se odnosi na tacno jednu poslasticaru (1..1),
 * a jedna poslasticara moze imati vise recenzija (0..*).
 *
 * @author Sara Radulovic
 * @version 1.0
 */
@Entity
@Table(name = "recenzije")
public class Recenzija implements MyEntity {

	/**
     * Jedinstveni identifikator recenzije kao Long.
     * Vrednost se automatski generise od strane baze podataka.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recenzijaId;
	
	/**
     * Tekstualni komentar recenzije kao String.
     */
	private String komentar;
	
	/**
     * Ocena poslasticare kao Integer.
     */
	private Integer ocena;

	/**
     * Korisnik koji je napisao recenziju.
     * Jedna recenzija je napisana od strane tacno jednog korisnika (1..1),
     * a jedan korisnik moze napisati vise recenzija (0..*).
     */
	@ManyToOne(optional = false)
	@JoinColumn(name = "korisnikId", nullable = false)
	private Korisnik korisnik;

	/**
     * Poslasticara na koju se recenzija odnosi.
     * Jedna recenzija se odnosi na tacno jednu poslasticaru (1..1),
     * a jedna poslasticara moze imati vise recenzija (0..*).
     */
	@ManyToOne(optional = false)
	@JoinColumn(name = "poslasticaraId", nullable = false)
	private Poslasticara poslasticara;

	/**
     * Kreira objekat klase Recenzija sa podrazumevanim (null) vrednostima
     * svih atributa.
     */
	public Recenzija() {
	}

	/**
     * Kreira objekat klase Recenzija sa unetim identifikatorom.
     * Koristi se za kreiranje reference na recenziju
     * bez ucitavanja svih podataka iz baze.
     *
     * @param recenzijaId Identifikator recenzije.
     */
	public Recenzija(Long recenzijaId) {
		super();
		this.recenzijaId = recenzijaId;
	}

	/**
     * Kreira objekat klase Recenzija sa svim unetim vrednostima atributa.
     *
     * @param recenzijaId Identifikator recenzije.
     * @param komentar Tekstualni komentar recenzije.
     * @param ocena Ocena poslasticare.
     * @param korisnik Korisnik koji je napisao recenziju.
     * @param poslasticara Poslasticara na koju se recenzija odnosi.
     */
	public Recenzija(Long recenzijaId, String komentar, Integer ocena, Korisnik korisnik, Poslasticara poslasticara) {
		super();
		this.recenzijaId = recenzijaId;
		this.komentar = komentar;
		this.ocena = ocena;
		this.korisnik = korisnik;
		this.poslasticara = poslasticara;
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
     *
     * @param ocena Nova ocena poslasticare.
     */
	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}

	/**
     * Vraca korisnika koji je napisao recenziju.
     *
     * @return korisnik koji je napisao recenziju kao objekat klase Korisnik.
     */
	public Korisnik getKorisnik() {
		return korisnik;
	}

	/**
     * Postavlja korisnika koji je napisao recenziju na unetu vrednost.
     *
     * @param korisnik Korisnik koji je napisao recenziju.
     */
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	/**
     * Vraca poslasticaru na koju se recenzija odnosi.
     *
     * @return poslasticara na koju se recenzija odnosi kao objekat klase Poslasticara.
     */
	public Poslasticara getPoslasticara() {
		return poslasticara;
	}

	/**
     * Postavlja poslasticaru na koju se recenzija odnosi na unetu vrednost.
     *
     * @param poslasticara Poslasticara na koju se recenzija odnosi.
     */
	public void setPoslasticara(Poslasticara poslasticara) {
		this.poslasticara = poslasticara;
	}

}
