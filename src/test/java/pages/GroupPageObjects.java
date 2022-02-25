package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class GroupPageObjects {
    //locators
    SelenideElement firstLineInTheTable = $(".dx-column-lines", 1);

    SelenideElement groupNameInput = $("#group-edit-form_sign");
    SelenideElement groupDescriptionInput = $("#group-edit-form_desc");


    //actions
    @Step ("Смотрим что в результатах поиска появилась созданная группа")
    public GroupPageObjects newGroupCheck(String groupName) {
        firstLineInTheTable.shouldBe(visible).shouldHave(text(groupName));
        return this;
    }
    @Step("Вводим сигнатуру группы")
    public GroupPageObjects setGroupName (String groupName) {
        groupNameInput.setValue(groupName);
        return this;
    }
    @Step("Вводим описание группы")
    public GroupPageObjects setGroupDescription (String groupDescription) {
        groupDescriptionInput.setValue(groupDescription);
        return this;
    }

}
