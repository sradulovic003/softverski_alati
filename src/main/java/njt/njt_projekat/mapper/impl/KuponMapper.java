package njt.njt_projekat.mapper.impl;

import org.springframework.stereotype.Component;

import njt.njt_projekat.dto.impl.KuponDto;
import njt.njt_projekat.entity.impl.Korisnik;
import njt.njt_projekat.entity.impl.Kupon;
import njt.njt_projekat.entity.impl.Porudzbina;
import njt.njt_projekat.mapper.DtoEntityMapper;

@Component
public class KuponMapper implements DtoEntityMapper<KuponDto, Kupon> {

	@Override
	public KuponDto toDto(Kupon e) {
		if (e == null) {
			return null;
		}
		Long korisnikId = e.getKorisnik() != null ? e.getKorisnik().getKorisnikId() : null;
		Long porudzbinaId = e.getPorudzbina() != null ? e.getPorudzbina().getPorudzbinaId() : null;
		return new KuponDto(e.getKuponId(), e.getKod(), e.getPopust(), e.isIskoriscen(), korisnikId, porudzbinaId);
	}

	@Override
	public Kupon toEntity(KuponDto t) {
		if (t == null) {
			return null;
		}
		Korisnik korisnik = t.getKorisnikId() != null ? new Korisnik(t.getKorisnikId()) : null;
		Porudzbina porudzbina = t.getPorudzbinaId() != null ? new Porudzbina(t.getPorudzbinaId()) : null;
		return new Kupon(t.getKuponId(), t.getKod(), t.getPopust(), t.isIskoriscen(), korisnik, porudzbina);
	}

}
