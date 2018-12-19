package components;

import org.openqa.selenium.By;

public class ComponentEntity {
    private String xpath = "";
    private String css = "";
    public String getXpath(){
        return xpath;
    }
    public String getCss(){
        return css;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public By getBy(){
        if(!xpath.isEmpty()) return By.xpath(xpath);
        if(!css.isEmpty()) return By.cssSelector(css);
        return null;
    }
}
