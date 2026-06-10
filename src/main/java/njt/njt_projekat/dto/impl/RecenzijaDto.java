package njt.njt_projekat.dto.impl;

import jakarta.validation.constraints.*;
import njt.njt_projekat.dto.Dto;

public class RecenzijaDto implements Dto {
	private Long recenzijaId;

	@Size(max = 500, message = "Komentar ne sme da sadrzi vise od 500 karaktera")
	private String komentar;

	@NotNull(message = "Niste uneli ocenu!")
	@Min(value = 1, message = "Ocena mora biti najmanje 1")
	@Max(value = 5, message = "Ocena moze biti najvise 5")
	private Integer ocena;

	@NotNull(message = "Nedostaje korisnik")
	private Long korisnikId;

	@NotNull(message = "Nedostaje poslasticara")
	private Long poslasticaraId;

	public RecenzijaDto() {
	}

	public RecenzijaDto(Long recenzijaId, String komentar, Integer ocena, Long korisnikId, Long poslasticaraId) {
		this.recenzijaId = recenzijaId;
		this.komentar = komentar;
		this.ocena = ocena;
		this.korisnikId = korisnikId;
		this.poslasticaraId = poslasticaraId;
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

	public Long getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Long korisnikId) {
		this.korisnikId = korisnikId;
	}

	public Long getPoslasticaraId() {
		return poslasticaraId;
	}

	public void setPoslasticaraId(Long poslasticaraId) {
		this.poslasticaraId = poslasticaraId;
	}
	
	
}
