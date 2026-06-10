package njt.njt_projekat.dto.impl;

import jakarta.validation.constraints.*;
import njt.njt_projekat.dto.Dto;

public class DostavljacDto implements Dto {

	private Long dostavljacId;

	@NotBlank(message = "Niste uneli ime!")
	@Size(max = 50, message = "Ime ne sme da sadrzi vise od 50 karaktera")
	private String ime;

	@NotBlank(message = "Niste uneli prezime!")
	@Size(max = 50, message = "Prezime ne sme da sadrzi vise od 50 karaktera")
	private String prezime;

	@NotBlank(message = "Niste uneli telefon!")
	@Pattern(regexp = "^[+\\d\\s/\\-]{6,20}$", message = "Telefon mora imati 6 do 20 karaktera (cifre, +, -, /, razmak)")
	private String telefon;

	public DostavljacDto() {
	}

	public DostavljacDto(Long dostavljacId, String ime, String prezime, String telefon) {
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
