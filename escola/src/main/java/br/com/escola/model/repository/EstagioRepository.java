package br.com.escola.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escola.model.entidades.EstagioModel;

public interface EstagioRepository extends JpaRepository<EstagioModel, Integer> {

}
