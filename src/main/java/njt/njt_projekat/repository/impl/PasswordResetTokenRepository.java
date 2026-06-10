/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import njt.njt_projekat.tokens.PasswordResetToken;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sara
 */
@Repository
public class PasswordResetTokenRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(PasswordResetToken prt) {
        em.persist(prt);
    }

    public PasswordResetToken find(String token) {
        return em.find(PasswordResetToken.class, token);
    }

    @Transactional
    public void delete(PasswordResetToken prt) {
        em.remove(em.contains(prt) ? prt : em.merge(prt));
    }

    
}