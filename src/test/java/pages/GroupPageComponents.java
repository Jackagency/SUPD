package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class GroupPageComponents {

    public GroupPageComponents clickGroupSubmitButton() {
        SelenideElement groupCreateSubmitButton = $("[data-id=group-edit-form_btn-submit]");
        SelenideElement toolbarText = $("div.dx-toolbar-before div.dx-item-content div");
        groupCreateSubmitButton.scrollIntoView(false).shouldBe(visible).click();
        groupCreateSubmitButton.shouldBe(not(visible), Duration.ofMillis(15000));
        toolbarText.shouldBe(Condition.not(Condition.visible), Duration.ofMillis(15000));
        return this;
    }

    public GroupPageComponents mainGroupClick() {
        SelenideElement mainToggleGroupButton =  $("div.v-btn-toggle a:nth-child(2) span");
        mainToggleGroupButton.shouldBe(visible).click();
        return this;
    }

    public GroupPageComponents groupCreateButtonClick() {
        SelenideElement groupCreateButton = $("[data-id=groups__actions_btn-create]");
        groupCreateButton.shouldBe(visible).click();
        return this;
    }

    
}
