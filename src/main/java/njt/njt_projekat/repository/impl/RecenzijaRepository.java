package njt.njt_projekat.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import njt.njt_projekat.entity.impl.Recenzija;
import njt.njt_projekat.repository.MyAppRepository;

@Repository
public class RecenzijaRepository implements MyAppRepository<Recenzija, Long> {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Recenzija> findAll() {
		return entityManager.createQuery("SELECT r FROM Recenzija r", Recenzija.class).getResultList();
	}

	@Override
	public Recenzija findById(Long id) throws Exception {
		Recenzija r = entityManager.find(Recenzija.class, id);
		if (r == null) {
			throw new Exception("Recenzija nije pronadjena");
		}
		return r;
	}

	@Override
	@Transactional
	public void save(Recenzija entity) {
		if (entity.getRecenzijaId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Recenzija r = entityManager.find(Recenzija.class, id);
		if (r != null) {
			entityManager.remove(r);
		}
	}

	public List<Recenzija> findByPoslasticaraId(Long id) {
		return entityManager
				.createQuery("SELECT r FROM Recenzija r WHERE r.poslasticara.poslasticaraId=:id", Recenzija.class)
				.setParameter("id", id).getResultList();
	}
}
