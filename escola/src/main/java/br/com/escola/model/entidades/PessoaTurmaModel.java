package br.com.escola.model.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PESSOA_TURMA")
public class PessoaTurmaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPessoaTurma;

	@ManyToOne
	private PessoaModel pessoa;

	private Double nota;

	public Integer getIdPessoaTurma() {
		return idPessoaTurma;
	}

	public void setIdPessoaTurma(Integer idPessoaTurma) {
		this.idPessoaTurma = idPessoaTurma;
	}

	public PessoaModel getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaModel pessoa) {
		this.pessoa = pessoa;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPessoaTurma == null) ? 0 : idPessoaTurma.hashCode());
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
		PessoaTurmaModel other = (PessoaTurmaModel) obj;
		if (idPessoaTurma == null) {
			if (other.idPessoaTurma != null)
				return false;
		} else if (!idPessoaTurma.equals(other.idPessoaTurma))
			return false;
		return true;
	}

}
