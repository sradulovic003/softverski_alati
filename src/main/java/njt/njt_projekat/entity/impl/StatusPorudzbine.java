/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.entity.impl;

/**
 * Predstavlja status porudzbine u sistemu.
 *
 * Svaka porudzbina prolazi kroz odgovarajuce statuse
 * tokom svog zivotnog ciklusa.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
public enum StatusPorudzbine {
	
	/** Porudzbina je kreirana i ceka potvrdu. */
    KREIRANA, 
    
    /** Porudzbina je potvrdjena i u pripremi. */
    POTVRDJENA, 
    
    /** Porudzbina je otkazana. */
    OTKAZANA, 
    
    /** Porudzbina je uspesno zavrsena i isporucena. */
    ZAVRSENA
}
