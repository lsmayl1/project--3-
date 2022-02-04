package com.example.project.model;

import lombok.*;


import javax.persistence.*;

@Entity
@Table(name = "computer_project")
@Data
public class ModelComputer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String marka;
    private String model;
    private int price;
    private String opisanie;
    private String sostoyanie;
    private String img;
    private int operativpamat;
    private String processor;
    private int pamat;
    private String tippamat;
    private String operacsistema;
    private String videokarta;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
