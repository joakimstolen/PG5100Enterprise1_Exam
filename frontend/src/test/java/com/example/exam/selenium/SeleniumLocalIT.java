package com.example.exam.selenium;

import com.example.exam.Application;
import com.example.exam.backend.entity.Copy;
import com.example.exam.backend.entity.Item;
import com.example.exam.backend.service.ItemService;
import com.example.exam.selenium.po.IndexPO;
import com.example.exam.selenium.po.SignUpPO;
import com.example.exam.selenium.po.UserPO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
public class SeleniumLocalIT {

    @Autowired
    ItemService itemService;

    private static WebDriver driver;

    @LocalServerPort
    private int port;


    @BeforeAll
    public static void initClass() {

        driver = SeleniumDriverHandler.getChromeDriver();

        assumeTrue(driver != null, "Cannot find/initialize Chrome driver");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected String getServerHost() {
        return "localhost";
    }

    protected int getServerPort() {
        return port;
    }

    private static final AtomicInteger counter = new AtomicInteger(0);

    private String getUniqueId() {
        return "foo_SeleniumLocalIT_" + counter.getAndIncrement();
    }

    private IndexPO home;

    private UserPO userPO;

    private IndexPO createNewUser(String username, String password) {
        home.toStartingPage();

        SignUpPO signUpPO = home.toSignUp();

        IndexPO indexPO = signUpPO.signUP(username, password);
        assertNotNull(indexPO);

        return indexPO;
    }

    @BeforeEach
    public void initTest() {

        getDriver().manage().deleteAllCookies();

        home = new IndexPO(getDriver(), getServerHost(), getServerPort());

        home.toStartingPage();

        assertTrue(home.isOnPage(), "Failed to start from home page");
    }

    //EXtra test to create and logout user
    @Test
    public void testCreateAndLogoutUser() {
        assertFalse(home.isLoggedIn());

        String userID = getUniqueId();
        String password = "123456";

        home = createNewUser(userID, password);

        assumeTrue(home.isLoggedIn());
        assumeTrue(home.getDriver().getPageSource().contains(userID));

        home.doLogout();

        assertFalse(home.isLoggedIn());
        assertFalse(home.getDriver().getPageSource().contains(userID));
    }


    //testing access to user page. should fail if user is not logged in
    @Test
    public void testDisplayUserInfo() {
        UserPO userPO = home.getUserInfo();
        assertNull(userPO);
        String userID = getUniqueId();
        home = createNewUser(userID, "123123");
        userPO = home.getUserInfo();
        assertNotNull(userPO);
        assertTrue(userPO.getUserName().contains(userID));
        userPO.doLogout();
        userPO = home.getUserInfo();
        assertNull(userPO);
    }

    @Test
    public void testDisplayHomePage(){
        assertEquals(20, home.getNumberOfItemsDisplayed());
    }


    @Test
    public void testEmptyCollection(){
        UserPO userPO = home.getUserInfo();
        assertNull(userPO);

        String userID = getUniqueId();
        home = createNewUser(userID, "123456");
        userPO = home.getUserInfo();
        assertNotNull(userPO);
        assertTrue(userPO.getUserName().contains(userID));


        assertEquals(0, home.getNumberOfItemsDisplayedUserPage());
        assertTrue(userPO.getDriver().getPageSource().contains("Available boxes: 3"));

    }


    @Test
    public void testRedeemLootBox() {

        UserPO userPO = home.getUserInfo();
        assertNull(userPO);

        String userID = getUniqueId();
        home = createNewUser(userID, "123456");
        userPO = home.getUserInfo();
        assertNotNull(userPO);
        assertTrue(userPO.getUserName().contains(userID));

        userPO.clickAndWait("openLootBtn");
        assertTrue(userPO.getDriver().getPageSource().contains("Available boxes: 2"));

        assertEquals(2, home.getNumberOfItemsDisplayed());
    }


    @Test
    public void testFailedReedemLootBox(){
        UserPO userPO = home.getUserInfo();
        assertNull(userPO);

        String userID = getUniqueId();
        home = createNewUser(userID, "123456");
        userPO = home.getUserInfo();
        assertNotNull(userPO);
        assertTrue(userPO.getUserName().contains(userID));



        for (int i = 0; i < 3; i++){
            userPO.clickAndWait("openLootBtn");
        }

        assertTrue(userPO.getDriver().getPageSource().contains("You are out of Lootboxes"));
    }

    @Test
    public void testBuyLootbox(){
        UserPO userPO = home.getUserInfo();
        assertNull(userPO);

        String userID = getUniqueId();
        home = createNewUser(userID, "123456");
        userPO = home.getUserInfo();
        assertNotNull(userPO);
        assertTrue(userPO.getUserName().contains(userID));

        assertTrue(userPO.getDriver().getPageSource().contains("Currency: 700"));

        userPO.clickAndWait("buyLootBtn");

        assertTrue(userPO.getDriver().getPageSource().contains("Currency: 600"));
        assertTrue(userPO.getDriver().getPageSource().contains("Available boxes: 4"));

    }


    //Did not manage to complete this perfectly
    @Test
    public void testMillItem(){
        UserPO userPO = home.getUserInfo();
        assertNull(userPO);

        String userID = getUniqueId();
        home = createNewUser(userID, "123456");
        userPO = home.getUserInfo();
        assertNotNull(userPO);
        assertTrue(userPO.getUserName().contains(userID));
        userPO.clickAndWait("openLootBtn");
        assertTrue(userPO.getDriver().getPageSource().contains("Available boxes: 2"));
        assertTrue(userPO.getDriver().getPageSource().contains("Currency: 700"));
        userPO.clickAndWait("j_idt20:itemTable:0:millBtn");
        assertEquals(2, home.getNumberOfItemsDisplayed());

    }



    //Search function
    @Test
    public void testSearch() {

        List<Item> allItems = itemService.getItems();
        Item firstItem = allItems.get(0);
        home = home.searchOnPage("byPrice", firstItem.getPrice().toString());
        assertTrue(home.isInFirstColumn(firstItem.getId().toString()));
        home.toStartingPage();
        home = home.searchOnPage("byName", firstItem.getName());
        assertTrue(home.isInFirstColumn(firstItem.getId().toString()));
    }



}
