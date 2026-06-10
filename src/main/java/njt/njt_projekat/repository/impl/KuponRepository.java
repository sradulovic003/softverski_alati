package njt.njt_projekat.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import njt.njt_projekat.entity.impl.Kupon;
import njt.njt_projekat.repository.MyAppRepository;

@Repository
public class KuponRepository implements MyAppRepository<Kupon, Long>{
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
    public List<Kupon> findAll() {
        return entityManager.createQuery("SELECT k FROM Kupon k", Kupon.class).getResultList();
    }

	@Override
    public Kupon findById(Long id) throws Exception {
        Kupon k = entityManager.find(Kupon.class, id);
        if (k == null) {
            throw new Exception("Kupon nije pronadjen");
        }
        return k;
    }

	@Override
    @Transactional
    public void save(Kupon entity) {
        if (entity.getKuponId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

	@Override
    @Transactional
    public void deleteById(Long id) {
        Kupon k = entityManager.find(Kupon.class, id);
        if (k != null) {
            entityManager.remove(k);
        }
    }
	
	public List<Kupon> findByKorisnikId(Long id) {
        return entityManager.createQuery("SELECT k FROM Kupon k WHERE k.korisnik.korisnikId=:id", Kupon.class)
                .setParameter("id", id).getResultList();
    }

}
