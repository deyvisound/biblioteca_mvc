package br.com.biblioteca.dao;

import java.util.Collection;

public interface AbstractDao {

	public <T> Collection<T> findAll();
	
}
