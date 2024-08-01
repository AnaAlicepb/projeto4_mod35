package com.ana.dao.jpa;


import com.ana.dao.generic.jpa.GenericJpaDao;
import com.ana.domain.jpa.ProdutoJpa;

public class ProdutoJpaDao extends GenericJpaDao<ProdutoJpa, Long> implements IProdutoJpaDao {
    public ProdutoJpaDao() {
        super(ProdutoJpa.class);
    }
}
