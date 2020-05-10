package br.com.escola.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.escola.model.entidades.EstagioModel;
import br.com.escola.model.repository.EstagioRepository;
import br.com.escola.view.dto.EstagioDTO;

public class EstagioController {

	private EstagioRepository repository;
	private final ModelMapper mapper = new ModelMapper();

	public EstagioController(EstagioRepository EstagioRepository) {
		this.repository = EstagioRepository;
	}

	public List<EstagioDTO> buscar() {
		List<EstagioDTO> estagio = new ArrayList<>();
		repository.findByArquivadoFalse().forEach(pessoa -> {
			estagio.add(mapper.map(pessoa, EstagioDTO.class));
		});

		return estagio;
	}

	public EstagioDTO buscar(Integer id) {
		EstagioModel estagio = repository.findById(id).get();
		if (estagio != null)
			return mapper.map(estagio, EstagioDTO.class);
		
		return new EstagioDTO();
	}

	public EstagioDTO persistir(EstagioDTO dto) {
		EstagioModel map = mapper.map(dto, EstagioModel.class);
		if (map.getIdEstagio() == null)
			map.setArquivado(false);
			
		EstagioModel estagio = repository.save(map);

		return mapper.map(estagio, EstagioDTO.class);
	}
	
	public EstagioDTO arquivar(Integer idEstagio) {
		EstagioModel estagio = repository.getOne(idEstagio);
		estagio.setArquivado(true);
		estagio = repository.save(estagio);
		return mapper.map(estagio, EstagioDTO.class);
	}

}
