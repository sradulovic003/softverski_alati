package njt.njt_projekat.entity.impl;

import jakarta.persistence.*;
import njt.njt_projekat.entity.MyEntity;

/**
 * Predstavlja dostavljaca koji isporucuje porudzbine korisnicima.
 *
 * Svaki dostavljac ima jedinstveni identifikator, ime, prezime i broj telefona.
 * Jedan dostavljac moze da bude dodeljen vecem broju porudzbina (0..*),
 * a jedna porudzbina moze imati najvise jednog dostavljaca (0..1).
 *
 * @author Sara Radulovic
 * @version 1.0
 */
@Entity
@Table(name="dostavljaci")
public class Dostavljac implements MyEntity{
	
	/**
     * Jedinstveni identifikator dostavljaca kao Long.
     * Vrednost se automatski generise od strane baze podataka.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dostavljacId;
	
	/**
     * Ime dostavljaca kao String.
     */
	private String ime;
	
	/**
     * Prezime dostavljaca kao String.
     */
	private String prezime;
	
	 /**
     * Broj telefona dostavljaca kao String.
     */
	private String telefon;
	
	
	/**
     * Kreira objekat klase Dostavljac sa podrazumevanim (null) vrednostima
     * svih atributa.
     */
	public Dostavljac() {
		
	}

	/**
     * Kreira objekat klase Dostavljac sa unetim identifikatorom.
     * Koristi se za kreiranje reference na dostavljaca
     * bez ucitavanja svih podataka iz baze.
     *
     * @param dostavljacId Identifikator dostavljaca.
     */
	
	public Dostavljac(Long dostavljacId) {
		super();
		this.dostavljacId = dostavljacId;
	}

	/**
     * Kreira objekat klase Dostavljac sa svim unetim vrednostima atributa.
     *
     * @param dostavljacId Identifikator dostavljaca.
     * @param ime Ime dostavljaca.
     * @param prezime Prezime dostavljaca.
     * @param telefon Broj telefona dostavljaca.
     */
	public Dostavljac(Long dostavljacId, String ime, String prezime, String telefon) {
		super();
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
     *
     * @param telefon Novi broj telefona dostavljaca.
     */
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	
}
