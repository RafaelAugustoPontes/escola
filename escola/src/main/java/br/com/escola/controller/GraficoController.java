package br.com.escola.controller;

import br.com.escola.model.repository.TurmaRepository;
import br.com.escola.view.dto.graficos.pizza.GraficoDePizza;

public class GraficoController {

	private TurmaRepository turmaRepository;

	public GraficoController(TurmaRepository turmaRepository) {
		this.turmaRepository = turmaRepository;
	}

	public GraficoDePizza buscarPessoasPorTurma() {
		GraficoDePizza grafico = new GraficoDePizza();
		Object[] pessoasPorTurma = turmaRepository.contarPessoasPorTurma();
		for (Object linha : pessoasPorTurma) {
			Object[] array = (Object[]) linha;
			grafico.addSerie(Integer.valueOf(array[1].toString()));
			grafico.addLabel(array[0].toString());
		}

		return grafico;
	}

	public GraficoDePizza buscarTurmasPorUnidade() {
		GraficoDePizza grafico = new GraficoDePizza();
		Object[] turmasPorUnidade = turmaRepository.contarTurmasPorUnidade();
		for (Object linha : turmasPorUnidade) {
			Object[] array = (Object[]) linha;
			grafico.addSerie(Integer.valueOf(array[1].toString()));
			grafico.addLabel(array[0].toString());
		}
		return grafico;
	}

}
