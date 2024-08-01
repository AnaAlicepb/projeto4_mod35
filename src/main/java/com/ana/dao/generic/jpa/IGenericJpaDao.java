package com.ana.dao.generic.jpa;

import com.ana.dao.Persistente;
import com.ana.exceptions.DAOException;
import com.ana.exceptions.MaisDeUmRegistroException;
import com.ana.exceptions.TableException;
import com.ana.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericJpaDao<T extends Persistente, E extends Serializable> {

    T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException;

    void excluir(T entity) throws DAOException;

    T alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException;

    T consultar(E id) throws MaisDeUmRegistroException, TableException, DAOException;

    Collection<T> buscarTodos() throws DAOException;
}
