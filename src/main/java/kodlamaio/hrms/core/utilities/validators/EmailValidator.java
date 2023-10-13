package kodlamaio.hrms.core.utilities.validators;

import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;

public class EmailValidator {
    private static final int MAX_EMAIL_LENGTH = 320;
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static Result validate(String email) {
        if (email == null || email.isEmpty()) {
            return new ErrorResult("Email address is required.");
        }

        if (email.length() > MAX_EMAIL_LENGTH) {
            return new ErrorResult("Email address exceeds the maximum length of " + MAX_EMAIL_LENGTH + " characters.");
        }

        if (!email.matches(EMAIL_PATTERN)) {
            return new ErrorResult("Please enter a valid email address.");
        }

        return new SuccessResult(); // No validation error
    }
}
