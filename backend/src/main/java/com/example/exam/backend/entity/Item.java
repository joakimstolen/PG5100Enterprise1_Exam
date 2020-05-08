//This file contains code from the lecturer and has been altered to fit the needs of this assignment
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/main/java/org/tsdes/intro/exercises/quizgame/backend/entity/Category.java

package com.example.exam.backend.entity;


import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String type;

    @Range(min = 0, max = 3000)
    private Long price;

    @OneToMany(mappedBy = "itemInformation")
    private List<Copy> cardInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }


    public List<Copy> getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(List<Copy> cardInfo) {
        this.cardInfo = cardInfo;
    }
}
