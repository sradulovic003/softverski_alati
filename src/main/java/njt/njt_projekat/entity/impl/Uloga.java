/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package njt.njt_projekat.entity.impl;

/**
 * Predstavlja ulogu korisnika u sistemu.
 *
 * Uloga odredjuje koje operacije korisnik moze da izvrsava u sistemu.
 *
 * @author Sara Radulovic
 * @version 1.0
 */
public enum Uloga {
	
	/**
     * Obican korisnik sistema.
     * Moze da se registruje, prijavi, kreira porudzbinu,
     * ostavi recenziju i pregleda svoje kupone.
     */
    USER, 
    
    /**
     * Administrator sistema.
     * Moze da kreira, azurira, pretrazuje i brise poslasticare i proizvode,
     * upravlja porudzbinama, kreira dostavljace, dodeljuje dostavljace
     * porudzbinama i kreira kupone.
     */
    ADMIN;
}
