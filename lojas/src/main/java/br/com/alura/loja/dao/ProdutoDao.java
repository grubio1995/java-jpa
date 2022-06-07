package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.h2.util.StringUtils;

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
		return em.createNamedQuery("Produto.produtosPorCategoria", Produto.class).setParameter("nome", nome)
				.getResultList();
	}

	public List<Produto> listarPorCategoria(String nomeCategoria) {
		var jpql = "SELECT p FROM Produto p WHERE p.categoria.nome LIKE :nomeCategoria";
		return em.createQuery(jpql, Produto.class).setParameter("nomeCategoria", nomeCategoria).getResultList();
	}

	public BigDecimal buscarPrecoPorProduto(String nome) {
		var jpql = "SELECT p.preco FROM Produto p where p.nome LIKE :nome";
		return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult();
	}

	public List<Produto> buscarPorParamatros(String nome, BigDecimal preco, LocalDate dataCadastro){
		
		String jpql = "SELECT p from produto p WHERE 1=1";
		
		if(!StringUtils.isNullOrEmpty(nome)) {
			jpql = "AND p.nome = :nome ";
		}

		if(Objects.nonNull(preco)) {
			jpql = "AND p.preco = :preco ";
		}

		if(Objects.nonNull(dataCadastro)) {
			jpql = "AND p.dataCadastro = :dataCadastro ";
		}
		
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		
		if(!StringUtils.isNullOrEmpty(nome)) {
			query.setParameter("nome", nome);
		}

		if(Objects.nonNull(preco)) {
			query.setParameter("preco", preco);			
		}

		if(Objects.nonNull(dataCadastro)) {
			query.setParameter("dataCadastro", dataCadastro);

		}

		return query.getResultList();
	}
}
