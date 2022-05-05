package dev.in.villaDevin.controller.service;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.in.villaDevin.exeptions.ResidentNotFoundExcetion;
import dev.in.villaDevin.model.Resident;
import dev.in.villaDevin.model.repository.ResidentRepository;
import dev.in.villaDevin.model.transport.ResidentDTO;
import dev.in.villaDevin.model.transport.ResidentNameAndIdProjection;

@Service
public class ResidentService {

	private final Logger LOG = LogManager.getLogger(ResidentService.class);

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

	public List<ResidentNameAndIdProjection> listResident() throws SQLException {
		return this.residentRepository.findAllResident();
	}

	
	  public ResidentDTO getById(Long id) throws SQLException {
	 
	  
	  if (id == null) { throw new
	  IllegalArgumentException("O id não pode ser nulo"); }
	  
	  Resident resident = this.residentRepository.findAllById(id); return new
	  ResidentDTO(resident); }
	

//	public ResidentDTO getById(String id) throws SQLException {
//		
//		if(id == null) {
//			throw new IllegalArgumentException("O Id não pode ser nulo");
//		}
//		
//		Optional<Resident> resident = residentRepository.findById(id);
//		
//		if(resident.isPresent()) {
//			return new ResidentDTO(resident.get());
//		}
//		return null;
//	}

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

	// Esse metodo getMoradorDTOByFilter faz a mesma que o getMoradorByFilter porem
	// esse metodo usa projeção
	// como o metodo criado no MoradorRepository
	public List<ResidentNameAndIdProjection> getResidentDTOByFilter(String name) throws SQLException {

		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("O nome não pode ser nulo");
		}

		List<ResidentNameAndIdProjection> resident = residentRepository.findDTOByName(name);
		if (!resident.isEmpty()) {
			return resident;
		}
		return new ArrayList<>();

	}

	public void delete(Long id) throws IllegalArgumentException {
		residentRepository.deleteById(id);
	}

	public List<ResidentNameAndIdProjection> getResidentFilterByMonth(String month) throws SQLException {

		if (month == null || month.isEmpty()) {
			throw new IllegalArgumentException("O mês não pode ser nulo");
		}

		List<Resident> residents = residentRepository.findAll();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM").withLocale(
				new Locale("pt","BR"));
		
		List<ResidentNameAndIdProjection> filteredResidents = residents.stream().filter((resident)->{
			String monthNasc = resident.getDateNasc().format(formatter);
			return monthNasc.equalsIgnoreCase(month);
		}).collect(Collectors.toList());
		
		return filteredResidents;

	}

}
