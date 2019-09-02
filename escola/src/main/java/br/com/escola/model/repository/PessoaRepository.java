package br.com.escola.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.escola.model.entidades.PerfilModel;
import br.com.escola.model.entidades.PessoaModel;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Integer> {

	@Query(value = "SELECT COALESCE(MAX(MATRICULA) + 1, 1) FROM PESSOA", nativeQuery = true)
	Integer findProximaMatricula();

	List<PessoaModel> findByPerfil(PerfilModel perfil);

	List<PessoaModel> findByMatricula(Integer numero);

	PessoaModel findByCpf(String cpf);

}
