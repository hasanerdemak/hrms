package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.Result;

public interface EmailService {
    Result sendEmail(String mailAddress, String mail);
}
