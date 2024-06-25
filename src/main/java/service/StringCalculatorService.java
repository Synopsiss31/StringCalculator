package service;

import org.springframework.stereotype.Service;

@Service
public class StringCalculatorService {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiterPattern = ",|\n";

        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf("\n");
            String customDelimiter = numbers.substring(2, delimiterEndIndex);
            numbers = numbers.substring(delimiterEndIndex + 1);
            delimiterPattern = java.util.regex.Pattern.quote(customDelimiter);
        }

        if (numbers.matches(".*[,\n]$")) {
            throw new IllegalArgumentException("Input must not end with a separator");
        }

        String[] parts = numbers.split(delimiterPattern);

        try {
            int sum = 0;
            for (String part : parts) {
                if (!part.isEmpty()) {
                    sum += Integer.parseInt(part.trim());
                }
            }
            return sum;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("All inputs must be valid numbers", e);
        }
    }
}
