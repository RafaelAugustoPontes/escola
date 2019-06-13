package br.com.escola.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.escola.model.PessoaModel;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel,Integer> {
	
}
