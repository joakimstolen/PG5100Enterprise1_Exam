//This file contains code from the lecturer and has been altered to fit the needs of this assignment
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/test/java/org/tsdes/intro/exercises/quizgame/backend/service/CategoryServiceTest.java

package com.example.exam.backend.service;


import com.example.exam.backend.TestApplication;
import com.example.exam.backend.entity.Copy;
import com.example.exam.backend.entity.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CopyServiceTest extends ServiceTestBase{

    @Autowired
    private ItemService itemService;

    @Autowired
    private CopyService copyService;

    @Autowired
    private UserService userService;

    @Test
    public void testCreateCopy(){
        userService.createUser("test", "test", "test", "123", "test@mail.com", "user", 100L, 1);
        Long itemId = itemService.createItem("itemTest", "itemTest", "itemTest", 1100L);
        Long copyId = copyService.newCopy(itemId, "test");
        assertNotNull(copyId);
    }


    @Test
    public void testFilterCopyByUserId(){
        String userId = "test";
        userService.createUser(userId, userId, "lastname", "123", "test@mail.com", "user", 400L, 3);
        Long firstItem = itemService.createItem("name1", "desc1", "type1", 900L);
        Long secondItem = itemService.createItem("name2", "desc2", "type2", 900L);

        Long firstCopy = copyService.newCopy(firstItem, userId);
        Long secondCopy = copyService.newCopy(secondItem, userId);
        Users user = userService.findUserByUserName(userId);
        assertNotNull(firstCopy);
        assertNotNull(secondCopy);

        List<Copy> userCopy = copyService.filterCopyByUser(user.getUserID());
        assertEquals(2, userCopy.size());

    }


    @Test
    public void testMillItem(){
        String userId = "test";
        userService.createUser(userId, userId, "lastname", "123", "test@mail.com", "user", 400L, 1);
        Long firstItem = itemService.createItem("name1", "desc1", "type1", 900L);
        Long firstCopy = copyService.newCopy(firstItem, userId);
        assertNotNull(firstCopy);

        Long milling = copyService.millCopy(firstCopy, userId);
        assertEquals(milling.longValue(), 0);

    }




}
