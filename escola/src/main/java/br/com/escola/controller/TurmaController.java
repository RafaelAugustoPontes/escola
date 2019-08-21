package br.com.escola.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.escola.model.repository.TurmaRepository;
import br.com.escola.view.dto.TurmaDTO;

public class TurmaController {

	private TurmaRepository repository;
	private final ModelMapper mapper = new ModelMapper();

	public TurmaController(TurmaRepository EstagioRepository) {
		this.repository = EstagioRepository;
	}

	public List<TurmaDTO> buscar() {
		List<TurmaDTO> turma = new ArrayList<>();
		repository.findAll().forEach(pessoa -> {
			turma.add(mapper.map(pessoa, TurmaDTO.class));
		});

		return turma;
	}

	public TurmaDTO persistir(TurmaDTO dto) {
		return null;
	}

}
