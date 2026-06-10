package njt.njt_projekat.entity.impl;

import jakarta.persistence.*;
import njt.njt_projekat.entity.MyEntity;

@Entity
@Table(name = "recenzije")
public class Recenzija implements MyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recenzijaId;
	private String komentar;
	private Integer ocena;

	@ManyToOne(optional = false)
	@JoinColumn(name = "korisnikId", nullable = false)
	private Korisnik korisnik;

	@ManyToOne(optional = false)
	@JoinColumn(name = "poslasticaraId", nullable = false)
	private Poslasticara poslasticara;

	public Recenzija() {
	}

	public Recenzija(Long recenzijaId) {
		super();
		this.recenzijaId = recenzijaId;
	}

	public Recenzija(Long recenzijaId, String komentar, Integer ocena, Korisnik korisnik, Poslasticara poslasticara) {
		super();
		this.recenzijaId = recenzijaId;
		this.komentar = komentar;
		this.ocena = ocena;
		this.korisnik = korisnik;
		this.poslasticara = poslasticara;
	}

	public Long getRecenzijaId() {
		return recenzijaId;
	}

	public void setRecenzijaId(Long recenzijaId) {
		this.recenzijaId = recenzijaId;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public Integer getOcena() {
		return ocena;
	}

	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Poslasticara getPoslasticara() {
		return poslasticara;
	}

	public void setPoslasticara(Poslasticara poslasticara) {
		this.poslasticara = poslasticara;
	}

}
