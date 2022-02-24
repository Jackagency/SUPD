package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.GroupPageComponents;
import pages.GroupPageObjects;
import pages.UserPageComponents;
import utils.RandomUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class TestGroups {

    UserPageComponents userPageComponents = new UserPageComponents();
    GroupPageComponents groupPageComponents = new GroupPageComponents();
    GroupPageObjects groupPageObjects = new GroupPageObjects();

    //Стринг рандомайзер
    public static String getRandomString(int length) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < length) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            result.append(SALTCHARS.charAt(index));
        }
        return result.toString();
    }


    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "http://192.168.151.19:8080/";
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    public void clearCache() {
        closeWebDriver();
    }

    static Stream<Arguments> mixedGroupCreateProvider() {
        Faker faker = new Faker();

        return Stream.of(
                Arguments.of(faker.funnyName().name(), faker.hobbit().location()),
                Arguments.of(getRandomString(30), getRandomString(30))
        );
    }

    @MethodSource(value = "mixedGroupCreateProvider")
    @ParameterizedTest (name = "Создание группы с названием содержащим {0}")
     void mixedGroupCreate(String groupName, String groupDescription) {
        open("login");
        userPageComponents.authorizeSupd("admin", "123");
//перехожу на основную вкладку Группы
        groupPageComponents.mainGroupClick();
        //кликаю "Создать"
        groupPageComponents.groupCreateButtonClick();
//заполняю поля имя и описание группы
        groupPageObjects
                .setGroupName(groupName)
                .setGroupDescription(groupDescription);
//Заполняю поля основания
        userPageComponents.reasonForm(
                "because",
                "because2",
                "3234",
                "Petrov Ivan Dmitrievich",
                "30.10.2020");
//кликаю подтвердить
        groupPageComponents.clickGroupSubmitButton();
        //проверяю в поиске созданную группу
        groupPageObjects.newGroupCheck(groupName);
    }
}
