package com.example.exam.selenium.po;

import com.example.exam.selenium.PageObject;
import org.openqa.selenium.WebDriver;

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


}
