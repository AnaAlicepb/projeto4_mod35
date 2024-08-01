package com.ana.jpa;

import com.ana.dao.jpa.IProdutoJpaDao;
import com.ana.dao.jpa.ProdutoJpaDao;
import com.ana.domain.jpa.ProdutoJpa;
import com.ana.exceptions.DAOException;
import com.ana.exceptions.MaisDeUmRegistroException;
import com.ana.exceptions.TableException;
import com.ana.exceptions.TipoChaveNaoEncontradaException;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Collection;

public class ProdutoJpaDAOTest {
    private IProdutoJpaDao produtoDao;

    public ProdutoJpaDAOTest() {
        this.produtoDao = new ProdutoJpaDao();
    }

    @After
    public void end() throws DAOException {
        Collection<ProdutoJpa> list = produtoDao.buscarTodos();
        list.forEach(cli -> {
            try {
                produtoDao.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @Test
    public void pesquisar() throws MaisDeUmRegistroException, TableException, DAOException, TipoChaveNaoEncontradaException {
        ProdutoJpa produto = criarProduto("A1");
        assertNotNull(produto);
        ProdutoJpa produtoDB = this.produtoDao.consultar(produto.getId());
        assertNotNull(produtoDB);
    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException, DAOException {
        ProdutoJpa produto = criarProduto("A2");
        assertNotNull(produto);
    }

    @Test
    public void excluir() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        ProdutoJpa produto = criarProduto("A3");
        assertNotNull(produto);
        this.produtoDao.excluir(produto);
        ProdutoJpa produtoBD = this.produtoDao.consultar(produto.getId());
        assertNull(produtoBD);
    }

    @Test
    public void alterarProduto() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        ProdutoJpa produto = criarProduto("A4");
        produto.setNome("Galaxy S21");
        produtoDao.alterar(produto);
        ProdutoJpa produtoBD = this.produtoDao.consultar(produto.getId());
        assertNotNull(produtoBD);
        assertEquals("Galaxy S21", produtoBD.getNome());
    }

    @Test
    public void buscarTodos() throws DAOException, TipoChaveNaoEncontradaException {
        criarProduto("A5");
        criarProduto("A6");
        Collection<ProdutoJpa> list = produtoDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        for (ProdutoJpa prod : list) {
            this.produtoDao.excluir(prod);
        }

        list = produtoDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 0);

    }

    private ProdutoJpa criarProduto(String codigo) throws TipoChaveNaoEncontradaException, DAOException {
        ProdutoJpa produto = new ProdutoJpa();
        produto.setCodigo(codigo);
        produto.setDescricao("Descricao");
        produto.setNome("Produto");
        produto.setValor(BigDecimal.TEN);
        produto.setMarca("ebac");
        produtoDao.cadastrar(produto);
        return produto;
    }
}
