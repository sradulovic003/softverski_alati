package njt.njt_projekat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import njt.njt_projekat.dto.impl.KuponDto;
import njt.njt_projekat.service.KuponService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/kuponi")
public class KuponController {
	private final KuponService kuponService;

	public KuponController(KuponService kuponService) {
		this.kuponService = kuponService;
	}

	@GetMapping
	public ResponseEntity<List<KuponDto>> getAll() {
		return new ResponseEntity<>(kuponService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<KuponDto> getById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(kuponService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PostMapping
	@Operation(summary = "Kreiranje kupona")
	public ResponseEntity<KuponDto> add(@Valid @RequestBody KuponDto kuponDto) {
		try {
			KuponDto saved = kuponService.create(kuponDto);
			return new ResponseEntity<>(saved, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom cuvanja kupona");
		}
	}

	@GetMapping("/korisnik/{korisnikId}")
	@Operation(summary = "Pregled kupona korisnika")
	public ResponseEntity<List<KuponDto>> getByKorisnik(@PathVariable Long korisnikId) {
		return new ResponseEntity<>(kuponService.findByKorisnikId(korisnikId), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Azuriranje kupona")
	public ResponseEntity<KuponDto> update(@PathVariable Long id, @Valid @RequestBody KuponDto kuponDto) {
		try {
			kuponDto.setKuponId(id);
			return new ResponseEntity<>(kuponService.update(kuponDto), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska pri azuriranju kupona");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		try {
			kuponService.deleteById(id);
			return new ResponseEntity<>("Kupon je uspesno obrisan", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Kupon ne postoji: " + id, HttpStatus.NOT_FOUND);
		}
	}
}
