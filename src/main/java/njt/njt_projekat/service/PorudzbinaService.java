/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import njt.njt_projekat.dto.impl.PorudzbinaDto;
import njt.njt_projekat.dto.impl.StavkaPorudzbineDto;
import njt.njt_projekat.entity.impl.Dostavljac;
import njt.njt_projekat.entity.impl.Korisnik;
import njt.njt_projekat.entity.impl.Porudzbina;
import njt.njt_projekat.entity.impl.Proizvod;
import njt.njt_projekat.entity.impl.StatusPorudzbine;
import njt.njt_projekat.entity.impl.StavkaPorudzbine;
import njt.njt_projekat.mapper.impl.PorudzbinaMapper;
import njt.njt_projekat.repository.impl.PorudzbinaRepository;
import org.springframework.stereotype.Service;

/**
 * Servis koji implementira poslovnu logiku za upravljanje porudzbinama.
 *
 * Omogucava kreiranje porudzbine sa svim stavkama u jednoj transakciji,
 * pregled, promenu statusa, brisanje porudzbine i dodelu dostavljaca.
 * Koristi PorudzbinaRepository za pristup bazi podataka i
 * PorudzbinaMapper za konverziju izmedju entiteta i DTO objekata.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
@Service
public class PorudzbinaService {

	/**
     * Repozitorijum za pristup podacima o porudzbinama u bazi podataka.
     */
    private final PorudzbinaRepository porudzbine;
    
    /**
     * Maper za konverziju izmedju entiteta Porudzbina i DTO objekta PorudzbinaDto.
     */
    private final PorudzbinaMapper mapper;

    /**
     * Entity manager za direktan pristup bazi podataka.
     * Koristi se za kreiranje LAZY referenci na povezane entitete.
     */
    @PersistenceContext
    private EntityManager em;

    
    /**
     * Kreira objekat klase PorudzbinaService sa unetim zavisnostima.
     *
     * @param porudzbine Repozitorijum za pristup podacima o porudzbinama.
     * @param mapper Maper za konverziju izmedju entiteta i DTO objekata.
     */
    public PorudzbinaService(PorudzbinaRepository porudzbine, PorudzbinaMapper mapper) {
        this.porudzbine = porudzbine;
        this.mapper = mapper;
    }

    /**
     * Vraca listu svih porudzbina iz baze podataka.
     *
     * @return lista svih porudzbina kao lista objekata klase PorudzbinaDto.
     */
    public List<PorudzbinaDto> findAll() {
        return porudzbine.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    /**
     * Vraca porudzbinu sa unetim identifikatorom.
     *
     * @param id Identifikator porudzbine.
     * @return porudzbina sa unetim identifikatorom kao objekat klase PorudzbinaDto.
     * @throws java.lang.Exception Ako porudzbina sa unetim identifikatorom ne postoji.
     */
    public PorudzbinaDto findById(Long id) throws Exception {
        return mapper.toDto(porudzbine.findById(id));
    }

    /**
     * Kreira novu porudzbinu sa svim njenim stavkama u jednoj transakciji.
     * Za svaku stavku uzima trenutnu cenu proizvoda iz baze i racuna iznos
     * kao proizvod cene i kolicine. Ukupan iznos porudzbine se racuna
     * sabiranjem iznosa svih stavki. Ako status porudzbine nije unet, 
     * postavlja se na StatusPorudzbine.KREIRANA. Zbog CascadeType.ALL
     * na stavkama, sve stavke se automatski cuvaju zajedno sa porudzbinom.
     *
     * @param dto Podaci o novoj porudzbini sa listom stavki.
     * @return kreirana porudzbina kao objekat klase PorudzbinaDto.
     * @throws java.lang.Exception Ako nedostaje identifikator korisnika ili
     * je lista stavki prazna.
     */
    @Transactional
    public PorudzbinaDto create(PorudzbinaDto dto) throws Exception {
        Porudzbina porudzbina = new Porudzbina();
        porudzbina.setStatus(dto.getStatus() != null ? dto.getStatus() : StatusPorudzbine.KREIRANA);
        

        if (dto.getKorisnikId() == null) {
            throw new Exception("Nedostaje id korisnika");
        }
        porudzbina.setKorisnik(em.getReference(Korisnik.class, dto.getKorisnikId()));
        
        if (dto.getStavkePorudzbine() == null || dto.getStavkePorudzbine().isEmpty()) {
            throw new Exception("Porudzbina mora da sadrzi bar jednu stavku");
        }

        double ukupanIznos=0.0;
        for (StavkaPorudzbineDto stavkaDto : dto.getStavkePorudzbine()) {
            StavkaPorudzbine sp = new StavkaPorudzbine();
        
            Proizvod p = em.getReference(Proizvod.class, stavkaDto.getProizvodId());
            sp.setProizvod(p);
            
            double cena =p.getCena();
            sp.setCena(cena);
            double kolicina = stavkaDto.getKolicina();
            sp.setKolicina(kolicina);
            double iznos=cena*kolicina;
            sp.setIznos(iznos);
            
            ukupanIznos+=iznos;

            porudzbina.addItem(sp); 
        }
        porudzbina.setUkupanIznos(ukupanIznos);
        
        porudzbine.save(porudzbina);

        
        return mapper.toDto(porudzbina);
    }

    /**
     * Menja status porudzbine sa unetim identifikatorom.
     *
     * @param id Identifikator porudzbine.
     * @param status Novi status porudzbine.
     * @return azurirana porudzbina kao objekat klase PorudzbinaDto.
     * @throws java.lang.Exception Ako porudzbina sa unetim identifikatorom ne postoji.
     */
    @Transactional
    public PorudzbinaDto updateStatus(Long id, StatusPorudzbine status) throws Exception {
        Porudzbina p = porudzbine.findById(id);
        p.setStatus(status);
        porudzbine.save(p);
        return mapper.toDto(p);
    }

    /**
     * Brise porudzbinu sa unetim identifikatorom iz baze podataka.
     * Brisanjem porudzbine brisu se i sve njene stavke (orphanRemoval).
     *
     * @param id Identifikator porudzbine koja se brise.
     */
    @Transactional
    public void deleteById(Long id) {
        porudzbine.deleteById(id);
    }
    
    /**
     * Dodeljuje dostavljaca porudzbini sa unetim identifikatorom.
     *
     * @param porudzbinaId Identifikator porudzbine kojoj se dodeljuje dostavljac.
     * @param dostavljacId Identifikator dostavljaca koji se dodeljuje porudzbini.
     * @return azurirana porudzbina sa dodeljenim dostavljacem kao objekat klase PorudzbinaDto.
     * @throws java.lang.Exception Ako porudzbina sa unetim identifikatorom ne postoji.
     */
    @Transactional
    public PorudzbinaDto dodeliDostavljaca(Long porudzbinaId, Long dostavljacId) throws Exception {
        Porudzbina p = porudzbine.findById(porudzbinaId);
        p.setDostavljac(em.getReference(Dostavljac.class, dostavljacId));
        porudzbine.save(p);
        return mapper.toDto(p);
    }
}
