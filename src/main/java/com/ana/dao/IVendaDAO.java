package com.ana.dao;

import com.ana.dao.generic.IGenericDAO;
import com.ana.domain.Venda;
import com.ana.exceptions.DAOException;
import com.ana.exceptions.TipoChaveNaoEncontradaException;

public interface IVendaDAO extends IGenericDAO<Venda, String> {

	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
	
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
}
