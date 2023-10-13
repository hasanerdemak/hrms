package kodlamaio.hrms.core.utilities.validators;

import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;

import java.util.regex.Pattern;

public class NameValidator {
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 35;
    private static final String NAME_PATTERN = "^[A-Za-z0-9]+$";

    public static Result validate(String name) {
        if (name == null || name.isEmpty()) {
            return new ErrorResult("Name is required.");
        }

        if (name.length() < MIN_NAME_LENGTH) {
            return new ErrorResult("Name is too short. It must be at least " + MIN_NAME_LENGTH + " characters long.");
        }

        if (name.length() > MAX_NAME_LENGTH) {
            return new ErrorResult("Name exceeds the maximum length of " + MAX_NAME_LENGTH + " characters.");
        }

        if (!name.matches(NAME_PATTERN)) {
            return new ErrorResult("Name contains invalid characters. Please use only letters.");
        }

        return new SuccessResult(); // No validation error
    }
}
