package com.karolina.tuesdaystasks.peselnumber;


import static com.karolina.tuesdaystasks.peselnumber.PeselSexExtractor.Gender.*;
import static org.junit.jupiter.api.Assertions.*;

import com.karolina.tuesdaystasks.peselnumber.PeselSexExtractor.Gender;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class PeselNumberTest {

    @Test
    public void constructorDoesNotAcceptNullParameter() {
        Executable methodToExecute = () -> new PeselNumber(null);
        Exception ex = assertThrows(IllegalArgumentException.class, methodToExecute);

        assertEquals("The pesel value must not be empty", ex.getMessage());
    }

    @Test
    public void constructorDoesNotAcceptEmptyParameter() {
        Executable methodToExecute = () -> new PeselNumber("");
        Exception ex = assertThrows(IllegalArgumentException.class, methodToExecute);

        assertEquals("The pesel value must not be empty", ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a2355896471", "aaaaaaaaaaa", "1234b567891"})
    public void constructorDoesNotAcceptLettersInInputString(String value) {
        Executable methodToExecute = () -> new PeselNumber(value);

        Exception ex = assertThrows(IllegalArgumentException.class, methodToExecute);
        assertEquals("The input should contains 11 digits", ex.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideStringsForLengthTest")
    public void constructorDoesNotAcceptStringOfLengthDifferentThanEleven(String value) {
        Executable methodToExecute = () -> new PeselNumber(value);

        Exception ex = assertThrows(IllegalArgumentException.class, methodToExecute);
        assertEquals("The input should contains 11 digits", ex.getMessage());
    }

    private static Stream<Arguments> provideStringsForLengthTest() {
        List<String> stringsOfLengthDifferentThanEleven = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= 20; i++) {
            stringBuilder.append("1");
            if (i == 11) {
                continue;
            }
            stringsOfLengthDifferentThanEleven.add(stringBuilder.toString());
        }
        return stringsOfLengthDifferentThanEleven.stream().map(Arguments::of);
    }

    @ParameterizedTest
    @ValueSource(strings = {"11111111111", "00450369874"})
    public void constructorDoesNotAcceptInvalidPeselNumber(String value) {
        Executable methodToExecute = () -> new PeselNumber(value);

        Exception ex = assertThrows(IllegalArgumentException.class, methodToExecute);
        assertEquals("Given number is not valid PESEL number", ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"07321656897", "00310453143", "87051456486", "88063039412"})
    public void constructorCreateObjectWhenPeselIsValid(String value) {
        var pesel = new PeselNumber(value);

        assertNotNull(pesel);
    }

    @ParameterizedTest
    @ValueSource(strings = {" 07321656897 ", "\n00310453143 ", "\t87051456486 ", "\n88063039412\t"})
    public void constructorCreateObjectWhenPeselIsValidWithWhitespaces(String value) {
        var pesel = new PeselNumber(value);

        assertNotNull(pesel);
    }

    @ParameterizedTest
    @ValueSource(strings = {"06322992737", "51102314575"})
    public void extractUsingWithGenderDataExtractorReturnsManForValidManPesel(String value) {
        var pesel = new PeselNumber(value);
        var sexExtractor = new PeselSexExtractor();

        Gender sex = pesel.extractUsing(sexExtractor);

        assertEquals(MAN, sex);
    }

    @ParameterizedTest
    @ValueSource(strings = {"68100928529", "03302869667"})
    public void extractSexReturnsManForValidWomanPesel(String value) {
        var pesel = new PeselNumber(value);
        var sexExtractor = new PeselSexExtractor();

        Gender sex = pesel.extractUsing(sexExtractor);

        assertEquals(WOMAN, sex);
    }

    @Test
    public void extractBirthDateReturnsCorrectDateForValidPeselBefore2000Year() {
        var pesel = new PeselNumber("66011171661");
        var birthDateExtractor = new PeselBirthDateExtractor();

        LocalDate dateOfBirth = pesel.extractUsing(birthDateExtractor);

        assertEquals(LocalDate.of(1966, 1, 11), dateOfBirth);
    }

    @Test
    public void extractBirthDateReturnsCorrectDateForValidPeselAfter2000Year() {
        var pesel = new PeselNumber("07320342135");
        var birthDateExtractor = new PeselBirthDateExtractor();

        LocalDate dateOfBirth = pesel.extractUsing(birthDateExtractor);

        assertEquals(LocalDate.of(2007, 12, 3), dateOfBirth);
    }

}

