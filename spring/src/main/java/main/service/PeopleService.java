package main.service;

import main.entity.Marks;
import main.entity.People;

import java.util.List;

public interface PeopleService {
    List<People> listPeople();
    People findPeople(Long id);
    People addPeople(People people);
    void deletePeople(Long id);
}
