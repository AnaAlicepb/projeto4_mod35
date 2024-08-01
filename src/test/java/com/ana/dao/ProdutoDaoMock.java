package com.ana.dao;

import java.util.Collection;

import com.ana.dao.IProdutoDAO;
import com.ana.domain.Produto;
import com.ana.exceptions.DAOException;
import com.ana.exceptions.MaisDeUmRegistroException;
import com.ana.exceptions.TableException;
import com.ana.exceptions.TipoChaveNaoEncontradaException;


public class ProdutoDaoMock implements IProdutoDAO {


	@Override
	public Boolean cadastrar(Produto entity) throws TipoChaveNaoEncontradaException, DAOException {
		return null;
	}

	@Override
	public void excluir(String valor) throws DAOException {

	}

	@Override
	public void alterar(Produto entity) throws TipoChaveNaoEncontradaException, DAOException {

	}

	@Override
	public Produto consultar(String valor) throws MaisDeUmRegistroException, TableException, DAOException {
		Produto produto = new Produto();
		produto.setCodigo(valor);
		return produto;
	}

	@Override
	public Collection<Produto> buscarTodos() throws DAOException {
		return null;
	}
}
