package com.nequi.repository;

import com.nequi.model.FranquiciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranquiciaRepository extends JpaRepository<FranquiciaEntity, Long> {
}
