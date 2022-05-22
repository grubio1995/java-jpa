package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {

	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
		
	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = this.em.merge(produto);
		this.em.remove(produto);
	}

	public Produto buscarPorId(Long id) {
		return this.em.find(Produto.class, id);
	}

	public List<Produto> listarTodos() {
		var jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> listarPorNome(String nome) {
		var jpql = "SELECT p FROM Produto p where p.nome LIKE :nome";
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public List<Produto> listarPorCategoria(String nomeCategoria) {
		var jpql = "SELECT p FROM Produto p WHERE p.categoria.nome LIKE :nomeCategoria";
		return em.createQuery(jpql, Produto.class)
				.setParameter("nomeCategoria", nomeCategoria)
				.getResultList();
	}
}
