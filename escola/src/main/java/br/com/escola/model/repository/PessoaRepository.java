package br.com.escola.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.escola.model.entidades.PerfilModel;
import br.com.escola.model.entidades.PessoaModel;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Integer> {

	@Query("SELECT MAX(pessoa.matricula) FROM PessoaModel pessoa")
	Integer findProximaMatricula();

	List<PessoaModel>findByPerfil(PerfilModel perfil);

}
