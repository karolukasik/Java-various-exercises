package com.karolina.tuesdaystasks.peselnumber;

import static com.karolina.tuesdaystasks.peselnumber.PeselDecoder.*;

public class PeselNumber {

    private final String pesel;

    public PeselNumber(String pesel) {
        if (pesel == null || pesel.isEmpty()) {
            throw new IllegalArgumentException("The field must not be empty");
        }
        String trimmedPesel = pesel.trim();
        validatePeselCorrectness(trimmedPesel);
        this.pesel = trimmedPesel;

    }

    public String extractDateOfBirth() {
        return BIRTH_DATE_DECODER.extract(pesel);
    }

    public String extractSex() {
        return SEX_DECODER.extract(pesel);
    }


    private boolean validatePeselCorrectness(String pesel) {
        PeselNumberRule[] allRules = PeselNumberRule.values();
        boolean areRulesFulfilled = true;

        for (var rule : allRules) {
            if (!rule.applyRule(pesel)) {
                throw new IllegalArgumentException(rule.errorMessage());
            }
        }

        return areRulesFulfilled;
    }


}

