package com.ana.dao;

import java.util.Collection;

import com.ana.dao.IClienteDAO;
import com.ana.domain.Cliente;
import com.ana.exceptions.DAOException;
import com.ana.exceptions.MaisDeUmRegistroException;
import com.ana.exceptions.TableException;
import com.ana.exceptions.TipoChaveNaoEncontradaException;

public class ClienteDaoMock implements IClienteDAO {


	@Override
	public Boolean cadastrar(Cliente entity) throws TipoChaveNaoEncontradaException, DAOException {
		return true;
	}

	@Override
	public void excluir(Long valor) throws DAOException {

	}

	@Override
	public void alterar(Cliente entity) throws TipoChaveNaoEncontradaException, DAOException {

	}

	@Override
	public Cliente consultar(Long valor) throws MaisDeUmRegistroException, TableException, DAOException {
		Cliente cliente = new Cliente();
		cliente.setCpf(valor);
		return cliente;
	}

	@Override
	public Collection<Cliente> buscarTodos() throws DAOException {
		return null;
	}
}
