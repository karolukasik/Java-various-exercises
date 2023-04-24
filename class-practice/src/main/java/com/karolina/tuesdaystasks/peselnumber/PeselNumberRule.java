package com.karolina.tuesdaystasks.peselnumber;

import static com.karolina.tuesdaystasks.peselnumber.util.StringUtils.*;

public enum PeselNumberRule implements RuleValidator {
    HAS_11_CHARS {
        @Override
        public boolean applyRule(String pesel) {
            return pesel.length() == 11;
        }

        @Override
        public String errorMessage() {
            return "Incorrect length of input, should be 11 digits";
        }


    },
    HAS_ONLY_DIGITS {
        @Override
        public boolean applyRule(String pesel) {
            return pesel.matches("^[0-9]*$");
        }

        @Override
        public String errorMessage() {
            return "Input should contain only numbers";
        }
    },
    FULFILL_PESEL_CORRECTNESS_EQUATION {
        @Override
        public boolean applyRule(String pesel) {
            return peselCorrectnessEquation(pesel);
        }

        @Override
        public String errorMessage() {
            return "Given number is not valid PESEL number";
        }
    };

    private static boolean peselCorrectnessEquation(String pesel) {
        int sumOfDigits = 0;
        for (int i = 9; i >= 0; i--) {
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

    private static int multiplyByWeightAndExtractOnesDigit(int i, int digit) {
        int multiplicationResult = digit;
        if (i == 7 || i == 3) {
            multiplicationResult = 9 * digit;
        }
        if (i == 6 || i == 2) {
            multiplicationResult = 7 * digit;
        }
        if (i == 9 || i == 5 || i == 1) {
            multiplicationResult = 3 * digit;
        }

        if (multiplicationResult > 10) {
            return multiplicationResult % 10;
        } else {
            return multiplicationResult;
        }
    }

}

