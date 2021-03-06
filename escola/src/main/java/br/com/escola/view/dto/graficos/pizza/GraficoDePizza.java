package br.com.escola.view.dto.graficos.pizza;

import java.util.ArrayList;
import java.util.Collection;

public class GraficoDePizza {

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
