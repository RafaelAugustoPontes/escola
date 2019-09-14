package br.com.escola.view.dto;

public class PessoaTurmaDTO {

	private Integer idPessoaTurma;
	private PessoaDTO pessoa;
	private TurmaDTO turma;
	private Double nota;

	public Integer getIdPessoaTurma() {
		return idPessoaTurma;
	}

	public void setIdPessoaTurma(Integer idPessoaTurma) {
		this.idPessoaTurma = idPessoaTurma;
	}

	public PessoaDTO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaDTO pessoa) {
		this.pessoa = pessoa;
	}

	public TurmaDTO getTurma() {
		return turma;
	}

	public void setTurma(TurmaDTO turma) {
		this.turma = turma;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

}
