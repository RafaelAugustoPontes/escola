package br.com.escola.view.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.controller.GraficoController;
import br.com.escola.model.repository.TurmaRepository;
import br.com.escola.view.dto.graficos.pizza.GraficoDePizza;

@RestController
@RequestMapping("/grafico")
public class GraficosResource {

	@Autowired
	private TurmaRepository turmaRepository;

	@GetMapping("/pessoas-turma")
	public GraficoDePizza getPessoasPorTurma() {
		return new GraficoController(turmaRepository).buscarPessoasPorTurma();
	}

	@GetMapping("/turma-unidade")
	public GraficoDePizza getTurmasPorUnidade() {
		return new GraficoController(turmaRepository).buscarTurmasPorUnidade();
	}

}
