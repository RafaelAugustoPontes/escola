package br.com.escola.bo.pessoa.dto;

import java.time.LocalDate;

public class PessoaDTO {

	private Integer idPessoa;
	private String nome;
	private String cpf;
	private Integer matricula;
	private LocalDate dataNascimento;

	public PessoaDTO() {

	}

	public PessoaDTO(Integer idPessoa, String nome, String cpf, Integer matricula, LocalDate dataNascimento) {
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.dataNascimento = dataNascimento;
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

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
