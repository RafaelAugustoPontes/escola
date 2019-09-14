package br.com.escola.view.dto.turma;

import java.util.ArrayList;
import java.util.List;

public class TurmaConsultaDTO {

	private Integer idTurma;
	private String nomeTurma;
	private String nomeCurso;
	private String nomeEstagio;
	private String nomeProfessor;

	private List<AlunoTurmaDTO> alunos = new ArrayList<>();

	public void adicionarAluno(AlunoTurmaDTO aluno) {
		alunos.add(aluno);
	}

	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}

	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getNomeEstagio() {
		return nomeEstagio;
	}

	public void setNomeEstagio(String nomeEstagio) {
		this.nomeEstagio = nomeEstagio;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	public List<AlunoTurmaDTO> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<AlunoTurmaDTO> alunos) {
		this.alunos = alunos;
	}

}
