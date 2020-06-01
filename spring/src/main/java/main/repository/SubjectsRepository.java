package main.repository;

import main.entity.People;
import main.entity.Subjects;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubjectsRepository extends CrudRepository<Subjects, Long> {
}
