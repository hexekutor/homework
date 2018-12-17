import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.MainPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CheckShardingButton_Test {
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"ОДНОКЛАССНИКИ", "ok"},
                {"ВКОНТАКТЕ", "vk"},
                {"FACEBOOK", "facebook"},
                {"TWITTER", "twitter"}
        });
    }
    private final String nameButton;
    private final String expectedUrlSubstring;

    public CheckShardingButton_Test(String nameButton, String expectedUrlSubstring){
        this.nameButton = nameButton;
        this.expectedUrlSubstring = expectedUrlSubstring;
    }
    @Test
    @DisplayName("Проверка кнопок шардинга")
    public void checkShardingButton() {
        new MainPage()
                .open()
                .pressNews("Экономика")
                .pressShardingButton(nameButton, expectedUrlSubstring);

    }
    @After
    public void close(){
        WebDriverRunner.closeWebDriver();
    }
}
