//This file contains code from the lecturer and has been altered to fit the needs of this assignment
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/main/java/org/tsdes/intro/exercises/quizgame/backend/entity/SubCategory.java



package com.example.exam.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Copy {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private int duplicates;

    @ManyToOne
    private Users itemCopyOwner;

    @ManyToOne
    @NotNull
    private Item itemInformation;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getItemCopyOwner() {
        return itemCopyOwner;
    }

    public void setItemCopyOwner(Users itemCopyOwner) {
        this.itemCopyOwner = itemCopyOwner;
    }

    public Item getItemInformation() {
        return itemInformation;
    }

    public void setItemInformation(Item itemInformation) {
        this.itemInformation = itemInformation;
    }

    public int getDuplicates() {
        return duplicates;
    }

    public void setDuplicates(int duplicates) {
        this.duplicates = duplicates;
    }
}
