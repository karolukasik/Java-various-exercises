package com.karolina.tuesdaystasks.peselnumber;

public interface RuleValidator {

    boolean applyRule(String pesel);

    String errorMessage();
}
