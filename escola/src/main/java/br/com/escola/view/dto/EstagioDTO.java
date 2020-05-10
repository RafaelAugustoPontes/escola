package br.com.escola.view.dto;

public class EstagioDTO implements OpcaoParaSelect {

	private Integer idEstagio;
	private String nome;
	private Boolean arquivado;

	public Integer getIdEstagio() {
		return idEstagio;
	}

	public void setIdEstagio(Integer idEstagio) {
		this.idEstagio = idEstagio;
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
		return idEstagio;
	}

	public Boolean getArquivado() {
		return arquivado;
	}

	public void setArquivado(Boolean arquivado) {
		this.arquivado = arquivado;
	}

}
