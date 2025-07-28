package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class randomDataGenerators {
    private static final Logger logger = LoggerFactory.getLogger(randomDataGenerators.class);

    public static String Randomnumber() {
        Random random = new Random();
        int number = random.nextInt(1_000_000); // 0 to 999999
        String formatted = String.format("%06d", number);
        return formatted;

    }

    public static String generateRandomEmail() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String validChars = alphabet + numbers;
        Random random = new Random();

        StringBuilder localPart = new StringBuilder();
        int length = 8; // total length before @

        for (int i = 0; i < length; i++) {
            localPart.append(validChars.charAt(random.nextInt(validChars.length())));
        }

        String domain = "@optimworks.com"; // You can change this to any domain
        return localPart.toString() + domain;
    }

}


