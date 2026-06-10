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
@Table(name = "password_reset_tokens")
public class PasswordResetToken {

    @Id
    private String token; // čuvamo UUID kao PK

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "korisnikId", nullable = false)
    private Korisnik korisnik;

    @Column(nullable = false)
    private Instant expiresAt;

    @Column(nullable = false)
    private boolean used = false;

    public static PasswordResetToken of(Korisnik k, long ttlSeconds) {
        PasswordResetToken t = new PasswordResetToken();
        t.token = UUID.randomUUID().toString();
        t.korisnik = k;
        t.expiresAt = Instant.now().plusSeconds(ttlSeconds);
        t.used = false;
        return t;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }

    // getters/setters
    public String getToken() {
        return token;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
