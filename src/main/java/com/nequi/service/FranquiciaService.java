package com.nequi.service;

import com.nequi.model.dto.FranquiciaRequestDTO;
import com.nequi.model.dto.FranquiciaResponseDTO;

import java.util.List;

public interface FranquiciaService {

    FranquiciaResponseDTO agregarFranquicia(FranquiciaRequestDTO franquiciaRequest);
    FranquiciaResponseDTO modificarFranquicia(Long id, FranquiciaRequestDTO franquiciaActualizada);
    void eliminarFranquicia(Long id);
    FranquiciaResponseDTO obtenerFranquicia(Long id);
    List<FranquiciaResponseDTO> obtenerTodasFranquicias();
}
