package com.quipux.prova.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonJpaRepository extends JpaRepository<PersonEntity, UUID> {

	boolean existsByDocumento(String documento);
}
