package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Produto;

public class CadastroDeProduto {

	public static void main(String[] args) {
	
		Produto celular = new Produto();
		celular.setNome("Xiamo Redmi");
		celular.setDescricao("Muito legal");
		celular.setPreco(new BigDecimal("800"));
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lojas");
		
		EntityManager em = factory.createEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(em);
		
		em.getTransaction().begin();
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}
}
