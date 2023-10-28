package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.SystemPersonnel;

public interface ConfirmationService {
    Result confirmEmployerBySystemPersonnel(Employer employer, SystemPersonnel systemPersonnel);
}
