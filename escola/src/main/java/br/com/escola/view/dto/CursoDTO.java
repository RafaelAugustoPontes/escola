package br.com.escola.view.dto;

public class CursoDTO implements OpcaoParaSelect {

	private Integer idCurso;
	private String nome;

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getText() {
		return nome;
	}

	public Integer getValue() {
		return idCurso;
	}

}
