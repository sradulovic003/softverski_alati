/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import njt.njt_projekat.entity.impl.Proizvod;
import njt.njt_projekat.repository.MyAppRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sara
 */
@Repository
public class ProizvodRepository implements MyAppRepository<Proizvod, Long> {
    @PersistenceContext //"ubaci mi ovde (u ovu promenljivu) EntityManager koji je već spreman i povezan sa bazom."
    private EntityManager entityManager;
    
    @Override
    public List<Proizvod> findAll() {
        return entityManager.createQuery("SELECT p FROM Proizvod p", Proizvod.class).getResultList();
    }

    @Override
    public Proizvod findById(Long id) throws Exception {
        Proizvod proizvod = entityManager.find(Proizvod.class, id);
        if(proizvod==null){
            throw new Exception("Proizvod nije pronadjen");
        }
        return proizvod;
    }

    @Override
    @Transactional
    public void save(Proizvod entity) {
        if(entity.getProizvodId()==null){
            entityManager.persist(entity);
        }
        else{
            entityManager.merge(entity);
        }
    }

    @Override
    @Transactional  //Transakcija: skup operacija nad bazom koji se svi uspešno izvrše(commit) ili se svi ponište (rollback) ako nešto krene naopako.
    public void deleteById(Long id) {
        Proizvod proizvod = entityManager.find(Proizvod.class, id);
        if(proizvod!=null){
            entityManager.remove(proizvod);
        }
    }

    public List<Proizvod> findByPoslasticaraId(Long id) {
        return entityManager.createQuery("SELECT p from Proizvod p WHERE p.poslasticara.poslasticaraId=:id", Proizvod.class).
                setParameter("id",id).getResultList();
    }
    
}
