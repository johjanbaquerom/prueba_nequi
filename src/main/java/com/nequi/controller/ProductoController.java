package com.nequi.controller;

import com.nequi.model.dto.ProductoRequestDTO;
import com.nequi.model.dto.ProductoResponseDTO;
import com.nequi.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursales/{sucursalId}/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> agregarProducto(@PathVariable Long sucursalId, @RequestBody ProductoRequestDTO productoRequest) {
        ProductoResponseDTO response = productoService.agregarProducto(sucursalId, productoRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{productoId}")
    public ResponseEntity<ProductoResponseDTO> modificarProducto(@PathVariable Long productoId, @RequestBody ProductoRequestDTO productoRequest) {
        ProductoResponseDTO response = productoService.modificarProducto(productoId, productoRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{productoId}")
    public ResponseEntity<ProductoResponseDTO> obtenerProductoPorId(@PathVariable Long sucursalId, @PathVariable Long productoId) {
        ProductoResponseDTO response = productoService.obtenerProductoPorId(sucursalId, productoId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/mayor-stock")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerProductosConMayorStockPorSucursal(@PathVariable Long sucursalId) {
        List<ProductoResponseDTO> response = productoService.obtenerProductosConMayorStockPorSucursal(sucursalId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{productoId}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long sucursalId, @PathVariable Long productoId) {
        productoService.eliminarProducto(sucursalId, productoId);
        return ResponseEntity.noContent().build();
    }

}
