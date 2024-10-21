package com.nequi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SucursalResponseDTO {

    private Long id;
    private String nombre;
    private Long franquiciaId;
    private List<ProductoResponseDTO> productos;
}
