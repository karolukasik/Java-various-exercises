package com.karolina.tuesdaystasks.peselnumber;

import static com.karolina.tuesdaystasks.peselnumber.util.StringUtils.substringAsNumber;

import java.time.LocalDate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PeselBirthDateExtractor implements DataExtractor<LocalDate> {

    @Override
    public LocalDate extract(String encodedData) {
        int birthYearNumber = substringAsNumber(0, 1, encodedData);
        int birthMonthNumber = substringAsNumber(2, 3, encodedData);
        int birthDayNumber = substringAsNumber(4, 5, encodedData);

        if (birthMonthNumber > 12) {
            birthMonthNumber = birthMonthNumber - 20;
            birthYearNumber = 2000 + birthYearNumber;
        } else {
            birthYearNumber = 1900 + birthYearNumber;
        }

        return LocalDate.of(birthYearNumber, birthMonthNumber, birthDayNumber);

    }

}
