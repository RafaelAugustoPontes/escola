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
			+ " WHERE turma.DATA_FIM >= CURDATE() "
			+ " GROUP BY turma.NOME ", nativeQuery = true)
	public Object[] contarPessoasPorTurma();

	@Query(" SELECT unidade.nome, COUNT(DISTINCT turma.idTurma) FROM TurmaModel turma JOIN turma.unidade unidade WHERE turma.dataFim >= CURDATE() GROUP BY unidade.nome")
	public Object[] contarTurmasPorUnidade();
	
	public List<TurmaModel> findByArquivadoFalse();

	@Query("SELECT turma FROM TurmaModel turma JOIN FETCH turma.professor professor WHERE professor.idPessoa = :idPessoa ")
	public List<TurmaModel> getByProfessorArquivadoFalse(@Param("idPessoa") Integer idPessoa);

	@Query(value = "SELECT turma.NOME,AVG(pessoaTurma.NOTA) FROM PESSOA_TURMA pessoaTurma " 
			+ " JOIN TURMA turma "
			+ " ON turma.IDTURMA = pessoaTurma.IDTURMA "
			+ " WHERE turma.DATA_FIM >= CURDATE() " 
			+ " GROUP BY turma.NOME "
			+ " HAVING AVG(pessoaTurma.NOTA) IS NOT NULL " + " ORDER BY AVG(pessoaTurma.NOTA)  "
			+ " LIMIT 10 ", nativeQuery = true)
	Object[] find10TurmasMenoresMedias();
	

}
