package com.karolina;

import static com.karolina.PeselSexExtractor.Gender;
import static com.karolina.PeselSexExtractor.Gender.*;
import static com.karolina.PeselSexExtractor.Gender.WOMAN;
import static com.karolina.util.StringUtils.getDigitAtIndexFrom;

public class PeselSexExtractor implements DataExtractor<Gender> {

    @Override
    public Gender extract(String encodedData) {
        int controlDigit = getDigitAtIndexFrom(9, encodedData);
        return (controlDigit % 2 == 0) ? WOMAN : MAN;
    }

    public enum Gender {
        WOMAN,
        MAN
    }
}
