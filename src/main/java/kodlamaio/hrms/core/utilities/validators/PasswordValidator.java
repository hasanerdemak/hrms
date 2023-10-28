package kodlamaio.hrms.core.utilities.validators;

import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;

public class PasswordValidator {
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MAX_PASSWORD_LENGTH = 25;
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{6,25}$";

    public static Result validate(String password) {
        if (password == null || password.isEmpty()) {
            return new ErrorResult("Password is required.");
        }

        if (password.length() < MIN_PASSWORD_LENGTH) {
            return new ErrorResult("Password is too short. It must be at least " + MIN_PASSWORD_LENGTH + " characters long.");
        }

        if (password.length() > MAX_PASSWORD_LENGTH) {
            return new ErrorResult("Password exceeds the maximum length of " + MAX_PASSWORD_LENGTH + " characters.");
        }

        if (!password.matches(PASSWORD_PATTERN)) {
            return new ErrorResult("Password must be at least 8 characters long and include at least one letter and one digit.");
        }

        return new SuccessResult(); // No validation error
    }
}
