package service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StringCalculatorService.class)
public class StringServiceCalculatorTest {

    @Autowired
    private StringCalculatorService stringCalculatorService;

    @Test
    public void testResultOfMethodAddReturnZeroWhenStringIsEmpty() {
        int result = stringCalculatorService.add("");
        Assertions.assertEquals(0, result);
    }

    @Test
    public void testResultOfMethodAddReturnNumberItselfIfStringIsUnique() {
        int result = stringCalculatorService.add("1");
        Assertions.assertEquals(1, result);
    }

}
