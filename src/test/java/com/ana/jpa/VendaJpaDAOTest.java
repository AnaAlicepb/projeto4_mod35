package com.ana.jpa;

import com.ana.dao.jpa.ClienteJpaDao;
import com.ana.dao.jpa.IClienteJpaDao;
import com.ana.dao.jpa.IProdutoJpaDao;
import com.ana.dao.jpa.IVendaJpaDao;
import com.ana.dao.jpa.ProdutoJpaDao;
import com.ana.dao.jpa.VendaExclusaoJpaDao;
import com.ana.dao.jpa.VendaJpaDao;
import com.ana.domain.jpa.ClienteJpa;
import com.ana.domain.jpa.ProdutoJpa;
import com.ana.domain.jpa.VendaJpa;
import com.ana.domain.jpa.VendaJpa.Status;
import com.ana.exceptions.DAOException;
import com.ana.exceptions.MaisDeUmRegistroException;
import com.ana.exceptions.TableException;
import com.ana.exceptions.TipoChaveNaoEncontradaException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.*;

public class VendaJpaDAOTest {
    private IVendaJpaDao vendaDao;

    private IVendaJpaDao vendaExclusaoDao;

    private IClienteJpaDao clienteDao;

    private IProdutoJpaDao produtoDao;

    private Random random;

    private ClienteJpa cliente;

    private ProdutoJpa produto;

    public VendaJpaDAOTest() {
        this.vendaDao = new VendaJpaDao();
        vendaExclusaoDao = new VendaExclusaoJpaDao();
        this.clienteDao = new ClienteJpaDao();
        this.produtoDao = new ProdutoJpaDao();
        random = new Random();
    }

    @Before
    public void init() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        this.cliente = cadastrarCliente();
        this.produto = cadastrarProduto("A1", BigDecimal.TEN);
    }

    @After
    public void end() throws DAOException {
        excluirVendas();
        excluirProdutos();
        clienteDao.excluir(this.cliente);
    }

    @Test
    public void pesquisar() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        VendaJpa venda = criarVenda("A1");
        VendaJpa retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        VendaJpa vendaConsultada = vendaDao.consultar(venda.getId());
        assertNotNull(vendaConsultada);
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        VendaJpa venda = criarVenda("A2");
        VendaJpa retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);

        assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(20)));
        assertTrue(venda.getStatus().equals(Status.INICIADA));

        VendaJpa vendaConsultada = vendaDao.consultar(venda.getId());
        assertTrue(vendaConsultada.getId() != null);
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
    }

    @Test
    public void cancelarVenda() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        String codigoVenda = "A3";
        VendaJpa venda = criarVenda(codigoVenda);
        VendaJpa retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        retorno.setStatus(Status.CANCELADA);
        vendaDao.cancelarVenda(venda);

        VendaJpa vendaConsultada = vendaDao.consultar(venda.getId());
        assertEquals(codigoVenda, vendaConsultada.getCodigo());
        assertEquals(Status.CANCELADA, vendaConsultada.getStatus());
    }

    @Test
    public void adicionarMaisProdutosDoMesmo() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        VendaJpa venda = criarVenda("A4");
        VendaJpa retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals("A4", venda.getCodigo());

        VendaJpa vendaConsultada = vendaDao.consultarComCollection(venda.getId());
        vendaConsultada.adicionarProduto(produto, 1);

        assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(30).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
        assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
    }

    @Test
    public void adicionarMaisProdutosDiferentes() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        String codigoVenda = "A5";
        VendaJpa venda = criarVenda(codigoVenda);
        VendaJpa retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        ProdutoJpa prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getCodigo());

        //TODO Usando este método apra evitar a exception org.hibernate.LazyInitializationException
        // Ele busca todos os dados da lista pois a mesma por default é lazy
        VendaJpa vendaConsultada = vendaDao.consultarComCollection(venda.getId());
        vendaConsultada.adicionarProduto(prod, 1);

        assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
        assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
    }

    @Test(expected = DAOException.class)
    public void salvarVendaMesmoCodigoExistente() throws TipoChaveNaoEncontradaException, DAOException {
        VendaJpa venda = criarVenda("A6");
        VendaJpa retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);

        VendaJpa venda1 = criarVenda("A6");
        VendaJpa retorno1 = vendaDao.cadastrar(venda1);
        assertNull(retorno1);
        assertTrue(venda.getStatus().equals(Status.INICIADA));
    }

    @Test
    public void removerProduto() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        String codigoVenda = "A7";
        VendaJpa venda = criarVenda(codigoVenda);
        VendaJpa retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        ProdutoJpa prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getCodigo());

        VendaJpa vendaConsultada = vendaDao.consultarComCollection(venda.getId());
        vendaConsultada.adicionarProduto(prod, 1);
        assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));


        vendaConsultada.removerProduto(prod, 1);
        assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 2);
        valorTotal = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
        assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
    }

    @Test
    public void removerApenasUmProduto() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        String codigoVenda = "A8";
        VendaJpa venda = criarVenda(codigoVenda);
        VendaJpa retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        ProdutoJpa prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getCodigo());

        VendaJpa vendaConsultada = vendaDao.consultarComCollection(venda.getId());
        vendaConsultada.adicionarProduto(prod, 1);
        assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));


        vendaConsultada.removerProduto(prod, 1);
        assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 2);
        valorTotal = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
        assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
    }

    @Test
    public void removerTodosProdutos() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        String codigoVenda = "A9";
        VendaJpa venda = criarVenda(codigoVenda);
        VendaJpa retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        ProdutoJpa prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getCodigo());

        VendaJpa vendaConsultada = vendaDao.consultarComCollection(venda.getId());
        vendaConsultada.adicionarProduto(prod, 1);
        assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));


        vendaConsultada.removerTodosProdutos();
        assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 0);
        assertTrue(vendaConsultada.getValorTotal().equals(BigDecimal.valueOf(0)));
        assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
    }

    @Test
    public void finalizarVenda() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        String codigoVenda = "A10";
        VendaJpa venda = criarVenda(codigoVenda);
        VendaJpa retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        venda.setStatus(Status.CONCLUIDA);
        vendaDao.finalizarVenda(venda);

        VendaJpa vendaConsultada = vendaDao.consultarComCollection(venda.getId());
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
        assertEquals(Status.CONCLUIDA, vendaConsultada.getStatus());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void tentarAdicionarProdutosVendaFinalizada() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        String codigoVenda = "A11";
        VendaJpa venda = criarVenda(codigoVenda);
        VendaJpa retorno = vendaDao.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        venda.setStatus(Status.CONCLUIDA);
        vendaDao.finalizarVenda(venda);

        VendaJpa vendaConsultada = vendaDao.consultarComCollection(venda.getId());
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
        assertEquals(Status.CONCLUIDA, vendaConsultada.getStatus());

        vendaConsultada.adicionarProduto(this.produto, 1);

    }


    private void excluirProdutos() throws DAOException {
        Collection<ProdutoJpa> list = this.produtoDao.buscarTodos();
        list.forEach(prod -> {
            try {
                this.produtoDao.excluir(prod);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    private void excluirVendas() throws DAOException {
        Collection<VendaJpa> list = this.vendaExclusaoDao.buscarTodos();
        list.forEach(prod -> {
            try {
                this.vendaExclusaoDao.excluir(prod);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    private ProdutoJpa cadastrarProduto(String codigo, BigDecimal valor) throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        ProdutoJpa produto = new ProdutoJpa();
        produto.setCodigo(codigo);
        produto.setDescricao("Descrição");
        produto.setNome("Produto");
        produto.setValor(valor);
        produto.setMarca("ebac");
        produtoDao.cadastrar(produto);
        return produto;
    }

    private ClienteJpa cadastrarCliente() throws TipoChaveNaoEncontradaException, DAOException {
        ClienteJpa cliente = new ClienteJpa();
        cliente.setCpf(random.nextLong());
        cliente.setNome("Samuel");
        cliente.setEmail("sam@ebac.com");
        cliente.setCidade("Beberibe");
        cliente.setEndereco("Serra do Félix");
        cliente.setEstado("Ceará");
        cliente.setNumero(10);
        cliente.setTelelefone(1199999999L);
        clienteDao.cadastrar(cliente);
        return cliente;
    }

    private VendaJpa criarVenda(String codigo) {
        VendaJpa venda = new VendaJpa();
        venda.setCodigo(codigo);
        venda.setDataVenda(Instant.now());
        venda.setCliente(this.cliente);
        venda.setStatus(Status.INICIADA);
        venda.adicionarProduto(this.produto, 2);
        return venda;
    }
}
