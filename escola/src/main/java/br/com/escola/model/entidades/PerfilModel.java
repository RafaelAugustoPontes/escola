package br.com.escola.model.entidades;

public enum PerfilModel {

	ADMINISTRADOR("Administrador"), PROFESSOR("Professor"), ALUNO("Aluno");

	private String descricao;

	PerfilModel(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
