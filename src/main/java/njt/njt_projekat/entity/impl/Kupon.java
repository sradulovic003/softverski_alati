package njt.njt_projekat.entity.impl;

import jakarta.persistence.*;
import njt.njt_projekat.entity.MyEntity;

@Entity
@Table(name = "kuponi")
public class Kupon implements MyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long kuponId;
	private String kod;
	private Double popust;
	private boolean iskoriscen;

	@ManyToOne(optional = false)
	@JoinColumn(name = "korisnikId", nullable = false)
	private Korisnik korisnik;

	@OneToOne
	@JoinColumn(name = "porudzbinaId")
	private Porudzbina porudzbina;

	public Kupon() {
	}

	public Kupon(Long kuponId) {
		super();
		this.kuponId = kuponId;
	}

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

	public Long getKuponId() {
		return kuponId;
	}

	public void setKuponId(Long kuponId) {
		this.kuponId = kuponId;
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public Double getPopust() {
		return popust;
	}

	public void setPopust(Double popust) {
		this.popust = popust;
	}

	public boolean isIskoriscen() {
		return iskoriscen;
	}

	public void setIskoriscen(boolean iskoriscen) {
		this.iskoriscen = iskoriscen;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Porudzbina getPorudzbina() {
		return porudzbina;
	}

	public void setPorudzbina(Porudzbina porudzbina) {
		this.porudzbina = porudzbina;
	}

}
