package service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class StringCalculatorService {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiterPattern = ",|\n";

        if (numbers.matches(".*[,\n]$")) {
            throw new IllegalArgumentException("Input must not end with a separator");
        }

        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf("\n");
            String customDelimiter = numbers.substring(2, delimiterEndIndex);
            numbers = numbers.substring(delimiterEndIndex + 1);
            delimiterPattern = Pattern.quote(customDelimiter);
        }

        String[] parts = numbers.split(delimiterPattern);

        List<Integer> negativeNumbers = new ArrayList<>();
        int sum = 0;

        for (String part : parts) {
            if (!part.isEmpty()) {
                int number = Integer.parseInt(part.trim());
                if (number < 0) {
                    negativeNumbers.add(number);
                }
                sum += number;
            }
        }

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("Negative number(s) not allowed: " + negativeNumbers.toString().replaceAll("[\\[\\]]", ""));
        }

        return sum;
    }
}
