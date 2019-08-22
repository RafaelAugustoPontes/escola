package br.com.escola.model.entidades;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AULA")
public class AulaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAula;

	private LocalDate data;

	private String diario;

	@ManyToOne
	@JoinColumn(name = "IDTURMA")
	private TurmaModel turma;

	@OneToMany(mappedBy = "aula")
	private Set<PessoaAulaModel> pessoasAula;

	public Integer getIdAula() {
		return idAula;
	}

	public void setIdAula(Integer idAula) {
		this.idAula = idAula;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getDiario() {
		return diario;
	}

	public void setDiario(String diario) {
		this.diario = diario;
	}

	public TurmaModel getTurma() {
		return turma;
	}

	public void setTurma(TurmaModel turma) {
		this.turma = turma;
	}

	public Set<PessoaAulaModel> getPessoasAula() {
		return pessoasAula;
	}

	public void setPessoasAula(Set<PessoaAulaModel> pessoasAula) {
		this.pessoasAula = pessoasAula;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAula == null) ? 0 : idAula.hashCode());
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
		AulaModel other = (AulaModel) obj;
		if (idAula == null) {
			if (other.idAula != null)
				return false;
		} else if (!idAula.equals(other.idAula))
			return false;
		return true;
	}

}
