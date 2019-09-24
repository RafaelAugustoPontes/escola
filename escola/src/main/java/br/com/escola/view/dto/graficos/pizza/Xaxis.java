package br.com.escola.view.dto.graficos.pizza;

import java.util.ArrayList;
import java.util.List;

public class Xaxis {

	private List<Object> categories = new ArrayList<>();

	public List<Object> getCategories() {
		return categories;
	}

	public void addCategorie(Object categorie) {
		this.categories.add(categorie);
	}

}
