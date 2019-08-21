package br.com.escola.model.entidades;

public enum TurnoModel {

	MANHA("Manhã"), TARDE("Tarde"), NOITE("Noite");

	private String descricao;

	TurnoModel(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
