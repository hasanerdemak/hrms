package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.ConfirmationService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ConfirmationDao;
import kodlamaio.hrms.entities.concretes.Confirmation;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.SystemPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationManager implements ConfirmationService {

    private final ConfirmationDao confirmationDao;
    private final EmployerService employerService;

    @Autowired
    public ConfirmationManager(ConfirmationDao confirmationDao, EmployerService employerService) {
        this.confirmationDao = confirmationDao;
        this.employerService = employerService;
    }

    public Result add(Confirmation confirmation) {
        confirmationDao.save(confirmation);
        return new SuccessResult("Confirmation from " + confirmation.getSystemPersonnel().getFirstName() + " to " + confirmation.getEmployer().getCompanyName() + " added");
    }

    @Override
    public Result confirmEmployerBySystemPersonnel(Employer employer, SystemPersonnel systemPersonnel) {
        Confirmation confirmation = new Confirmation(systemPersonnel, employer);

        employerService.confirm(employer.getId());

        add(confirmation);

        return new SuccessResult("Employer: " + employer.getCompanyName() + " confirmed by " + systemPersonnel.getFirstName());
    }
}
