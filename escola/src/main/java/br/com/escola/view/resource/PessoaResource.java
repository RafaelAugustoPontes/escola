package br.com.escola.view.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.controller.PessoaController;
import br.com.escola.model.repository.PessoaRepository;
import br.com.escola.view.dto.OpcaoDTO;
import br.com.escola.view.dto.PessoaDTO;
import br.com.escola.view.dto.pessoa.AlunoConsultaDTO;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public List<PessoaDTO> buscar() {
		return new PessoaController(pessoaRepository).buscar();
	}

	@GetMapping("/{id}")
	public PessoaDTO buscar(@PathVariable Integer id) {
		return new PessoaController(pessoaRepository).buscar(id);
	}

	@PostMapping
	public void inserir(@RequestBody PessoaDTO pessoa) {
		new PessoaController(pessoaRepository).persistir(pessoa);
	}

	@PutMapping
	public PessoaDTO atualizar(@RequestBody PessoaDTO pessoa) {
		return new PessoaController(pessoaRepository).persistir(pessoa);
	}

	@GetMapping("/perfis")
	public List<OpcaoDTO> buscarPerfis() {
		return new PessoaController(pessoaRepository).buscarPerfis();
	}

	@GetMapping("/alunos")
	public List<PessoaDTO> buscarAlunos() {
		return new PessoaController(pessoaRepository).buscarAlunos();
	}

	@GetMapping("/alunos/{idPessoa}")
	public AlunoConsultaDTO buscarAlunos(@PathVariable Integer idPessoa) {
		return new PessoaController(pessoaRepository).buscarAluno(idPessoa);
	}

	@GetMapping("/professores")
	public List<PessoaDTO> buscarProfessores() {
		return new PessoaController(pessoaRepository).buscarProfessores();
	}

}
