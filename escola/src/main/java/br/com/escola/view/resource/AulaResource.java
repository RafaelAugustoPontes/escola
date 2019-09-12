package br.com.escola.view.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.controller.AulaController;
import br.com.escola.model.repository.AulaRepository;
import br.com.escola.model.repository.PessoaAulaRepository;
import br.com.escola.model.repository.TurmaRepository;
import br.com.escola.view.dto.AulaDTO;

@RestController
@RequestMapping("/aula")
public class AulaResource {

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private AulaRepository aulaRepository;

	@Autowired
	private PessoaAulaRepository pessoaAulaRepository;

	@GetMapping("/{idTurma}")
	public List<AulaDTO> buscarAulasPorTurma(@PathVariable Integer idTurma) {
		return new AulaController(turmaRepository, aulaRepository, pessoaAulaRepository).buscarAulas(idTurma);
	}

	@PostMapping
	@Transactional(rollbackFor = Exception.class)
	public void gravar(@RequestBody AulaDTO dto) {
		new AulaController(turmaRepository, aulaRepository, pessoaAulaRepository).inserir(dto);
	}

	@PutMapping
	@Transactional(rollbackFor = Exception.class)
	public void atualizar(@RequestBody AulaDTO dto) {
		new AulaController(turmaRepository, aulaRepository, pessoaAulaRepository).atualizar(dto);
	}

}
