package main.service;

import main.entity.Groups;
import main.entity.Marks;
import main.exception.GroupsNotFoundException;
import main.exception.MarksNotFoundException;
import main.repository.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupsServiceImpl implements GroupsService{
    @Autowired
    private GroupsRepository grRepository;

    @Override
    public List<Groups> listGroups() {
        return (List<Groups>) grRepository.findAll();
    }

    @Override
    public Groups findGroups(Long id) {
        Optional<Groups> optGroups = grRepository.findById(id);
        if (optGroups.isPresent()){
            return optGroups.get();
        }else {
            throw new GroupsNotFoundException("Group not found");
        }
    }

    @Override
    public Groups addGroups(Groups groups) {
        return grRepository.save(groups);
    }

    @Override
    public void deleteGroups(Long id) {
        grRepository.deleteById(id);
    }


}
