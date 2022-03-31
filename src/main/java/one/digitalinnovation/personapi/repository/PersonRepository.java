package one.digitalinnovation.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import one.digitalinnovation.personapi.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	@Query(nativeQuery = true, value = "SELECT cpf FROM Person WHERE cpf = ?1")
	String findByCpf(String cpf);
	
}
