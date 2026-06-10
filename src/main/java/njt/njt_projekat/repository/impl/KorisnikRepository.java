/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import njt.njt_projekat.entity.impl.Korisnik;
import njt.njt_projekat.repository.MyAppRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sara
 */
@Repository
public class KorisnikRepository implements MyAppRepository<Korisnik, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Korisnik> findAll() {
        return entityManager.createQuery("SELECT k FROM Korisnik k", Korisnik.class).getResultList();
    }

    @Override
    public Korisnik findById(Long id) throws Exception {
        Korisnik korisnik = entityManager.find(Korisnik.class, id);
        if (korisnik == null) {
            throw new Exception("Proizvod nije pronadjen");
        }
        return korisnik;
    }

    @Override
    @Transactional
    public void save(Korisnik entity) {
        if (entity.getKorisnikId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Korisnik korisnik = entityManager.find(Korisnik.class, id);
        if (korisnik != null) {
            entityManager.remove(korisnik);
        }
    }

    // custom:
    public Korisnik findByUsername(String username) {
        List<Korisnik> list = entityManager.createQuery("SELECT k FROM Korisnik k WHERE k.korisnickoIme = :kn", Korisnik.class)
                .setParameter("kn", username).getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    public boolean existsByUsername(String username) {
        Long c = entityManager.createQuery("SELECT COUNT(k) FROM Korisnik k WHERE k.korisnickoIme = :kn", Long.class)
                .setParameter("kn", username).getSingleResult();
        return c > 0;
    }

    public boolean existsByEmail(String email) {
        Long c = entityManager.createQuery("SELECT COUNT(k) FROM Korisnik k WHERE k.email = :km", Long.class)
                .setParameter("km", email).getSingleResult();
        return c > 0;
    }

    public Korisnik findByEmail(String email) {
        List<Korisnik> list = entityManager.createQuery("SELECT k FROM Korisnik k WHERE k.email = :email", Korisnik.class)
                .setParameter("email", email).getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

}
