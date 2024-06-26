package service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

@Service
public class StringCalculatorService {
    public int add(String input) {

        String[] delimiters = {",","\n"};
        StringBuilder delimitersRegex = getRegex(delimiters);

        if (input.startsWith("//")) {
            int newlineIndex = input.indexOf("\n");
            if (newlineIndex != -1) {
                String customDelimiter = input.substring(2, newlineIndex);
                String[] customDelimiterArray = {customDelimiter};
                delimitersRegex = getRegex(customDelimiterArray);
                input = input.substring(newlineIndex + 1);
            }
        }


        List<Integer> numbers = extractNumbers(input, delimitersRegex);
        List<Integer> negativeNumbers = negativeNumber(numbers);

        if (input.isEmpty()) {
            return 0;
        } else if(input.length() == 1){
            return Integer.parseInt(input);
        } else {
            String message = "";
            if(delimitersRegex.toString().contains(Pattern.quote(input.substring(input.length() - 1)))){
                message += "Input ends with a delimiter: " + input.substring(input.length() - 1) + "\n";
                //throw new IllegalArgumentException("Input ends with a delimiter: " + input.substring(input.length() - 1));
            }
            if(hasDifferentDelimiters(input)){
                message += "Delimiters must be identical\n";
                //throw new IllegalArgumentException("Delimiters must be identical");
            }
            if(!negativeNumbers.isEmpty()){
                message += "Negative number(s) not allowed: "+convertListToCommaSeparatedString(negativeNumbers) + "\n";
                //throw new IllegalArgumentException("Negative number(s) not allowed: "+convertListToCommaSeparatedString(negativeNumbers));
            }

            if(!message.isEmpty()){
                throw new IllegalArgumentException(message);
            }
            else {
                return sumOfList(numbers);
            }

        }
    }

    public static String convertListToCommaSeparatedString(List<Integer> numbers) {
        return numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    public static boolean hasDifferentDelimiters(String input) {
        Set<String> delimiters = new HashSet<>();

        // Regex pattern to match non-digit characters (which are likely delimiters)
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            delimiters.add(matcher.group());
        }

        // Return true if there are more than one unique delimiters
       if(delimiters.size()==2){
            for(String delimiter : delimiters){
                if(delimiter.equals("\n")){
                    return false;
                }
            }
            return true;
        }else if(delimiters.size()==1){
            return false;
        }

        return true;
    }

    public static StringBuilder getRegex(String[] delimiters){
        // Create the regex pattern from the delimiters array
        StringBuilder regex = new StringBuilder();
        for (int i = 0; i < delimiters.length; i++) {
            regex.append(Pattern.quote(delimiters[i]));
            if (i != delimiters.length - 1) {
                regex.append("|");
            }
        }
        return regex;
    }

    public static List<Integer> extractNumbers(String input, StringBuilder delimitersRegex) {
        List<Integer> numbers = new ArrayList<>();
        String[] parts = input.split(delimitersRegex.toString());

        for (String part : parts) {
            try {
                int number = Integer.parseInt(part);
                numbers.add(number);
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format in input: " + part);
            }
        }

        return numbers;
    }

    public static List<Integer> negativeNumber(List<Integer> numbers){
        List<Integer> negativeNumbers = new ArrayList<>();
        for (Integer number: numbers){
            if(number<0){
                negativeNumbers.add(number);
            }
        }
        return negativeNumbers;
    }

    public static int sumOfList(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}
