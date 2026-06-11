/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.service;

import jakarta.transaction.Transactional;
import java.util.Map;
import njt.njt_projekat.dto.impl.AuthResponse;
import njt.njt_projekat.dto.impl.KorisnikDto;
import njt.njt_projekat.dto.impl.LoginRequest;
import njt.njt_projekat.dto.impl.RegisterRequest;
import njt.njt_projekat.entity.impl.Korisnik;
import njt.njt_projekat.entity.impl.Uloga;
import njt.njt_projekat.tokens.VerificationToken;
import njt.njt_projekat.mapper.impl.KorisnikMapper;
import njt.njt_projekat.repository.impl.KorisnikRepository;
import njt.njt_projekat.repository.impl.PasswordResetTokenRepository;
import njt.njt_projekat.repository.impl.VerificationTokenRepository;
import njt.njt_projekat.security.JwtService;
import njt.njt_projekat.tokens.PasswordResetToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servis koji implementira poslovnu logiku za autentifikaciju korisnika.
 *
 * Omogucava registraciju novog korisnika, prijavu, verifikaciju email adrese,
 * slanje zahteva za reset lozinke i resetovanje lozinke.
 * Koristi JWT token za autentifikaciju i PasswordEncoder za hesovanje lozinke.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
@Service
public class AuthService {

	/**
     * Menadzer za autentifikaciju korisnika.
     */
    private final AuthenticationManager authManager;
    
    /**
     * Servis za generisanje i validaciju JWT tokena.
     */
    private final JwtService jwt;  
    
    /**
     * Repozitorijum za pristup podacima o korisnicima u bazi podataka.
     */
    private final KorisnikRepository korisnici;
    
    /**
     * Enkoder za hesovanje lozinki korisnika.
     */
    private final PasswordEncoder encoder;
    
    /**
     * Maper za konverziju izmedju entiteta Korisnik i DTO objekta KorisnikDto.
     */
    private final KorisnikMapper korisnikMapper;
    
    /**
     * Servis za slanje email poruka korisnicima.
     */
    private final MailService mail;
    
    /**
     * Repozitorijum za pristup tokenima za verifikaciju email adrese.
     */
    private final VerificationTokenRepository tokens;
    
    /**
     * Repozitorijum za pristup tokenima za reset lozinke.
     */
    private final PasswordResetTokenRepository resetTokens;

    /**
     * URL adresa frontenda koja se koristi za generisanje linka za reset lozinke.
     */
    @Value("${app.frontend.url}")
    private String frontendUrl;

    /**
     * Kreira objekat klase AuthService sa unetim zavisnostima.
     *
     * @param authManager Menadzer za autentifikaciju korisnika.
     * @param jwt Servis za generisanje i validaciju JWT tokena.
     * @param korisnici Repozitorijum za pristup podacima o korisnicima.
     * @param encoder Enkoder za hesovanje lozinki.
     * @param korisnikMapper Maper za konverziju izmedju entiteta i DTO objekata.
     * @param tokens Repozitorijum za pristup tokenima za verifikaciju email adrese.
     * @param resetTokens Repozitorijum za pristup tokenima za reset lozinke.
     * @param mail Servis za slanje email poruka.
     */
    public AuthService(AuthenticationManager authManager, JwtService jwt, KorisnikRepository korisnici, PasswordEncoder encoder, KorisnikMapper korisnikMapper, VerificationTokenRepository tokens, PasswordResetTokenRepository resetTokens, MailService mail) {
        this.authManager = authManager;
        this.jwt = jwt;
        this.korisnici = korisnici;
        this.encoder = encoder;
        this.korisnikMapper = korisnikMapper;
        this.tokens = tokens;
        this.resetTokens = resetTokens;
        this.mail = mail;
    }

    /**
     * Registruje novog korisnika u sistemu.
     * Proverava da li korisnicko ime i email vec postoje u sistemu.
     * Hesuje lozinku pre cuvanja u bazi podataka pomocu PasswordEncoder-a.
     * Uloga novog korisnika se automatski postavlja na Uloga.USER.
     * Nakon kreiranja naloga, generise se token za verifikaciju email adrese
     * koji vazi 24 sata i salje se verifikacioni email na adresu korisnika.
     * Nalog nije aktivan dok korisnik ne potvrdi email adresu klikom na link.
     *
     * @param req Podaci o novom korisniku.
     * @return registrovani korisnik kao objekat klase KorisnikDto.
     * @throws java.lang.Exception Ako korisnicko ime ili email vec postoje u sistemu.
     */
    public KorisnikDto register(RegisterRequest req) throws Exception {
        if (korisnici.existsByUsername(req.getKorisnickoIme())) {
            throw new Exception("Korisnicko ime vec postoji");
        }
        if (korisnici.existsByEmail(req.getEmail())) {
            throw new Exception("Email vec postoji");
        }

        Korisnik k = new Korisnik();
        k.setKorisnickoIme(req.getKorisnickoIme());
        k.setIme(req.getIme());
        k.setPrezime(req.getPrezime());
        k.setAdresa(req.getAdresa());
        k.setEmail(req.getEmail());
        k.setLozinka(encoder.encode(req.getLozinka()));
        k.setUloga(Uloga.USER);

        korisnici.save(k);
        var vt = VerificationToken.of(k, 86400);
        System.out.println("Generated token: " + vt.getToken());
        tokens.save(vt);
        System.out.println("Token saved.");

        String verifyUrl = "http://localhost:8080/api/auth/verify?token=" + vt.getToken();
        String body = """
                Zdravo %s,

                Hvala na registraciji. Molimo potvrdite email klikom na link:

                %s

                Link važi 24h.
                """.formatted(k.getKorisnickoIme(), verifyUrl);

        mail.send(k.getEmail(), "Potvrda naloga", body);

        return korisnikMapper.toDto(k);
    }

    /**
     * Prijavljuje korisnika u sistem i generise JWT token.
     * Autentifikacija se vrsi na osnovu korisnickog imena i lozinke.
     * JWT token sadrzi ulogu korisnika kao dodatnu informaciju.
     *
     * @param req Podaci za prijavu (korisnicko ime i lozinka).
     * @return odgovor sa JWT tokenom i podacima o korisniku kao objekat klase AuthResponse.
     */
    public AuthResponse login(LoginRequest req) {
        Authentication auth = authManager.authenticate(  
                new UsernamePasswordAuthenticationToken(req.getKorisnickoIme(), req.getLozinka()));

        Korisnik me = korisnici.findByUsername(req.getKorisnickoIme());
        String token = jwt.generate((org.springframework.security.core.userdetails.User) auth.getPrincipal(),
                Map.of("uloga", me.getUloga().name()));

        return new AuthResponse(token, korisnikMapper.toDto(me));
    }

    /**
     * Salje email sa linkom za reset lozinke na unetu email adresu.
     * Kreira token za reset lozinke koji vazi 30 minuta.
     * Ako email adresa ne postoji u sistemu, metoda se zavrsava bez greske
     * kako se ne bi otkrivalo da li email postoji.
     *
     * @param email Email adresa korisnika koji je zatrazio reset lozinke.
     */
    @Transactional
    public void requestPasswordReset(String email) {
        Korisnik k = korisnici.findByEmail(email);
        if (k == null) {
            return;
        }

        PasswordResetToken t = PasswordResetToken.of(k, 1800);
        resetTokens.save(t);

        String link = frontendUrl + "/reset-password?token=" + t.getToken();
        String html = buildResetEmailHtml(k.getKorisnickoIme(), link);

        mail.sendHtml(k.getEmail(), "Reset lozinke", html);
    }
    
    /**
     * Kreira HTML sadrzaj emaila za reset lozinke.
     *
     * @param korisnickoIme Korisnicko ime korisnika kome se salje email.
     * @param link Link za reset lozinke koji se ubacuje u email.
     * @return HTML sadrzaj emaila kao String.
     */
    private String buildResetEmailHtml(String korisnickoIme, String link) {
        return """
        <div style="font-family: Inter,Segoe UI,Arial,sans-serif; max-width: 560px; margin: 0 auto; padding: 24px; background:#f7f8fb;">
          <div style="background:#fff; border:1px solid #e6e8ef; border-radius:14px; padding:24px;">
            <h2 style="margin:0 0 8px; color:#0f172a;">Pozdrav %s,</h2>
            <p style="margin:0 0 16px; color:#475569;">Dobili smo zahtev za reset lozinke. Klikni na dugme ispod da postaviš novu lozinku.</p>
            <div style="text-align:center; margin:24px 0;">
              <a href="%s" style="display:inline-block; padding:12px 18px; background:#2563eb; color:#fff; text-decoration:none; border-radius:10px; font-weight:700;">
                Postavi novu lozinku
              </a>
            </div>
            <p style="margin:0 0 6px; color:#64748b; font-size:14px;">Ako dugme ne radi, otvori ovaj link u pretraživaču:</p>
            <p style="margin:0; word-break:break-all; color:#0f172a; font-size:13px;">%s</p>
            <hr style="border:none; border-top:1px solid #e6e8ef; margin:20px 0;">
            <p style="margin:0; color:#94a3b8; font-size:12px;">Link važi 30 minuta. Ako nisi tražio reset, ignoriši ovaj mejl.</p>
          </div>
        </div>
        """.formatted(korisnickoIme, link, link);
    }

    /**
     * Resetuje lozinku korisnika na osnovu tokena za reset.
     * Nova lozinka se hesuje pre cuvanja u bazi podataka.
     * Token se oznacava kao iskoriscen nakon uspesnog resetovanja lozinke.
     *
     * @param token Token za reset lozinke.
     * @param password Nova lozinka korisnika.
     * @throws java.lang.RuntimeException Ako je token neispravan, iskoriscen ili je istekao.
     */
    public void resetPassword(String token, String password) {
        PasswordResetToken t = resetTokens.find(token);
        if (t == null || t.isUsed() || t.isExpired()) {
            throw new RuntimeException("Neispravan ili istekao token.");
        }
        Korisnik k = t.getKorisnik();
        k.setLozinka(encoder.encode(password));
        korisnici.save(k);

        t.setUsed(true);              
        resetTokens.save(t);          
    }

}
