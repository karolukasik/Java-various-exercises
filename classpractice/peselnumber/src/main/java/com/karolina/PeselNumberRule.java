package com.karolina;

import static com.karolina.util.StringUtils.getDigitAtIndexFrom;

public enum PeselNumberRule implements RuleValidator {
    HAS_11_DIGITS {
        @Override
        public boolean applyRule(String pesel) {
            return pesel.matches("^\\d{11}$");
        }

        @Override
        public String errorMessage() {
            return "The input should contains 11 digits";
        }

    },
    FULFILL_PESEL_CORRECTNESS_EQUATION {
        @Override
        public boolean applyRule(String pesel) {
            return doesPeselFulfillCorrectnessEquation(pesel);
        }

        @Override
        public String errorMessage() {
            return "Given number is not valid PESEL number";
        }
    };

    private static boolean doesPeselFulfillCorrectnessEquation(String pesel) {
        int sumOfDigits = 0;
        for (int i = 0; i <= 9; i++) {
            int digit = getDigitAtIndexFrom(i, pesel);
            digit = multiplyByWeightAndExtractOnesDigit(i, digit);
            sumOfDigits += digit;
        }
        if (sumOfDigits > 10) {
            sumOfDigits = sumOfDigits % 10;
        }
        int controlNumber = getDigitAtIndexFrom(10, pesel);
        return controlNumber == 10 - sumOfDigits;
    }

    private static int multiplyByWeightAndExtractOnesDigit(int index, int digit) {
        int multiplicationResult = digit;
        if (index == 7 || index == 3) {
            multiplicationResult = 9 * digit;
        }
        if (index == 6 || index == 2) {
            multiplicationResult = 7 * digit;
        }
        if (index == 9 || index == 5 || index == 1) {
            multiplicationResult = 3 * digit;
        }

        return multiplicationResult % 10;

    }

}

