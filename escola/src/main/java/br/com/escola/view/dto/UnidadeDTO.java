package br.com.escola.view.dto;

public class UnidadeDTO implements OpcaoParaSelect {

	private Integer idUnidade;
	private String nome;

	public Integer getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Integer idUnidade) {
		this.idUnidade = idUnidade;
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
		return idUnidade;
	}

}
