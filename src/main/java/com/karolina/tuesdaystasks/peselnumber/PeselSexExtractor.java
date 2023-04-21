package com.karolina.tuesdaystasks.peselnumber;

import static com.karolina.tuesdaystasks.peselnumber.PeselSexExtractor.Gender.WOMAN;
import static com.karolina.tuesdaystasks.peselnumber.PeselSexExtractor.Gender.MAN;
import static com.karolina.tuesdaystasks.peselnumber.util.StringUtils.getDigitAtIndexFrom;

import com.karolina.tuesdaystasks.peselnumber.PeselSexExtractor.Gender;

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
