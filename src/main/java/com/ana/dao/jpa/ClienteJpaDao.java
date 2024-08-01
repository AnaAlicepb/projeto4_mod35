package com.ana.dao.jpa;


import com.ana.dao.generic.jpa.GenericJpaDao;
import com.ana.domain.jpa.ClienteJpa;



public class ClienteJpaDao extends GenericJpaDao<ClienteJpa, Long> implements IClienteJpaDao {
    public ClienteJpaDao() {
        super(ClienteJpa.class);
    }

}
