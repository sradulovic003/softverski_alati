/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.security;

import java.util.List;
import njt.njt_projekat.entity.impl.Korisnik;
import njt.njt_projekat.repository.impl.KorisnikRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sara
 */
@Service
public class AppUserDetailsService implements UserDetailsService{

    private final KorisnikRepository korisnici;

    public AppUserDetailsService(KorisnikRepository korisnici) {
        this.korisnici = korisnici;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik k = korisnici.findByUsername(username);
        if (k == null) {
            throw new UsernameNotFoundException("Not found");
        }
        return new org.springframework.security.core.userdetails.User(
                k.getKorisnickoIme(),
                k.getLozinka(),
                k.isEnabled(),
                true, //accountNonExpired
                true, // credentialsNonExpired
                true, // accountNonLocked
                List.of(new SimpleGrantedAuthority("ULOGA_" + k.getUloga().name()))
        );
    }

}
