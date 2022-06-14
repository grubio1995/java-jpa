package br.com.alura.loja.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

	@EmbeddedId
	private CategoriaId categoriaId;

	public Categoria() {
	}

	public Categoria(String nome) {
		this.categoriaId = new CategoriaId(nome, "xpto");
	}

	public String getNome() {
		return this.categoriaId.getNome();
	}
}
