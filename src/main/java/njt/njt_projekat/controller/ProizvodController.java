/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package njt.njt_projekat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import njt.njt_projekat.dto.impl.ProizvodDto;
import njt.njt_projekat.service.ProizvodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Sara
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/proizvod")
public class ProizvodController {

    private final ProizvodService proizvodService;

    public ProizvodController(ProizvodService proizvodService) {
        this.proizvodService = proizvodService;
    }

    @GetMapping //ako je ruta navedena u requestMapping poziva se ova metoda
    @Operation(summary = "Ucitani su svi proizvodi")  //za automatsku dokumentaciju
    @ApiResponse(responseCode = "200", content = { //za dok
        @Content(schema = @Schema(implementation = ProizvodDto.class), mediaType = "application/json")}) //za heder, schema def struktura jsona koji se vraca
    public ResponseEntity<List<ProizvodDto>> getAll() {
        return new ResponseEntity<>(proizvodService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Kreiranje proizvoda")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = ProizvodDto.class), mediaType = "application/json")
    })
    public ResponseEntity<ProizvodDto> add(@Valid @RequestBody ProizvodDto proizvodDto) { //Uzimamo telo HTTP zahteva (body),Spring automatski parsira JSON iz tog tela,i pretvara ga u objekat klase ProizvodDto,
        try {
            ProizvodDto saved = proizvodService.create(proizvodDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom cuvanja proizvoda");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        try {
            proizvodService.deleteById(id);
            return new ResponseEntity<>("Proizvod je uspesno obrisana", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Proizvod ne postoji: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Azuriranje proizvoda")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = ProizvodDto.class), mediaType = "application/json")
    })
    public ResponseEntity<ProizvodDto> azuriraj(@PathVariable(value = "id") Long id, @Valid @RequestBody ProizvodDto proizvodDto) {
        try {
            proizvodDto.setProizvodId(id);
            ProizvodDto azuriran = proizvodService.update(proizvodDto);
            return new ResponseEntity<>(azuriran, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska pri azuriranju restorana");
        }

    }

    @GetMapping("/poslasticara/{id}")
    public ResponseEntity<List<ProizvodDto>> getByPoslasticaraId(@PathVariable Long id) {
        return new ResponseEntity<>(proizvodService.findByPoslasticaraId(id), HttpStatus.OK);
    }

}
