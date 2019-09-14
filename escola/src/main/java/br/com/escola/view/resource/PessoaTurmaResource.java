package br.com.escola.view.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.controller.PessoaTurmaController;
import br.com.escola.model.repository.PessoaTurmaRepository;
import br.com.escola.model.repository.TurmaRepository;
import br.com.escola.view.dto.PessoaTurmaDTO;

@RestController
@RequestMapping("/pessoa-turma")
public class PessoaTurmaResource {

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private PessoaTurmaRepository pessoaTurmaRepository;

	@GetMapping("/{idTurma}")
	public List<PessoaTurmaDTO> buscarPessoasTurma(@PathVariable Integer idTurma) {
		return new PessoaTurmaController(turmaRepository, pessoaTurmaRepository).buscarPessoasTurma(idTurma);
	}

	@PutMapping
	public void atualizarPessoasTurma(@RequestBody List<PessoaTurmaDTO> pessoasTurma) {
		new PessoaTurmaController(turmaRepository, pessoaTurmaRepository).atualizarPessoasTurma(pessoasTurma);
	}

}
