package njt.njt_projekat.dto.impl;

import jakarta.validation.constraints.*;
import njt.njt_projekat.dto.Dto;

public class KuponDto implements Dto {

	private Long kuponId;

	@NotBlank(message = "Niste uneli kod kupona!")
	@Size(max = 30, message = "Kod ne sme da sadrzi vise od 30 karaktera")
	private String kod;

	@NotNull(message = "Niste uneli popust!")
	@Positive(message = "Popust mora biti pozitivan broj")
	private Double popust;

	private boolean iskoriscen;

	@NotNull(message = "Nedostaje korisnik")
	private Long korisnikId;

	private Long porudzbinaId;

	public KuponDto() {
	}

	public KuponDto(Long kuponId, String kod, Double popust, boolean iskoriscen, Long korisnikId, Long porudzbinaId) {
		this.kuponId = kuponId;
		this.kod = kod;
		this.popust = popust;
		this.iskoriscen = iskoriscen;
		this.korisnikId = korisnikId;
		this.porudzbinaId = porudzbinaId;
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

	public Long getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Long korisnikId) {
		this.korisnikId = korisnikId;
	}

	public Long getPorudzbinaId() {
		return porudzbinaId;
	}

	public void setPorudzbinaId(Long porudzbinaId) {
		this.porudzbinaId = porudzbinaId;
	}

}
