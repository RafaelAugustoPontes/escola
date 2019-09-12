package br.com.escola.model.entidades;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TURMA")
public class TurmaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTurma;

	private String nome;

	@Column(name = "DATA_INICIO")
	private LocalDate dataInicio;

	@Column(name = "DATA_FIM")
	private LocalDate dataFim;

	@ManyToOne
	@JoinColumn(name = "IDUNIDADE")
	private UnidadeModel unidade;

	@ManyToOne
	@JoinColumn(name = "IDCURSO")
	private CursoModel curso;

	@ManyToOne
	@JoinColumn(name = "IDPROFESSOR")
	private PessoaModel professor;

	@Enumerated(EnumType.STRING)
	private TurnoModel turno;

	@OneToMany(mappedBy = "turma", fetch = FetchType.EAGER)
	private Set<PessoaTurmaModel> alunosTurma;

	@ManyToOne
	@JoinColumn(name = "IDESTAGIO")
	private EstagioModel estagio;

	@OneToMany(mappedBy = "turma")
	private Set<AulaModel> aulas;

	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public UnidadeModel getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeModel unidade) {
		this.unidade = unidade;
	}

	public CursoModel getCurso() {
		return curso;
	}

	public void setCurso(CursoModel curso) {
		this.curso = curso;
	}

	public PessoaModel getProfessor() {
		return professor;
	}

	public void setProfessor(PessoaModel professor) {
		this.professor = professor;
	}

	public TurnoModel getTurno() {
		return turno;
	}

	public void setTurno(TurnoModel turno) {
		this.turno = turno;
	}

	public Set<PessoaTurmaModel> getAlunosTurma() {
		return alunosTurma;
	}

	public void setAlunosTurma(Set<PessoaTurmaModel> alunosTurma) {
		this.alunosTurma = alunosTurma;
	}

	public void adicionarAlunoTurma(PessoaTurmaModel alunoTurma) {
		if (alunosTurma == null)
			alunosTurma = new HashSet<>();
		alunosTurma.add(alunoTurma);
	}

	public EstagioModel getEstagio() {
		return estagio;
	}

	public void setEstagio(EstagioModel estagio) {
		this.estagio = estagio;
	}

	public Set<AulaModel> getAulas() {
		return aulas;
	}

	public void setAulas(Set<AulaModel> aulas) {
		this.aulas = aulas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTurma == null) ? 0 : idTurma.hashCode());
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
		TurmaModel other = (TurmaModel) obj;
		if (idTurma == null) {
			if (other.idTurma != null)
				return false;
		} else if (!idTurma.equals(other.idTurma))
			return false;
		return true;
	}

}
