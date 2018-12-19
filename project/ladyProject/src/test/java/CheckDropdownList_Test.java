import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.RecipePage;

public class CheckDropdownList_Test {

    @Test
    @DisplayName("Проверка выбора единицы измерения")
    public void checkDropdownList() {
        new RecipePage()
                .open()
                .closeLoginPopup()
                .checkSelect(0, "шт.");

    }
}
