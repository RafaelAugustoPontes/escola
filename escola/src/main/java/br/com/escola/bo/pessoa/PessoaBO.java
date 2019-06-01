package br.com.escola.bo.pessoa;

import java.util.ArrayList;
import java.util.List;

import br.com.escola.bo.pessoa.dto.PessoaDTO;
import br.com.escola.model.PessoaModel;
import br.com.escola.repository.PessoaRepository;

public class PessoaBO {

	private PessoaRepository repository;

	public PessoaBO(PessoaRepository repository) {
		this.repository = repository;
	}

	public List<PessoaDTO> buscarUsuarios() {
		List<PessoaDTO> pessoas = new ArrayList<>();
		repository.findAll().forEach(pessoa -> pessoas.add(new PessoaDTO(pessoa.getIdPessoa(), pessoa.getNome(),
				pessoa.getCpf(), pessoa.getMatricula(), pessoa.getDataNascimento())));

		return pessoas;
	}

	public void cadastrarPessoa(PessoaDTO pessoa) {
		PessoaModel pessoaModel = obterPessoa(pessoa);
		repository.save(pessoaModel);
	}

	public PessoaModel obterPessoa(PessoaDTO dto) {
		PessoaModel model = new PessoaModel();

		model.setCpf(dto.getCpf());
		model.setDataNascimento(dto.getDataNascimento());
		model.setMatricula(dto.getMatricula());
		model.setNome(dto.getNome());

		return model;
	}

}
