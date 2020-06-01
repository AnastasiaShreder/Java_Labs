package main.repository;

import main.entity.Groups;
import main.entity.People;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GroupsRepository extends CrudRepository <Groups, Long> {
}
