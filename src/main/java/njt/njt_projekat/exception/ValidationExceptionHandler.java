/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author Sara
 */
@RestControllerAdvice //hvata greske iz restControllera
public class ValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)  //kad ova metoda vrati odgovor, HTTP status biti 400 Bad Request
    @ExceptionHandler(MethodArgumentNotValidException.class) //metoda se poziva samo kada se desi ovaj izuzetak , to je izuzetak koji Spring baca kada validacija podataka
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage(); // poruka validacije
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
