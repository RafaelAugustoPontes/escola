package br.com.escola.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.escola.model.entidades.PerfilModel;
import br.com.escola.model.entidades.PessoaModel;
import br.com.escola.model.repository.PessoaRepository;
import br.com.escola.view.dto.OpcaoDTO;
import br.com.escola.view.dto.PessoaDTO;

public class PessoaController {

	private PessoaRepository repository;
	private final ModelMapper mapper = new ModelMapper();

	public PessoaController(PessoaRepository repository) {
		this.repository = repository;
	}

	public PessoaDTO buscar(Integer id) {
		PessoaModel pessoa = repository.findById(id).get();
		if (pessoa != null)
			return mapper.map(pessoa, PessoaDTO.class);

		return new PessoaDTO();
	}

	public List<PessoaDTO> buscar() {
		List<PessoaDTO> pessoas = new ArrayList<>();
		repository.findAll().forEach(pessoa -> {
			pessoas.add(mapper.map(pessoa, PessoaDTO.class));
		});

		return pessoas;
	}

	public PessoaDTO persistir(PessoaDTO dto) {
		try {
			CPFValidator validator = new CPFValidator();
			validator.assertValid(dto.getCpf());
			PessoaModel pessoa = mapper.map(dto, PessoaModel.class);
			if (pessoa.getIdPessoa() == null)
				pessoa.setMatricula(buscarMatricula());
			pessoa.setPerfil(PerfilModel.valueOf(dto.getPerfilDescricao()));
			pessoa = repository.save(pessoa);

			return mapper.map(pessoa, PessoaDTO.class);
		} catch (InvalidStateException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("CPF inválido");
		}
	}

	private Integer buscarMatricula() {
		return repository.findProximaMatricula();
	}

	public List<OpcaoDTO> buscarPerfis() {
		List<OpcaoDTO> opcoes = new ArrayList<>();
		PerfilModel[] perfis = PerfilModel.values();
		for (PerfilModel perfilModel : perfis) {
			OpcaoDTO opcao = new OpcaoDTO();
			opcao.setText(perfilModel.getDescricao());
			opcao.setValue(perfilModel.name());
			opcoes.add(opcao);
		}

		return opcoes;
	}

	public List<PessoaDTO> buscarAlunos() {
		List<PessoaDTO> resultado = new ArrayList<>();
		List<PessoaModel> alunos = repository.findByPerfil(PerfilModel.ALUNO);

		alunos.forEach(pessoa -> {
			resultado.add(mapper.map(pessoa, PessoaDTO.class));
		});

		return resultado;
	}

	public List<PessoaDTO> buscarProfessores() {
		List<PessoaDTO> resultado = new ArrayList<>();
		List<PessoaModel> alunos = repository.findByPerfil(PerfilModel.PROFESSOR);

		alunos.forEach(pessoa -> {
			resultado.add(mapper.map(pessoa, PessoaDTO.class));
		});

		return resultado;
	}

}
