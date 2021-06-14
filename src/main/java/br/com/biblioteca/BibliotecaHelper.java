package br.com.biblioteca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BibliotecaHelper {

	public static boolean isEmpty(Object o) {

		if (o == null)
			return true;
		else if (o instanceof String)
			return o.equals("");
		else if (o instanceof Number)
			return o.equals(0);

		return false;

	}

	public static boolean isNotEmpty(Object o) {
		return !BibliotecaHelper.isEmpty(o);
	}

	public static String wordToCamelCase(String word) {
		return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
	}

	/**
	 * 
	 * @param strDate ( dd/mm/yyyy )
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateFromString(String strDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.parse(strDate);
	}

}
