package br.com.escola.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ESTAGIO")
public class EstagioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstagio;
	private String nome;


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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEstagio == null) ? 0 : idEstagio.hashCode());
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
		EstagioModel other = (EstagioModel) obj;
		if (idEstagio == null) {
			if (other.idEstagio != null)
				return false;
		} else if (!idEstagio.equals(other.idEstagio))
			return false;
		return true;
	}

}
