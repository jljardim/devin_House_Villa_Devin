package dev.in.villaDevin.controller.rest;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.in.villaDevin.controller.service.ResidentService;
import dev.in.villaDevin.exeptions.ResidentNotFoundExcetion;
import dev.in.villaDevin.model.Resident;
import dev.in.villaDevin.model.transport.ResidentDTO;
import dev.in.villaDevin.model.transport.ResidentFindAllProjection;
import dev.in.villaDevin.model.transport.ResidentNameProjection;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/resident")
public class ResidentRest {
	
	private ResidentService residentService;
	
	
	public ResidentRest(ResidentService residentService) {
		this.residentService = residentService;
	}
	

	
	@GetMapping("/list-all")  // Workning this moment
	public List<ResidentFindAllProjection> listResident() throws SQLException {
		return residentService.listResident();
	}
	
	

	@GetMapping("/{id}")
	public ResidentDTO getById(@PathVariable("id") Integer id) throws SQLException {
		return residentService.getById(id);
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<ResidentDTO> createNewResident(@RequestBody ResidentDTO resident) throws SQLException,
	ResidentNotFoundExcetion {
		ResidentDTO residentCreated = this.residentService.create(resident);
		if(residentCreated == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(residentCreated);
	}
	
	
	@GetMapping("/filter")
	public List<ResidentDTO> getResidentByFilter(@RequestParam("name") String name)
			throws  SQLException {
		return residentService.getResidentDTOByFilter(name);
	}
	
//	@GetMapping("/list")
//	public List<ResidentFindAllProjection> listResidentAll() throws SQLException {
//		return residentService.listResidentAll();
//	}
}
