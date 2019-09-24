package br.com.escola.view.dto.graficos.pizza;

import java.util.ArrayList;
import java.util.Collection;

public class ChartOptions {

	private Collection<String> labels = new ArrayList<>();
	private Xaxis xaxis;

	public void adicionarLabel(String label) {
		this.labels.add(label);
	}

	public Collection<String> getLabels() {
		return labels;
	}

	public Xaxis getXaxis() {
		return xaxis;
	}

	public void setXaxis(Xaxis xaxis) {
		this.xaxis = xaxis;
	}

}
