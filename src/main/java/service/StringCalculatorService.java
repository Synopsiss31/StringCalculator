package service;

import org.springframework.stereotype.Service;

@Service
public class StringCalculatorService {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        throw new UnsupportedOperationException();
    }
}

