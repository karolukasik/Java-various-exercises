package com.karolina.tuesdaystasks.peselnumber;

public interface RuleValidator {

    boolean applyRule(String condition);

    String errorMessage();

    default boolean notValidFor(String condition){
        return !applyRule(condition);
    }
}
