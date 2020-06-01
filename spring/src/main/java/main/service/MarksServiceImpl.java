package main.service;

import main.entity.Marks;
import main.exception.MarksNotFoundException;
import main.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarksServiceImpl implements MarksService {

    @Autowired
    private MarksRepository mkRepository;

    @Override
    public List<Marks> listMarks() {
        return (List<Marks>) mkRepository.findAll();
    }

    @Override
    public Marks findMarks(Long id) {
        Optional<Marks> optMarks = mkRepository.findById(id);
        if (optMarks.isPresent()){
            return optMarks.get();
        }else {
            throw new MarksNotFoundException("Mark not found");
        }
    }

    @Override
    public Marks addMarks(Marks marks) {
        return mkRepository.save(marks);
    }

    @Override
    public void deleteMarks(Long id) {
        mkRepository.deleteById(id);
    }
}
