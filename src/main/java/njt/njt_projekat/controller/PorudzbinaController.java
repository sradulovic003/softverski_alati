/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import njt.njt_projekat.dto.impl.PorudzbinaDto;
import njt.njt_projekat.entity.impl.StatusPorudzbine;
import njt.njt_projekat.service.PorudzbinaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Sara
 */
@CrossOrigin(origins="http://localhost:3000") 
@RestController 
@RequestMapping("/api/porudzbine")
public class PorudzbinaController {
    private final PorudzbinaService service;

    public PorudzbinaController(PorudzbinaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PorudzbinaDto>> all() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PorudzbinaDto> byId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Kreiranje porudzbine sa stavkama u jednoj transakciji")
    public ResponseEntity<PorudzbinaDto> create(@Valid @RequestBody @NotNull PorudzbinaDto dto) {
        try {
            PorudzbinaDto saved = service.create(dto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Porudzbina je kreirana: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}/status")  //put kad azuriramo ceo obj ili nekoliko polja, patch samo za jedno polje (ovde statu)
    public ResponseEntity<PorudzbinaDto> updateStatus(@PathVariable Long id, @RequestParam StatusPorudzbine status) {
        try {
            return new ResponseEntity<>(service.updateStatus(id, status), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>("Porudzbina je obrisana", HttpStatus.OK);
    }
}
