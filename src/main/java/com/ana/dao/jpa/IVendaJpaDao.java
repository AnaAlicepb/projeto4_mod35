package com.ana.dao.jpa;


import com.ana.dao.generic.jpa.IGenericJpaDao;
import com.ana.domain.jpa.VendaJpa;
import com.ana.exceptions.DAOException;
import com.ana.exceptions.TipoChaveNaoEncontradaException;

public interface IVendaJpaDao extends IGenericJpaDao<VendaJpa, Long> {
    void finalizarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException;
    void cancelarVenda(VendaJpa venda) throws TipoChaveNaoEncontradaException, DAOException;
    VendaJpa consultarComCollection(Long id);
}
