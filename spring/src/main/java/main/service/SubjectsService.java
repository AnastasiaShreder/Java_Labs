package main.service;

import main.entity.Marks;
import main.entity.Subjects;

import java.util.List;

public interface SubjectsService {
    List<Subjects> listSubjects();
    Subjects findSubjects(Long id);
    Subjects addSubjects(Subjects subjects);
    void deleteSubjects(Long id);
}
