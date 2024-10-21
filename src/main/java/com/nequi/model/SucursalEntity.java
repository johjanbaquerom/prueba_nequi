package com.nequi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SucursalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "franquicia_id")
    private FranquiciaEntity franquicia;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL)
    private List<ProductoEntity> productos = new ArrayList<>();
}
