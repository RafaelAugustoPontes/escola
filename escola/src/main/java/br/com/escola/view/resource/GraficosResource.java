package br.com.escola.view.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.controller.GraficoController;
import br.com.escola.model.repository.PessoaRepository;
import br.com.escola.model.repository.TurmaRepository;
import br.com.escola.view.dto.graficos.barra.GraficoEmBarra;
import br.com.escola.view.dto.graficos.pizza.GraficoDePizza;

@RestController
@RequestMapping("/grafico")
public class GraficosResource {

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping("/pessoas-turma")
	public GraficoDePizza getPessoasPorTurma() {
		return new GraficoController(turmaRepository, pessoaRepository).buscarPessoasPorTurma();
	}

	@GetMapping("/turma-unidade")
	public GraficoEmBarra getTurmasPorUnidade() {
		return new GraficoController(turmaRepository, pessoaRepository).buscarTurmasPorUnidade();
	}

	@GetMapping("/aluno-falta")
	public GraficoEmBarra buscarAlunosComMaisFaltas() {
		return new GraficoController(turmaRepository, pessoaRepository).buscarAlunosComMaisFaltas();
	}

	@GetMapping("/turma-media")
	public GraficoEmBarra buscarTurmasMenoresMedias() {
		return new GraficoController(turmaRepository, pessoaRepository).buscarTurmasMenoresMedias();
	}

}
