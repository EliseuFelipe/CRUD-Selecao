package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nome;
	
	@JoinColumn(name = "autor_id")
	@JsonBackReference
	@ManyToOne
	private Autor autor;
	
	private String data;
	
	private int paginas;
	
	public Livro() {
		
	}
	
	public Livro(String nome, String data, int paginas, Autor autor) {
		this.nome = nome;
		this.data = data;
		this.paginas = paginas;
		this.autor = autor;
	}
	
	public Livro(int id, String nome, String data, int paginas, Autor autor) {
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.paginas = paginas;
		this.autor = autor;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getPaginas() {
		return paginas;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	
}
