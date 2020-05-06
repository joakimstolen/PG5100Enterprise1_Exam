package com.example.exam.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;

@Service
public class DefaultDataInitializerService {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CopyService copyService;


    @PostConstruct
    public void init() {

        String firstUser = "admin";
        String secondUser = "foo";
        String thirdUser = "bar";
        String fourthUser = "foobario";

        Long currency = 600L;


        //Create som default users
        attempt(() -> {
            return userService.createUser(
                    firstUser, firstUser, "admin-last-name", "123", "admin@email.com", "admin", currency);

        });

        attempt(() -> {
            return userService.createUser(
                    secondUser, secondUser, "foo-last-name", "123", "foo@email.com", "user", currency);

        });

        attempt(() -> {
            return userService.createUser(
                    thirdUser, thirdUser, "bar-last-name", "123", "bar@email.com", "user", currency);

        });

        attempt(() -> {
            return userService.createUser(
                    fourthUser, fourthUser, "foobario-last-name", "123", "foobario@email.com", "user", currency);

        });


        //Create som default items
        Long firstItem = attempt(() ->
                itemService.createItem("Charmander", "Reception to Bulbasaur has been largely positive and it often appears in top Pokémon lists", "Grass & Poison", 900L)
        );

        Long secondItem = attempt(() ->
                itemService.createItem("Pikachu", "It raises its tail to check its surroundings and it sometimes gets struck by lightning in that pose", "Electric", 1200L)
        );


        Long thirdItem = attempt(() ->
                itemService.createItem("Oddish", "It buries itself in the soil to absorb the nutrients. the more water it drinks, the glossier it becomes.", "Grass & Poison", 400L)
        );

        Long fourthItem = attempt(() ->
                itemService.createItem("Squirtle", "Its English name is a portmanteau of squirt and turtle", "Water", 750L)
        );

        Long fifthItem = attempt(() ->
                itemService.createItem("Diglett", "Farmers like to use Digletts to plow to soil for crops to grow plentifully.", "Ground", 575L)
        );

        Long sixthItem = attempt(() ->
                itemService.createItem("Grimer", "It eats sewer waste for food. Its body is very slippery so it can slip through almost any opening.", "Poison", 1100L)
        );


        copyService.newCopy(firstItem, firstUser);
        copyService.newCopy(secondItem, firstUser);
        copyService.newCopy(thirdItem, thirdUser);
        copyService.newCopy(fourthItem, fourthUser);
        copyService.newCopy(fifthItem, thirdUser);
        copyService.newCopy(sixthItem, secondUser);


    }

    private <T> T attempt(Supplier<T> lambda) {
        try {
            return lambda.get();
        } catch (Exception e) {
            return null;
        }
    }


}
