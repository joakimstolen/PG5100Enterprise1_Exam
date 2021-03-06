//This file contains code from the lecturer and has been altered to fit the needs of this assignment
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/test/java/org/tsdes/intro/exercises/quizgame/backend/service/MatchStatsServiceTest.java

package com.example.exam.backend.service;

import com.example.exam.backend.TestApplication;
import com.example.exam.backend.entity.Item;
import com.example.exam.backend.entity.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ItemServiceTest extends ServiceTestBase{

    @Autowired
    private ItemService itemService;

    @Test
    public void testCreateItem(){
        Long itemId = itemService.createItem("test", "test", "test", 100L);
        assertNotNull(itemId);

        Item item = itemService.getItem(itemId, true);
        assertNotNull(item);
    }

    @Test
    public void testGetRandomItem(){
        Long itemId = itemService.createItem("test", "test", "test", 100L);
        Long itemId2 = itemService.createItem("test2", "test2", "test2", 300L);
        assertNotNull(itemId);
        assertNotNull(itemId2);

        Item randomItem = itemService.getRandomItem();
        assertNotNull(randomItem);
    }


    @Test
    public void testNoItem(){
        assertEquals(0, itemService.getItems().size());
    }

    @Test
    public void getAllItems(){
        Long firstItem = itemService.createItem("test", "test", "test", 100L);
        Long secondItem = itemService.createItem("test2", "test2", "test2", 300L);
        assertNotNull(firstItem);
        assertNotNull(secondItem);

        List<Item> allItems = itemService.getAllItems(false);
        assertEquals(2, allItems.size());
    }


    @Test
    public void testFilterItemsByName(){
        Long firstItem = itemService.createItem("test", "test", "test", 100L);
        Long secondItem = itemService.createItem("test2", "test2", "test2", 300L);
        Long thirdItem = itemService.createItem("test2", "test2", "test2", 200L);

        assertNotNull(firstItem);
        assertNotNull(secondItem);
        assertNotNull(thirdItem);

        List<Item> name1 = itemService.filterItemsByName("test2");
        List<Item> name2 = itemService.filterItemsByName("test");

        assertEquals(2, name1.size());
        assertEquals(1, name2.size());
    }

    @Test
    public void filterByPrice() {
        Long firstItem = itemService.createItem("test", "test", "test", 100L);
        Long secondItem = itemService.createItem("test2", "test2", "test2", 300L);
        Long thirdItem = itemService.createItem("test2", "test2", "test2", 100L);

        assertNotNull(firstItem);
        assertNotNull(secondItem);
        assertNotNull(thirdItem);

        List<Item> lowPrice = itemService.filterByPrice(100L);
        List<Item> highPrice = itemService.filterByPrice(300L);

        assertEquals(1,highPrice.size());
        assertEquals(2,lowPrice.size());
    }

}
