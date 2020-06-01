package main.repository;

import main.entity.People;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PeopleRepository extends CrudRepository<People, Long> {
    Optional<People> findPeopleByGroupId(Long id);
}
