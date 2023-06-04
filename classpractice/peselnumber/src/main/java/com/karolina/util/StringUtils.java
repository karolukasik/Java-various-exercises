package com.karolina.util;

import lombok.experimental.UtilityClass;

import static java.lang.Character.getNumericValue;

@UtilityClass
public class StringUtils {

    public static int getDigitAtIndexFrom(int index, String from) {
        return getNumericValue(from.charAt(index));
    }

    public static int substringAsNumber(int fromInclusive, int toInclusive, String string) {
        return Integer.parseInt(string.substring(fromInclusive, toInclusive + 1));
    }

}
