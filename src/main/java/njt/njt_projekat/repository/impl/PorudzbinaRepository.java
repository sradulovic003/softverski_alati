/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import njt.njt_projekat.entity.impl.Porudzbina;
import njt.njt_projekat.repository.MyAppRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sara
 */
@Repository
public class PorudzbinaRepository implements MyAppRepository<Porudzbina, Long>{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Porudzbina> findAll() {
        return em.createQuery("SELECT p FROM Porudzbina p", Porudzbina.class).getResultList();
    }

    @Override
    public Porudzbina findById(Long id) throws Exception {
        Porudzbina p = em.find(Porudzbina.class, id);
        if (p == null) throw new Exception("Porudzbina nije pronadjena: " + id);
        return p;
    }

    @Override @Transactional
    public void save(Porudzbina entity) {
        if (entity.getPorudzbinaId()== null) em.persist(entity);
        else em.merge(entity);
    }

    @Override @Transactional
    public void deleteById(Long id) {
        Porudzbina p = em.find(Porudzbina.class, id);
        if (p != null) em.remove(p); // orphanRemoval briše i stavke
    }
}
