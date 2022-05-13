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
	
		Categoria celulares = new Categoria("Celulares");
		
		EntityManager em = JpaUtil.getEntityManager();
		em.getTransaction().begin();;
		
		em.persist(celulares);
		celulares.setNome("XPTO");
		
		em.getTransaction().commit();
		em.close();
		
		celulares.setNome("1234");

	}
}
