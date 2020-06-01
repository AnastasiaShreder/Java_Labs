package main.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "deanery.marks")
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = People.class)
    @NotNull(message = "student is required")
    private People student;

    @ManyToOne (targetEntity = Subjects.class)
    @NotNull(message = "subject is required")
    private Subjects subject;

    @ManyToOne (targetEntity = People.class)
    @NotNull(message = "teacher is required")
    private People teacher;

    @Column (name = "value")
    @NotNull(message = "value is required")
    private Integer value;

    public Marks(){}

    public Marks(People student, Subjects subject, People teacher, Integer value) {
        this.student = student;
        this.subject = subject;
        this.teacher = teacher;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public People getStudent() {
        return student;
    }

    public void setStudent(People student) {
        this.student = student;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }

    public People getTeacher() {
        return teacher;
    }

    public void setTeacher(People teacher) {
        this.teacher = teacher;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Marks{" +
                "id=" + id +
                ", student=" + student +
                ", subject=" + subject +
                ", teacher=" + teacher +
                ", value=" + value +
                '}';
    }
}
