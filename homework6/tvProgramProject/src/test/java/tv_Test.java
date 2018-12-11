import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

public class tv_Test {
    @Test
    @DisplayName("Проверка кнопок переключения вида списка каналов")
    public void checkSwitchView() {

        new MainPage()
                .open()
                .switchToListView()
                .switchToGridView();
    }
}
