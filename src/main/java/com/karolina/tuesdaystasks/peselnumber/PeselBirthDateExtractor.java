package com.karolina.tuesdaystasks.peselnumber;

import static com.karolina.tuesdaystasks.peselnumber.util.StringUtils.generateDecimalNumberFromDigitsAtIndexes;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.FastDateFormat;

@AllArgsConstructor
public class PeselBirthDateExtractor implements DataExtractor{

    private final FastDateFormat dateFormat;

    public PeselBirthDateExtractor(){
        this("dd.MM.yyyy");
    }

    public PeselBirthDateExtractor(String format){
        this.dateFormat = FastDateFormat.getInstance(format, TimeZone.getTimeZone("Europe/Warsaw"));
    }
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



            Calendar date = new GregorianCalendar(birthYearNumber,birthMonthNumber - 1,birthDayNumber);
            return dateFormat.format(date);
    }

}
