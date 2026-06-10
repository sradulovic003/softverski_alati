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
import njt.njt_projekat.entity.impl.Korisnik;
import njt.njt_projekat.entity.impl.Porudzbina;
import njt.njt_projekat.entity.impl.Proizvod;
import njt.njt_projekat.entity.impl.StatusPorudzbine;
import njt.njt_projekat.entity.impl.StavkaPorudzbine;
import njt.njt_projekat.mapper.impl.PorudzbinaMapper;
import njt.njt_projekat.repository.impl.PorudzbinaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sara
 */
@Service
public class PorudzbinaService {

    private final PorudzbinaRepository porudzbine;
    private final PorudzbinaMapper mapper;

    @PersistenceContext
    private EntityManager em;

    public PorudzbinaService(PorudzbinaRepository porudzbine, PorudzbinaMapper mapper) {
        this.porudzbine = porudzbine;
        this.mapper = mapper;
    }

    public List<PorudzbinaDto> findAll() {
        return porudzbine.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public PorudzbinaDto findById(Long id) throws Exception {
        return mapper.toDto(porudzbine.findById(id));
    }

    /* Kreira narudžbinu i SVE njene stavke u JEDNOJ transakciji */
    @Transactional
    public PorudzbinaDto create(PorudzbinaDto dto) throws Exception {
        // 1) Napravi Porudzbinu
        Porudzbina porudzbina = new Porudzbina();
        porudzbina.setStatus(dto.getStatus() != null ? dto.getStatus() : StatusPorudzbine.KREIRANA);
        

        // 2) Poveži korisnika (LAZY referenca, bez dodatnog SELECT-a)
        if (dto.getKorisnikId() == null) {
            throw new Exception("Nedostaje id korisnika");
        }
        porudzbina.setKorisnik(em.getReference(Korisnik.class, dto.getKorisnikId()));
        
        // 3) Napravi stavke i uveži ih
        if (dto.getStavkePorudzbine() == null || dto.getStavkePorudzbine().isEmpty()) {
            throw new Exception("Porudzbina mora da sadrzi bar jednu stavku");
        }

        double ukupanIznos=0.0;
        for (StavkaPorudzbineDto stavkaDto : dto.getStavkePorudzbine()) {
            StavkaPorudzbine sp = new StavkaPorudzbine();
            // referenca na Product
            Proizvod p = em.getReference(Proizvod.class, stavkaDto.getProizvodId());
            sp.setProizvod(p);
            
            double cena =p.getCena();
            sp.setCena(cena);
            double kolicina = stavkaDto.getKolicina();
            sp.setKolicina(kolicina);
            double iznos=cena*kolicina;
            sp.setIznos(iznos);
            
            ukupanIznos+=iznos;

            // cena – ako nije poslata iz fronta, uzmi trenutnu iz proizvoda (pretpostavka da postoji getPrice())
            porudzbina.addItem(sp); // sinhronizuje owner stranu i listu
        }
        porudzbina.setUkupanIznos(ukupanIznos);
        // 4) Sačuvaj – zbog cascade=ALL biće upisane i stavke
        porudzbine.save(porudzbina);

        // 5) Vrati DTO
        return mapper.toDto(porudzbina);
    }

    /* Promena statusa (npr. CONFIRMED/CANCELED/COMPLETED) */
    @Transactional
    public PorudzbinaDto updateStatus(Long id, StatusPorudzbine status) throws Exception {
        Porudzbina p = porudzbine.findById(id);
        p.setStatus(status);
        porudzbine.save(p);
        return mapper.toDto(p);
    }

    /* Brisanje narudžbine + stavki u jednoj transakciji (orphanRemoval) */
    @Transactional
    public void deleteById(Long id) {
        porudzbine.deleteById(id);
    }
}
