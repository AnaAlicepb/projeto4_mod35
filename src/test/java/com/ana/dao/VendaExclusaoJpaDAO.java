package com.ana.dao;


import com.ana.dao.generic.jpa.GenericJpaDao;
import com.ana.dao.jpa.IVendaJpaDao;
import com.ana.domain.jpa.VendaJpa;
import com.ana.exceptions.DAOException;
import com.ana.exceptions.TipoChaveNaoEncontradaException;

public class VendaExclusaoJpaDAO extends GenericJpaDao<VendaJpa, Long> implements IVendaJpaDao {

	public VendaExclusaoJpaDAO() {
		super(VendaJpa.class);
	}

	@Override
	public void finalizarVenda(VendaJpa venda) {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public void cancelarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public VendaJpa consultarComCollection(Long id) {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

}
