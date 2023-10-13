package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmailService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeDao;
import kodlamaio.hrms.entities.concretes.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VerificationCodeManager implements VerificationCodeService {

    private final VerificationCodeDao verificationCodeDao;
    private final EmailService emailService;

    @Autowired
    public VerificationCodeManager(VerificationCodeDao verificationCodeDao, EmailService emailService) {
        this.verificationCodeDao = verificationCodeDao;
        this.emailService = emailService;
    }

    @Override
    public Result add(VerificationCode verificationCode) {
        verificationCodeDao.save(verificationCode);
        emailService.sendEmail(verificationCode.getUser().getEmail(), "User's verification code is: " + verificationCode.getCode());
        return new SuccessResult("Verification Code added: " + verificationCode.getCode());
    }

    @Override
    public Result update(Integer id, VerificationCode verificationCode) {
        VerificationCode existingVerificationCode = getById(id).getData();
        if (existingVerificationCode == null) {
            return new ErrorResult("Verification Code with id: " + id + " cannot be updated. It does not exist.");
        }

        existingVerificationCode.setUser(verificationCode.getUser());
        existingVerificationCode.setCode(verificationCode.getCode());
        existingVerificationCode.setVerified(verificationCode.isVerified());
        //existingVerificationCode.setCreatedAt(verificationCode.getCreatedAt());
        existingVerificationCode.setVerifiedDate(verificationCode.getVerifiedDate());

        verificationCodeDao.save(existingVerificationCode);

        return new SuccessResult("Verification Code with id: " + id + " updated.");
    }

    @Override
    public Result delete(Integer id) {
        VerificationCode verificationCode = getById(id).getData();

        if (verificationCode == null) {
            return new ErrorResult("Verification Code with id: " + id + " cannot be deleted. It does not exist.");
        }

        verificationCodeDao.delete(verificationCode);

        return new SuccessResult("Verification Code deleted with id: " + id);
    }

    @Override
    public DataResult<VerificationCode> getById(Integer id) {
        Optional<VerificationCode> optionalVerificationCode = verificationCodeDao.findById(id);

        if (optionalVerificationCode.isEmpty()) {
            return new ErrorDataResult<>("Verification Code with id: " + id + " does not exist.");
        }

        return new SuccessDataResult<>(optionalVerificationCode.get(), "Verification Code with id: " + id + " found and retrieved successfully");
    }

    @Override
    public DataResult<List<VerificationCode>> getAll() {
        return new SuccessDataResult<>(verificationCodeDao.findAll(), "Verification Code data listed");
    }

    @Override
    public Result addForUser(User user) {
        VerificationCode verificationCode = new VerificationCode(user, generateCode());
        return add(verificationCode);
    }

    @Override
    public Result verifyById(int id) {
        return verify(getById(id).getData());
    }

    @Override
    public Result verifyByUser(User user) {
        return verify(verificationCodeDao.findByUser(user));
    }

    private Result verify(VerificationCode verificationCode) {
        if (verificationCode == null) {
            return new ErrorResult("Unable to verify user's verification code. It is not exist");
        }

        verificationCode.setVerified(true);
        verificationCode.setVerifiedDate(ZonedDateTime.now());

        verificationCodeDao.save(verificationCode);

        return new SuccessResult("User's verification code verified: " + verificationCode.getCode());
    }

    private String generateCode() {
        UUID uuid = UUID.randomUUID();
        String verificationCode = uuid.toString();
        System.out.println("Your verification code :  " + verificationCode);
        return verificationCode;
    }
}
