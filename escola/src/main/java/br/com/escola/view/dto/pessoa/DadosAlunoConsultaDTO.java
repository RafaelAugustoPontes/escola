package br.com.escola.view.dto.pessoa;

public class DadosAlunoConsultaDTO {
	private String nomeTurma;
	private String nomeCurso;
	private String nomeProfessor;
	private String nomeEstagio;
	private Integer faltas;
	private String situacao;
	private Boolean aprovado;
	private Double nota;
	private Integer quantidadeAulas;

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

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	public String getNomeEstagio() {
		return nomeEstagio;
	}

	public void setNomeEstagio(String nomeEstagio) {
		this.nomeEstagio = nomeEstagio;
	}

	public Integer getFaltas() {
		return faltas;
	}

	public void setFaltas(Integer faltas) {
		this.faltas = faltas;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Boolean getAprovado() {
		return aprovado;
	}

	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Integer getQuantidadeAulas() {
		return quantidadeAulas;
	}

	public void setQuantidadeAulas(Integer quantidadeAulas) {
		this.quantidadeAulas = quantidadeAulas;
	}

}
