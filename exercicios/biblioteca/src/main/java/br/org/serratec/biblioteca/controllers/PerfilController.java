package br.org.serratec.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import br.org.serratec.biblioteca.entities.Perfil;
import br.org.serratec.biblioteca.repositories.PerfilRepository;
import br.org.serratec.biblioteca.services.PerfilService;

@RestController
@RequestMapping("/perfil")
public class PerfilController  {
	
	@Autowired
	PerfilService perfilService;
	
	@GetMapping
	public ResponseEntity<List<Perfil>> findAll() {
		return new ResponseEntity<>(perfilService.findAll(),HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Perfil> findById(@PathVariable Integer id) { 
		Perfil perfil = perfilService.findById(id);
		
		if (perfil ==  null) {
			return new ResponseEntity<>(perfil,HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(perfil,HttpStatus.OK);
		}
	}

	@PostMapping
	public ResponseEntity<Perfil> save(@RequestBody Perfil perfil) {
		return new ResponseEntity<>(perfilService.save(perfil), HttpStatus.CREATED) ;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Perfil> updatePerfil(@PathVariable Integer id, @RequestBody Perfil perfilNovo) {
		Perfil perfil = perfilService.findById(id);
		
		if (perfil == null) {
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Perfil perfilFinal = perfilService.updatePerfil(perfil,perfilNovo);
		
		return new ResponseEntity<>(perfilFinal, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Perfil>update(@RequestBody Perfil perfil){
        return new ResponseEntity<>(perfilService.update(perfil), HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Perfil> deletaPerfil(@PathVariable Integer id) {
		Perfil perfil = perfilService.findById(id);
		
		if (perfil ==  null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			perfilService.delete(id);
			return new ResponseEntity<>(perfil,HttpStatus.OK);
		}
		
	}	
}
