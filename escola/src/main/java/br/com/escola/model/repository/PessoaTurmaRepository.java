package br.com.escola.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escola.model.entidades.PessoaTurmaModel;

public interface PessoaTurmaRepository extends JpaRepository<PessoaTurmaModel, Integer> {

}
