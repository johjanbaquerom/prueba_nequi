package com.nequi.controller;

import com.nequi.model.dto.FranquiciaRequestDTO;
import com.nequi.model.dto.FranquiciaResponseDTO;
import com.nequi.service.FranquiciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/franquicias")
public class FranquiciaController {

    private final FranquiciaService franquiciaService;

    public FranquiciaController(FranquiciaService franquiciaService) {
        this.franquiciaService = franquiciaService;
    }

    @PostMapping
    public ResponseEntity<FranquiciaResponseDTO> agregarFranquicia(@RequestBody FranquiciaRequestDTO franquiciaRequest) {
        FranquiciaResponseDTO response = franquiciaService.agregarFranquicia(franquiciaRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FranquiciaResponseDTO> modificarFranquicia(@PathVariable Long id, @RequestBody FranquiciaRequestDTO franquiciaRequest) {
        FranquiciaResponseDTO response = franquiciaService.modificarFranquicia(id, franquiciaRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<FranquiciaResponseDTO>> obtenerTodasFranquicias() {
        List<FranquiciaResponseDTO> response = franquiciaService.obtenerTodasFranquicias();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FranquiciaResponseDTO> obtenerFranquicia(@PathVariable Long id) {
        FranquiciaResponseDTO response = franquiciaService.obtenerFranquicia(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFranquicia(@PathVariable Long id) {
        franquiciaService.eliminarFranquicia(id);
        return ResponseEntity.noContent().build();
    }
}
