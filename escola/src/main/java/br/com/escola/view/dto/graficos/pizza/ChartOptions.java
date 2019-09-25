package br.com.escola.view.dto.graficos.pizza;

import java.util.ArrayList;
import java.util.Collection;

class ChartOptions {

	private Collection<String> labels = new ArrayList<>();

	public void adicionarLabel(String label) {
		this.labels.add(label);
	}

	public Collection<String> getLabels() {
		return labels;
	}

}
