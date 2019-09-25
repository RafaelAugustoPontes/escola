package br.com.escola.view.dto.graficos.barra;

import java.util.ArrayList;
import java.util.List;

public class GraficoEmBarra {

	private Options options = new Options();
	private List<Serie> series = new ArrayList<>();

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

	public List<Serie> getSeries() {
		return series;
	}

	public void addSerie(Serie serie) {
		this.series.add(serie);
	}

}
