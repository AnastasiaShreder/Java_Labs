package main.service;

import main.entity.Marks;
import main.entity.Subjects;
import main.exception.MarksNotFoundException;
import main.exception.SubjectsNotFoundException;
import main.repository.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectsServiceImpl implements SubjectsService {

    @Autowired
    private SubjectsRepository subRepository;

    @Override
    public List<Subjects> listSubjects() {
        return (List<Subjects>) subRepository.findAll();
    }

    @Override
    public Subjects findSubjects(Long id) {
        Optional<Subjects> optSubjects = subRepository.findById(id);
        if (optSubjects.isPresent()){
            return optSubjects.get();
        }else {
            throw new SubjectsNotFoundException("Subject not found");
        }
    }

    @Override
    public Subjects addSubjects(Subjects subjects) {
        return subRepository.save(subjects);
    }

    @Override
    public void deleteSubjects(Long id) {
        subRepository.deleteById(id);
    }
}
