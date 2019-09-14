package br.com.escola.view.dto.pessoa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AlunoConsultaDTO {

	private Integer idPessoa;
	private String nome;
	private String cpf;
	private Integer matricula;
	private LocalDate dataNascimento;
	private List<DadosAlunoConsultaDTO> dados = new ArrayList<>();

	public void adicionarDado(DadosAlunoConsultaDTO dado) {
		dados.add(dado);
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getDataNascimentoFormatada() {
		if (dataNascimento == null)
			return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formatter.format(dataNascimento);
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<DadosAlunoConsultaDTO> getDados() {
		return dados;
	}

	public void setDados(List<DadosAlunoConsultaDTO> dados) {
		this.dados = dados;
	}

}
