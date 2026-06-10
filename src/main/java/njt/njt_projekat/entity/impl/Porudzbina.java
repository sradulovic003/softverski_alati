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
 *
 * @author Sara
 */
@Entity
@Table(name = "porudzbine")
public class Porudzbina {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long porudzbinaId;

	@Column(nullable = false)
	private Double ukupanIznos;

	@Column(nullable = false)
	private Date datum = new Date();

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private StatusPorudzbine status = StatusPorudzbine.KREIRANA;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnikId", nullable = false)
	private Korisnik korisnik;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dostavljacId")
	private Dostavljac dostavljac;

	@OneToMany(mappedBy = "porudzbina", cascade = CascadeType.ALL, orphanRemoval = true) // uklanja sirocice
	private List<StavkaPorudzbine> stavkePorudzbine = new ArrayList<>();

	public Porudzbina() {
	}

	public Porudzbina(Long porudzbinaId) {
		this.porudzbinaId = porudzbinaId;
	}

	public Porudzbina(Long porudzbinaId, Double ukupanIznos, Korisnik korisnik, Date datum, StatusPorudzbine status,
			List<StavkaPorudzbine> stavkePorudzbine) {
		this.porudzbinaId = porudzbinaId;
		this.ukupanIznos = ukupanIznos;
		this.korisnik = korisnik;
		this.datum = datum;
		this.status = status;
		this.stavkePorudzbine = stavkePorudzbine;
	}

	public Long getPorudzbinaId() {
		return porudzbinaId;
	}

	public void setPorudzbinaId(Long porudzbinaId) {
		this.porudzbinaId = porudzbinaId;
	}

	public Double getUkupanIznos() {
		return ukupanIznos;
	}

	public void setUkupanIznos(Double ukupanIznos) {
		this.ukupanIznos = ukupanIznos;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<StavkaPorudzbine> getStavkePorudzbine() {
		return stavkePorudzbine;
	}

	public void setStavkePorudzbine(List<StavkaPorudzbine> stavkePorudzbine) {
		this.stavkePorudzbine = stavkePorudzbine;
	}

	public StatusPorudzbine getStatus() {
		return status;
	}

	public void setStatus(StatusPorudzbine status) {
		this.status = status;
	}

	public void addItem(StavkaPorudzbine stavka) {
		stavka.setPorudzbina(this);
		this.stavkePorudzbine.add(stavka);
	}

	public void removeItem(StavkaPorudzbine stavka) {
		stavka.setPorudzbina(null);
		this.stavkePorudzbine.remove(stavka);
	}

	public Dostavljac getDostavljac() {
		return dostavljac;
	}

	public void setDostavljac(Dostavljac dostavljac) {
		this.dostavljac = dostavljac;
	}

}
