package com.karolina.tuesdaystasks.peselnumber;

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

    public String extractUsing(DataExtractor strategy){
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

