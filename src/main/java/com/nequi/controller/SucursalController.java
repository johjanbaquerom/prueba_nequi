package com.nequi.controller;

import com.nequi.model.dto.SucursalRequestDTO;
import com.nequi.model.dto.SucursalResponseDTO;
import com.nequi.service.SucursalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/franquicias/{franquiciaId}/sucursales")
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @PostMapping
    public ResponseEntity<SucursalResponseDTO> agregarSucursal(@PathVariable Long franquiciaId, @RequestBody SucursalRequestDTO sucursalRequest) {

        SucursalResponseDTO response = sucursalService.agregarSucursal(franquiciaId, sucursalRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{sucursalId}")
    public ResponseEntity<SucursalResponseDTO> modificarSucursal(@PathVariable Long sucursalId, @RequestBody SucursalRequestDTO sucursalRequest) {

        SucursalResponseDTO response = sucursalService.modificarSucursal(sucursalId, sucursalRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{sucursalId}")
    public ResponseEntity<SucursalResponseDTO> obtenerSucursalPorId(@PathVariable Long franquiciaId, @PathVariable Long sucursalId) {

        SucursalResponseDTO response = sucursalService.obtenerSucursalPorId(franquiciaId, sucursalId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<SucursalResponseDTO>> obtenerSucursalesPorFranquicia(@PathVariable Long franquiciaId) {

        List<SucursalResponseDTO> response = sucursalService.obtenerSucursalesPorFranquicia(franquiciaId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Long id) {

        sucursalService.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
