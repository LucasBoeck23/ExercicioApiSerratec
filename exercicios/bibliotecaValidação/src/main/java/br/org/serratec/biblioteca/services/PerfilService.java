package br.org.serratec.biblioteca.services;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.biblioteca.dto.PerfilResumidoDto;
import br.org.serratec.biblioteca.entities.Perfil;
import br.org.serratec.biblioteca.repositories.PerfilRepository;

@Service
public class PerfilService {
	
	@Autowired
	PerfilRepository perfilRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	public List<Perfil> findAll() {
		return perfilRepository.findAll();
	}
	public List<PerfilResumidoDto> findAllPerfilResumido() {
		List<Perfil> perfis = perfilRepository.findAll();		
		List<PerfilResumidoDto> perfisDto = new ArrayList<>();
		
		for(Perfil perfil : perfis) {
			PerfilResumidoDto perfilDto = new PerfilResumidoDto();
			perfilDto.setNome(perfil.getNome());
			perfilDto.setDescricao(perfil.getDescricao());
			
			perfisDto.add(perfilDto);
		}
		return perfisDto;
	}
	
	 
	
	public Perfil findById(Integer id) { 
		//return perfilRepository.findById(id).orElse(null);
		return perfilRepository.findById(id).get();
	}
	
	public PerfilResumidoDto findByIdResumido(Integer id) { 
		Perfil perfil = perfilRepository.findById(id).orElse(null);
		PerfilResumidoDto perfilDto = null;
		//if (perfil!= null) {
	//	try {	
		perfilDto = modelMapper.map(perfil,PerfilResumidoDto.class);
		//}
		//} catch (IllegalArgumentException e) {
		//	throw new IllegalArgumentException("Ocorreu uma exceção: ", e);
	//	}
		return perfilDto;
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
