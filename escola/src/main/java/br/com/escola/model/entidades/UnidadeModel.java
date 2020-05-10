package br.com.escola.model.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "UNIDADE")
public class UnidadeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUnidade;
	private String nome;
	@NotNull
	private Boolean arquivado;

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

	public Boolean getArquivado() {
		return arquivado;
	}

	public void setArquivado(Boolean arquivado) {
		this.arquivado = arquivado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUnidade == null) ? 0 : idUnidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnidadeModel other = (UnidadeModel) obj;
		if (idUnidade == null) {
			if (other.idUnidade != null)
				return false;
		} else if (!idUnidade.equals(other.idUnidade))
			return false;
		return true;
	}

}
