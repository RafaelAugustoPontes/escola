package br.com.escola.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.escola.model.entidades.UnidadeModel;
import br.com.escola.model.repository.UnidadeRepository;
import br.com.escola.view.dto.UnidadeDTO;

public class UnidadeController {

	private UnidadeRepository repository;
	private final ModelMapper mapper = new ModelMapper();

	public UnidadeController(UnidadeRepository unidadeRepository) {
		this.repository = unidadeRepository;
	}

	public List<UnidadeDTO> buscar() {
		List<UnidadeDTO> unidades = new ArrayList<>();
		repository.findByArquivadoFalse().forEach(unidade -> {
			unidades.add(mapper.map(unidade, UnidadeDTO.class));
		});

		return unidades;
	}

	public UnidadeDTO buscar(Integer id) {
		UnidadeModel unidade = repository.findById(id).get();
		if (unidade != null)
			return mapper.map(unidade, UnidadeDTO.class);
		
		return new UnidadeDTO();
	}

	public UnidadeDTO persistir(UnidadeDTO dto) {
		UnidadeModel unidadeModel = mapper.map(dto, UnidadeModel.class);
		unidadeModel.setArquivado(false);
		UnidadeModel unidadeAtualizada = repository.save(unidadeModel);

		return mapper.map(unidadeAtualizada, UnidadeDTO.class);
	}

	public UnidadeDTO arquivar(Integer idUnidade) {
		UnidadeModel unidadeModel = repository.getOne(idUnidade);
		unidadeModel.setArquivado(true);
		repository.save(unidadeModel);
		
		return mapper.map(unidadeModel, UnidadeDTO.class);
	}

}
