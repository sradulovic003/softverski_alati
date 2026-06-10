/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package njt.njt_projekat.mapper;

/**
 *
 * @author Sara
 */
public interface DtoEntityMapper<T,E>{ //ima dto obj i entity obj
    T toDto(E e);
    E toEntity(T t);
}
