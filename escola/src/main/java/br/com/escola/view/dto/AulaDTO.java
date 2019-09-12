package br.com.escola.view.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AulaDTO {

	private Integer idAula;
	private LocalDate data;
	private String diario;
	private TurmaDTO turma;
	private List<PessoaDTO> alunos = new ArrayList<>();

	public Integer getIdAula() {
		return idAula;
	}

	public void setIdAula(Integer idAula) {
		this.idAula = idAula;
	}

	public LocalDate getData() {
		return data;
	}

	public String getDataAula() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formatter.format(data);
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getDiario() {
		return diario;
	}

	public String getDiarioResumido() {
		if (diario.isEmpty() || diario.length() < 8)
			return diario;
		return diario.substring(0, 8).concat("...");
	}

	public void setDiario(String diario) {
		this.diario = diario;
	}

	public TurmaDTO getTurma() {
		return turma;
	}

	public void setTurma(TurmaDTO turma) {
		this.turma = turma;
	}

	public List<PessoaDTO> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<PessoaDTO> alunos) {
		this.alunos = alunos;
	}

}
