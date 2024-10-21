package com.nequi.repository;

import com.nequi.model.SucursalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepository extends JpaRepository<SucursalEntity, Long> {

    List<SucursalEntity> findByFranquiciaId(Long franquiciaId);
}
