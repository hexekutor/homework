package components;


import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public abstract class BaseComponent<T> {
    {
        validate();
    }
    private void validate(){
        Class<? extends BaseComponent> clazz = getClass();
        if(clazz.isAnnotationPresent(Component.class)){
            Component component = clazz.getAnnotation(Component.class);
            //String xpath = component.xpath();
            ComponentEntity componentEntity = new ComponentEntity();
            if(!component.xpath().isEmpty()) componentEntity.setXpath(component.xpath());
            if(!component.css().isEmpty()) componentEntity.setCss(component.css());
            $(componentEntity.getBy()).should(Condition.visible);
        }
    }

}
