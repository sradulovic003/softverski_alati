package njt.njt_projekat.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import njt.njt_projekat.entity.impl.Dostavljac;
import njt.njt_projekat.repository.MyAppRepository;

@Repository
public class DostavljacRepository implements MyAppRepository<Dostavljac, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Dostavljac> findAll() {
		return entityManager.createQuery("SELECT d FROM Dostavljac d", Dostavljac.class).getResultList();
	}

	@Override
	public Dostavljac findById(Long id) throws Exception {
		Dostavljac d = entityManager.find(Dostavljac.class, id);
		if (d == null) {
			throw new Exception("Dostavljac nije pronadjen");
		}
		return d;
	}

	@Override
	@Transactional
	public void save(Dostavljac entity) {
		if (entity.getDostavljacId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}

	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Dostavljac d = entityManager.find(Dostavljac.class, id);
		if (d != null) {
			entityManager.remove(d);
		}

	}

}
