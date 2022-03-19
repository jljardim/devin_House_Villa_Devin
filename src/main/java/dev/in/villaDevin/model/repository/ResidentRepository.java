package dev.in.villaDevin.model.repository;

//import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.in.villaDevin.model.Resident;
import dev.in.villaDevin.model.transport.ResidentDTO;
import dev.in.villaDevin.model.transport.ResidentFindAllProjection;
import dev.in.villaDevin.model.transport.ResidentNameProjection;

@Repository
public interface ResidentRepository extends CrudRepository<Resident, Integer> {
	
	public List<Resident> findByName(String name);
	
	@Query("SELECT a FROM Resident a WHERE a.name =:name")
	public List<Resident> findByNameDTO(String name);
	
	@Query("SELECT a FROM Resident a WHERE a.name =:name")
	public List<ResidentDTO> findDTOByName(String name);
	
	public Resident findAllById(Integer id);
	
//	@Query(value = "SELECT * FROM resident", nativeQuery = true)
//	public List<ResidentDTO> findById(Integer id);
	
	
//	@Query(value = "SELECT * FROM resident  WHERE nome =:nome", nativeQuery = true)
//	public List<Resident> findByNomeDTO3(String nome);
	
	// Faz a mesma coisa que as queries acima porem foi criado uma interface para essa facilitand assim o trabalho
	@Query(value = "SELECT * FROM resident", nativeQuery = true)
	public List<ResidentFindAllProjection> findAllResident();
	
	
//	public List<Resident>  findByData_NascGreaterThan(Date data_Nasc);
	
	// spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details  saite com
	//documentação do spring para tipos de metodos de acesso ao banco de dados.
//	
//	@Query(value = "SELECT * FROM resident", nativeQuery = true)
//	public List<ResidentFindAllProjection> findAll();
//	
//
}
