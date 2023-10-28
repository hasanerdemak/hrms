package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;

public interface EmployerService extends BaseEntityService<Employer, Integer> {
    Result verify(Integer jobSeekerId, String verificationCode);

    Result confirm(Integer id);
}
