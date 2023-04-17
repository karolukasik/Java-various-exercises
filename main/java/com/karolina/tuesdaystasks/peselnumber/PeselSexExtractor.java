package com.karolina.tuesdaystasks.peselnumber;

import static com.karolina.tuesdaystasks.peselnumber.util.StringUtils.getDigitAtIndexFrom;

public class PeselSexExtractor implements DataExtractor {

    @Override
    public String extract(String encodedData) {
        int controlDigit = getDigitAtIndexFrom(9, encodedData);
        if (controlDigit % 2 == 0) {
            return Gender.WOMAN.toString();
        }
        return Gender.MAN.toString();
    }

    private enum Gender {
        WOMAN,
        MAN;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
