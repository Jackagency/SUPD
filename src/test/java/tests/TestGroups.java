package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.GroupPageComponents;
import pages.GroupPageObjects;
import pages.UserPageComponents;

import static com.codeborne.selenide.Selenide.*;

public class TestGroups {

    UserPageComponents userPageComponents = new UserPageComponents();
    GroupPageComponents groupPageComponents = new GroupPageComponents();
    GroupPageObjects groupPageObjects = new GroupPageObjects();
    Faker faker = new Faker();

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
    public void groupCreate() {
        String groupName = faker.hobbit().character();
        open("login");
        userPageComponents.authorizeSupd("admin", "123");

        groupPageComponents.mainGroupClick();
        groupPageComponents.groupCreateButtonClick();

        groupPageObjects
                .setGroupName(groupName)
                .setGroupDescription(groupName);

        userPageComponents.reasonForm(
                "because",
                "because2",
                "3234",
                "Petrov Ivan Dmitrievich",
                "30.10.2020");

        groupPageComponents.clickGroupSubmitButton();
        groupPageObjects.newGroupCheck(groupName);


    }
}
