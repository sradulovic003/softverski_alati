/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Sara
 */
@SpringBootApplication  //kombinuje @Configuration - klasa je konfigur i moze sadrzati def bina
                        //, @EnableAutoConfiguration, -- automatska konf na osnovu dodatih bibl
                        //@ComponentScan  - govori springu da u ovom i svim podpaketima sve anotacije i registruje beanove
public class DessertsDeliveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(DessertsDeliveryApplication.class, args);  //pokrece app
    }
}
