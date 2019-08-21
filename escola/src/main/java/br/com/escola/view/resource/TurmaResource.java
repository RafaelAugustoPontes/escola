package br.com.escola.view.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.controller.TurmaController;
import br.com.escola.model.repository.TurmaRepository;
import br.com.escola.view.dto.TurmaDTO;

@RestController
@RequestMapping("/turma")
public class TurmaResource {

	@Autowired
	private TurmaRepository repository;

	@GetMapping
	public List<TurmaDTO> buscar() {
		return new TurmaController(repository).buscar();
	}

}
