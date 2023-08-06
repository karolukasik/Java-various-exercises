package org.karolina;

import lombok.Getter;
import lombok.NonNull;

import static org.apache.commons.lang3.StringUtils.capitalize;

@Getter
public class FullName {

    private String firstName;
    private String middleName;
    private String lastName;
    private String maidenName;

    public FullName (@NonNull String firstName, @NonNull String lastName) {
        this(firstName, "", lastName, "");
    }

    public FullName (@NonNull String firstName, @NonNull String middleName, @NonNull String lastName) {
        this(firstName, middleName, lastName, "");
    }

    public FullName (@NonNull String firstName, String middleName, @NonNull String lastName, @NonNull String maidenName) {
        if (middleName == null) {
            middleName = "";
        }

        this.firstName = processName(firstName);
        this.middleName = processName(middleName);
        this.lastName = processSurname(lastName);
        this.maidenName = processSurname(maidenName);
    }

    private String processName (String name) {
        var trimmedName = name.toLowerCase().trim();
        checkNameCorrectness(trimmedName);
        return capitalize(trimmedName);
    }

    private void checkNameCorrectness (String name) {
        if (!name.matches("(^$)|\\p{L}+")) {
            throw new IllegalArgumentException("The input should contain only letters");
        }
    }

    private String processSurname (String surname) {
        var trimmedSurname = surname.toLowerCase().trim();
        checkSurnameCorrectness(trimmedSurname);
        return formattedSurname(trimmedSurname, "-");
    }

    private void checkSurnameCorrectness (String surname) {
        if (!surname.matches("(^$)|\\p{L}+|\\p{L}+(\\s*)(-|\\s)(\\s*)\\p{L}+")) {
            throw new IllegalArgumentException("The input should contain only letters (optionally space or dash for two-part surname");
        }
    }

    private String formattedSurname (String surname, String separator) {
        String[] surnameParts = surname.split("-|\\s", 2);
        if (surnameParts.length == 1) {
            return capitalize(surname);
        }

        var firstPart = surnameParts[0].replace("-", " ").trim();
        var secondPart = surnameParts[1].replace("-", " ").trim();
        return capitalize(firstPart) + separator + capitalize(secondPart);
    }

    @Override
    public String toString () {
        StringBuilder valueToReturn = new StringBuilder(firstName).append(" ");
        if (!middleName.isEmpty()) {
            valueToReturn.append(middleName).append(" ");
        }
        valueToReturn.append(lastName);
        if (!maidenName.isEmpty()) {
            valueToReturn.append(", maiden name: ").append(maidenName);
        }
        return valueToReturn.toString();
    }
}
