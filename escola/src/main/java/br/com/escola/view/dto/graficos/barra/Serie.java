package br.com.escola.view.dto.graficos.barra;

import java.util.ArrayList;
import java.util.List;

public class Serie {

	private String name;
	private List<Number> data = new ArrayList<>();

	public Serie(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addData(Number data) {
		this.data.add(data);
	}

	public List<Number> getData() {
		return data;
	}

}
