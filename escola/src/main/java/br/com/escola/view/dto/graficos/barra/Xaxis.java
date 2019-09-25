package br.com.escola.view.dto.graficos.barra;

import java.util.ArrayList;
import java.util.List;

public class Xaxis {

	private List<String> categories = new ArrayList<>();

	public void addCategorie(String categorie) {
		this.categories.add(categorie);
	}

	public List<String> getCategories() {
		return categories;
	}

}
