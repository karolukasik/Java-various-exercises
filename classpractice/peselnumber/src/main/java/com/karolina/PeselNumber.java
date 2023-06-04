package com.karolina;

public class PeselNumber {

    private final String pesel;

    public PeselNumber(String pesel) {
        if (pesel == null || pesel.isEmpty()) {
            throw new IllegalArgumentException("The pesel value must not be empty");
        }
        String trimmedPesel = pesel.trim();
        validatePeselCorrectness(trimmedPesel);
        this.pesel = trimmedPesel;
    }

    public <T> T extractUsing(DataExtractor<T> strategy) {
        return strategy.extract(pesel);
    }

    private void validatePeselCorrectness(String pesel) {
        PeselNumberRule[] allRules = PeselNumberRule.values();

        for (var rule : allRules) {
            if (rule.notValidFor(pesel)) {
                throw new IllegalArgumentException(rule.errorMessage());
            }
        }
    }

}

