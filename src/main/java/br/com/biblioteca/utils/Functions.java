package br.com.biblioteca.utils;

import java.util.Collection;

public final class Functions {

	private Functions() {
		// Hide constructor.
	}

	/**
	 * 
	 * @param collection
	 * @param item
	 * @return
	 */
	public static boolean contains(Collection<Object> collection, Object item) {
		return collection.contains(item);
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String toCamelCase(String str) {
		return BibliotecaHelper.toCamelCase(str);
	}

}