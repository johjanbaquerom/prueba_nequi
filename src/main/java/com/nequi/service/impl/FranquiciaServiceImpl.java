package com.nequi.service.impl;

import com.nequi.model.FranquiciaEntity;
import com.nequi.model.SucursalEntity;
import com.nequi.model.dto.FranquiciaRequestDTO;
import com.nequi.model.dto.FranquiciaResponseDTO;
import com.nequi.model.dto.SucursalResponseDTO;
import com.nequi.repository.FranquiciaRepository;
import com.nequi.repository.SucursalRepository;
import com.nequi.service.FranquiciaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FranquiciaServiceImpl implements FranquiciaService {

    private final FranquiciaRepository franquiciaRepository;
    private final SucursalRepository sucursalRepository;

    public FranquiciaServiceImpl(FranquiciaRepository franquiciaRepository, SucursalRepository sucursalRepository) {
        this.franquiciaRepository = franquiciaRepository;
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public FranquiciaResponseDTO agregarFranquicia(FranquiciaRequestDTO franquiciaRequest) {

        FranquiciaEntity franquicia = new FranquiciaEntity();
        franquicia.setNombre(franquiciaRequest.getNombre());

        franquicia.setSucursales(new ArrayList<>());

        franquicia = franquiciaRepository.save(franquicia);
        return mapToResponseDTO(franquicia);
    }

    @Override
    public FranquiciaResponseDTO modificarFranquicia(Long id, FranquiciaRequestDTO franquiciaActualizada) {
        FranquiciaEntity franquicia = franquiciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));
        franquicia.setNombre(franquiciaActualizada.getNombre());
        franquicia = franquiciaRepository.save(franquicia);
        return mapToResponseDTO(franquicia);
    }

    @Override
    public void eliminarFranquicia(Long id) {
        franquiciaRepository.deleteById(id);
    }

    @Override
    public FranquiciaResponseDTO obtenerFranquicia(Long id) {
        FranquiciaEntity franquicia = franquiciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));
        return mapToResponseDTO(franquicia);
    }

    @Override
    public List<FranquiciaResponseDTO> obtenerTodasFranquicias() {
        return franquiciaRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private FranquiciaResponseDTO mapToResponseDTO(FranquiciaEntity franquicia) {
        FranquiciaResponseDTO responseDTO = new FranquiciaResponseDTO();
        responseDTO.setId(franquicia.getId());
        responseDTO.setNombre(franquicia.getNombre());
        responseDTO.setSucursales(new ArrayList<>());
        return responseDTO;
    }

    private List<SucursalResponseDTO> mapSucursalesToResponseDTO(List<SucursalEntity> sucursales) {
        return sucursales.stream()
                .map(this::mapSucursalToResponseDTO)
                .collect(Collectors.toList());
    }

    private SucursalResponseDTO mapSucursalToResponseDTO(SucursalEntity sucursal) {
        SucursalResponseDTO sucursalResponseDTO = new SucursalResponseDTO();
        sucursalResponseDTO.setId(sucursal.getId());
        sucursalResponseDTO.setNombre(sucursal.getNombre());
        sucursalResponseDTO.setFranquiciaId(sucursal.getFranquicia().getId());
        return sucursalResponseDTO;
    }
}
