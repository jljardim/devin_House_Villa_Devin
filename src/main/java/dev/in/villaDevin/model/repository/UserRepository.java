package dev.in.villaDevin.model.repository;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.in.villaDevin.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findOneByEmail(String email) throws SQLException;

    Optional<User> findOneByResidentId(Long residentId) throws SQLException;

    void deleteByResidentId(Long residentId) throws SQLException;
}
