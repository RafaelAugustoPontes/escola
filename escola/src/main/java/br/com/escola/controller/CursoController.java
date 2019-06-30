package br.com.escola.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.escola.model.entidades.CursoModel;
import br.com.escola.model.repository.CursoRepository;
import br.com.escola.view.dto.CursoDTO;

public class CursoController {

	private CursoRepository repository;
	private final ModelMapper mapper = new ModelMapper();

	public CursoController(CursoRepository CursoRepository) {
		this.repository = CursoRepository;
	}

	public List<CursoDTO> buscar() {
		List<CursoDTO> curso = new ArrayList<>();
		repository.findAll().forEach(pessoa -> {
			curso.add(mapper.map(pessoa, CursoDTO.class));
		});

		return curso;
	}

	public CursoDTO buscar(Integer id) {
		CursoModel curso = repository.findById(id).get();
		if (curso != null)
			return mapper.map(curso, CursoDTO.class);
		
		return new CursoDTO();
	}

	public CursoDTO persistir(CursoDTO dto) {
		CursoModel cursoAtualizado = repository.save(mapper.map(dto, CursoModel.class));

		return mapper.map(cursoAtualizado, CursoDTO.class);
	}

}
