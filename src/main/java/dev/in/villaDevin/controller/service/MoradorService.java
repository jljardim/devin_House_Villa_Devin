package dev.in.villaDevin.controller.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import dev.in.villaDevin.model.Morador;
import dev.in.villaDevin.model.repository.MoradorRepository;
import dev.in.villaDevin.model.transport.MoradorDTO;
import dev.in.villaDevin.model.transport.MoradorNameProjection;

public class MoradorService {
	
	private MoradorRepository moradorRepository;
	
	public MoradorService(MoradorRepository moradorReepository) {
		this.moradorRepository = moradorReepository;
	}
	
	@Transactional(readOnly = false)
	public MoradorDTO create(MoradorDTO morador) throws SQLException {
		if (morador == null) {
			throw new IllegalArgumentException("O morador está nulo");
		}
		
		Morador saveMorador = moradorRepository.save(new Morador(morador));
		
		return new MoradorDTO(saveMorador); 	
	}
	
	public List<MoradorNameProjection> listMoradores() throws SQLException {
		return this.moradorRepository.findAllName();
	}
	
	public MoradorDTO getById(Integer id) throws SQLException {
		
		if(id == null) {
			throw new IllegalArgumentException("O Id não pode ser nulo");
		}
		
		Optional<Morador> morador = moradorRepository.findById(id);
		
		if(morador.isPresent()) {
			return new MoradorDTO(morador.get());
		}
		return null;
	}
	
	public List<MoradorDTO> getMoradoresByFilter(String nome) throws SQLException {
		
		if(nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("O nome não pode ser nulo");
		}
		
		List<Morador> morador = moradorRepository.findByName(nome);
		if(!morador.isEmpty()) {
			return morador.stream().map(MoradorDTO::new).collect(Collectors.toList());
		}
		return new ArrayList<>();
		
	}
	
	// Esse metodo getMoradorDTOByFilter faz a mesma que o getMoradorByFilter porem esse metodo usa projeção 
	// como o metodo criado no MoradorRepository
	public List<MoradorDTO> getMoradoresDTOByFilter(String nome) throws SQLException {
		
		if(nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("O nome não pode ser nulo");
		}
		
		List<MoradorDTO> morador = moradorRepository.findDTOByName(nome);
		if(!morador.isEmpty()) {
			return morador;
		}
		return new ArrayList<>();
		
	}

}
