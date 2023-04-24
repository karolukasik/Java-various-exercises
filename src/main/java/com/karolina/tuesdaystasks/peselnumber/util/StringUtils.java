package com.karolina.tuesdaystasks.peselnumber.util;

import static java.lang.Character.getNumericValue;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    public static int getDigitAtIndexFrom(int index, String from) {
        return getNumericValue(from.charAt(index));
    }

    public static int substringAsNumber(int fromInclusive, int toInclusive, String string) {
        return Integer.parseInt(string.substring(fromInclusive, toInclusive + 1));
    }

}
