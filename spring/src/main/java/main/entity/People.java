package main.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "deanery.people")
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    @NotBlank(message = "first name is required")
    private String first_name;

    @Column(name = "last_name")
    @NotBlank(message = "last name is required")
    private String last_name;

    @Column (name = "pather_name")
    @NotBlank(message = "pather name required")
    private String pather_name;

    @ManyToOne(targetEntity = Groups.class)
    @NotNull(message = "group is required")
    private Groups group;

    @Column (name = "type")
    @NotNull(message = "type is required")
    private Character type;


    public People() {
    }

    public People(String first_name, String last_name, String pather_name, Groups group, Character type) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.pather_name = pather_name;
        this.group = group;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPather_name() {
        return pather_name;
    }

    public void setPather_name(String pather_name) {
        this.pather_name = pather_name;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", pather_name='" + pather_name + '\'' +
                ", group=" + group +
                ", type=" + type +
                '}';
    }
}