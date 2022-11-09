import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileExistingTest {

    @Test
    void isInputFileExists(){
        System.out.println("in test");
        File inputTxt = new File("INPUT.TXT");
        assertEquals(true, inputTxt.exists());
    }
}
