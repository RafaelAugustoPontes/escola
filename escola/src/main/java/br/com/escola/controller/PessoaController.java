package br.com.escola.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.escola.model.entidades.PerfilModel;
import br.com.escola.model.entidades.PessoaModel;
import br.com.escola.model.repository.PessoaRepository;
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

			// TODO Remover
			PessoaModel pessoa = mapper.map(dto, PessoaModel.class);
			pessoa.setPerfil(PerfilModel.ADMINISTRADOR);
			pessoa = repository.save(pessoa);

			return mapper.map(pessoa, PessoaDTO.class);
		} catch (InvalidStateException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("CPF inválido");
		}
	}

	public Integer buscarMatricula() {
		return 1;
	}

}
