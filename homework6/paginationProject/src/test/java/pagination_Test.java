import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

public class pagination_Test {
    @Test
    @DisplayName("Проверка работы кнопок перехода на страницы в меню пагинации")
    public void checkPaginationView() {
        new MainPage()
                .open()
                .goToPage(5)
                .goToPreviousPage()
                .goToNextPage()
                .goNextTenPages()
                .goBackTenPages();
    }
}
