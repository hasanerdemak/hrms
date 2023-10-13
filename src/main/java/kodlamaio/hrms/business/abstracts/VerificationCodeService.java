package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.VerificationCode;

public interface VerificationCodeService extends BaseEntityService<VerificationCode, Integer> {

    Result addForUser(User user);

    Result verifyById(int id);

    Result verifyByUser(User user);
}
