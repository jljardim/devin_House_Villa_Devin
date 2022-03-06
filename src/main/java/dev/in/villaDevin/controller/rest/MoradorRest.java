package dev.in.villaDevin.controller.rest;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.in.villaDevin.controller.service.MoradorService;
import dev.in.villaDevin.model.transport.MoradorDTO;
import dev.in.villaDevin.model.transport.MoradorNameProjection;

@RestController
@RequestMapping("/morador")
public class MoradorRest {
	
	private MoradorService moradorService;
	
	public MoradorRest(MoradorService moradorService) {
		this.moradorService = moradorService;
	}
	
	@GetMapping("/list-all")
	public List<MoradorNameProjection> listMoradores() throws SQLException {
		return moradorService.listMoradores();
	}
	
	@GetMapping("/{id}")
	public MoradorDTO getById(@PathVariable("id") Integer id) throws SQLException {
		return moradorService.getById(id);
	}
	
	@PostMapping("/create")
	public ResponseEntity<MoradorDTO> createNewMorador(@RequestBody MoradorDTO morador) throws SQLException {
		MoradorDTO moradorCreated = this.moradorService.create(morador);
		if(moradorCreated == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(moradorCreated);
	}
	
	@GetMapping("/filter")
	public List<MoradorDTO> getMoradoresByFilter(@RequestParam("name") String name)
			throws  SQLException {
		return moradorService.getMoradoresDTOByFilter(name);
	}
	
}
