package br.com.escola.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escola.model.entidades.CursoModel;

public interface CursoRepository extends JpaRepository<CursoModel, Integer> {

	
}
