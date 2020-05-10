package br.com.escola.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escola.model.entidades.CursoModel;

public interface CursoRepository extends JpaRepository<CursoModel, Integer> {

	public List<CursoModel> findByArquivadoFalse();
	
}
