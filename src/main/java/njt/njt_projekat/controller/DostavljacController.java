package njt.njt_projekat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import njt.njt_projekat.dto.impl.DostavljacDto;
import njt.njt_projekat.service.DostavljacService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/dostavljaci")
public class DostavljacController {
	
	private final DostavljacService dostavljacService;

    public DostavljacController(DostavljacService dostavljacService) {
        this.dostavljacService = dostavljacService;
    }

    @GetMapping
    public ResponseEntity<List<DostavljacDto>> getAll() {
        return new ResponseEntity<>(dostavljacService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DostavljacDto> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(dostavljacService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Kreiranje dostavljaca")
    public ResponseEntity<DostavljacDto> add(@Valid @RequestBody DostavljacDto dostavljacDto) {
        try {
            DostavljacDto saved = dostavljacService.create(dostavljacDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom cuvanja dostavljaca");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Azuriranje dostavljaca")
    public ResponseEntity<DostavljacDto> update(@PathVariable Long id, @Valid @RequestBody DostavljacDto dostavljacDto) {
        try {
            dostavljacDto.setDostavljacId(id);
            return new ResponseEntity<>(dostavljacService.update(dostavljacDto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska pri azuriranju dostavljaca");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            dostavljacService.deleteById(id);
            return new ResponseEntity<>("Dostavljac je uspesno obrisan", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Dostavljac ne postoji: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
