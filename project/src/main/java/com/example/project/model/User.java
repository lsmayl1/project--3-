package com.example.project.model;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_project")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private String nameSurname;
    private String email;
    private String number;

    @OneToMany(mappedBy = "user")
    private List<ModelComputer> computers;

    public void addToList(ModelComputer computer){
        if (computers == null){
            computers = new ArrayList<>();
        }
        computers.add(computer);
    }
}
