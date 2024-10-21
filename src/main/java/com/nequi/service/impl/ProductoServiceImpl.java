package com.nequi.service.impl;

import com.nequi.model.ProductoEntity;
import com.nequi.model.SucursalEntity;
import com.nequi.model.dto.ProductoRequestDTO;
import com.nequi.model.dto.ProductoResponseDTO;
import com.nequi.repository.ProductoRepository;
import com.nequi.repository.SucursalRepository;
import com.nequi.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final SucursalRepository sucursalRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository,
                               SucursalRepository sucursalRepository) {
        this.productoRepository = productoRepository;
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public ProductoResponseDTO agregarProducto(Long sucursalId, ProductoRequestDTO productoRequest) {

        SucursalEntity sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        ProductoEntity producto = new ProductoEntity();
        producto.setNombre(productoRequest.getNombre());
        producto.setStock(productoRequest.getStock());
        producto.setSucursal(sucursal);

        producto = productoRepository.save(producto);
        return mapToResponseDTO(producto);

    }

    @Override
    public ProductoResponseDTO modificarProducto(Long productoId, ProductoRequestDTO productoActualizado) {

        ProductoEntity producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setNombre(productoActualizado.getNombre());
        producto.setStock(productoActualizado.getStock());

        producto = productoRepository.save(producto);
        return mapToResponseDTO(producto);
    }

    @Override
    public ProductoResponseDTO obtenerProductoPorId(Long sucursalId, Long productoId) {

        ProductoEntity producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (!producto.getSucursal().getId().equals(sucursalId)) {
            throw new RuntimeException("El producto no pertenece a la sucursal indicada");
        }

        return mapToResponseDTO(producto);
    }

    @Override
    public void eliminarProducto(Long sucursalId,Long productoId) {
        sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        productoRepository.deleteById(productoId);
    }

    @Override
    public List<ProductoResponseDTO> obtenerProductosConMayorStockPorSucursal(Long sucursalId) {

        SucursalEntity sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        List<ProductoEntity> productos = productoRepository.findBySucursalId(sucursalId);

        if (productos.isEmpty()) {
            return Collections.emptyList();
        }

        ProductoEntity productoConMayorStock = productos.stream()
                .max(Comparator.comparingInt(ProductoEntity::getStock))
                .orElseThrow(() -> new RuntimeException("No se pudo encontrar el producto con mayor stock"));

        ProductoResponseDTO productoResponseDTO = mapToResponseDTO(productoConMayorStock);

        return Collections.singletonList(productoResponseDTO);
    }

    private ProductoResponseDTO mapToResponseDTO(ProductoEntity producto) {

        ProductoResponseDTO responseDTO = new ProductoResponseDTO();
        responseDTO.setId(producto.getId());
        responseDTO.setNombre(producto.getNombre());
        responseDTO.setStock(producto.getStock());
        responseDTO.setSucursalId(producto.getSucursal().getId());
        return responseDTO;
    }
}
