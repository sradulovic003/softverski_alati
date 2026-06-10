package njt.njt_projekat.entity.impl;

import jakarta.persistence.*;
import njt.njt_projekat.entity.MyEntity;


@Entity
@Table(name="dostavljaci")
public class Dostavljac implements MyEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dostavljacId;
	private String ime;
	private String prezime;
	private String telefon;
	
	public Dostavljac() {
		
	}

	public Dostavljac(Long dostavljacId) {
		super();
		this.dostavljacId = dostavljacId;
	}

	public Dostavljac(Long dostavljacId, String ime, String prezime, String telefon) {
		super();
		this.dostavljacId = dostavljacId;
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
	}

	public Long getDostavljacId() {
		return dostavljacId;
	}

	public void setDostavljacId(Long dostavljacId) {
		this.dostavljacId = dostavljacId;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	
}
