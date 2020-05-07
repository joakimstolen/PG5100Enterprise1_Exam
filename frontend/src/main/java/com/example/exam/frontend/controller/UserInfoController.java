package com.example.exam.frontend.controller;

import com.example.exam.backend.entity.Users;
import com.example.exam.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class UserInfoController {


    @Autowired
    private UserService userService;

    public String getUserName() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public Users getUser() {
        return userService.findUserByUserName(getUserName());
    }


    public String buyLootBox(String userID){
        userService.buyLootBox(userID);
        return "/user.xhtml?faces-redirect=true&successFull=true";
    }

}
