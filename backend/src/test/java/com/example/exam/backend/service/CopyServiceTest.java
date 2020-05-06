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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    public void testCreatePurchase(){
        userService.createUser("test", "test", "test", "123", "test@mail.com", "user", 100L);
        Long itemId = itemService.createItem("itemTest", "itemTest", "itemTest", 50L);
        Long copyId = copyService.newCopy(itemId, "test");
        assertNotNull(copyId);
    }


    @Test
    public void testFilterCopyByUserId(){
        String userId = "test";
        userService.createUser(userId, userId, "lastname", "123", "test@mail.com", "user", 100L);
        Long firstItem = itemService.createItem("name1", "desc1", "type1", 1000L);
        Long secondItem = itemService.createItem("name2", "desc2", "type2", 1400L);

        Long firstCopy = copyService.newCopy(firstItem, userId);
        Long secondCopy = copyService.newCopy(secondItem, userId);
        Users user = userService.findUserByUserName(userId);
        assertNotNull(firstCopy);
        assertNotNull(secondCopy);

        List<Copy> userCopy = copyService.filterCopyByUser(user.getUserID());
        assertEquals(2, userCopy.size());

    }



}
