/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.entity.impl;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Predstavlja porudzbinu koju korisnik kreira u sistemu.
 *
 * Svaka porudzbina ima jedinstveni identifikator, ukupan iznos, datum kreiranja,
 * status i listu stavki. Jedna porudzbina pripada tacno jednom korisniku (1..1),
 * a jedan korisnik moze kreirati vise porudzbina (0..*).
 * Jedna porudzbina moze imati najvise jednog dostavljaca (0..1),
 * a jedan dostavljac moze isporuciti vise porudzbina (0..*).
 * Jedna porudzbina moze imati najvise jedan kupon (0..1),
 * a jedan kupon moze biti iskoriscen za najvise jednu porudzbinu (0..1).
 * Jedna porudzbina sadrzi jednu ili vise stavki (1..*),
 * a jedna stavka pripada tacno jednoj porudzbini (1..1).
 *
 * @author Sara Radulovic
 * @version 1.0
 */
@Entity
@Table(name = "porudzbine")
public class Porudzbina {
	
	/**
     * Jedinstveni identifikator porudzbine kao Long.
     * Vrednost se automatski generise od strane baze podataka.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long porudzbinaId;

	/**
	 * Ukupan iznos porudzbine kao Double.
	 * Racuna se automatski od strane servisa na osnovu stavki porudzbine.
	 * Ne sme biti null.
	 */
	@Column(nullable = false)
	private Double ukupanIznos;

	/**
	 * Datum kreiranja porudzbine kao Date.
	 * Automatski se postavlja na trenutni datum pri kreiranju porudzbine.
	 * Ne sme biti null.
	 */
	@Column(nullable = false)
	private Date datum = new Date();

	/**
	 * Status porudzbine kao enumeracija StatusPorudzbine.
	 * Podrazumevana vrednost je StatusPorudzbine.KREIRANA.
	 * Ne sme biti null i moze imati najvise 20 karaktera.
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private StatusPorudzbine status = StatusPorudzbine.KREIRANA;

	/**
	 * Korisnik koji je kreirao porudzbinu.
	 * Jedna porudzbina pripada tacno jednom korisniku (1..1),
	 * a jedan korisnik moze kreirati vise porudzbina (0..*).
	 * Podaci o korisniku se ne ucitavaju iz baze zajedno sa
	 * porudzbinom, nego tek kada se eksplicitno 
	 * pozove geter getKorisnik() (FetchType.LAZY).
	 */
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnikId", nullable = false)
	private Korisnik korisnik;

	/**
	 * Dostavljac koji isporucuje porudzbinu.
	 * Jedna porudzbina moze imati najvise jednog dostavljaca (0..1),
	 * a jedan dostavljac moze isporuciti vise porudzbina (0..*).
	 * Podaci o dostavljacu se ne ucitavaju iz baze zajedno sa
	 * porudzbinom, nego tek kada se eksplicitno 
	 * pozove geter getDostavljac() (FetchType.LAZY).
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dostavljacId")
	private Dostavljac dostavljac;

	/**
	 * Lista stavki porudzbine.
	 * Jedna porudzbina sadrzi jednu ili vise stavki (1..*),
	 * a jedna stavka pripada tacno jednoj porudzbini (1..1).
	 * Sve operacije (dodavanje, izmena, brisanje) se automatski
	 * primenjuju na stavke (CascadeType.ALL).
	 * Stavke koje vise nisu povezane sa porudzbinom se
	 * automatski brisu iz baze (orphanRemoval = true).
	 */
	@OneToMany(mappedBy = "porudzbina", cascade = CascadeType.ALL, orphanRemoval = true) // uklanja sirocice
	private List<StavkaPorudzbine> stavkePorudzbine = new ArrayList<>();

	/**
     * Kreira objekat klase Porudzbina sa podrazumevanim vrednostima atributa.
     * Datum se postavlja na trenutni datum, a status na StatusPorudzbine.KREIRANA.
     */
	public Porudzbina() {
	}

	/**
     * Kreira objekat klase Porudzbina sa unetim identifikatorom.
     * Koristi se za kreiranje reference na porudzbinu
     * bez ucitavanja svih podataka iz baze.
     *
     * @param porudzbinaId Identifikator porudzbine.
     */
	public Porudzbina(Long porudzbinaId) {
		this.porudzbinaId = porudzbinaId;
	}

	/**
     * Kreira objekat klase Porudzbina sa svim unetim vrednostima atributa.
     *
     * @param porudzbinaId Identifikator porudzbine.
     * @param ukupanIznos Ukupan iznos porudzbine.
     * @param korisnik Korisnik koji je kreirao porudzbinu.
     * @param datum Datum kreiranja porudzbine.
     * @param status Status porudzbine.
     * @param stavkePorudzbine Lista stavki porudzbine.
     */
	public Porudzbina(Long porudzbinaId, Double ukupanIznos, Korisnik korisnik, Date datum, StatusPorudzbine status,
			List<StavkaPorudzbine> stavkePorudzbine) {
		this.porudzbinaId = porudzbinaId;
		this.ukupanIznos = ukupanIznos;
		this.korisnik = korisnik;
		this.datum = datum;
		this.status = status;
		this.stavkePorudzbine = stavkePorudzbine;
	}

	/**
     * Vraca identifikator porudzbine.
     *
     * @return identifikator porudzbine kao Long.
     */
	public Long getPorudzbinaId() {
		return porudzbinaId;
	}

	 /**
     * Postavlja identifikator porudzbine na unetu vrednost.
     *
     * @param porudzbinaId Novi identifikator porudzbine.
     */
	public void setPorudzbinaId(Long porudzbinaId) {
		this.porudzbinaId = porudzbinaId;
	}

	/**
     * Vraca ukupan iznos porudzbine.
     *
     * @return ukupan iznos porudzbine kao Double.
     */
	public Double getUkupanIznos() {
		return ukupanIznos;
	}

	/**
     * Postavlja ukupan iznos porudzbine na unetu vrednost.
     *
     * @param ukupanIznos Novi ukupan iznos porudzbine.
     */
	public void setUkupanIznos(Double ukupanIznos) {
		this.ukupanIznos = ukupanIznos;
	}

	/**
     * Vraca datum kreiranja porudzbine.
     *
     * @return datum kreiranja porudzbine kao Date.
     */
	public Date getDatum() {
		return datum;
	}

	/**
     * Postavlja datum kreiranja porudzbine na unetu vrednost.
     *
     * @param datum Novi datum kreiranja porudzbine.
     */
	public void setDatum(Date datum) {
		this.datum = datum;
	}

	/**
     * Vraca korisnika koji je kreirao porudzbinu.
     *
     * @return korisnik koji je kreirao porudzbinu kao objekat klase Korisnik.
     */
	public Korisnik getKorisnik() {
		return korisnik;
	}

	/**
     * Postavlja korisnika koji je kreirao porudzbinu na unetu vrednost.
     *
     * @param korisnik Korisnik koji je kreirao porudzbinu.
     */
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	/**
     * Vraca listu stavki porudzbine.
     *
     * @return lista stavki porudzbine.
     */
	public List<StavkaPorudzbine> getStavkePorudzbine() {
		return stavkePorudzbine;
	}

	/**
     * Postavlja listu stavki porudzbine na unetu vrednost.
     *
     * @param stavkePorudzbine Nova lista stavki porudzbine.
     */
	public void setStavkePorudzbine(List<StavkaPorudzbine> stavkePorudzbine) {
		this.stavkePorudzbine = stavkePorudzbine;
	}

	/**
     * Vraca status porudzbine.
     *
     * @return status porudzbine kao enumeracija StatusPorudzbine.
     */
	public StatusPorudzbine getStatus() {
		return status;
	}

	/**
     * Postavlja status porudzbine na unetu vrednost.
     *
     * @param status Novi status porudzbine.
     */
	public void setStatus(StatusPorudzbine status) {
		this.status = status;
	}

	/**
     * Dodaje stavku porudzbine u listu stavki i postavlja vezu ka ovoj porudzbini.
     *
     * @param stavka Stavka porudzbine koja se dodaje.
     */
	public void addItem(StavkaPorudzbine stavka) {
		stavka.setPorudzbina(this);
		this.stavkePorudzbine.add(stavka);
	}

	/**
     * Uklanja stavku porudzbine iz liste stavki i uklanja vezu ka ovoj porudzbini.
     *
     * @param stavka Stavka porudzbine koja se uklanja.
     */
	public void removeItem(StavkaPorudzbine stavka) {
		stavka.setPorudzbina(null);
		this.stavkePorudzbine.remove(stavka);
	}

	/**
     * Vraca dostavljaca koji isporucuje porudzbinu.
     *
     * @return dostavljac koji isporucuje porudzbinu kao objekat klase Dostavljac,
     * ili null ako dostavljac jos nije dodeljen porudzbini.
     */
	public Dostavljac getDostavljac() {
		return dostavljac;
	}

	/**
     * Postavlja dostavljaca koji isporucuje porudzbinu na unetu vrednost.
     *
     * @param dostavljac Dostavljac koji isporucuje porudzbinu.
     */
	public void setDostavljac(Dostavljac dostavljac) {
		this.dostavljac = dostavljac;
	}

}
