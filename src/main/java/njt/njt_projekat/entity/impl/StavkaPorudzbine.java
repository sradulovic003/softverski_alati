/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.entity.impl;

import jakarta.persistence.*;


/**
 * Predstavlja stavku porudzbine koja povezuje porudzbinu i proizvod.
 *
 * Svaka stavka ima jedinstveni identifikator, cenu, kolicinu i iznos.
 * Jedna stavka pripada tacno jednoj porudzbini (1..1),
 * a jedna porudzbina moze sadrzati vise stavki (1..*).
 * Jedna stavka odnosi se na tacno jedan proizvod (1..1),
 * a jedan proizvod moze se nalaziti u vise stavki (0..*).
 *
 * @author Sara Radulovic
 * @version 1.0
 */
@Entity
@Table(name="stavke_porudzbine")
public class StavkaPorudzbine {
	
	/**
     * Jedinstveni identifikator stavke porudzbine kao Long.
     * Vrednost se automatski generise od strane baze podataka.
     */
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long rb;
    
    /**
     * Cena proizvoda u trenutku kreiranja porudzbine kao Double.
     * Ne sme biti null. Racuna se automatski od strane servisa
     * na osnovu trenutne cene proizvoda.
     */
    @Column(nullable=false)
    private Double cena;
    
    /**
     * Kolicina proizvoda u stavci porudzbine kao Double.
     * Ne sme biti null.
     */
    @Column(nullable=false)
    private Double kolicina;
    
    /**
     * Ukupan iznos stavke porudzbine kao Double.
     * Ne sme biti null. Racuna se automatski od strane servisa
     * kao proizvod cene i kolicine.
     */
    @Column(nullable=false)
    private Double iznos;
    
    /**
     * Porudzbina kojoj stavka pripada.
     * Jedna stavka pripada tacno jednoj porudzbini (1..1),
     * a jedna porudzbina moze sadrzati vise stavki (1..*).
     * Podaci o porudzbini se ucitavaju iz baze tek pri
     * pozivu metode getPorudzbina() (FetchType.LAZY).
     */
    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="porudzbinaId")
    private Porudzbina porudzbina;
    
    /**
     * Proizvod na koji se stavka odnosi.
     * Jedna stavka odnosi se na tacno jedan proizvod (1..1),
     * a jedan proizvod moze se nalaziti u vise stavki (0..*).
     * Podaci o proizvodu se ucitavaju iz baze tek pri 
     * pozivu metode getProizvod() (FetchType.LAZY).
     */
    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="proizvodId")
    private Proizvod proizvod; 

    /**
     * Kreira objekat klase StavkaPorudzbine sa podrazumevanim (null) vrednostima
     * svih atributa.
     */
    public StavkaPorudzbine() {
    }

    /**
     * Kreira objekat klase StavkaPorudzbine sa svim unetim vrednostima atributa.
     *
     * @param rb Identifikator stavke porudzbine.
     * @param cena Cena proizvoda u trenutku kreiranja porudzbine.
     * @param kolicina Kolicina proizvoda u stavci porudzbine.
     * @param iznos Ukupan iznos stavke porudzbine.
     * @param porudzbina Porudzbina kojoj stavka pripada.
     * @param proizvod Proizvod na koji se stavka odnosi.
     */
    public StavkaPorudzbine(Long rb, Double cena, Double kolicina, Double iznos, Porudzbina porudzbina, Proizvod proizvod) {
        this.rb = rb;
        this.cena = cena;
        this.kolicina = kolicina;
        this.iznos = iznos;
        this.porudzbina = porudzbina;
        this.proizvod = proizvod;
    }

    /**
     * Vraca identifikator stavke porudzbine.
     *
     * @return identifikator stavke porudzbine kao Long.
     */
    public Long getRb() {
        return rb;
    }

    /**
     * Postavlja identifikator stavke porudzbine na unetu vrednost.
     *
     * @param rb Novi identifikator stavke porudzbine.
     */
    public void setRb(Long rb) {
        this.rb = rb;
    }

    /**
     * Vraca cenu proizvoda u trenutku kreiranja porudzbine.
     *
     * @return cena proizvoda kao Double.
     */
    public Double getCena() {
        return cena;
    }

    /**
     * Postavlja cenu proizvoda na unetu vrednost.
     *
     * @param cena Nova cena proizvoda.
     */
    public void setCena(Double cena) {
        this.cena = cena;
    }

    /**
     * Vraca kolicinu proizvoda u stavci porudzbine.
     *
     * @return kolicina proizvoda kao Double.
     */
    public Double getKolicina() {
        return kolicina;
    }

    /**
     * Postavlja kolicinu proizvoda na unetu vrednost.
     *
     * @param kolicina Nova kolicina proizvoda.
     */
    public void setKolicina(Double kolicina) {
        this.kolicina = kolicina;
    }

    /**
     * Vraca ukupan iznos stavke porudzbine.
     *
     * @return ukupan iznos stavke porudzbine kao Double.
     */
    public Double getIznos() {
        return iznos;
    }

    /**
     * Postavlja ukupan iznos stavke porudzbine na unetu vrednost.
     *
     * @param iznos Novi ukupan iznos stavke porudzbine.
     */
    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    /**
     * Vraca porudzbinu kojoj stavka pripada.
     *
     * @return porudzbina kojoj stavka pripada kao objekat klase Porudzbina.
     */
    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    /**
     * Postavlja porudzbinu kojoj stavka pripada na unetu vrednost.
     *
     * @param porudzbina Porudzbina kojoj stavka pripada.
     */
    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    /**
     * Vraca proizvod na koji se stavka odnosi.
     *
     * @return proizvod na koji se stavka odnosi kao objekat klase Proizvod.
     */
    public Proizvod getProizvod() {
        return proizvod;
    }

    /**
     * Postavlja proizvod na koji se stavka odnosi na unetu vrednost.
     *
     * @param proizvod Proizvod na koji se stavka odnosi.
     */
    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }
    
    
}
