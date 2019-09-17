package br.com.escola.model.entidades;

public enum PerfilModel {

	ADMINISTRADOR("ADMINISTRADOR"), PROFESSOR("PROFESSOR"), ALUNO("ALUNO");

	private String descricao;

	PerfilModel(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDescricaoRole() {
		return "ROLE_" + name();
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
