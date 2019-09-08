package br.com.escola.model.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PESSOA_AULA")
public class PessoaAulaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDPESSOAAULA")
	private Integer idPessoaAula;

	@ManyToOne
	@JoinColumn(name = "IDAULA")
	private AulaModel aula;

	@ManyToOne
	@JoinColumn(name = "IDPESSOA")
	private PessoaModel pessoa;

	private Boolean presente;

	public Integer getIdPessoaAula() {
		return idPessoaAula;
	}

	public void setIdPessoaAula(Integer idPessoaAula) {
		this.idPessoaAula = idPessoaAula;
	}

	public AulaModel getAula() {
		return aula;
	}

	public void setAula(AulaModel aula) {
		this.aula = aula;
	}

	public PessoaModel getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaModel pessoa) {
		this.pessoa = pessoa;
	}

	public Boolean getPresente() {
		return presente;
	}

	public void setPresente(Boolean presente) {
		this.presente = presente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aula == null) ? 0 : aula.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
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
		PessoaAulaModel other = (PessoaAulaModel) obj;
		if (aula == null) {
			if (other.aula != null)
				return false;
		} else if (!aula.equals(other.aula))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}

}
