package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.adapters.mernis.JobSeekerCheckService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.core.utilities.validators.EmailValidator;
import kodlamaio.hrms.core.utilities.validators.NameValidator;
import kodlamaio.hrms.core.utilities.validators.PasswordValidator;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobSeekerManager implements JobSeekerService {

    private final JobSeekerDao jobSeekerDao;
    private final JobSeekerCheckService jobSeekerCheckService;
    private final VerificationCodeService verificationCodeService;

    @Autowired
    public JobSeekerManager(JobSeekerDao jobSeekerDao, JobSeekerCheckService jobSeekerCheckService,
                            VerificationCodeService verificationCodeService) {
        this.jobSeekerDao = jobSeekerDao;
        this.jobSeekerCheckService = jobSeekerCheckService;
        this.verificationCodeService = verificationCodeService;
    }

    @Override
    public Result add(JobSeeker jobSeeker) {
        Result validationResult = validateJobSeeker(jobSeeker);
        if (!validationResult.isSuccess()) {
            return validationResult;
        }

        jobSeekerDao.save(jobSeeker);

        String code = verificationCodeService.generateCode();
        verificationCodeService.add(new VerificationCode(jobSeeker, code));

        // Fake verification
        verify(jobSeeker.getId(), code);

        return new SuccessResult("Job Seeker added: " + jobSeeker.getFirstName());
    }


    @Override
    public Result update(Integer id, JobSeeker jobSeeker) {
        JobSeeker existingJobSeeker = getById(id).getData();
        if (existingJobSeeker == null) {
            return new ErrorResult("Job Seeker with id: " + id + " cannot be updated. He/She does not exist.");
        }

        existingJobSeeker.setEmail(jobSeeker.getEmail());
        existingJobSeeker.setPassword(jobSeeker.getPassword());
        existingJobSeeker.setIsDeleted(jobSeeker.getIsDeleted());

        existingJobSeeker.setFirstName(jobSeeker.getFirstName());
        existingJobSeeker.setLastName(jobSeeker.getLastName());
        existingJobSeeker.setIdentityNumber(jobSeeker.getIdentityNumber());
        existingJobSeeker.setBirthDate(jobSeeker.getBirthDate());
        existingJobSeeker.setIsVerified(jobSeeker.getIsVerified());

        jobSeekerDao.save(existingJobSeeker);

        return new SuccessResult("Job Seeker with id: " + id + " updated.");
    }

    @Override
    public Result delete(Integer id) {
        JobSeeker jobSeeker = getById(id).getData();
        if (jobSeeker == null) {
            return new ErrorResult("Job Seeker with id: " + id + " cannot be deleted. He/She does not exist.");
        }

        jobSeeker.setIsDeleted(true);
        jobSeekerDao.save(jobSeeker);

        return new SuccessResult("Job Seeker deleted with id: " + id);
    }

    @Override
    public DataResult<JobSeeker> getById(Integer id) {
        Optional<JobSeeker> optionalJobSeeker = jobSeekerDao.findById(id);

        if (optionalJobSeeker.isEmpty() || optionalJobSeeker.get().getIsDeleted()) {
            return new ErrorDataResult<>("Job Seeker with id: " + id + " does not exist.");
        }

        return new SuccessDataResult<>(optionalJobSeeker.get(), "Job Seeker with id: " + id + " found and retrieved successfully");
    }

    @Override
    public DataResult<List<JobSeeker>> getAll() {
        return new SuccessDataResult<>(jobSeekerDao.findByIsDeletedFalse(), "Job Seeker data listed");
    }

    @Override
    public Result verify(Integer jobSeekerId, String verificationCode) {
        JobSeeker existingJobSeeker = getById(jobSeekerId).getData();
        if (existingJobSeeker == null) {
            return new ErrorResult("Job Seeker with id: " + jobSeekerId + " cannot be verified. He/She does not exist.");
        }

        VerificationCode existingVerificationCode = verificationCodeService.getByCode(verificationCode);
        if (existingVerificationCode == null) {
            return new ErrorResult("Job Seeker's Verification Code with code: " + verificationCode + " cannot be verified. It does not exist.");
        }

        existingJobSeeker.setIsVerified(true);
        jobSeekerDao.save(existingJobSeeker);

        existingVerificationCode.setIsVerified(true);
        verificationCodeService.update(existingVerificationCode.getId(), existingVerificationCode);

        return new SuccessResult("Job Seeker with id: " + jobSeekerId + " verified.");
    }

    private boolean emailExists(String email) {
        return jobSeekerDao.findByEmail(email) != null;
    }

    private boolean tcExists(String identityNumber) {
        return jobSeekerDao.findByIdentityNumber(identityNumber) != null;
    }

    private Result validateJobSeeker(JobSeeker jobSeeker) {
        Result firstNameValidationResult = NameValidator.validate(jobSeeker.getFirstName());
        if (!firstNameValidationResult.isSuccess()) {
            return firstNameValidationResult;
        }

        Result lastNameValidationResult = NameValidator.validate(jobSeeker.getLastName());
        if (!lastNameValidationResult.isSuccess()) {
            return lastNameValidationResult;
        }

        Result emailValidationResult = EmailValidator.validate(jobSeeker.getEmail());
        if (!emailValidationResult.isSuccess()) {
            return emailValidationResult;
        }

        Result passwordValidationResult = PasswordValidator.validate(jobSeeker.getPassword());
        if (!passwordValidationResult.isSuccess()) {
            return passwordValidationResult;
        }

        if (emailExists(jobSeeker.getEmail())) {
            return new ErrorResult("Email is already in use.");
        } else if (tcExists(jobSeeker.getIdentityNumber())) {
            return new ErrorResult("TC number is already in use.");
        }

        // mernis
        if (!jobSeekerCheckService.checkIfRealPerson(jobSeeker)) {
            return new ErrorResult("Please enter your information correctly. Mernis couldn't find a person with this information.");
        }

        return new SuccessResult("Job Seeker validated");
    }
}
