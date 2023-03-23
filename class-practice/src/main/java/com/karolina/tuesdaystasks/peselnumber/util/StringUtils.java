package com.karolina.tuesdaystasks.peselnumber.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    public static int getDigitAtIndexFrom(int index, String from) {
        return Integer.parseInt(String.valueOf(from.charAt(index)));
    }

    public static int generateDecimalNumberFromDigitsAtIndexes(int decimalIndex, int onesIndex, String from) {
        int decimalNumber = getDigitAtIndexFrom(decimalIndex, from);
        int onesNumber = getDigitAtIndexFrom(onesIndex, from);
        return 10 * decimalNumber + onesNumber;
    }

}
