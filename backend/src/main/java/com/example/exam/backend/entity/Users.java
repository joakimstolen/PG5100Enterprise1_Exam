package com.example.exam.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
public class Users {

    public static final int MIN_PASSWORD_LENGTH = 3;

    @Id
    @NotBlank
    private String userID;

    @NotBlank
    @Size(max = 128)
    private String firstName;

    @NotBlank
    @Size(max = 128)
    private String lastName;

    @NotNull
    @NotBlank
    private String hashedPassword;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private Boolean enabled;

    @NotNull
    private Long currency;


    @ManyToMany
    private List<Item> lootBoxesList;






    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public List<Item> getLootBoxesList() {
        return lootBoxesList;
    }

    public void setLootBoxesList(List<Item> lootBoxesList) {
        this.lootBoxesList = lootBoxesList;
    }

    public Long getCurrency() {
        return currency;
    }

    public long setCurrency(Long currency) {
        this.currency = currency;
        return 0;
    }
}