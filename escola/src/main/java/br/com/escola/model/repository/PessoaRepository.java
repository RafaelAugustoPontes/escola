package br.com.escola.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.escola.model.entidades.PerfilModel;
import br.com.escola.model.entidades.PessoaModel;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Integer> {

	@Query(value = "SELECT COALESCE(MAX(MATRICULA) + 1, 1) FROM PESSOA", nativeQuery = true)
	Integer findProximaMatricula();

	@Query(value = "SELECT pessoa.NOME, COUNT(*) FROM PESSOA pessoa " 
			+ " JOIN PESSOA_AULA pessoaAula "
			+ " ON pessoa.IDPESSOA = pessoaAula.IDPESSOA " 
			+ " JOIN PESSOA_TURMA pessoaTurma "
			+ " ON pessoa.IDPESSOA = pessoaTurma.IDPESSOA "
			+ " JOIN TURMA turma "
			+ " ON turma.IDTURMA = pessoaTurma.IDTURMA "
			+ " WHERE pessoaAula.presente = false "
			+ " AND turma.DATA_FIM >= CURDATE() "
			+ " GROUP BY pessoa.NOME " + " ORDER BY COUNT(*) DESC LIMIT 10 ", nativeQuery = true)
	Object[] find10AlunosComMaisFaltas();

	List<PessoaModel> findByPerfil(PerfilModel perfil);
	
	List<PessoaModel> findByPerfilAndArquivadoFalse(PerfilModel perfil);

	List<PessoaModel> findByMatricula(Integer numero);

	@Query("SELECT pessoa FROM PessoaModel pessoa" + " JOIN pessoa.pessoaTurmas pessoaTurmas"
			+ " JOIN pessoaTurmas.turma turma " + " JOIN turma.professor professor "
			+ " WHERE professor.idPessoa = :idPessoa ")
	List<PessoaModel> findAlunoByProfessor(@Param("idPessoa") Integer idPessoa);

	PessoaModel findByCpf(String cpf);

}
