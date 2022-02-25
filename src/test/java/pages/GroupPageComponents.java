package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class GroupPageComponents {

    @Step("Нажимаем кнопку подтвердить и ждем создания группы")
    public GroupPageComponents clickGroupSubmitButton() {
        SelenideElement groupCreateSubmitButton = $("[data-id=group-edit-form_btn-submit]");
        SelenideElement toolbarText = $("div.dx-toolbar-before div.dx-item-content div");
        groupCreateSubmitButton.scrollIntoView(false).shouldBe(visible).click();
        groupCreateSubmitButton.shouldBe(not(visible), Duration.ofMillis(15000));
        toolbarText.shouldBe(Condition.not(Condition.visible), Duration.ofMillis(15000));
        return this;
    }
    @Step("Переходим на вкладку группы нажимая кнопку Группы")
    public GroupPageComponents mainGroupClick() {
        SelenideElement mainToggleGroupButton =  $("[data-id=main-nav_groups]");
        mainToggleGroupButton.shouldBe(visible).click();
        return this;
    }
    @Step("Нажимаем кнопку Создать")
    public GroupPageComponents groupCreateButtonClick() {
        SelenideElement groupCreateButton = $("[data-id=groups__actions_btn-create]");
        groupCreateButton.shouldBe(visible).click();
        return this;
    }

    
}
