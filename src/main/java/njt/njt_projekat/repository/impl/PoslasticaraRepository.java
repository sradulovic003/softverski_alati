/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import njt.njt_projekat.entity.impl.Poslasticara;
import njt.njt_projekat.repository.MyAppRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Sara
 */
@Repository
public class PoslasticaraRepository implements MyAppRepository<Poslasticara, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Poslasticara> findAll() {
        return entityManager.createQuery("SELECT p FROM Poslasticara p", Poslasticara.class).getResultList();
    }

    @Override
    public Poslasticara findById(Long id) throws Exception {
        Poslasticara proizvod = entityManager.find(Poslasticara.class, id);
        if (proizvod == null) {
            throw new Exception("Proizvod nije pronadjen");
        }
        return proizvod;
    }

    @Override
    @Transactional
    public void save(Poslasticara entity) {
        if (entity.getPoslasticaraId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Poslasticara poslasticara = entityManager.find(Poslasticara.class, id);
        if (poslasticara != null) {
            entityManager.remove(poslasticara);
        }
    }

}
