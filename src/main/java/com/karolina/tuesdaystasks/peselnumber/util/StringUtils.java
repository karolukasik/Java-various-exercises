package com.karolina.tuesdaystasks.peselnumber.util;

import static java.lang.Character.getNumericValue;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    public static int getDigitAtIndexFrom(int index, String from) {
        return getNumericValue(from.charAt(index));
    }

    public static int generateDecimalNumberFromDigitsAtIndexes(int decimalIndex, int onesIndex, String from) {
        return Integer.parseInt(from.substring(decimalIndex, onesIndex + 1));
    }

}
