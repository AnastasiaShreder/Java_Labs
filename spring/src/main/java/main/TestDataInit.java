package main;

import main.entity.*;
import main.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class TestDataInit implements CommandLineRunner {

    @Autowired
    MarksRepository mkRep;

    @Autowired
    UserRepository userRep;

    @Autowired
    GroupsRepository grRep;

    @Autowired
    PeopleRepository peopleRep;

    @Autowired
    SubjectsRepository subRep;

    @Autowired
    PasswordEncoder pwdEncoder;

    @Override
    public void run(String... args) throws Exception {
//        Groups my_gr = new Groups("STUDENTS");
//        Groups t_gr = new Groups("MASTERS");
//        People stud1 = new People("Daria", "Gorshkova", "Dmitrievna", my_gr, 'S');
//        People stud2 = new People("Anastasia", "Shreder", "Dmitrievna", my_gr, 'S');
//        People stud3 = new People("Elena", "Andreeva", "Dmitrievna", my_gr, 'S');
//        Subjects eco = new Subjects("Economy");
//        Subjects math = new Subjects("Math");
//        Subjects os = new Subjects("Operating Systems");
//        People master1 = new People ("Ivan", "Zima", "Antonovich", t_gr, 'T');
//        People master2 = new People ("Igor", "Lennov", "Alekseevich", t_gr, 'T');
//        People master3 = new People ("Anton", "Murin", "Antonovich", t_gr, 'T');
//        grRep.save(my_gr);
//        grRep.save(t_gr);
//        peopleRep.save(stud1);
//        peopleRep.save(stud2);
//        peopleRep.save(stud3);
//        peopleRep.save(master1);
//        peopleRep.save(master2);
//        peopleRep.save(master3);
//        subRep.save(eco);
//        subRep.save(math);
//        subRep.save(os);
//        mkRep.save(new Marks(stud1, eco, master1, 5));
//        mkRep.save(new Marks(stud2, eco, master1, 4));
//        mkRep.save(new Marks(stud3, eco, master1, 5));
//        mkRep.save(new Marks(stud1, math, master2, 5));
//        mkRep.save(new Marks(stud2, math, master2, 4));
//        mkRep.save(new Marks(stud3, math, master2, 3));
//        mkRep.save(new Marks(stud1, os, master3, 4));
//        mkRep.save(new Marks(stud2, os, master3, 4));
//        mkRep.save(new Marks(stud3, os, master3, 3));


//          userRep.save(new User("user", pwdEncoder.encode("pwd"), Collections.singletonList("ROLE_USER")));
//          userRep.save(new User("admin", pwdEncoder.encode("apwd"), Collections.singletonList("ROLE_ADMIN")));

    }
}
