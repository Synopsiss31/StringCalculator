package service;

import org.springframework.stereotype.Service;

@Service
public class StringCalculatorService {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String[] parts = numbers.split("[,\n]");

        if (numbers.matches(".*[,\n]$")) {
            throw new IllegalArgumentException("Input must not end with a separator");
        }

        try {
            int sum = 0;
            for (String part : parts) {
                sum += Integer.parseInt(part.trim());
            }
            return sum;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("All inputs must be valid numbers", e);
        }
    }
}

