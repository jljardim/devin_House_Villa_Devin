package dev.in.villaDevin.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.in.villaDevin.model.Morador;
import dev.in.villaDevin.model.transport.MoradorDTO;
import dev.in.villaDevin.model.transport.MoradorNameProjection;

@Repository
public interface MoradorRepository extends CrudRepository<Morador, Integer> {
	
	public List<Morador> findByName(String nome);
	
	@Query("SELECT a FROM Morador a WHERE a .nome =:nome")
	public List<Morador> findByNameDTO(String nome);
	
	@Query("SELECT a FROM Morador a WHERE a.nome =:nome")
	public List<MoradorDTO> findDTOByName(String nome);
	
	
	@Query(value = "SELECT * FROM morador  WHERE nome =:nome", nativeQuery = true)
	public List<Morador> findByNameDTO3(String nome);
	
	// Faz a mesma coisa que as queries acima porem foi criado uma interface para essa facilitand assim o trabalho
	@Query(value = "SELECT * FROM morador", nativeQuery = true)
	public List<MoradorNameProjection> findAllName();
	
	
	public List<Morador>  findByData_NascGreaterThan(Date data_Nasc);
	
	// spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details  saite com
	//documentação do spring para tipos de metodos de acesso ao banco de dados.
	
	

}
