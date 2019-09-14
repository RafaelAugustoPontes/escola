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
@Table(name = "PESSOA_TURMA")
public class PessoaTurmaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDPESSOATURMA")
	private Integer idPessoaTurma;

	@ManyToOne
	@JoinColumn(name = "IDPESSOA", nullable = false, insertable = true, updatable = true)
	private PessoaModel pessoa;

	@ManyToOne
	@JoinColumn(name = "IDTURMA", nullable = false, insertable = true, updatable = true)
	private TurmaModel turma;

	private Double nota;

	public int calcularFaltas() {
		int total = 0;
		if (turma.getAulas() != null && !turma.getAulas().isEmpty()) {
			for (AulaModel aula : turma.getAulas()) {
				if (aula.getPessoasAula() != null) {
					for (PessoaAulaModel pessoaAula : aula.getPessoasAula()) {
						if (!pessoaAula.getPresente()
								&& pessoaAula.getPessoa().getIdPessoa().equals(pessoa.getIdPessoa()))
							total++;
					}
				}
			}
		}
		return total;
	}

	public int quantidadeDeAulas() {
		if (turma.getAulas() == null)
			return 0;
		return turma.getAulas().size();
	}

	public boolean aprovado() {
		if (nota == null || nota < 70D)
			return false;
		return true;
	}

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

	public TurmaModel getTurma() {
		return turma;
	}

	public void setTurma(TurmaModel turma) {
		this.turma = turma;
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
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
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
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		return true;
	}

}
