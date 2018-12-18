import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;
import view.View;

public class tv_Test {
    @Test
    @DisplayName("Проверка кнопок переключения вида списка каналов")
    public void checkSwitchView() {
        new MainPage()
                .open()
                .switchView(View.LIST)
                .checkView(View.LIST)
                .switchView(View.GRID)
                .checkView(View.GRID);
    }
}
