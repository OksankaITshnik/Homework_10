package org.example;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FirstTask {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\vital\\OneDrive\\Escritorio\\file.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                validateAndPrintPhoneNumbers(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateAndPrintPhoneNumbers(String line) {
        String phoneRegex = "\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}";

        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String phoneNumber = matcher.group();
            System.out.println(phoneNumber);
        }
    }
}