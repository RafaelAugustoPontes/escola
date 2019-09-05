package br.com.escola.model.entidades;

import br.com.escola.view.dto.OpcaoParaSelect;

public enum TurnoModel implements OpcaoParaSelect {

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

	@Override
	public String getText() {
		return this.name();
	}

	@Override
	public Integer getValue() {
		return this.ordinal();
	}

}
