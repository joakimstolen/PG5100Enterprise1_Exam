package com.example.exam.backend.entity;


import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

    @ManyToMany(mappedBy = "lootBoxesList")
    private List<Users> allItemBuyers;

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

    public List<Users> getAllItemBuyers() {
        return allItemBuyers;
    }

    public void setAllItemBuyers(List<Users> allItemBuyers) {
        this.allItemBuyers = allItemBuyers;
    }
}
