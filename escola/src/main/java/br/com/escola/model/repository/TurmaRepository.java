package br.com.escola.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.escola.model.entidades.TurmaModel;

@Repository
public interface TurmaRepository extends JpaRepository<TurmaModel, Integer> {

	@Query(value = "SELECT turma.NOME, COUNT(pessoa_turma.IDPESSOA) FROM PESSOA_TURMA pessoa_turma "
			+ " JOIN TURMA turma " + " ON pessoa_turma.IDTURMA = turma.IDTURMA "
			+ " GROUP BY turma.NOME ", nativeQuery = true)
	public Object[] contarPessoasPorTurma();

	@Query(" SELECT unidade.nome, COUNT(DISTINCT turma.idTurma) FROM TurmaModel turma JOIN turma.unidade unidade GROUP BY unidade.nome")
	public Object[] contarTurmasPorUnidade();

	@Query("SELECT turma FROM TurmaModel turma JOIN FETCH turma.professor professor WHERE professor.idPessoa = :idPessoa ")
	public List<TurmaModel> getByProfessor(@Param("idPessoa") Integer idPessoa);

}
