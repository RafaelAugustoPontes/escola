package br.com.escola.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.escola.model.entidades.AulaModel;

@Repository
public interface AulaRepository extends JpaRepository<AulaModel, Integer> {

}
