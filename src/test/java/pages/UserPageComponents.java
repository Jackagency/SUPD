package pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class UserPageComponents {

    //авторизация
    public UserPageComponents authorizeSupd (String login, String password){

        SelenideElement loginInput = $("[data-id=login_login]");
        SelenideElement passwordInput = $("[data-id=login_password]");
        SelenideElement submitButton = $("[data-id=login_submit]");

        loginInput.setValue(login).pressEnter();
        passwordInput.setValue(password).pressEnter();
        submitButton.click();

        return this;

    }

    //основание
    public UserPageComponents reasonForm (String reason, String type, String number, String name, String date){

        SelenideElement userReasonInput = $("#reason_form_reason");
        SelenideElement userReasonTypeInput = $("#reason_form_type");
        SelenideElement userReasonNumberInput = $("#reason_form_number");
        SelenideElement userAgreementNameInput = $("#reason_form_fullname_agreement");
        SelenideElement userAgreementDate = $("#reason_form_date_agreement");

        userReasonInput.scrollIntoView(false).setValue(reason);
        userReasonTypeInput.scrollIntoView(false).setValue(type);
        userReasonNumberInput.scrollIntoView(false).setValue(number);
        userAgreementNameInput.scrollIntoView(false).setValue(name);
        userAgreementDate.scrollIntoView(false).setValue(date);

        return this;

    }

    public UserPageComponents userInfoCheck(String surname, String name, String patronymic, String login, String kadrid, String email, String isactive, String type)
    {

        SelenideElement userInfoSurname = $("#user_info_surname");
        SelenideElement userInfoName = $("#user_info_name");
        SelenideElement userInfoPatronymic = $("#user_info_patronymic");
        SelenideElement userInfoLogin = $("#user_info_login");
        SelenideElement userInfoKadrId = $("#user_info_kadr_id");
        SelenideElement userInfoEmail = $("#user_info_email");
        SelenideElement userInfoActive = $("#user_info_active");
        SelenideElement userInfoAccType = $("#user_info_account_type");

        userInfoSurname.shouldHave(text(surname));
        userInfoName.shouldHave(text(name));
        userInfoPatronymic.shouldHave(text(patronymic));
        userInfoLogin.shouldHave(text(login));
        userInfoKadrId.scrollIntoView(false).shouldHave(text(kadrid));
        userInfoEmail.scrollIntoView(false).shouldHave(text(email));
        userInfoActive.scrollIntoView(false).shouldHave(text(isactive));
        userInfoAccType.scrollIntoView(false).shouldHave(text(type));

        return this;

    }

    public UserPageComponents userCreateButton(){
        SelenideElement userCreateButton = $("#user_create_btn");
        userCreateButton.shouldBe(visible).click();
        return this;
    }

    public UserPageComponents userCreateSubmitButton(){
        SelenideElement userCreateSubmitButton = $("#user_edit_submit");
        userCreateSubmitButton.scrollIntoView(false).shouldBe(visible).click();
        userCreateSubmitButton.shouldBe(not(visible), Duration.ofMillis(15000));
        return this;
    }

    public UserPageComponents clickInfoButton(){
        SelenideElement userInfoButton = $("[data-id=user_show_info]");
        userInfoButton.scrollIntoView(false).shouldBe(visible).click();
        return this;
    }


}
