package br.com.escola.view.dto.graficos.barra;

import java.util.ArrayList;
import java.util.Collection;

import br.com.escola.view.dto.graficos.pizza.ChartOptions;

public class GraficoDeBarra {

	private Collection<Number> series = new ArrayList<>();
	private ChartOptions chartOptions = new ChartOptions();

	public void addSerie(Number serie) {
		series.add(serie);
	}

	public void addLabel(String label) {
		chartOptions.adicionarLabel(label);
	}

	public Collection<Number> getSeries() {
		return series;
	}

	public ChartOptions getChartOptions() {
		return chartOptions;
	}

}
