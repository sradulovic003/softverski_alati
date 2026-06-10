/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;
import njt.njt_projekat.dto.impl.KorisnikDto;
import njt.njt_projekat.dto.impl.PorudzbinaDto;
import njt.njt_projekat.entity.impl.Korisnik;
import njt.njt_projekat.entity.impl.Porudzbina;
import njt.njt_projekat.mapper.DtoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sara
 */
@Component
public class KorisnikMapper implements DtoEntityMapper<KorisnikDto, Korisnik>{
    
    @Autowired
    private PorudzbinaMapper porudzbinaMapper;

    @Override
    public KorisnikDto toDto(Korisnik e) {
        if(e==null){
            return null;
        }
        List<PorudzbinaDto> porudzbineDto = null;
        if (e.getPorudzbine() != null) {
            porudzbineDto = e.getPorudzbine().stream().map(porudzbinaMapper::toDto).collect(Collectors.toList());
        }
        
        return new KorisnikDto(e.getKorisnikId(), e.getIme(), e.getPrezime(), e.getKorisnickoIme(), e.getEmail(), e.getAdresa(),e.getUloga(),porudzbineDto);
    }

    @Override
    public Korisnik toEntity(KorisnikDto t) {
        if(t==null){
            return null;
        }
        Korisnik k = new Korisnik();
        k.setKorisnikId(t.getKorisnikId());
        k.setIme(t.getIme());
        k.setPrezime(t.getPrezime());
        k.setKorisnickoIme(t.getKorisnickoIme());
        k.setEmail(t.getEmail());
        k.setUloga(t.getUloga());
        k.setAdresa(t.getAdresa());
        //sifra se postavlja u servisu, posle encoder-a;
        List<Porudzbina> porudzbine = null;
        if (t.getPorudzbine()!= null) {
            porudzbine = t.getPorudzbine().stream().map(porudzbinaMapper::toEntity).collect(Collectors.toList());
        }
        k.setPorudzbine(porudzbine);
        
        return k;
        
    }
    
}
