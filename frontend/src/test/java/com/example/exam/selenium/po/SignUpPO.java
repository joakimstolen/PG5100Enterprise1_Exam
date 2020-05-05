package com.example.exam.selenium.po;

import com.example.exam.selenium.PageObject;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class SignUpPO extends LayoutPO {

    public SignUpPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public SignUpPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Sign Up");
    }

    public IndexPO signUP(String userID, String password) {
        setText("username", userID);
        setText("password", password);
        clickAndWait("signUpBtn");

        IndexPO indexPO = new IndexPO(this);
        assertTrue(getDriver().getTitle().contains("Home page"));

        return indexPO;
    }


}
