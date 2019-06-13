package br.com.escola.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.com.escola.model.PessoaModel;
import br.com.escola.model.repository.PessoaRepository;
import br.com.escola.view.dto.PessoaDTO;

public class PessoaBO {

	private PessoaRepository repository;

	public PessoaBO(PessoaRepository repository) {
		this.repository = repository;
	}

	public PessoaDTO buscarUsuario(Integer id) {
		PessoaModel pessoa = repository.findById(id).get();
		PessoaDTO dto = new PessoaDTO();
		if (pessoa != null)
			BeanUtils.copyProperties(pessoa, dto);
		return dto;
	}

	public List<PessoaDTO> buscarUsuarios() {
		List<PessoaDTO> pessoas = new ArrayList<>();
		repository.findAll().forEach(pessoa -> {
			PessoaDTO dto = new PessoaDTO();
			BeanUtils.copyProperties(pessoa, dto);
			pessoas.add(dto);
		});

		return pessoas;
	}
	
	public void cadastrarPessoa(PessoaDTO dto) {
		PessoaModel pessoaModel = new PessoaModel();
		BeanUtils.copyProperties(dto, pessoaModel);
		repository.save(pessoaModel);
	}


}
