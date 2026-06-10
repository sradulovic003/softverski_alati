/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.mapper.impl;

import njt.njt_projekat.dto.impl.StavkaPorudzbineDto;
import njt.njt_projekat.entity.impl.Porudzbina;
import njt.njt_projekat.entity.impl.Proizvod;
import njt.njt_projekat.entity.impl.StavkaPorudzbine;
import njt.njt_projekat.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sara
 */
@Component
public class StavkaPorudzbineMapper implements DtoEntityMapper<StavkaPorudzbineDto, StavkaPorudzbine>{

    @Override
    public StavkaPorudzbineDto toDto(StavkaPorudzbine e) {
        Long porudzbinaId = e.getPorudzbina()!= null ? e.getPorudzbina().getPorudzbinaId(): null;
        Long proizvodId = e.getProizvod()!= null ? e.getProizvod().getProizvodId(): null;
        return new StavkaPorudzbineDto(e.getRb(), e.getCena(), e.getKolicina(), e.getIznos(), porudzbinaId, proizvodId);
    }

    @Override
    public StavkaPorudzbine toEntity(StavkaPorudzbineDto t) {
        Porudzbina porudzbina = t.getPorudzbinaId()!= null ? new Porudzbina(t.getPorudzbinaId()) : null;
        Proizvod proizvod = t.getProizvodId()!=null ? new Proizvod(t.getProizvodId()) : null;
        return new StavkaPorudzbine(t.getRb(), t.getCena(), t.getKolicina(), t.getIznos(), porudzbina, proizvod);
    }
    
}
