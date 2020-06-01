package main.service;

import main.entity.Groups;
import main.entity.Marks;

import java.util.List;

public interface GroupsService {
    List<Groups> listGroups();
    Groups findGroups(Long id);
    Groups addGroups(Groups groups);
    void deleteGroups(Long id);
}
