package com.example.exam.backend.service;

import com.example.exam.backend.entity.Item;
import com.example.exam.backend.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private EntityManager em;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(String userName, String firstName, String lastName, String password, String email, String role, Long currency, int availableBoxes) {
        String hashedPassword = passwordEncoder.encode(password);

        if ((em.find(Users.class, userName) != null) || (em.find(Users.class, email) != null)) {
            return false;
        }

        Users users = new Users();
        users.setUserID(userName);
        users.setFirstName(firstName);
        users.setLastName(lastName);
        users.setHashedPassword(hashedPassword);
        users.setRoles(Collections.singleton(role));
        users.setEnabled(true);
        users.setEmail(email);
        users.setCurrency(currency);
        users.setAvailableBoxes(availableBoxes);

        em.persist(users);

        return true;
    }

    public Users findUserByUserName(String userName) {
        Users users = em.find(Users.class, userName);
        if (users == null) {
            throw new IllegalStateException("No user with given userName");
        }
        users.getCopies().size();
        return users;
    }


    public Long buyLootBox(String userID){
        Users user = em.find(Users.class, userID);

        if (user == null){
            throw new IllegalArgumentException("User not found");
        }

        long currency = user.getCurrency();
        int lootBox = user.getAvailableBoxes();

        if (currency < 100L){
            return null;
        }

        currency = (currency-100L);
        lootBox++;
        user.setAvailableBoxes(lootBox);

        return user.setCurrency(currency);
    }





}