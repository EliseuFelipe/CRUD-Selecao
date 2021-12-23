package com.example.demo.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Livro;
import com.example.demo.repositories.LivroRepository;


@RestController
@RequestMapping(path="/livros")
public class LivroResource {
	
	
	private LivroRepository livroRepository;
	
	public LivroResource(LivroRepository livroRepository) {
		super();
		this.livroRepository = livroRepository;
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Livro> save(@RequestBody Livro livro){
		livroRepository.save(livro);
		return new ResponseEntity<>(livro, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Livro>> getAll(){
		List<Livro> livros = new ArrayList<>();
		livros = livroRepository.findAll();
		return new ResponseEntity<>(livros, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping(path="/{id}")
	public ResponseEntity<Optional<Livro>> getById(@PathVariable Integer id){
		Optional<Livro> livro;
		try {
			livro = livroRepository.findById(id);
			return new ResponseEntity<Optional<Livro>>(livro, HttpStatus.OK);
		}catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<Livro>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Optional<Livro>> deleteById(@PathVariable Integer id){
		try {
			livroRepository.deleteById(id);
			return new ResponseEntity<Optional<Livro>>(HttpStatus.OK);
		}catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<Livro>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro newLivro){
		return livroRepository.findById(id)
				.map(livro -> { 
					livro.setAutor(newLivro.getAutor());
					livro.setData(newLivro.getData());
					livro.setNome(newLivro.getNome());
					livro.setPaginas(newLivro.getPaginas());
					
					Livro livroUpdated = livroRepository.save(livro);
					return ResponseEntity.ok().body(livroUpdated);
				}).orElse(ResponseEntity.notFound().build());
	}
}
