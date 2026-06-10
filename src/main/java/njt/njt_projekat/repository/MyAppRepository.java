/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package njt.njt_projekat.repository;

import java.util.List;

/**
 *
 * @author Sara
 */
public interface MyAppRepository<E,ID> { //E - entity, obj nad kojim vrsimo op, id-tip prim kljuca
    List<E> findAll();
    E findById(ID id) throws Exception;
    void save(E entity); //ako ima id -> azuriranje, ako nema -> kreiranje
    void deleteById(ID id);
    
}
