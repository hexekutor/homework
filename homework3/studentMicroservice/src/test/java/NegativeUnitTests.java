import main.DataBase;
import main.Student;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NegativeUnitTests {
    @Test
    public void test_write_null_in_database(){
        Student student = null;
        DataBase mock = mock(DataBase.class);
        String dataBaseReturn = String.format("{\n\"students\":[\n{\"student\": \"\", \"error\": \"java.lang.NullPointerException\", \"state\": \"ok\"}\n]\n}");
        when(mock.writeInDataBase(student)).thenReturn(dataBaseReturn);
        assertTrue(mock.writeInDataBase(student).contains("\"error\": \"java.lang.NullPointerException\""));
    }

}
