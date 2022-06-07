package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class PedidoDao {

	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public BigDecimal valorTotalVendido(){ 
		var jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class)
				.getSingleResult();
	}
	
	public Pedido buscarPedidoComCliente(Long id){ 
		var jpql = "SELECT p FROM Pedido p"
				+ " JOIN FETCH p.cliente "
				+ " WHERE p.id = (:id)";
		return em.createQuery(jpql, Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public List<RelatorioDeVendasVo> relatorioVendas(){
		String jpql = "SELECT new br.com.alura.loja.vo.RelatorioDeVendasVo( "
				+ " produto.nome,"
				+ " SUM(item.quantidade),"
				+ " MAX(p.data)) "
				+ " FROM Pedido p"
				+ " JOIN p.itens item "
				+ " JOIN item.produto produto "
				+ " GROUP BY produto.nome "
				+ " ORDER BY item.quantidade DESC";
		
		return em.createQuery(jpql,RelatorioDeVendasVo.class)
				.getResultList();
		
	}
}
