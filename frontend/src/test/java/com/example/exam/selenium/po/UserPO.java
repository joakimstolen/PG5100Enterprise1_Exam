package com.example.exam.selenium.po;

import com.example.exam.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserPO extends LayoutPO {

    public UserPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public UserPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("User page");
    }

    public String getUserName() {
        return getText("userNameID");
    }


    public UserPO redeemLoot(String itemId){
        if(getDriver().findElements(By.id("openLootBtn")).size() == 0)
            return null;
        clickAndWait("openLootBtn");
        UserPO userPO = new UserPO(this);
        //After clicking booking button our table should have id of user in it
        //User id is found first column

        assertTrue(isInFirstColumn(itemId));
        return userPO;
    }

}
