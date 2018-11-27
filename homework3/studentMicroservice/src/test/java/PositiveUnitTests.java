import main.DataBase;
import main.Student;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PositiveUnitTests{

    @Test
    public void test_write_student_in_database(){
        Student student = new Student("alex", "DASR", 4);
        DataBase mock = mock(DataBase.class);
        String dataBaseReturn = String.format("{\n\"students\":[\n{\"student\": \"%d\", \"error\": \"\", \"state\": \"ok\"}\n]\n}", student.hashCode());
        when(mock.writeInDataBase(student)).thenReturn(dataBaseReturn);
        assertTrue(mock.writeInDataBase(student).contains("\"error\": \"\""));
    }
}
