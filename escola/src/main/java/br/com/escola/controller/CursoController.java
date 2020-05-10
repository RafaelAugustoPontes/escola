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
		List<CursoDTO> cursos = new ArrayList<>();
		repository.findByArquivadoFalse().forEach(curso -> {
			cursos.add(mapper.map(curso, CursoDTO.class));
		});

		return cursos;
	}

	public CursoDTO buscar(Integer id) {
		CursoModel curso = repository.findById(id).get();
		if (curso != null)
			return mapper.map(curso, CursoDTO.class);
		
		return new CursoDTO();
	}

	public CursoDTO persistir(CursoDTO dto) {
		CursoModel map = mapper.map(dto, CursoModel.class);
		if (map.getIdCurso() == null)
			map.setArquivado(false);
		
		CursoModel cursoAtualizado = repository.save(map);

		return mapper.map(cursoAtualizado, CursoDTO.class);
	}

	public CursoDTO arquivar(Integer idUnidade) {
		CursoModel curso = repository.getOne(idUnidade);
		curso.setArquivado(true);
		curso = repository.save(curso);
		return mapper.map(curso, CursoDTO.class);
	}

}
