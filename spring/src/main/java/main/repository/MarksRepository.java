package main.repository;

import main.entity.Marks;
import main.entity.Subjects;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MarksRepository extends CrudRepository<Marks, Long> {
    Optional<Marks> findMarkByStudentId(Long id);
    Optional<Marks> findMarkBySubjectId(Long id);
}
