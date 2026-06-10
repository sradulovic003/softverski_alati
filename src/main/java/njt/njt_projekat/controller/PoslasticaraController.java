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
import jakarta.validation.constraints.NotNull;
import java.util.List;
import njt.njt_projekat.dto.impl.PoslasticaraDto;
import njt.njt_projekat.service.PoslasticaraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Sara
 */
@CrossOrigin(origins="http://localhost:3000") //dajemo dozvolu sa kog porta mozemo daprimamo zahteve, jer su spring i react na razlicitim portovima
@RestController  //naglasava Springu da je kontroler
@RequestMapping("/api/poslasticara")  //da li zahtevi tako pocinju, ako da onda je ovaj kontroler zaduzen za to
public class PoslasticaraController {

    private final PoslasticaraService poslasticaraService;

    public PoslasticaraController(PoslasticaraService poslasticaraService) {
        this.poslasticaraService = poslasticaraService;
    }

    @GetMapping //ako je ruta navedena u requestMapping poziva se ova metoda
    @Operation(summary = "Ucitane su sve poslasticare")  //za automatsku dokumentaciju
    @ApiResponse(responseCode = "200", content = {
    @Content(schema = @Schema(implementation = PoslasticaraDto.class), mediaType = "application/json")}) //za heder, schema def struktura jsona koji se vraca
    public ResponseEntity<List<PoslasticaraDto>> getAll() {
        return new ResponseEntity<>(poslasticaraService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoslasticaraDto> getById(
            @NotNull(message = "Parametar ne sme da bude null ili prazan")
            @PathVariable(value = "id") Long id) { //izvlaci vredsnot iz url i dodeljuje ga kao param metodi
        try {
            return new ResponseEntity<>(poslasticaraService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RestaurantController error");
        }
    }

    @PostMapping
    @Operation(summary = "Kreiranje poslasticare")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = PoslasticaraDto.class), mediaType = "application/json")
    })
    public ResponseEntity<PoslasticaraDto> add(@Valid @RequestBody PoslasticaraDto poslasticaraDto) {
        try {
            PoslasticaraDto saved = poslasticaraService.create(poslasticaraDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom cuvanja poslasticare");
        }
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value="id") Long id){
        try {
            poslasticaraService.deleteById(id);
            return new ResponseEntity<>("Poslasticara je uspesno obrisana",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Poslasticara ne postoji: "+id,HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Azuriranje poslasticare")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = PoslasticaraDto.class), mediaType = "application/json")
    })
    public ResponseEntity<PoslasticaraDto>azuriraj(@PathVariable(value="id") Long id, @Valid @RequestBody PoslasticaraDto poslasticaraDto){
        try{
            poslasticaraDto.setPoslasticaraId(id);
            PoslasticaraDto azurirana = poslasticaraService.update(poslasticaraDto);
            return new ResponseEntity<>(azurirana,HttpStatus.OK);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Greska pri azuriranju restorana");
        }
        
    }
}
