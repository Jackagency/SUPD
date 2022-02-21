package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.UserPageComponents;

import static com.codeborne.selenide.Selenide.*;

public class TestGroups {

    UserPageComponents userPageComponents = new UserPageComponents();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "http://192.168.151.19:8080/";
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    public void clearCache() {
        closeWebDriver();
    }

    @Test
    @DisplayName("Group creation")
    public void groupCreate(){
        open("login");
        userPageComponents.authorizeSupd("admin", "123");

        $("div.v-btn-toggle a:nth-child(2) span").click();

        $("div.groups__actions:nth-child(1) span").click();


        sleep(5000);



    }
}
