package com.example.exam.backend.service;

import com.example.exam.backend.TestApplication;
import com.example.exam.backend.entity.Item;
import com.example.exam.backend.entity.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

//    @Test
//    public void testGetRandomItem(){
//        Long itemId = itemService.createItem("test", "test", "test", 100L);
//        Long itemId2 = itemService.createItem("test2", "test2", "test2", 300L);
//        assertNotNull(itemId);
//        assertNotNull(itemId2);
//
//        List<Item> randomItems = itemService.getRandomItems(1,false);
//        assertNotNull(randomItems);
//    }


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
    public void testFilterItemsByLocation(){
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

}
