package br.com.escola.controller;

import br.com.escola.model.repository.PessoaRepository;
import br.com.escola.model.repository.TurmaRepository;
import br.com.escola.view.dto.graficos.barra.GraficoEmBarra;
import br.com.escola.view.dto.graficos.barra.Options;
import br.com.escola.view.dto.graficos.barra.Serie;
import br.com.escola.view.dto.graficos.barra.Xaxis;
import br.com.escola.view.dto.graficos.pizza.GraficoDePizza;

public class GraficoController {

	private TurmaRepository turmaRepository;
	private PessoaRepository pessoaRepository;

	public GraficoController(TurmaRepository turmaRepository, PessoaRepository pessoaRepository) {
		this.turmaRepository = turmaRepository;
		this.pessoaRepository = pessoaRepository;
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

	public GraficoEmBarra buscarTurmasPorUnidade() {
		GraficoEmBarra grafico = new GraficoEmBarra();
		Object[] turmasPorUnidade = turmaRepository.contarTurmasPorUnidade();
		Options options = new Options();
		Xaxis xaxis = new Xaxis();
		xaxis.addCategorie("Turmas");
		options.setXaxis(xaxis);
		grafico.setOptions(options);

		for (Object linha : turmasPorUnidade) {
			Object[] array = (Object[]) linha;
			Serie serie = new Serie(array[0].toString());
			serie.addData(Integer.valueOf(array[1].toString()));
			grafico.addSerie(serie);
		}
		return grafico;
	}

	public GraficoEmBarra buscarAlunosComMaisFaltas() {
		GraficoEmBarra grafico = new GraficoEmBarra();
		Object[] alunosComMaisFaltas = pessoaRepository.find10AlunosComMaisFaltas();
		Options options = new Options();
		Xaxis xaxis = new Xaxis();
		xaxis.addCategorie("Turmas");
		options.setXaxis(xaxis);
		grafico.setOptions(options);

		for (Object linha : alunosComMaisFaltas) {
			Object[] array = (Object[]) linha;
			Serie serie = new Serie(array[0].toString());
			serie.addData(Integer.valueOf(array[1].toString()));
			grafico.addSerie(serie);
		}
		return grafico;
	}

	public GraficoEmBarra buscarTurmasMenoresMedias() {
		GraficoEmBarra grafico = new GraficoEmBarra();
		Object[] turmasMenoresMedias = turmaRepository.find10TurmasMenoresMedias();
		Options options = new Options();
		Xaxis xaxis = new Xaxis();
		xaxis.addCategorie("Turmas");
		options.setXaxis(xaxis);
		grafico.setOptions(options);

		for (Object linha : turmasMenoresMedias) {
			Object[] array = (Object[]) linha;
			Serie serie = new Serie(array[0].toString());
			serie.addData(Math.ceil(Double.valueOf(array[1].toString())));
			grafico.addSerie(serie);
		}
		return grafico;
	}

}
