package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import pages.UserPageComponents;
import pages.UserPageObjects;


import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;


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
    public void userCreate() throws InterruptedException {
        Random random = new Random();
        //random word
        StringBuilder word = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            word.append((char) ('a' + random.nextInt(26)));
        }

        open("/users");
        userPageComponents.authorizeSupd("admin", "123");
        userPageComponents.userCreateButton();

        userPageObjects
                .setUserSurname(String.valueOf(word))
                .setUsetName(String.valueOf(word))
                .setUserPatronymic(String.valueOf(word))
                .setUserLogin(String.valueOf(word))
                .setUserKadrId("1112223334")
                .setUserTabel("1112223334")
                .setUserPassword("123")
                .setUserEmail("123@ya.ru");

        userPageComponents.reasonForm(
                "because",
                "because2",
                "3234",
                "Petrov Ivan Dmitrievich",
                "30.10.2020");

        userPageComponents.userCreateSubmitButton();


        userPageObjects.newUserCheck(String.valueOf(word));

        userPageComponents.clickInfoButton();

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
    public void userEdit() throws InterruptedException {
        //random word
        Random random = new Random();
        StringBuilder word = new StringBuilder(15);
        StringBuilder word2 = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            word.append((char) ('a' + random.nextInt(26)));
            word2.append((char) ('a' + random.nextInt(26)));
        }
        open("http://192.168.151.19:8080/users");
        $(byXpath("//*[@data-id=\"login_login\"]")).setValue("admin").pressEnter();
        $(byXpath("//*[@data-id=\"login_password\"]")).setValue("123").pressEnter();
        $(byXpath("//*[@data-id=\"login_submit\"]")).click();
        Thread.sleep(3000);
        $(byXpath("//*[@id=\"user_create_btn\"]")).shouldBe(visible).click();
        $(byXpath("//*[@id=\"user_surname\"]")).shouldBe(visible).setValue(String.valueOf((word)));
        $(byXpath("//*[@id=\"user_name\"]")).setValue(String.valueOf(word));
        $(byXpath("//*[@id=\"user_patronymic\"]")).setValue(String.valueOf(word));
        $(byXpath("//*[@id=\"user_login\"]")).setValue(String.valueOf(word));
        $(byXpath("//*[@id=\"user_kadr_id\"]")).scrollIntoView(false).setValue("1112223334");
        $(byXpath("//*[@id=\"user_add_identity\"]")).scrollIntoView(false).setValue("1112223334");
        $(byXpath("//*[@id=\"user_password\"]")).scrollIntoView(false).setValue("123");
        $(byXpath("//*[@id=\"user_email\"]")).scrollIntoView(false).setValue("123@ya.ru");
        $(byXpath("//*[@id=\"reason_form_reason\"]")).scrollIntoView(false).setValue("because");
        $(byXpath("//*[@id=\"reason_form_type\"]")).scrollIntoView(false).setValue("because2");
        $(byXpath("//*[@id=\"reason_form_number\"]")).scrollIntoView(false).setValue("3234");
        $(byXpath("//*[@id=\"reason_form_fullname_agreement\"]")).scrollIntoView(false).setValue("Petrov Ivan Dmitrievich");
        $(byXpath("//*[@id=\"reason_form_date_agreement\"]")).scrollIntoView(false).setValue("30.10.2020");
        $(byXpath("//*[@id=\"user_edit_submit\"]")).scrollIntoView(false).shouldBe(visible).click();
        $(byXpath("//*[@id=\"user_edit_submit\"]")).shouldBe(not(visible), Duration.ofMillis(15000));
        Thread.sleep(3000);
        $(byCssSelector("[data-id=\"actions_btn\"]")).scrollIntoView(false).shouldBe(visible).click();
        $(byCssSelector("[data-id=\"actions_btn_edit\"]")).shouldBe(visible).click();
        $(byXpath("//*[@id=\"user_surname\"]")).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $(byXpath("//*[@id=\"user_surname\"]")).setValue(String.valueOf((word2)));
        $(byXpath("//*[@id=\"user_name\"]")).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $(byXpath("//*[@id=\"user_name\"]")).setValue(String.valueOf(word2));
        $(byXpath("//*[@id=\"user_patronymic\"]")).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $(byXpath("//*[@id=\"user_patronymic\"]")).setValue(String.valueOf(word2));
        $(byXpath("//*[@id=\"user_login\"]")).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $(byXpath("//*[@id=\"user_login\"]")).setValue(String.valueOf(word2));
        $(byXpath("//*[@id=\"user_kadr_id\"]")).scrollIntoView(false).setValue("1112223334");
        $(byXpath("//*[@id=\"user_add_identity\"]")).scrollIntoView(false).setValue("1112223334");
        $(byXpath("//*[@id=\"user_password\"]")).scrollIntoView(false).setValue("123");
        $(byXpath("//*[@id=\"reason_form_reason\"]")).scrollIntoView(false).setValue("because");
        $(byXpath("//*[@id=\"reason_form_type\"]")).scrollIntoView(false).setValue("because2");
        $(byXpath("//*[@id=\"reason_form_number\"]")).scrollIntoView(false).setValue("3234");
        $(byXpath("//*[@id=\"reason_form_fullname_agreement\"]")).scrollIntoView(false).setValue("Petrov Ivan Dmitrievich");
        $(byXpath("//*[@id=\"reason_form_date_agreement\"]")).scrollIntoView(false).setValue("30.10.2020");
        $(byXpath("//*[@id=\"user_edit_submit\"]")).scrollIntoView(false).shouldBe(visible).click();
        $(byXpath("//*[@id=\"user_edit_submit\"]")).shouldBe(not(visible), Duration.ofMillis(15000));
        Thread.sleep(3000);
        $(byCssSelector("[data-id=\"user_show_info\"]")).scrollIntoView(false).shouldBe(visible).click();
        $(byCssSelector("#user_info_surname [data-value]")).shouldHave(text(String.valueOf(word2)));
        $(byCssSelector("#user_info_name [data-value]")).shouldHave(text(String.valueOf(word2)));
        $(byCssSelector("#user_info_patronymic [data-value]")).shouldHave(text(String.valueOf(word2)));
        $(byCssSelector("#user_info_login [data-value]")).shouldHave(text(String.valueOf(word2)));
        $(byCssSelector("#user_info_kadr_id [data-value]")).scrollIntoView(false).shouldHave(text("1112223334"));
        $(byCssSelector("#user_info_email [data-value]")).scrollIntoView(false).shouldHave(text("123@ya.ru"));

    }

    @Test
    @DisplayName("User delete")
    public void userDelete() throws InterruptedException {
        //random word
        Random random = new Random();
        StringBuilder word = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            word.append((char) ('a' + random.nextInt(26)));
        }

        open("http://192.168.151.19:8080/users");
        $(byXpath("//*[@data-id=\"login_login\"]")).setValue("admin").pressEnter();
        $(byXpath("//*[@data-id=\"login_password\"]")).setValue("123").pressEnter();
        $(byXpath("//*[@data-id=\"login_submit\"]")).click();
        Thread.sleep(3000);
        $(byXpath("//*[@id=\"user_create_btn\"]")).shouldBe(visible).shouldBe(visible).click();
        $(byXpath("//*[@id=\"user_surname\"]")).setValue(String.valueOf((word)));
        $(byXpath("//*[@id=\"user_name\"]")).setValue(String.valueOf(word));
        $(byXpath("//*[@id=\"user_patronymic\"]")).setValue(String.valueOf(word));
        $(byXpath("//*[@id=\"user_login\"]")).setValue(String.valueOf(word));
        $(byXpath("//*[@id=\"user_kadr_id\"]")).scrollIntoView(false).setValue("1112223334");
        $(byXpath("//*[@id=\"user_add_identity\"]")).scrollIntoView(false).setValue("1112223334");
        $(byXpath("//*[@id=\"user_password\"]")).scrollIntoView(false).setValue("123");
        $(byXpath("//*[@id=\"user_email\"]")).scrollIntoView(false).setValue("123@ya.ru");
        $(byXpath("//*[@id=\"reason_form_reason\"]")).scrollIntoView(false).setValue("because");
        $(byXpath("//*[@id=\"reason_form_type\"]")).scrollIntoView(false).setValue("because2");
        $(byXpath("//*[@id=\"reason_form_number\"]")).scrollIntoView(false).setValue("3234");
        $(byXpath("//*[@id=\"reason_form_fullname_agreement\"]")).scrollIntoView(false).setValue("Petrov Ivan Dmitrievich");
        $(byXpath("//*[@id=\"reason_form_date_agreement\"]")).scrollIntoView(false).setValue("30.10.2020");
        $(byXpath("//*[@id=\"user_edit_submit\"]")).scrollIntoView(false).shouldBe(visible).click();
        $(byXpath("//*[@id=\"user_edit_submit\"]")).shouldBe(not(visible), Duration.ofMillis(15000));
        Thread.sleep(3000);
        $(byCssSelector("[data-id=\"actions_btn\"]")).scrollIntoView(false).shouldBe(visible).click();
        $(byCssSelector("[data-id=\"actions_btn_delete\"]")).shouldBe(visible).click();
        $(byXpath("//*[@id=\"reason_form_reason\"]")).scrollIntoView(false).setValue("because");
        $(byXpath("//*[@id=\"reason_form_type\"]")).scrollIntoView(false).setValue("because2");
        $(byXpath("//*[@id=\"reason_form_number\"]")).scrollIntoView(false).setValue("3234");
        $(byXpath("//*[@id=\"reason_form_fullname_agreement\"]")).scrollIntoView(false).setValue("Petrov Ivan Dmitrievich");
        $(byXpath("//*[@id=\"reason_form_date_agreement\"]")).scrollIntoView(false).setValue("30.10.2020");
        $(byXpath("//*[@data-id=\"confirm_submit\"]")).scrollIntoView(false).shouldBe(visible).click();
        $(byXpath("//*[@data-id=\"confirm_submit\"]")).shouldBe(not(visible), Duration.ofMillis(15000));
        $(byXpath("//*[@id=\"user_search\"]")).setValue(String.valueOf((word)));
        $(byXpath("//*[@id=\"user_table\"]/div/div[6]/span")).scrollIntoView(false).shouldBe(visible).click();


    }

}

