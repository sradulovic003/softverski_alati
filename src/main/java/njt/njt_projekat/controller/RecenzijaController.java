package njt.njt_projekat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import njt.njt_projekat.dto.impl.RecenzijaDto;
import njt.njt_projekat.service.RecenzijaService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/recenzije")
public class RecenzijaController {
	
	private final RecenzijaService recenzijaService;

    public RecenzijaController(RecenzijaService recenzijaService) {
        this.recenzijaService = recenzijaService;
    }

    @GetMapping
    public ResponseEntity<List<RecenzijaDto>> getAll() {
        return new ResponseEntity<>(recenzijaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecenzijaDto> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(recenzijaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Ostavljanje recenzije")
    public ResponseEntity<RecenzijaDto> add(@Valid @RequestBody RecenzijaDto recenzijaDto) {
        try {
            RecenzijaDto saved = recenzijaService.create(recenzijaDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom cuvanja recenzije");
        }
    }

    @GetMapping("/poslasticara/{poslasticaraId}")
    @Operation(summary = "Pregled recenzija poslasticare")
    public ResponseEntity<List<RecenzijaDto>> getByPoslasticara(@PathVariable Long poslasticaraId) {
        return new ResponseEntity<>(recenzijaService.findByPoslasticaraId(poslasticaraId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            recenzijaService.deleteById(id);
            return new ResponseEntity<>("Recenzija je uspesno obrisana", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Recenzija ne postoji: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
