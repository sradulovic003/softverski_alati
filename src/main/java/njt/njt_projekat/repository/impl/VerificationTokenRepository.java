/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import njt.njt_projekat.tokens.VerificationToken;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sara
 */
@Repository
public class VerificationTokenRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(VerificationToken vt) {
        em.persist(vt);
    }

    public VerificationToken find(String token) {
        return em.find(VerificationToken.class, token);
    }

    @Transactional
    public void delete(VerificationToken vt) {
        em.remove(em.contains(vt) ? vt : em.merge(vt));
    }
}
