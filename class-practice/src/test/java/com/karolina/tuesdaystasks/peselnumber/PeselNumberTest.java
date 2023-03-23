package com.karolina.tuesdaystasks.peselnumber;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PeselNumberTest {

    @Test
    public void constructorDoesNotAcceptNullParameter() {
        Executable methodToExecute = () -> new PeselNumber(null);
        Exception ex = assertThrows(IllegalArgumentException.class, methodToExecute);

        assertEquals("The field must not be empty", ex.getMessage());
    }

    @Test
    public void constructorDoesNotAcceptEmptyParameter() {
        Executable methodToExecute = () -> new PeselNumber("");
        Exception ex = assertThrows(IllegalArgumentException.class, methodToExecute);

        assertEquals("The field must not be empty", ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a2355896471", "aaaaaaaaaaa", "1234b567891"})
    public void constructorDoesNotAcceptLettersInInputString(String value) {
        Executable methodToExecute = () -> new PeselNumber(value);
        Exception ex = assertThrows(IllegalArgumentException.class, methodToExecute);

        assertEquals("Input should contain only numbers", ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "123456789123"})
    public void constructorDoesNotAcceptStringOfLengthDifferentThanEleven(String value) {
        Executable methodToExecute = () -> new PeselNumber(value);
        Exception ex = assertThrows(IllegalArgumentException.class, methodToExecute);

        assertEquals("Incorrect length of input, should be 11 digits", ex.getMessage());
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
    @ValueSource(strings = {"07321656897"})
    public void applyRuleMethodReturnsTrueForStringOfLengthEleven(String value) {
        var rule = PeselNumberRule.HAS_11_CHARS;
        boolean applyingRuleResult = rule.applyRule(value);

        assertTrue(applyingRuleResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "123456789123"})
    public void applyRuleMethodReturnsFalseForStringOfLengthDifferentThanEleven(String value) {
        var rule = PeselNumberRule.HAS_11_CHARS;
        boolean applyingRuleResult = rule.applyRule(value);

        assertFalse(applyingRuleResult);
    }

    @Test
    public void applyRuleMethodReturnsCorrectErrorMessageForHAS11CHARS() {
        var rule = PeselNumberRule.HAS_11_CHARS;
        String errorMessage = rule.errorMessage();

        assertEquals("Incorrect length of input, should be 11 digits", errorMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"07321656897", "1234567890"})
    public void applyRuleMethodReturnsTrueForStringContainingOnlyDigits(String value) {
        var rule = PeselNumberRule.HAS_ONLY_DIGITS;
        boolean applyingRuleResult = rule.applyRule(value);

        assertTrue(applyingRuleResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123a", "123b", "123t567"})
    public void applyRuleMethodReturnsFalseForStringContainingCharsDifferentThanDigits(String value) {
        var rule = PeselNumberRule.HAS_ONLY_DIGITS;
        boolean applyingRuleResult = rule.applyRule(value);

        assertFalse(applyingRuleResult);
    }

    @Test
    public void applyRuleMethodReturnsCorrectErrorMessageForHASONLYDIGITS() {
        var rule = PeselNumberRule.HAS_ONLY_DIGITS;
        String errorMessage = rule.errorMessage();

        assertEquals("Input should contain only numbers", errorMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"68012383151", "67081067449", "63081814654", "48092192838"})
    public void applyRuleMethodReturnsTrueForPeselNumberFulfillingPeselCorrectnessEquation(String value) {
        var rule = PeselNumberRule.FULFILL_PESEL_CORRECTNESS_EQUATION;
        boolean applyingRuleResult = rule.applyRule(value);

        assertTrue(applyingRuleResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"68012383152", "67081067445"})
    public void applyRuleMethodReturnsFalseForPeselNumberNotFulfillingPeselCorrectnessEquation(String value) {
        var rule = PeselNumberRule.FULFILL_PESEL_CORRECTNESS_EQUATION;
        boolean applyingRuleResult = rule.applyRule(value);

        assertFalse(applyingRuleResult);
    }

    @Test
    public void applyRuleMethodReturnsCorrectErrorMessageForPeselNumberNotFulfillingPeselCorrectnessEquation() {
        var rule = PeselNumberRule.FULFILL_PESEL_CORRECTNESS_EQUATION;
        String errorMessage = rule.errorMessage();
        assertEquals("Given number is not valid PESEL number", errorMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"06322992737", "51102314575"})
    public void extractSexReturnsManForValidManPesel(String value) {
        var pesel = new PeselNumber(value);
        String sex = pesel.extractSex();

        assertEquals("man", sex);
    }

    @ParameterizedTest
    @ValueSource(strings = {"68100928529", "03302869667"})
    public void extractSexReturnsManForValidWomanPesel(String value) {
        var pesel = new PeselNumber(value);
        String sex = pesel.extractSex();

        assertEquals("woman", sex);
    }

    @Test
    public void extractBirthDateReturnsCorrectDateForValidPeselBefore2000Year() {
        var pesel = new PeselNumber("66011171661");
        String dateOfBirth = pesel.extractDateOfBirth();

        assertEquals("11.01.1966", dateOfBirth);
    }

    @Test
    public void extractBirthDateReturnsCorrectDateForValidPeselAfter2000Year() {
        var pesel = new PeselNumber("07320342135");
        String dateOfBirth = pesel.extractDateOfBirth();

        assertEquals("03.12.2007", dateOfBirth);
    }
}

