package com.karolina;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PeselNumberRuleTest {


    @ParameterizedTest
    @ValueSource(strings = {"07321656874", "12345678902"})
    public void applyRuleMethodReturnsTrueForStringContainingOnlyElevenDigits(String value) {
        var rule = PeselNumberRule.HAS_11_DIGITS;

        boolean applyingRuleResult = rule.applyRule(value);

        assertTrue(applyingRuleResult);
    }


    @ParameterizedTest
    @ValueSource(strings = {"123a2564871", "1233548721l", "123t567;124"})
    public void applyRuleMethodReturnsFalseForStringContainingElevenCharsDifferentThanDigits(String value) {
        var rule = PeselNumberRule.HAS_11_DIGITS;

        boolean applyingRuleResult = rule.applyRule(value);

        assertFalse(applyingRuleResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "123456789123", "1234567891"})
    public void applyRuleMethodReturnsFalseForStringOfLengthDifferentThanEleven(String value) {
        var rule = PeselNumberRule.HAS_11_DIGITS;

        boolean applyingRuleResult = rule.applyRule(value);

        assertFalse(applyingRuleResult);
    }

    @Test
    public void testApplyRuleMethodReturnsCorrectErrorMessageForHAS11DIGITS() {
        var rule = PeselNumberRule.HAS_11_DIGITS;

        String errorMessage = rule.errorMessage();

        assertEquals("The input should contains 11 digits", errorMessage);
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

}
