package br.org.serratec.biblioteca.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.org.serratec.biblioteca.entities.Perfil;
import br.org.serratec.biblioteca.repositories.PerfilRepository;
import jakarta.persistence.Id;

@Service
public class PerfilService {
	
	@Autowired
	PerfilRepository perfilRepository;
	
	public List<Perfil> findAll() {
		return perfilRepository.findAll();
	}
	
	public Perfil findById(Integer id) { 
		return perfilRepository.findById(id).orElse(null);
	}
	
	public Perfil save(Perfil perfil) {
		return perfilRepository.save(perfil);
	}
	
	public Perfil updatePerfil(Perfil perfil, Perfil perfilNovo) {		
		
		try {
			perfil.setNome(perfilNovo.getNome());
			perfil.setDescricao(perfilNovo.getDescricao());
			
			return perfilRepository.save(perfil);	
		} catch (Exception e) {
			System.out.println(e);
		}
		return perfilRepository.save(perfil);
	}
	
	public Perfil update(Perfil perfil) {
        return perfilRepository.save(perfil);
    }
	
	
	public Perfil delete(Integer id) {
		Perfil perfilDeletado = perfilRepository.findById(id).orElse(null);
		
		if (perfilDeletado != null) {
			try {
				perfilRepository.deleteById(id);
				return perfilDeletado;
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return perfilDeletado;
		
	}
	
	public long count() {
		return perfilRepository.count();
	}
 }
