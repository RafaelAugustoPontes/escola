package br.com.escola.view.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PessoaDTO implements OpcaoParaSelect {

	private Integer idPessoa;
	private String nome;
	private String cpf;
	private Integer matricula;
	private LocalDate dataNascimento;
	private String perfilDescricao;
	private String endereco;
	private Integer numero;
	private String bairro;
	private String cidade;
	private String cep;
	private String telefone;
	private String email;
	private Boolean arquivado;
	private String descricaoArquivado;

	public PessoaDTO() {

	}

	public PessoaDTO(Integer idPessoa, String nome) {
		this.idPessoa = idPessoa;
		this.nome = nome;
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

	public String getPerfilDescricao() {
		return perfilDescricao;
	}

	public void setPerfilDescricao(String perfilDescricao) {
		this.perfilDescricao = perfilDescricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getText() {
		return nome;
	}

	public Integer getValue() {
		return idPessoa;
	}

	public Boolean getArquivado() {
		return arquivado;
	}

	public void setArquivado(Boolean arquivado) {
		this.arquivado = arquivado;
	}

	public String getDescricaoArquivado() {
		return descricaoArquivado;
	}

	public void setDescricaoArquivado(String descricaoArquivado) {
		this.descricaoArquivado = descricaoArquivado;
	}

}
