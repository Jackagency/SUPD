package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import pages.UserPageComponents;
import pages.UserPageObjects;


import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;



public class TestUsers {

    UserPageComponents userPageComponents = new UserPageComponents();
    UserPageObjects userPageObjects = new UserPageObjects();

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
    @DisplayName("User create")
    public void userCreate() {
        Random random = new Random();
        //random word
        StringBuilder word = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            word.append((char) ('a' + random.nextInt(26)));
        }
        open("/users");
        userPageComponents.authorizeSupd("admin", "123");
        userPageComponents.userCreateButton();
        //ввожу данные пользователя
        userPageObjects
                .setUserSurname(String.valueOf(word))
                .setUsetName(String.valueOf(word))
                .setUserPatronymic(String.valueOf(word))
                .setUserLogin(String.valueOf(word))
                .setUserKadrId("1112223334")
                .setUserTabel("1112223334")
                .setUserPassword("123")
                .setUserEmail("123@ya.ru");
        //ввожу основание
        userPageComponents.reasonForm(
                "because",
                "because2",
                "3234",
                "Petrov Ivan Dmitrievich",
                "30.10.2020");
        //кликаю создать
        userPageComponents.userCreateSubmitButton();
        //проверяю пользователя в таблице
        userPageObjects.newUserCheck(String.valueOf(word));
        //кликаю кнопку информации
        userPageComponents.clickInfoButton();
        //сверяю данные
        userPageComponents.userInfoCheck(
                String.valueOf(word),
                String.valueOf(word),
                String.valueOf(word),
                String.valueOf(word),
                "1112223334",
                "123@ya.ru",
                "Активный",
                "Нет типа");

    }

    @Test
    @DisplayName("User edit")
    public void userEdit(){
        //random word
        Random random = new Random();
        StringBuilder word = new StringBuilder(15);
        StringBuilder word2 = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            word.append((char) ('a' + random.nextInt(26)));
            word2.append((char) ('a' + random.nextInt(26)));
        }
        open("/users");
        userPageComponents.authorizeSupd("admin", "123");
        userPageComponents.userCreateButton();
        //ввожу данные пользователя
        userPageObjects
                .setUserSurname(String.valueOf(word))
                .setUsetName(String.valueOf(word))
                .setUserPatronymic(String.valueOf(word))
                .setUserLogin(String.valueOf(word))
                .setUserKadrId("1112223334")
                .setUserTabel("1112223334")
                .setUserPassword("123")
                .setUserEmail("123@ya.ru");
        //ввожу основание
        userPageComponents.reasonForm(
                "because",
                "because2",
                "3234",
                "Petrov Ivan Dmitrievich",
                "30.10.2020");
        //кликаю создать
        userPageComponents.userCreateSubmitButton();
        //проверяю пользователя в таблице
        userPageObjects.newUserCheck(String.valueOf(word));
        //Ищу и нажимаю кнопку редактировать
        userPageComponents.clickActionButton().clickUserEditButton();
        //меняю значения на новые
        userPageObjects
                .clearUserCreationFields()
                .setUserSurname(String.valueOf(word2))
                .setUsetName(String.valueOf(word2))
                .setUserPatronymic(String.valueOf(word2))
                .setUserLogin(String.valueOf(word2))
                .setUserKadrId("1112223334")
                .setUserTabel("1112223334")
                .setUserPassword("123")
                .setUserEmail("123@ya.ru");
        //ввожу основание
        userPageComponents.reasonForm(
                "because",
                "because2",
                "3234",
                "Petrov Ivan Dmitrievich",
                "30.10.2020");
        //кликаю подтвердить
        userPageComponents.userCreateSubmitButton();
        //скролю таблицу влево
        userPageComponents.scrollUserTableTobegin();
        //проверяю пользователя в таблице
        userPageObjects.newUserCheck(String.valueOf(word2));
        //кликаю по кнопке информация
        userPageComponents.clickInfoButton();
        //сверяю данные
        userPageComponents.userInfoCheck(
                String.valueOf(word2),
                String.valueOf(word2),
                String.valueOf(word2),
                String.valueOf(word2),
                "1112223334",
                "123@ya.ru",
                "Активный",
                "Нет типа");

    }

    @Test
    @DisplayName("User delete")
    public void userDelete(){
        //random word
        Random random = new Random();
        StringBuilder word = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            word.append((char) ('a' + random.nextInt(26)));
        }

        open("/users");
        userPageComponents.authorizeSupd("admin", "123");
        userPageComponents.userCreateButton();
        //ввожу данные пользователя
        userPageObjects
                .setUserSurname(String.valueOf(word))
                .setUsetName(String.valueOf(word))
                .setUserPatronymic(String.valueOf(word))
                .setUserLogin(String.valueOf(word))
                .setUserKadrId("1112223334")
                .setUserTabel("1112223334")
                .setUserPassword("123")
                .setUserEmail("123@ya.ru");
        //ввожу основание
        userPageComponents.reasonForm(
                "because",
                "because2",
                "3234",
                "Petrov Ivan Dmitrievich",
                "30.10.2020");
        //кликаю создать
        userPageComponents.userCreateSubmitButton();
        //проверяю пользователя в таблице
        userPageObjects.newUserCheck(String.valueOf(word));
        //Ищу и нажимаю кнопку удалить
        userPageComponents.clickActionButton().clickUserDeleteButton();
        //ввожу основание
        userPageComponents.reasonForm(
                "because",
                "because2",
                "3234",
                "Petrov Ivan Dmitrievich",
                "30.10.2020");
        //кликаю подтвердить
        userPageComponents.userDeleteSubmitButton();
        //ищу удаленного пользователя
        userPageObjects.userSearch(String.valueOf(word));
        //убеждаюсь что список пуст
        userPageComponents.emptyTableCheck();
    }

}

