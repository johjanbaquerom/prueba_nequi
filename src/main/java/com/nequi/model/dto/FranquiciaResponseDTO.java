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
public class FranquiciaResponseDTO {

    private Long id;
    private String nombre;
    private List<SucursalResponseDTO> sucursales;

}
