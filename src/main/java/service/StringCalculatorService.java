package service;

import org.springframework.stereotype.Service;

@Service
public class StringCalculatorService {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String[] parts = numbers.split(",");

        if (parts.length == 1) {
            try {
                return Integer.parseInt(parts[0].trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Input must be a valid number", e);
            }
        }

        if (parts.length == 2) {
            try {
                int num1 = Integer.parseInt(parts[0].trim());
                int num2 = Integer.parseInt(parts[1].trim());
                return num1 + num2;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("All inputs must be valid numbers", e);
            }
        }
        throw new UnsupportedOperationException();
    }
}

