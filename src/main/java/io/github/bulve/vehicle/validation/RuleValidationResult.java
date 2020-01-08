package io.github.bulve.vehicle.validation;

/**
 * Represents single {@link VehicleValidationRule} result.
 *
 * @author alex
 */
public class RuleValidationResult {

    private boolean valid;

    private String validationMessage;

    /**
     * Creates {@link RuleValidationResult} as failed / not valid result with
     * {@link this#valid} {@code false} and {@link this#validationMessage} as supplied messages.
     * Must have message supplied.
     *
     * @param validationMessage to create result with.
     * @return result
     */
    public static RuleValidationResult notValid(String validationMessage) {
        RuleValidationResult result = new RuleValidationResult();
        result.valid = false;
        result.validationMessage = validationMessage;
        return result;
    }

    /**
     * Creates {@link RuleValidationResult} as valid result with {@link this#valid} {@code true}
     *
     * @return result
     */
    public static RuleValidationResult valid() {
        RuleValidationResult result = new RuleValidationResult();
        result.valid = true;
        return result;
    }

    private RuleValidationResult() {
    }


    public boolean isValid() {
        return valid;
    }

    public String getValidationMessage() {
        return validationMessage;
    }
}
