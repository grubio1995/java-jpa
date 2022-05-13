package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JpaUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
	
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal",new BigDecimal("800"), celulares);
		
		EntityManager em = JpaUtil.getEntityManager();

		CategoriaDao categoriaDao = new CategoriaDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}
}
