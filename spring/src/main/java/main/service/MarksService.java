package main.service;

import main.entity.Marks;

import java.util.List;

public interface MarksService {
    List<Marks> listMarks();
    Marks findMarks(Long id);
    Marks addMarks(Marks marks);
    void deleteMarks(Long id);
}
