package dev.in.villaDevin.model.repository;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.in.villaDevin.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findOneByName(String name) throws SQLException;
    List<Role> findAllByNameIn(Set<String> names) throws SQLException;
}
