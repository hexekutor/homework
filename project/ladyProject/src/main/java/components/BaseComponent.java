package components;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.$;

public abstract class BaseComponent<T> {
    {
        switchFrame();
        validate();
    }
    private void validate(){
        Class<? extends BaseComponent> clazz = getClass();
        if(clazz.isAnnotationPresent(Component.class)){
            Component component = clazz.getAnnotation(Component.class);
            ComponentEntity componentEntity = new ComponentEntity();
            if(!component.xpath().isEmpty()) componentEntity.setXpath(component.xpath());
            if(!component.css().isEmpty()) componentEntity.setCss(component.css());
            $(componentEntity.getBy()).should(Condition.visible);
        }
    }
    private void switchFrame() {
        Class<? extends BaseComponent> clazz = getClass();
        if(clazz.isAnnotationPresent(Frame.class)) {
            Frame frame = clazz.getAnnotation(Frame.class);
            ComponentEntity componentEntity = new ComponentEntity();
            if(!frame.xpath().isEmpty()) componentEntity.setXpath(frame.xpath());
            if(!frame.css().isEmpty()) componentEntity.setCss(frame.css());
            SelenideElement frameElement = $(componentEntity.getBy());
            WebDriverRunner.getWebDriver().switchTo().frame(frameElement);
        }
    }

}
