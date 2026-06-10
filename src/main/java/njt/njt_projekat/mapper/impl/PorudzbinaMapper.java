/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;
import njt.njt_projekat.dto.impl.PorudzbinaDto;
import njt.njt_projekat.dto.impl.StavkaPorudzbineDto;
import njt.njt_projekat.entity.impl.Korisnik;
import njt.njt_projekat.entity.impl.Porudzbina;
import njt.njt_projekat.entity.impl.StavkaPorudzbine;
import njt.njt_projekat.mapper.DtoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sara
 */
@Component
public class PorudzbinaMapper implements DtoEntityMapper<PorudzbinaDto, Porudzbina>{
    @Autowired
    private StavkaPorudzbineMapper stavkaMApper;

    @Override
    public PorudzbinaDto toDto(Porudzbina e) {
        Long korisnikId = e.getKorisnik()!= null ? e.getKorisnik().getKorisnikId(): null;
        List<StavkaPorudzbineDto> stavkeDto = null;
        if (e.getStavkePorudzbine()!= null) {
            stavkeDto = e.getStavkePorudzbine().stream().map(stavkaMApper::toDto).collect(Collectors.toList());
        }
        return new PorudzbinaDto(e.getPorudzbinaId(), e.getUkupanIznos(), e.getDatum(),e.getStatus(), korisnikId, stavkeDto);
    }

    @Override
    public Porudzbina toEntity(PorudzbinaDto t) {
        Korisnik korisnik = t.getKorisnikId()!= null ? new Korisnik(t.getKorisnikId()) : null;
        List<StavkaPorudzbine> stavke = null;
        if (t.getStavkePorudzbine()!= null) {
            stavke = t.getStavkePorudzbine().stream().map(stavkaMApper::toEntity).collect(Collectors.toList());
        }
        return new Porudzbina(t.getPorudzbinaId(), t.getUkupanIznos(), korisnik,t.getDatum(),t.getStatus(), stavke);
    }
    
}
