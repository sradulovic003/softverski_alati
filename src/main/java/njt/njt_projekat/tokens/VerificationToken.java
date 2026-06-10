/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.tokens;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import njt.njt_projekat.entity.impl.Korisnik;

/**
 *
 * @author Sara
 */
@Entity
@Table(name="verification_tokens")
public class VerificationToken {
    @Id
    private String token; // čuvamo UUID kao string primarni ključ

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="korisnik_id", nullable=false, unique=true)
    private Korisnik korisnik;

    @Column(nullable=false)
    private Instant expiresAt;

    public static VerificationToken of(Korisnik k, long ttlSeconds){  //kreira token
        VerificationToken t = new VerificationToken();
        t.token = UUID.randomUUID().toString();
        t.korisnik = k;
        t.expiresAt = Instant.now().plusSeconds(ttlSeconds);
        return t;
    }

    public boolean isExpired(){ return Instant.now().isAfter(expiresAt); }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
}
