package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class UserPageObjects {

    //locators

    SelenideElement userSurnameInput = $("#user_surname");
    SelenideElement userNameInput = $("#user_name");
    SelenideElement userPatronymicInput = $("#user_patronymic");
    SelenideElement userLoginInput = $("#user_login");
    SelenideElement userKadrIdInput = $("#user_kadr_id");
    SelenideElement userTabelInput = $("#user_add_identity");
    SelenideElement userPasswordInput = $("#user_password");
    SelenideElement userEmailInput = $("#user_email");

    SelenideElement firstLineInTheTable = $(".dx-column-lines", 1);

    SelenideElement userSearchInput = $("#user_search");


    //actions

    public UserPageObjects setUserSurname(String surname) {
        userSurnameInput.setValue(surname);
        return this;
    }

    public UserPageObjects setUsetName(String name) {
        userNameInput.setValue(name);
        return this;
    }

    public UserPageObjects setUserPatronymic(String patronymic) {
        userPatronymicInput.setValue(patronymic);
        return this;
    }

    public UserPageObjects setUserLogin(String login) {
        userLoginInput.setValue(login);
        return this;
    }

    public UserPageObjects setUserKadrId(String kadrid) {
        userKadrIdInput.scrollIntoView(false).setValue(kadrid);
        return this;
    }

    public UserPageObjects setUserTabel(String tabel) {
        userTabelInput.scrollIntoView(false).setValue(tabel);
        return this;
    }

    public UserPageObjects setUserPassword(String password) {
        userPasswordInput.scrollIntoView(false).setValue(password);
        return this;
    }

    public UserPageObjects setUserEmail(String email) {
        userEmailInput.scrollIntoView(false).sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        userEmailInput.scrollIntoView(false).setValue(email);
        return this;
    }

    public UserPageObjects newUserCheck(String surname){
        firstLineInTheTable.shouldBe(visible).shouldHave(text(surname));
        return this;
    }

    public UserPageObjects clearUserCreationFields(){
        userSurnameInput.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        userNameInput.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        userPatronymicInput.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        userLoginInput.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        return this;
    }

    public UserPageObjects userSearch(String value){
        userSearchInput.setValue(value).pressEnter();
        return this;
    }


}
