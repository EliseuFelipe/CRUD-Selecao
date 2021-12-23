package com.example.demo.models;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Autor {
	
	@GeneratedValue
	@Id
	private int id;
	
	private String nome;
	
	private String email;
	
	@JsonManagedReference
	/*
	@OneToMany(
			mappedBy = "autor",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)*/
	@OneToMany(
			mappedBy = "autor",
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
	)
	private List<Livro> livros;
	
	public Autor() {
		
	}
	
	public Autor(String nome, String email, List<Livro> livros) {
		this.nome = nome;
		this.email = email;
		this.livros = livros;
	}
	
	public Autor(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Livro> getLivros(){
		return livros;
	}
	
	public void setLivro(List<Livro> livros) {
		this.livros = livros;
	}
	
	public int getId() {
		return id;
	}
	
	public void updateLivro(Livro livro, int index, Autor autor) {
		if(this.getLivros().size()<=index) {
			System.out.println("if inside updateLivro");
			Livro novoLivro = new Livro(livro.getNome(), livro.getData(), livro.getPaginas(), autor);
			this.livros.add(novoLivro);
		}
		else {
			this.livros.get(index).setNome(livro.getNome());
			this.livros.get(index).setPaginas(livro.getPaginas());
			this.livros.get(index).setData(livro.getData());
		}
		

	}

}
