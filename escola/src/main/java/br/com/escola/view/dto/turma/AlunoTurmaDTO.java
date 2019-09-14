package br.com.escola.view.dto.turma;

public class AlunoTurmaDTO {

	private Integer idPessoa;
	private String nome;
	private Double nota;
	private Integer falta;
	private Integer quantidadeAulas;
	private Boolean aprovado;

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Integer getFalta() {
		return falta;
	}

	public void setFalta(Integer falta) {
		this.falta = falta;
	}

	public Integer getQuantidadeAulas() {
		return quantidadeAulas;
	}

	public void setQuantidadeAulas(Integer quantidadeAulas) {
		this.quantidadeAulas = quantidadeAulas;
	}

	public Boolean getAprovado() {
		return aprovado;
	}

	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}

}
