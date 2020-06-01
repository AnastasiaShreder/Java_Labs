package main.service;

import main.entity.Marks;
import main.entity.People;
import main.exception.MarksNotFoundException;
import main.exception.PeopleNotFoundException;
import main.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleServiceImpl implements PeopleService{

    @Autowired
    private PeopleRepository ppRepository;

    @Override
    public List<People> listPeople() {
        return (List<People>) ppRepository.findAll();
    }

    @Override
    public People findPeople(Long id) {
        Optional<People> optPeople = ppRepository.findById(id);
        if (optPeople.isPresent()){
            return optPeople.get();
        }else {
            throw new PeopleNotFoundException("Person not found");
        }
    }

    @Override
    public People addPeople(People people) {
        return ppRepository.save(people);
    }

    @Override
    public void deletePeople(Long id) {
        ppRepository.deleteById(id);
    }
}
