package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.VerificationCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployerManager implements EmployerService {
    private final EmployerDao employerDao;
    private final VerificationCodeService verificationCodeService;

    public EmployerManager(EmployerDao employerDao, VerificationCodeService verificationCodeService) {
        this.employerDao = employerDao;
        this.verificationCodeService = verificationCodeService;
    }

    @Override
    public Result add(Employer employer) {
        Result validationResult = validateEmployer(employer);
        if (!validationResult.isSuccess()) {
            return validationResult;
        }

        employerDao.save(employer);

        String code = verificationCodeService.generateCode();
        verificationCodeService.add(new VerificationCode(employer, code));

        // todo system personnel confirmation

        // Fake verification
        verify(employer.getId(), code);

        return new SuccessResult("Employer added: " + employer.getCompanyName());
    }

    @Override
    public Result update(Integer id, Employer employer) {
        Employer existingEmployer = getById(id).getData();
        if (existingEmployer == null) {
            return new ErrorResult("Employer with id: " + id + " cannot be updated. It does not exist.");
        }

        existingEmployer.setEmail(employer.getEmail());
        existingEmployer.setPassword(employer.getPassword());
        existingEmployer.setIsDeleted(employer.getIsDeleted());

        existingEmployer.setCompanyName(employer.getCompanyName());
        existingEmployer.setWebAddress(employer.getWebAddress());
        existingEmployer.setPhoneNumber(employer.getPhoneNumber());
        existingEmployer.setIsVerified(employer.getIsVerified());
        existingEmployer.setIsConfirmed(employer.getIsConfirmed());

        employerDao.save(existingEmployer);

        return new SuccessResult("Employer with id: " + id + " updated.");
    }

    @Override
    public Result delete(Integer id) {
        Employer employer = getById(id).getData();
        if (employer == null) {
            return new ErrorResult("Employer with id: " + id + " cannot be deleted. It does not exist.");
        }

        employer.setIsDeleted(true);
        employerDao.save(employer);

        return new SuccessResult("Employer deleted with id: " + id);
    }

    @Override
    public DataResult<Employer> getById(Integer id) {
        Optional<Employer> optionalEmployer = employerDao.findById(id);

        if (optionalEmployer.isEmpty() || optionalEmployer.get().getIsDeleted()) {
            return new ErrorDataResult<>("Employer with id: " + id + " does not exist.");
        }

        return new SuccessDataResult<>(optionalEmployer.get(), "Employer with id: " + id + " found and retrieved successfully");
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<>(employerDao.findByIsDeletedFalse(), "Employer data listed");
    }

    @Override
    public Result verify(Integer employerId, String verificationCode) {
        Employer existingEmployer = getById(employerId).getData();
        if (existingEmployer == null) {
            return new ErrorResult("Employer with id: " + employerId + " cannot be verified. It does not exist.");
        }

        VerificationCode existingVerificationCode = verificationCodeService.getByCode(verificationCode);
        if (existingVerificationCode == null) {
            return new ErrorResult("Employer's Verification Code with code: " + verificationCode + " cannot be verified. It does not exist.");
        }

        existingEmployer.setIsVerified(true);
        employerDao.save(existingEmployer);

        existingVerificationCode.setIsVerified(true);
        verificationCodeService.update(existingVerificationCode.getId(), existingVerificationCode);

        return new SuccessResult("Employer with id: " + employerId + " verified.");
    }

    @Override
    public Result confirm(Integer id) {
        Employer existingEmployer = getById(id).getData();
        if (existingEmployer == null) {
            return new ErrorResult("Employer with id: " + id + " cannot be confirmed. It does not exist.");
        }

        existingEmployer.setIsConfirmed(true);
        employerDao.save(existingEmployer);
        return new SuccessResult("Employer with id: " + id + " confirmed.");
    }

    private boolean emailExists(String email) {
        return employerDao.findByEmail(email) != null;
    }

    private boolean isEmailDomainMatchingWebsite(String email, String website) {
        // Extract domain from email
        String emailDomain = extractDomainFromEmail(email);

        // Extract domain from website
        String websiteDomain = extractDomainFromWebsite(website);

        // Compare the domains
        return emailDomain.equals(websiteDomain);
    }

    private String extractDomainFromEmail(String email) {
        // Use regular expression to extract domain from email
        String domainPattern = ".*@(.*)";
        Pattern pattern = Pattern.compile(domainPattern);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    private String extractDomainFromWebsite(String website) {
        // Use regular expression to extract domain from the website
        String domainPattern = "(https?://)?(www\\d?\\.)?(.[^/:]+)";
        Pattern pattern = Pattern.compile(domainPattern);
        Matcher matcher = pattern.matcher(website);

        if (matcher.find()) {
            return matcher.group(3);
        } else {
            throw new IllegalArgumentException("Invalid website format");
        }
    }

    private Result validateEmployer(Employer employer) {
        if (!isEmailDomainMatchingWebsite(employer.getEmail(), employer.getWebAddress())) {
            return new ErrorResult("Email domain does not match the website domain.");
        }
        if (emailExists(employer.getEmail())) {
            return new ErrorResult("Email is already in use.");
        }

        return new SuccessResult("Employer validated");
    }
}
