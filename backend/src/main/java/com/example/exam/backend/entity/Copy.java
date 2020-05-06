package com.example.exam.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Copy {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @NotNull
    private Users purchasedBy;

    @ManyToOne //One pokemon can have many purchases, but one single purchase can only be related to one pokemon
    @NotNull
    private Item itemInformation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getPurchasedBy() {
        return purchasedBy;
    }

    public void setPurchasedBy(Users purchasedBy) {
        this.purchasedBy = purchasedBy;
    }

    public Item getItemInformation() {
        return itemInformation;
    }

    public void setItemInformation(Item itemInformation) {
        this.itemInformation = itemInformation;
    }
}
