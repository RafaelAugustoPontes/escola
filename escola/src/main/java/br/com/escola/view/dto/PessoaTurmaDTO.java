package br.com.escola.view.dto;

public class PessoaTurmaDTO {

	private Integer idPessoaTurma;
	private PessoaDTO pessoa;
	private Integer turmaIdTurma;
	private Integer turmaNome;
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

	public Integer getTurmaIdTurma() {
		return turmaIdTurma;
	}

	public void setTurmaIdTurma(Integer turmaIdTurma) {
		this.turmaIdTurma = turmaIdTurma;
	}

	public Integer getTurmaNome() {
		return turmaNome;
	}

	public void setTurmaNome(Integer turmaNome) {
		this.turmaNome = turmaNome;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

}
