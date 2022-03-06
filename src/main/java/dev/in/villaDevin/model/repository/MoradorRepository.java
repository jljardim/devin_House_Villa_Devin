package dev.in.villaDevin.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.in.villaDevin.model.Morador;

@Repository
public interface MoradorRepository extends CrudRepository<Morador, Long> {

}
