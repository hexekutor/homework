package data;

/**
 * Поддерживаемые браузеры
 */
public enum BrowsersData {
    Chrome("Google Chrome");

    private final String name;

    BrowsersData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
