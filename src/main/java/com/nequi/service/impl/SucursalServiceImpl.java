package com.nequi.service.impl;

import com.nequi.model.FranquiciaEntity;
import com.nequi.model.ProductoEntity;
import com.nequi.model.SucursalEntity;
import com.nequi.model.dto.ProductoRequestDTO;
import com.nequi.model.dto.ProductoResponseDTO;
import com.nequi.model.dto.SucursalRequestDTO;
import com.nequi.model.dto.SucursalResponseDTO;
import com.nequi.repository.FranquiciaRepository;
import com.nequi.repository.SucursalRepository;
import com.nequi.service.SucursalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;
    private final FranquiciaRepository franquiciaRepository;

    public SucursalServiceImpl(SucursalRepository sucursalRepository,
                               FranquiciaRepository franquiciaRepository) {
        this.sucursalRepository = sucursalRepository;
        this.franquiciaRepository = franquiciaRepository;
    }

    @Override
    public SucursalResponseDTO agregarSucursal(Long franquiciaId, SucursalRequestDTO sucursalRequest) {

        FranquiciaEntity franquicia = franquiciaRepository.findById(franquiciaId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));

        SucursalEntity sucursal = new SucursalEntity();
        sucursal.setNombre(sucursalRequest.getNombre());
        sucursal.setFranquicia(franquicia);

        if (sucursalRequest.getProductos() != null) {
            List<ProductoEntity> productos = sucursalRequest.getProductos().stream()
                    .map(productoRequest -> mapToProductoEntity(productoRequest, sucursal))
                    .collect(Collectors.toList());
            sucursal.setProductos(productos);
        }

        sucursalRepository.save(sucursal);

        return mapToResponseDTO(sucursal);
    }

    @Override
    public SucursalResponseDTO modificarSucursal(Long sucursalId, SucursalRequestDTO sucursalActualizada) {

        SucursalEntity sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        sucursal.setNombre(sucursalActualizada.getNombre());
        sucursalRepository.save(sucursal);

        return mapToResponseDTO(sucursal);
    }

    @Override
    public SucursalResponseDTO obtenerSucursalPorId(Long franquiciaId, Long sucursalId) {

        SucursalEntity sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        if (!sucursal.getFranquicia().getId().equals(franquiciaId)) {
            throw new RuntimeException("La sucursal no pertenece a la franquicia especificada");
        }
        return mapToResponseDTO(sucursal);
    }

    @Override
    public void eliminarSucursal(Long id) {
        sucursalRepository.deleteById(id);
    }

    @Override
    public List<SucursalResponseDTO> obtenerSucursalesPorFranquicia(Long franquiciaId) {
        return sucursalRepository.findByFranquiciaId(franquiciaId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private List<ProductoResponseDTO> mapProductosToResponseDTO(List<ProductoEntity> productos) {
        return productos.stream()
                .map(this::mapProductoToResponseDTO)
                .collect(Collectors.toList());
    }

    private ProductoResponseDTO mapProductoToResponseDTO(ProductoEntity producto) {
        ProductoResponseDTO productoResponseDTO = new ProductoResponseDTO();
        productoResponseDTO.setId(producto.getId());
        productoResponseDTO.setNombre(producto.getNombre());
        productoResponseDTO.setStock(producto.getStock());
        return productoResponseDTO;
    }

    private SucursalResponseDTO mapToResponseDTO(SucursalEntity sucursal) {
        SucursalResponseDTO dto = new SucursalResponseDTO();
        dto.setId(sucursal.getId());
        dto.setNombre(sucursal.getNombre());
        dto.setFranquiciaId(sucursal.getFranquicia().getId());
        dto.setProductos(mapProductosToResponseDTO(sucursal.getProductos()));
        return dto;
    }

    private ProductoEntity mapToProductoEntity(ProductoRequestDTO productoRequest, SucursalEntity sucursal) {
        ProductoEntity producto = new ProductoEntity();
        producto.setNombre(productoRequest.getNombre());
        producto.setStock(productoRequest.getStock());
        producto.setSucursal(sucursal);
        return producto;
    }

}
