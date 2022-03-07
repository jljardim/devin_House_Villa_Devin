package dev.in.villaDevin.controller.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.in.villaDevin.exeptions.ResidentNotFoundExcetion;
import dev.in.villaDevin.model.Resident;
import dev.in.villaDevin.model.repository.ResidentRepository;
import dev.in.villaDevin.model.transport.ResidentDTO;
import dev.in.villaDevin.model.transport.ResidentNomeProjection;


@Service
public class ResidentService {
	
	@Value("${ORCAMENTO_VILLA}")
	private Double OrcamentoDaVilla;
	
	private ResidentRepository residentRepository;
	
	
	public ResidentService(ResidentRepository residentRepository) {
		this.residentRepository = residentRepository;
	}
	
	@Transactional(readOnly = false)
	public ResidentDTO create(ResidentDTO resident) throws ResidentNotFoundExcetion {
		if (resident == null) {
			throw new IllegalArgumentException("O morador está nulo");
		}
		
		Resident saveResident = residentRepository.save(new Resident(resident));
		
		return new ResidentDTO(saveResident); 	
	}
	
	public List<ResidentNomeProjection> listResident() throws SQLException {
		return this.residentRepository.findAllNome();
	}
	
	public ResidentDTO getById(Integer id) throws SQLException {
		
		if(id == null) {
			throw new IllegalArgumentException("O Id não pode ser nulo");
		}
		
		Optional<Resident> resident = residentRepository.findById(id);
		
		if(resident.isPresent()) {
			return new ResidentDTO(resident.get());
		}
		return null;
	}
	
//	public List<ResidentDTO> getResidentByFilterService(String nome) throws SQLException {
//		
//		if(nome == null || nome.isEmpty()) {
//			throw new IllegalArgumentException("O nome não pode ser nulo");
//		}
//		
//		List<Resident> resident = residentRepository.findByNome(nome);
//		if(!resident.isEmpty()) {
//			return resident.stream().map(ResidentDTO::new).collect(Collectors.toList());
//		}
//		return new ArrayList<>();
//		
//	}
	
	// Esse metodo getMoradorDTOByFilter faz a mesma que o getMoradorByFilter porem esse metodo usa projeção 
	// como o metodo criado no MoradorRepository
	public List<ResidentDTO> getResidentDTOByFilter(String nome) throws SQLException {
		
		if(nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("O nome não pode ser nulo");
		}
		
		List<ResidentDTO> resident = residentRepository.findDTOByNome(nome);
		if(!resident.isEmpty()) {
			return resident;
		}
		return new ArrayList<>();
		
	}

}
