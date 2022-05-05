package dev.in.villaDevin.controller.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
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
import dev.in.villaDevin.model.transport.CreateResidentRequestDTO;
import dev.in.villaDevin.model.transport.CreateResidentResponseDTO;
import dev.in.villaDevin.model.transport.ResidentNameAndIdProjection;
import dev.in.villaDevin.model.transport.ResidentsByMonthResponseDTO;

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
	public CreateResidentResponseDTO create(CreateResidentRequestDTO createResidentDTO) throws ResidentNotFoundExcetion {
		if (createResidentDTO == null) {
			throw new IllegalArgumentException("O morador está nulo");
		}

		Resident resident = residentRepository.save(new Resident(createResidentDTO));
		CreateResidentResponseDTO saveResident = new CreateResidentResponseDTO(resident);

		return saveResident;
	}

	public List<ResidentNameAndIdProjection> listResident() throws SQLException {
		return this.residentRepository.findAllResident();
	}

	public CreateResidentRequestDTO getById(Long id) throws SQLException {

		if (id == null) {
			throw new IllegalArgumentException("O id não pode ser nulo");
		}

		Resident resident = this.residentRepository.findAllById(id);
		return new CreateResidentRequestDTO(resident);
	}

	public List<ResidentNameAndIdProjection> getResidentDTOByFilter(String name) 
			throws SQLException {

		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("O nome não pode ser nulo");
		}

		List<ResidentNameAndIdProjection> resident = residentRepository.findByName(name);
		if (!resident.isEmpty()) {
			return resident;
		}
		return resident;

	}

	public void delete(Long id) throws IllegalArgumentException {
		residentRepository.deleteById(id);
	}

	public List<ResidentsByMonthResponseDTO> getResidentFilterByMonth(String month) 
			throws SQLException {

		if (month == null || month.isEmpty()) {
			throw new IllegalArgumentException("O mês não pode ser nulo");
		}

		List<Resident> residents = residentRepository.findAll();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM")
				.withLocale(new Locale("pt", "BR"));

		List<ResidentsByMonthResponseDTO> filteredResidents = residents.stream()
				.filter((resident) -> {
			String monthNasc = resident.getDateNasc().format(formatter);
			return monthNasc.equalsIgnoreCase(month);
		}).map(resident -> new ResidentsByMonthResponseDTO(resident.getName(), resident.getId()))
				.collect(Collectors.toList());

		return filteredResidents;

	}
	
	public List<ResidentsByMonthResponseDTO> getResidentFilterByAge(Long age) 
			throws SQLException {
		
		if (age == null) {
			throw new IllegalArgumentException("O Idade não pode ser nulo");
		}
		
		List<Resident> residentList = residentRepository.findAll();
		
		LocalDate now = LocalDate.now();
		
		List<ResidentsByMonthResponseDTO> filterResidentsAge = residentList
				.stream().filter((resident) -> {
				LocalDate dateBirthdayResident = resident.getDateNasc(); 
				Period period = Period.between(now, dateBirthdayResident);
				Integer ageResident = Math.abs(period.getYears());
				return ageResident >= age;
				}).map(resident -> new ResidentsByMonthResponseDTO(resident.getName(), resident.getId()))
				.collect(Collectors.toList());
				

		return filterResidentsAge;
		
	}

}
