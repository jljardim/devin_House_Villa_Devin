package dev.in.villaDevin.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.in.villaDevin.model.Morador;
import dev.in.villaDevin.model.transport.MoradorDTO;

@Repository
public interface MoradorRepository extends CrudRepository<Morador, Integer> {
	
	public List<Morador> findByName(String nome);
	
	@Query
	public List<MoradorDTO> findByNameDTO(String nome);
	
	public List<Morador>  findByData_NascGreaterThan(Date data_Nasc);
	
	// spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details  saite com
	//documentação do spring para tipos de metodos de acesso ao banco de dados.
	
	

}
