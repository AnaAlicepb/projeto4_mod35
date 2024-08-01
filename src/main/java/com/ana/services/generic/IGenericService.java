package com.ana.services.generic;

import java.io.Serializable;
import java.util.Collection;

import com.ana.dao.Persistente;
import com.ana.exceptions.DAOException;
import com.ana.exceptions.TipoChaveNaoEncontradaException;

public interface IGenericService <T extends Persistente, E extends Serializable> {
	

    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException;


    public void excluir(E valor) throws DAOException;


    public void alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException;


    public T consultar(E valor) throws DAOException;

    public Collection<T> buscarTodos() throws DAOException;

}
