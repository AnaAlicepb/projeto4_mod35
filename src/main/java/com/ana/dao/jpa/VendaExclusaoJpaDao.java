package com.ana.dao.jpa;

import com.ana.dao.generic.jpa.GenericJpaDao;
import com.ana.domain.jpa.VendaJpa;
import com.ana.exceptions.DAOException;
import com.ana.exceptions.TipoChaveNaoEncontradaException;

public class VendaExclusaoJpaDao extends GenericJpaDao<VendaJpa, Long> implements IVendaJpaDao {

    public VendaExclusaoJpaDao() {
        super(VendaJpa.class);
    }

    @Override
    public void finalizarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException {
        throw new UnsupportedOperationException("Operação não permitida.");
    }

    @Override
    public void cancelarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException {
        throw new UnsupportedOperationException("Operação não permitida.");
    }

    @Override
    public VendaJpa consultarComCollection(Long id) {
        throw new UnsupportedOperationException("Operação não permitida.");
    }
}
