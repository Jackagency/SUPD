package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
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

import java.util.Random;
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
    @ParameterizedTest(name = "Создание группы с названием содержащим {0}")
    void mixedGroupCreate(String groupName, String groupDescription) {

        SelenideLogger.addListener("allure", new AllureSelenide());

        userPageComponents.openLoginPage();
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

    @Test
    @DisplayName("Редактирование группы")
    void groupEdit() {
        Random random = new Random();
        //random word
        StringBuilder word = new StringBuilder(15);
        StringBuilder word2 = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            word.append((char) ('a' + random.nextInt(26)));
            word2.append((char) ('a' + random.nextInt(26)));
        }

        SelenideLogger.addListener("allure", new AllureSelenide());

        userPageComponents.openLoginPage();
        userPageComponents.authorizeSupd("admin", "123");
//перехожу на основную вкладку Группы
        groupPageComponents.mainGroupClick();
        //кликаю "Создать"
        groupPageComponents.groupCreateButtonClick();
//заполняю поля имя и описание группы
        groupPageObjects
                .setGroupName(String.valueOf(word))
                .setGroupDescription(String.valueOf(word));
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
        groupPageObjects.newGroupCheck(String.valueOf(word));
        //открываю выбор действия с группой
        groupPageComponents.groupMenuOpen();
        //кликаю редактировать
        groupPageComponents.groupEditButton();
        //очищаю старые поля сигнатура и описание
        groupPageObjects.clearGroupCreationFields();
        //вношу новые значения
        groupPageObjects
                .setGroupName(String.valueOf(word2))
                .setGroupDescription(String.valueOf(word2));
//Заполняю поля основания
        userPageComponents.reasonForm(
                "because",
                "because2",
                "3234",
                "Petrov Ivan Dmitrievich",
                "30.10.2020");
//кликаю подтвердить
        groupPageComponents.clickGroupSubmitButton();
        //проверяю что измененная группа есть в списке
        groupPageObjects.newGroupCheck(String.valueOf(word2));
    }

    @Test
    @DisplayName("Удаление группы")
    void groupDelete() {
        Random random = new Random();
        //random word
        StringBuilder word = new StringBuilder(15);
        StringBuilder word2 = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            word.append((char) ('a' + random.nextInt(26)));
            word2.append((char) ('a' + random.nextInt(26)));
        }

        SelenideLogger.addListener("allure", new AllureSelenide());

        userPageComponents.openLoginPage();
        userPageComponents.authorizeSupd("admin", "123");
//перехожу на основную вкладку Группы
        groupPageComponents.mainGroupClick();
        //кликаю "Создать"
        groupPageComponents.groupCreateButtonClick();
//заполняю поля имя и описание группы
        groupPageObjects
                .setGroupName(String.valueOf(word))
                .setGroupDescription(String.valueOf(word));
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
        groupPageObjects.newGroupCheck(String.valueOf(word));
        //открываю выбор действия с группой
        groupPageComponents.groupMenuOpen();
        //Выбираю Удалить
        groupPageComponents.groupDeleteButton();
        //Заполняю поля основания удаления
        userPageComponents.reasonForm(
                "because",
                "because2",
                "3234",
                "Petrov Ivan Dmitrievich",
                "30.10.2020");
        //кликаю Подтвердить
        groupPageComponents.groupDeleteSubmit();
        //Подтверждаю удаление
        groupPageComponents.deleteSubmitWindow();
        //Проверяю что таблица пуста
        groupPageComponents.emptyTableCheck();

    }
    @Test
    @DisplayName("Создание группы с неуникальной сигнатурой")
    void notUniqueSign(){
        Random random = new Random();
        //random word
        StringBuilder word = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            word.append((char) ('a' + random.nextInt(26)));
        }

        SelenideLogger.addListener("allure", new AllureSelenide());

        userPageComponents.openLoginPage();
        userPageComponents.authorizeSupd("admin", "123");
//перехожу на основную вкладку Группы
        groupPageComponents.mainGroupClick();
        //кликаю "Создать"
        groupPageComponents.groupCreateButtonClick();
//заполняю поля имя и описание группы
        groupPageObjects
                .setGroupName(String.valueOf(word))
                .setGroupDescription(String.valueOf(word));
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
        groupPageObjects.newGroupCheck(String.valueOf(word));
        //кликаю "Создать"
        groupPageComponents.groupCreateButtonClick();
        //заполняю поля имя и описание группы
        groupPageObjects
                .setGroupName(String.valueOf(word))
                .setGroupDescription(String.valueOf(word));
        //Заполняю поля основания
        userPageComponents.reasonForm(
                "because",
                "because2",
                "3234",
                "Petrov Ivan Dmitrievich",
                "30.10.2020");
//кликаю подтвердить
        groupPageComponents.clickGroupSubmitButtonWithError();
        //проверяю появление и наполнение ошибки
        groupPageComponents.checkErrorMassage("Ошибка запроса. Группа с данной сигнатурой уже существует");


    }


}

