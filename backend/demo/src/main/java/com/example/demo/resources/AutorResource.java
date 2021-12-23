package com.example.demo.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

import com.example.demo.models.Autor;
import com.example.demo.models.Livro;
import com.example.demo.repositories.AutorRepository;


@RestController
@RequestMapping(path="/Autors")
public class AutorResource {
	
	
	private AutorRepository autorRepository;
	
	public AutorResource(AutorRepository autorRepository) {
		super();
		this.autorRepository = autorRepository;
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Autor> save(@RequestBody Autor autor){
		List<Livro> livros = autor.getLivros();
		List<Livro> novosLivros = new ArrayList<>();
		Livro novoLivro;
		
		for(Livro i : livros) {
			novoLivro = new Livro(i.getNome(), i.getData(), i.getPaginas(), autor);
			novosLivros.add(novoLivro);
		}
		autor.setLivro(novosLivros);
		
		autorRepository.save(autor);
		return new ResponseEntity<>(autor, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Autor>> getAll(){
		List<Autor> Autores = new ArrayList<>();
		Autores = autorRepository.findAll();
		for (Autor autor : Autores) {
			System.out.println(autor.getId());
		}
		return new ResponseEntity<>(Autores, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping(path="/{id}")
	public ResponseEntity<Optional<Autor>> getById(@PathVariable Integer id){
		Optional<Autor> Autor;
		try {
			Autor = autorRepository.findById(id);
			return new ResponseEntity<Optional<Autor>>(Autor, HttpStatus.OK);
		}catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<Autor>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Optional<Autor>> deleteById(@PathVariable Integer id){
		try {
			autorRepository.deleteById(id);
			return new ResponseEntity<Optional<Autor>>(HttpStatus.OK);
		}catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<Autor>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Autor> update(@PathVariable Integer id, @RequestBody Autor newAutor){
		return autorRepository.findById(id)
				.map(autor -> { 
					autor.setNome(newAutor.getNome());
					autor.setEmail(newAutor.getEmail());

					
					for (int i = 0; i<newAutor.getLivros().size(); i++) {
						autor.updateLivro(newAutor.getLivros().get(i), i, autor);
					}
					
					autor.setLivro(autor.getLivros());
					for(Livro livro: autor.getLivros()) {
						System.out.println(livro.getNome());
					}

					
					Autor AutorUpdated = autorRepository.save(autor);
					return ResponseEntity.ok().body(AutorUpdated);
				}).orElse(ResponseEntity.notFound().build());
	}
}
