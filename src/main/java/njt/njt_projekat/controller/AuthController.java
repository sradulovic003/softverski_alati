/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import njt.njt_projekat.dto.impl.AuthResponse;
import njt.njt_projekat.dto.impl.KorisnikDto;
import njt.njt_projekat.dto.impl.LoginRequest;
import njt.njt_projekat.dto.impl.PorudzbinaDto;
import njt.njt_projekat.dto.impl.RegisterRequest;
import njt.njt_projekat.entity.impl.Korisnik;
import njt.njt_projekat.tokens.VerificationToken;
import njt.njt_projekat.mapper.impl.PorudzbinaMapper;
import njt.njt_projekat.repository.impl.KorisnikRepository;
import njt.njt_projekat.repository.impl.VerificationTokenRepository;
import njt.njt_projekat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sara
 */
@CrossOrigin(origins = "http://localhost:3000") //Dozvoljava CORS (Cross-Origin Resource Sharing) zahteve sa date adrese, browser po defaultu blokira zahteve sa druge adr zbog bezb
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth")  //za dokum
public class AuthController {

    private final AuthService authService;
    private final VerificationTokenRepository tokens;
    private final KorisnikRepository korisnici;

    @Autowired
    private PorudzbinaMapper porudzbinaMapper;

    public AuthController(AuthService authService, VerificationTokenRepository tokens, KorisnikRepository korisnici) {
        this.authService = authService;
        this.tokens = tokens;
        this.korisnici = korisnici;
    }

    @PostMapping("/register")
    public ResponseEntity<KorisnikDto> register(@Valid @RequestBody RegisterRequest req) throws Exception {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")  //cuvamo token od usera
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        // JWT je stateless -> "logout" na klijentu (obriši token).
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/me")
//    public ResponseEntity<KorisnikDto> me(Authentication auth) throws Exception {
//        // auth.getName() je username iz SecurityContext-a
//        Korisnik k = korisnici.findByUsername(auth.getName());
//        List<PorudzbinaDto> porudzbineDto = null;
//        if (k.getPorudzbine() != null) {
//            porudzbineDto = k.getPorudzbine().stream().map(porudzbinaMapper::toDto).collect(Collectors.toList());
//        }
//        KorisnikDto kto = new KorisnikDto(k.getKorisnikId(), k.getIme(), k.getPrezime(), k.getKorisnickoIme(), k.getEmail(), k.getAdresa(),k.getUloga(), porudzbineDto);
//        return ResponseEntity.ok(kto);
//    }

    @GetMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam String token) {
        VerificationToken vt = tokens.find(token);
        if (vt == null) {
            return ResponseEntity.badRequest().body("Neispravan token.");
        }
        if (vt.isExpired()) {
            tokens.delete(vt);
            return ResponseEntity.badRequest().body("Token je istekao.");
        }
        Korisnik k = vt.getKorisnik();
        k.setEnabled(true);
        korisnici.save(k);      // merge/update po tvom repou
        tokens.delete(vt);  // potroši token

        // možeš vratiti plain tekst, ili redirect na frontend
        return ResponseEntity.ok("Nalog aktiviran. Sada se možete prijaviti.");
        /* return ResponseEntity.status(302)
            .header("Location", frontendUrl + "/login?verified=1")
            .build();*/
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        // čak i ako je null – vrati 200 da ne otkrivamo ništa
        authService.requestPasswordReset(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> body) {
        String token = body.get("token");
        String password = body.get("lozinka");
        if (password == null || password.length() < 6) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lozinka mora imati bar 6 karaktera.");
        }
        authService.resetPassword(token, password);
        return ResponseEntity.ok("Lozinka je promenjena. Sada se možete prijaviti.");
    }
    
  
}
