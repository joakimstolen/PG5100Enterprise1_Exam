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
                    firstUser, firstUser, "admin-last-name", "123", "admin@email.com", "admin", currency, 1);

        });

        attempt(() -> {
            return userService.createUser(
                    secondUser, secondUser, "foo-last-name", "123", "foo@email.com", "user", currency,1);

        });

        attempt(() -> {
            return userService.createUser(
                    thirdUser, thirdUser, "bar-last-name", "123", "bar@email.com", "user", currency,1);

        });

        attempt(() -> {
            return userService.createUser(
                    fourthUser, fourthUser, "foobario-last-name", "123", "foobario@email.com", "user", currency,1);

        });


        //Create som default items
        Long firstItem = attempt(() ->
                itemService.createItem("Charmander", "Reception to Bulbasaur has been largely positive and it often appears in top Pokémon lists", "Grass & Poison", 150L)
        );

        Long secondItem = attempt(() ->
                itemService.createItem("Pikachu", "It raises its tail to check its surroundings and it sometimes gets struck by lightning in that pose", "Electric", 120L)
        );


        Long thirdItem = attempt(() ->
                itemService.createItem("Oddish", "It buries itself in the soil to absorb the nutrients. the more water it drinks, the glossier it becomes.", "Grass & Poison", 150L)
        );

        Long fourthItem = attempt(() ->
                itemService.createItem("Squirtle", "Its English name is a portmanteau of squirt and turtle", "Water", 150L)
        );

        Long fifthItem = attempt(() ->
                itemService.createItem("Diglett", "Farmers like to use Digletts to plow to soil for crops to grow plentifully.", "Ground", 175L)
        );

        Long sixthItem = attempt(() ->
                itemService.createItem("Grimer", "It eats sewer waste for food. Its body is very slippery so it can slip through almost any opening.", "Poison", 125L)
        );

        Long seventhItem = attempt(() ->
                itemService.createItem("Bulbasaur", "Reception to Bulbasaur has been largely positive and it often appears in top Pokémon lists", "Grass & Poison", 125L)
        );

        Long eigthItem = attempt(() ->
                itemService.createItem("Caterpie", "To avoid predators, it releases an odor that makes bird Pokemon think twice about going after it.", "Bug", 205L)
        );

        Long ninethItem = attempt(() ->
                itemService.createItem("Pidgey", "The common bird of Kanto, Pidgey is a bird that will go after bug Pokemon until it scurries away", "Flying", 180L)
        );

        Long tenthItem = attempt(() ->
                itemService.createItem("Rattata", "Its teeth grow very long overtime, so it has to gnaw on logs, houses, telephone poles and more so it can be quite a pest at times", "Normal", 100L)
        );

        Long eleventhItem = attempt(() ->
                itemService.createItem("Arbok", "The pattern on its chest is different everywhere. It is hard to escape from its coil because its muscles are so strong.", "Poison", 165L)
        );

        Long twelvthItem = attempt(() ->
                itemService.createItem("Clefairy", "Originally proposed to be joint mascot of the Pokémon franchise alongside Pikachu, but was quickly overshadowed by the latter's popularity", "Fairy", 200L)
        );

        Long thirteenthItem = attempt(() ->
                itemService.createItem("Psyduck", "When Psyduck's headache gets severe, it unleashes its psychic powers", "Water", 185L)
        );

        Long forteenthItem = attempt(() ->
                itemService.createItem("Mankey", "Mankey specializes in physical fighting and is very aggressive and short-tempered", "Fighting", 225L)
        );

        Long fifteenthItem = attempt(() ->
                itemService.createItem("Abra", "Even when its sleeping, which it does most of its life, it can sense danger. When it is in danger, it will teleport to safety", "Psychic", 195L)
        );

        Long sixteenthItem = attempt(() ->
                itemService.createItem("Geodude", "Sometimes mistaken as ordinary rocks, people mistakenly step on them resulting in them getting hurt", "Rock", 150L)
        );

        Long eighteenthItem = attempt(() ->
                itemService.createItem("Magnemite", "Strong magnetic field", "Electric", 125L)
        );

        Long nineteenthItem = attempt(() ->
                itemService.createItem("Haunter", "Ghostly looking creature, that will haunt you", "Ghost", 125L)
        );

        Long twentyethItem = attempt(() ->
                itemService.createItem("Cubone", "The stains on its skull are caused by the tears it sheds knowing it will never see its mother again.", "Ground", 150L)
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
