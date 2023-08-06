package org.karolina;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FullNameTest {

    @ParameterizedTest
    @MethodSource ("testCasesWithNullsInNotAllowedPlaces")
    public void AllArgumentsInOverloadedConstructorsAreNotNull (Executable executable) {
        var calledConstructor = executable;

        assertThrows(NullPointerException.class, calledConstructor);
    }

    public void ifPassedMiddleNameIsNullItIsConvertedToEmptyString () {
        var fullName = new FullName("abc", null, "def", "ghi");

        var extractedMiddleName = fullName.getMiddleName();

        assertEquals("", extractedMiddleName);
    }

    @ParameterizedTest
    @MethodSource ("testCasesWithNumbers")
    public void constructorDoNotAcceptStringsWithNumbers (String firstName, String middleName, String lastName, String maidenName) {
        Executable codeToExecute = () -> new FullName(firstName, middleName, lastName, maidenName);
        Exception thrown = assertThrows(IllegalArgumentException.class, codeToExecute);

        assertTrue(thrown.getMessage().contains("The input should contain only letters"));
    }

    @ParameterizedTest
    @MethodSource ("testCasesAndExpectedFirstNames")
    public void getFirstNameReturnsCorrectString (String firstName, String middleName, String lastName, String maidenName, String expectedValue) {
        var fullName = new FullName(firstName, middleName, lastName, maidenName);

        String extractedFirstName = fullName.getFirstName();

        assertEquals(expectedValue, extractedFirstName);
    }

    @ParameterizedTest
    @MethodSource ("testCasesAndExpectedMiddleNames")
    public void getMiddleNameReturnsCorrectString (String firstName, String middleName, String lastName, String maidenName, String expectedValue) {
        var fullName = new FullName(firstName, middleName, lastName, maidenName);

        String extractedMiddleName = fullName.getMiddleName();

        assertEquals(expectedValue, extractedMiddleName);
    }

    @ParameterizedTest
    @MethodSource ("testCasesAndExpectedLastNames")
    public void getLastNameReturnsCorrectString (String firstName, String middleName, String lastName, String maidenName, String expectedValue) {
        var fullName = new FullName(firstName, middleName, lastName, maidenName);

        String extractedLastName = fullName.getLastName();

        assertEquals(expectedValue, extractedLastName);
    }

    @ParameterizedTest
    @MethodSource ("testCasesAndExpectedMaidenNames")
    public void getMaidenNameReturnsCorrectString (String firstName, String middleName, String lastName, String maidenName, String expectedValue) {
        var fullName = new FullName(firstName, middleName, lastName, maidenName);

        String extractedMaidenName = fullName.getMaidenName();

        assertEquals(expectedValue, extractedMaidenName);
    }

    static Stream<Executable> testCasesWithNullsInNotAllowedPlaces () {
        return Stream.of(
                () -> new FullName(null, "abc"),
                () -> new FullName("def", null),
                () -> new FullName(null, "abc", "def"),
                () -> new FullName("abc", null, "def"),
                () -> new FullName("abc", "def", null)
        );
    }

    static Stream<Arguments> testCasesWithNumbers () {
        return Stream.of(
                arguments("1abc", "abc", "def", "ghi"),
                arguments("jOhn", "a2bc", "def", "ghi"),
                arguments(" john  ", "abc", "abc3", "ghi"),
                arguments("JOHN ", "", "def", "9876")
        );
    }

    static Stream<Arguments> testCasesAndExpectedFirstNames () {
        return Stream.of(
                arguments("john", "abc", "def", "ghi", "John"),
                arguments("jOhn", "abc", "def", "ghi", "John"),
                arguments(" john  ", "abc", "def", "ghi", "John"),
                arguments("JOHN ", "", "def", "ghi", "John")
        );
    }

    static Stream<Arguments> testCasesAndExpectedMiddleNames () {
        return Stream.of(
                arguments("john", null, "def", "ghi", ""),
                arguments("jOhn", " ", "def", "ghi", ""),
                arguments(" john  ", " kate", "def", "ghi", "Kate"),
                arguments("JOHN ", "KaTe ", "def", "ghi", "Kate")
        );
    }

    static Stream<Arguments> testCasesAndExpectedLastNames () {
        return Stream.of(
                arguments("john", null, "", "ghi", ""),
                arguments("jOhn", " ", "mary", "ghi", "Mary"),
                arguments(" john  ", " kate", " mary ", "ghi", "Mary"),
                arguments("JOHN ", "KaTe ", "WIcK", "ghi", "Wick"),
                arguments("JOHN ", "KaTe ", "WIcK-END", "ghi", "Wick-End"),
                arguments("JOHN ", "KaTe ", "WIcK - END", "ghi", "Wick-End"),
                arguments("JOHN ", "KaTe ", "WicK  END", "ghi", "Wick-End")
        );
    }

    static Stream<Arguments> testCasesAndExpectedMaidenNames () {
        return Stream.of(
                arguments("jOhn", " ", "mary", " ", ""),
                arguments(" john  ", " kate", " mary ", "doe", "Doe"),
                arguments("JOHN ", "KaTe ", "WIcK", " Doe  ", "Doe"),
                arguments("JOHN ", "KaTe ", "WIcK", " Two-Part", "Two-Part"),
                arguments("JOHN ", "KaTe ", "WIcK-END", "TWO-PART", "Two-Part"),
                arguments("JOHN ", "KaTe ", "WIcK - END", " Two -  ParT", "Two-Part"),
                arguments("JOHN ", "KaTe ", "WicK  END", "TWO part", "Two-Part")
        );
    }

}