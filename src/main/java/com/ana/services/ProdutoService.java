package com.ana.services;

import com.ana.dao.generic.IGenericDAO;
import com.ana.domain.Produto;
import com.ana.exceptions.DAOException;
import com.ana.services.generic.GenericService;

public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {
	public ProdutoService(IGenericDAO<Produto, String> genericJpaDao) {
		super(genericJpaDao);
	}

	@Override
	public void excluir(String valor) throws DAOException {

	}
}
