package com.karolina.tuesdaystasks.peselnumber;

import static com.karolina.tuesdaystasks.peselnumber.util.StringUtils.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public enum PeselDecoder implements DataExtractor {
    BIRTH_DATE_DECODER {
        @Override
        public String extract(String encodedData) {
            int birthYearNumber = generateDecimalNumberFromDigitsAtIndexes(0, 1, encodedData);
            int birthMonthNumber = generateDecimalNumberFromDigitsAtIndexes(2, 3, encodedData);
            int birthDayNumber = generateDecimalNumberFromDigitsAtIndexes(4, 5, encodedData);

            if (birthMonthNumber > 12) {
                birthMonthNumber = birthMonthNumber - 20;
                birthYearNumber = 2000 + birthYearNumber;
            } else {
                birthYearNumber = 1900 + birthYearNumber;
            }

            var date = LocalDate.of(birthYearNumber, birthMonthNumber, birthDayNumber);
            var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return date.format(formatter);
        }

    },
    SEX_DECODER {
        @Override
        public String extract(String encodedData) {
            int controlDigit = getDigitAtIndexFrom(9, encodedData);
            if (controlDigit % 2 == 0) {
                return "woman";
            } else {
                return "man";
            }
        }

    }

}
