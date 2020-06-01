package main.web;

import main.entity.Groups;
import main.entity.Marks;
import main.entity.People;
import main.entity.Subjects;
import main.exception.GroupsNotFoundException;
import main.exception.MarksNotFoundException;
import main.exception.PeopleNotFoundException;
import main.exception.SubjectsNotFoundException;
import main.repository.MarksRepository;
import main.repository.PeopleRepository;
import main.service.GroupsService;
import main.service.MarksService;
import main.service.PeopleService;
import main.service.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bt")
public class Controller {

    private MarksService marksService;
    private PeopleService peopleService;
    private SubjectsService subjectsService;
    private GroupsService groupsService;
    private PeopleRepository ppRepository;
    private MarksRepository mkRepository;


    @PostMapping(value = "/addMarks", consumes = "application/json", produces = "application/json")
    public Marks addMarks (@RequestBody Marks newMark){
        return marksService.addMarks(newMark);
    }

    @PostMapping(value = "/addPeople", consumes = "application/json", produces = "application/json")
    public People addPeople (@RequestBody People newPerson){
        return peopleService.addPeople(newPerson);
    }

    @PostMapping(value = "/addSubjects", consumes = "application/json", produces = "application/json")
    public Subjects addSubjects (@RequestBody Subjects newSubject){
        return subjectsService.addSubjects(newSubject);
    }

    @PostMapping(value = "/addGroups", consumes = "application/json", produces = "application/json")
    public Groups addGroups (@RequestBody Groups newGroup){
        return groupsService.addGroups(newGroup);
    }

    @DeleteMapping(value = "/deleteGroups/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(@PathVariable("id") Long id){
        try{
            Optional<People> person = ppRepository.findPeopleByGroupId(id);
            if (person.isPresent()){
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Such group is related to person");
            }else{
                groupsService.deleteGroups(id);
            }
        } catch(EmptyResultDataAccessException exception){

        }
    }

    @DeleteMapping(value = "/deleteMarks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMark(@PathVariable("id") Long id){
        try{
            marksService.deleteMarks(id);
        } catch(EmptyResultDataAccessException exception){

        }
    }

    @DeleteMapping(value = "/deleteSubjects/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubject(@PathVariable("id") Long id){
        try{
            Optional<Marks> mark = mkRepository.findMarkBySubjectId(id);
            if (mark.isPresent()){
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Such subject is related to mark");
            }else{
                subjectsService.deleteSubjects(id);
            }
        } catch(EmptyResultDataAccessException exception){
        }
    }

    @DeleteMapping(value = "/deletePeople/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable("id") Long id){
        try{
            Optional<Marks> mark = mkRepository.findMarkByStudentId(id);
            if (mark.isPresent()){
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Such person is related to mark");
            }else{
                peopleService.deletePeople(id);
            }
        } catch(EmptyResultDataAccessException exception){

        }
    }

    @GetMapping("/marks")
    public ResponseEntity<List<Marks>> getAllMarks(){
        List<Marks> list = marksService.listMarks();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/marks/{id}")
    public ResponseEntity<Marks> getMarks(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(marksService.findMarks(id), HttpStatus.OK);
        } catch (MarksNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Marks not found");
        }
    }

    @GetMapping("/people")
    public ResponseEntity<List<People>> getAllPeople() {
        List<People> list = peopleService.listPeople();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/people/{id}")
    public ResponseEntity<People> getPeople(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(peopleService.findPeople(id), HttpStatus.OK);
        } catch (PeopleNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<Subjects>> getAllSubjects(){
        List<Subjects> list = subjectsService.listSubjects();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/subjects/{id}")
    public ResponseEntity<Subjects> getSubjects(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(subjectsService.findSubjects(id), HttpStatus.OK);
        } catch (SubjectsNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }
    }

    @GetMapping("/groups")
    public ResponseEntity<List<Groups>> getAllGroups(){
        List<Groups> list = groupsService.listGroups();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/groups/{id}")
    public ResponseEntity<Groups> getGroups(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(groupsService.findGroups(id), HttpStatus.OK);
        } catch (GroupsNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }
    }


    @Autowired
    public void setMarksService(MarksService marksService) {
        this.marksService = marksService;
    }

    @Autowired
    public void setPeopleService(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Autowired
    public void setSubjectsService(SubjectsService subjectsService) {
        this.subjectsService = subjectsService;
    }

    @Autowired
    public void setGroupsService(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    @Autowired
    public void setPpRepository(PeopleRepository ppRepository) {
        this.ppRepository = ppRepository;
    }

    @Autowired
    public void setMkRepository(MarksRepository mkRepository) {
        this.mkRepository = mkRepository;
    }

}
