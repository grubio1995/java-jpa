package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JpaUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();
		EntityManager em = JpaUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> produtos = produtoDao.listarTodos();
		produtos.forEach(produto -> System.out.println(p.getNome()));

		List<Produto> produtos2 = produtoDao.listarPorNome("Xiaomi Redmi");
		produtos2.forEach(produto -> System.out.println(p.getNome()));
		
		List<Produto> produtosPorCategoria = produtoDao.listarPorCategoria("CELULARES");
		produtosPorCategoria.forEach(produto -> System.out.println(p.getNome()));
		
		BigDecimal precoProduto = produtoDao.buscarPrecoPorProduto("Xiaomi Redmi");
		System.out.println(precoProduto);

	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );
		
		EntityManager em = JpaUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();
	}
}
