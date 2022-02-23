package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class GroupPageObjects {
    //locators
    SelenideElement firstLineInTheTable = $(".dx-column-lines", 1);

    SelenideElement groupNameInput = $("div:nth-child(2) > div > div.v-input__slot input");
    SelenideElement groupDescriptionInput = $("div:nth-child(4) div.v-input__slot > div input");


    //actions
    public GroupPageObjects newGroupCheck(String groupName) {
        firstLineInTheTable.shouldBe(visible).shouldHave(text(groupName));
        return this;
    }

    public GroupPageObjects setGroupName (String groupName) {
        groupNameInput.setValue(groupName);
        return this;
    }

    public GroupPageObjects setGroupDescription (String groupDescription) {
        groupDescriptionInput.setValue(groupDescription);
        return this;
    }

}
