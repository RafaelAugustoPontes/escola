package br.com.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escola.model.PessoaModel;

public interface PessoaRepository extends JpaRepository<PessoaModel,Integer> {
	
}
