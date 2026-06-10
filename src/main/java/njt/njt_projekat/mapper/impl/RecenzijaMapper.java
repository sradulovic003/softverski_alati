package njt.njt_projekat.mapper.impl;

import org.springframework.stereotype.Component;

import njt.njt_projekat.dto.impl.RecenzijaDto;
import njt.njt_projekat.entity.impl.Korisnik;
import njt.njt_projekat.entity.impl.Poslasticara;
import njt.njt_projekat.entity.impl.Recenzija;
import njt.njt_projekat.mapper.DtoEntityMapper;

@Component
public class RecenzijaMapper implements DtoEntityMapper<RecenzijaDto, Recenzija>{

	@Override
    public RecenzijaDto toDto(Recenzija e) {
        if (e == null) {
            return null;
        }
        Long korisnikId = e.getKorisnik() != null ? e.getKorisnik().getKorisnikId() : null;
        Long poslasticaraId = e.getPoslasticara() != null ? e.getPoslasticara().getPoslasticaraId() : null;
        return new RecenzijaDto(e.getRecenzijaId(), e.getKomentar(), e.getOcena(), korisnikId, poslasticaraId);
    }

    @Override
    public Recenzija toEntity(RecenzijaDto t) {
        if (t == null) {
            return null;
        }
        Korisnik korisnik = t.getKorisnikId() != null ? new Korisnik(t.getKorisnikId()) : null;
        Poslasticara poslasticara = t.getPoslasticaraId() != null ? new Poslasticara(t.getPoslasticaraId()) : null;
        return new Recenzija(t.getRecenzijaId(), t.getKomentar(), t.getOcena(), korisnik, poslasticara);
    }
}
