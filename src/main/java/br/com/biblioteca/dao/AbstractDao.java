package br.com.biblioteca.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import br.com.biblioteca.arq.DateBaseDriver;
import br.com.biblioteca.exception.BibliotecaException;
import br.com.biblioteca.utils.BibliotecaHelper;

public abstract class AbstractDao {

	@SuppressWarnings("unchecked")
	protected <T> T findByPrimaryKey(Class<T> clazz, int id) throws BibliotecaException {

		try {
			DateBaseDriver dataBase = getDataBase();
			String strMethod = "find" + BibliotecaHelper.toCamelCase(clazz.getSimpleName()) + "ById";
			Method method = dataBase.getClass().getMethod(strMethod, Integer.class);
			return (T) method.invoke(dataBase, id);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new BibliotecaException(e.getMessage());
		}
	}

	protected DateBaseDriver getDataBase() {
		return DateBaseDriver.getInstance();
	}

}
