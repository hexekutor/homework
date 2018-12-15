import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

public class CheckShardingButton_Test {
    @Test
    @DisplayName("Проверка кнопок шардинга")
    public void checkShardingButton() {
        new MainPage()
                .open()
                .pressNews("Экономика")
                .pressShardingButton("ОДНОКЛАССНИКИ");
    }
}
