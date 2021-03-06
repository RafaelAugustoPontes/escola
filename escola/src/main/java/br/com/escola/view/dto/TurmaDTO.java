package br.com.escola.view.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.escola.view.util.DataUtil;

public class TurmaDTO implements OpcaoParaSelect {

	private Integer idTurma;
	private String nome;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private UnidadeDTO unidade;
	private CursoDTO curso;
	private PessoaDTO professor;
	private String turno;
	private List<PessoaDTO> alunos;
	private EstagioDTO estagio;
	private Boolean arquivado;

	public void adicionarAluno(PessoaDTO aluno) {
		if (alunos == null)
			alunos = new ArrayList<>();
		alunos.add(aluno);
	}

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
	
	public String getDataInicioFormatada() {
		return DataUtil.formatar(dataInicio);
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}
	
	public String getDataFimFormatada() {
		return DataUtil.formatar(dataFim);
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public UnidadeDTO getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeDTO unidade) {
		this.unidade = unidade;
	}

	public CursoDTO getCurso() {
		return curso;
	}

	public void setCurso(CursoDTO curso) {
		this.curso = curso;
	}

	public PessoaDTO getProfessor() {
		return professor;
	}

	public void setProfessor(PessoaDTO professor) {
		this.professor = professor;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public List<PessoaDTO> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<PessoaDTO> alunos) {
		this.alunos = alunos;
	}

	public EstagioDTO getEstagio() {
		return estagio;
	}

	public void setEstagio(EstagioDTO estagio) {
		this.estagio = estagio;
	}

	@Override
	public String getText() {
		return getNome();
	}

	@Override
	public Integer getValue() {
		return getIdTurma();
	}

	public Boolean getArquivado() {
		return arquivado;
	}

	public void setArquivado(Boolean arquivado) {
		this.arquivado = arquivado;
	}

}
