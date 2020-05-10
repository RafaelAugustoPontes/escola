package br.com.escola.view.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataUtil {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public static String formatar(LocalDate data) {
		if (data == null)
			return "";
		return formatter.format(data);
	}

}
