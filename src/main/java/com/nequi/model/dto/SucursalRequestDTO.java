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
public class SucursalRequestDTO {

    private String nombre;
    private List<ProductoRequestDTO> productos;
}
