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

    @Test
    public void testResultOfMethodAddReturnSumWhenStringContainsTwoNumbers() {
        int result = stringCalculatorService.add("1,2");
        Assertions.assertEquals(3, result);
    }

    @Test
    public void testResultOfMethodAddReturnSumWhenStringContainsManyNumbers() {
        int result = stringCalculatorService.add("1,2,3");
        Assertions.assertEquals(6, result);
    }

    @Test
    public void testResultOfMethodAddReturnSumWhenStringContainsnewlines() {
        int result = stringCalculatorService.add("1,2\n3");
        Assertions.assertEquals(6, result);
    }

    @Test
    public void testResultOfMethodAddReturnSumWhenStringContainsnewlines2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stringCalculatorService.add("//|\\n1|2,3");
        });
    }

    @Test
    public void testResultOfMethodAddReturnSumWhenStringContainsSeparatorAtEnd() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stringCalculatorService.add("1,2,");
        });
    }

    @Test
    public void testAddWithCustomDelimiter() {
        int result = stringCalculatorService.add("//;\n1;3");
        Assertions.assertEquals(4, result, "Sum with ';' delimiter should be 4");
    }

    @Test
    public void testAddWithNegativeNumber() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stringCalculatorService.add("2,-4,-9");
        });
        Assertions.assertEquals("Negative number(s) not allowed: -4, -9", exception.getMessage());
    }

    @Test
    public void testAddWithAnyDelimiter(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stringCalculatorService.add("//|\n1|2,-3");
        });
        Assertions.assertEquals("Delimiters must be identical\nNegative number(s) not allowed: -3\n",exception.getMessage());
    }
}
