package njt.njt_projekat.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import njt.njt_projekat.dto.impl.AuthResponse;
import njt.njt_projekat.dto.impl.KorisnikDto;
import njt.njt_projekat.dto.impl.LoginRequest;
import njt.njt_projekat.dto.impl.RegisterRequest;
import njt.njt_projekat.entity.impl.Korisnik;
import njt.njt_projekat.entity.impl.Uloga;
import njt.njt_projekat.mapper.impl.KorisnikMapper;
import njt.njt_projekat.repository.impl.KorisnikRepository;
import njt.njt_projekat.repository.impl.PasswordResetTokenRepository;
import njt.njt_projekat.repository.impl.VerificationTokenRepository;
import njt.njt_projekat.security.JwtService;
import njt.njt_projekat.tokens.PasswordResetToken;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

	@Mock
    AuthenticationManager authManager;

    @Mock
    JwtService jwt;

    @Mock
    KorisnikRepository korisnici;

    @Mock
    PasswordEncoder encoder;

    @Mock
    KorisnikMapper korisnikMapper;

    @Mock
    MailService mail;

    @Mock
    VerificationTokenRepository tokens;

    @Mock
    PasswordResetTokenRepository resetTokens;

    @InjectMocks
    AuthService authService;

    RegisterRequest registerRequest;
    Korisnik korisnik;
    KorisnikDto korisnikDto;

    @BeforeEach
    void setUp() throws Exception {
        registerRequest = new RegisterRequest();
        registerRequest.setKorisnickoIme("marko123");
        registerRequest.setIme("Marko");
        registerRequest.setPrezime("Markovic");
        registerRequest.setAdresa("Ulica 123");
        registerRequest.setEmail("marko@gmail.com");
        registerRequest.setLozinka("lozinka123");

        korisnik = new Korisnik(1L);
        korisnik.setKorisnickoIme("marko123");
        korisnik.setEmail("marko@gmail.com");
        korisnik.setUloga(Uloga.USER);

        korisnikDto = new KorisnikDto(1L, "Marko", "Markovic", "marko123", "marko@gmail.com", "Ulica 123", Uloga.USER, null);
    }

    @AfterEach
    void tearDown() throws Exception {
        registerRequest = null;
        korisnik = null;
        korisnikDto = null;
    }

    @Test
    void testRegister() throws Exception {
        when(korisnici.existsByUsername("marko123")).thenReturn(false);
        when(korisnici.existsByEmail("marko@gmail.com")).thenReturn(false);
        when(encoder.encode("lozinka123")).thenReturn("hesovanaLozinka");
        when(korisnikMapper.toDto(any(Korisnik.class))).thenReturn(korisnikDto);

        KorisnikDto rezultat = authService.register(registerRequest);

        assertEquals(korisnikDto, rezultat);
        verify(korisnici, times(1)).save(any(Korisnik.class));
        verify(tokens, times(1)).save(any());
        verify(mail, times(1)).send(eq("marko@gmail.com"), eq("Potvrda naloga"), anyString());
    }

    @Test
    void testRegisterKorisnickoImeVecPostoji() {
        when(korisnici.existsByUsername("marko123")).thenReturn(true);

        Exception exception = assertThrows(Exception.class, () -> {
            authService.register(registerRequest);
        });
        assertEquals("Korisnicko ime vec postoji", exception.getMessage());
    }

    @Test
    void testRegisterEmailVecPostoji() {
        when(korisnici.existsByUsername("marko123")).thenReturn(false);
        when(korisnici.existsByEmail("marko@gmail.com")).thenReturn(true);

        Exception exception = assertThrows(Exception.class, () -> {
            authService.register(registerRequest);
        });
        assertEquals("Email vec postoji", exception.getMessage());
    }

    @Test
    void testLogin() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setKorisnickoIme("marko123");
        loginRequest.setLozinka("lozinka123");

        Authentication auth = Mockito.mock(Authentication.class);
        User userDetails = new User("marko123", "hesovanaLozinka", Collections.emptyList());

        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(userDetails);
        when(korisnici.findByUsername("marko123")).thenReturn(korisnik);
        when(jwt.generate(any(), any())).thenReturn("jwt-token-123");
        when(korisnikMapper.toDto(korisnik)).thenReturn(korisnikDto);

        AuthResponse rezultat = authService.login(loginRequest);

        assertEquals("jwt-token-123", rezultat.getToken());
        assertEquals(korisnikDto, rezultat.getKorisnik());
    }

    @Test
    void testResetPassword() {
        PasswordResetToken token = PasswordResetToken.of(korisnik, 1800);

        when(resetTokens.find("validan-token")).thenReturn(token);
        when(encoder.encode("novaLozinka")).thenReturn("hesovanaNovaLozinka");

        authService.resetPassword("validan-token", "novaLozinka");

        assertEquals("hesovanaNovaLozinka", korisnik.getLozinka());
        assertTrue(token.isUsed());
        verify(korisnici, times(1)).save(korisnik);
        verify(resetTokens, times(1)).save(token);
    }

    @Test
    void testResetPasswordNevalidanToken() {
        when(resetTokens.find("nevalidan-token")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authService.resetPassword("nevalidan-token", "novaLozinka");
        });
        assertEquals("Neispravan ili istekao token.", exception.getMessage());
    }

    @Test
    void testResetPasswordIskorisceniToken() {
        PasswordResetToken token = PasswordResetToken.of(korisnik, 1800);
        token.setUsed(true);

        when(resetTokens.find("iskorisceni-token")).thenReturn(token);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authService.resetPassword("iskorisceni-token", "novaLozinka");
        });
        assertEquals("Neispravan ili istekao token.", exception.getMessage());
    }

}
