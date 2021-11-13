package com.automation.framework.util;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Slf4j
@Component
@NoArgsConstructor
public class RandomUtils {

    private static final SecureRandom random= new SecureRandom();

    public static String generateRandomString(int length) {

        String text ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(length);
        for( int i = 0; i < length; i++ )
            sb.append( text.charAt( random.nextInt(text.length()) ) );
        return sb.toString();

    }

    public static String generateRandomNumericString(int length) {
        String textnumber ="0123456789";
        StringBuilder sb = new StringBuilder(length);
        for( int i = 0; i < length; i++ )
            sb.append( textnumber.charAt( random.nextInt(textnumber.length()) ) );
        return sb.toString();

    }

    public static String generateRandomMasterBillNumber() {
        return "KAKASHI"+generateRandomString(1).concat(generateRandomNumericString(2));
    }

    public static String generateRandomMasterFileNumber() {
        return generateRandomString(6).concat(generateRandomNumericString(3));
    }



    public static String generateRandomHBNumber() {
        return generateRandomString(3).concat(generateRandomNumericString(3));
    }

}
