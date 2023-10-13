package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmailService;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

@Service
public class EmailManager implements EmailService {

    @Override
    public Result sendEmail(String mailAddress, String mail) {
        System.out.println("Mail sent to " + mailAddress);
        System.out.println("Mail content " + mail);
        return new SuccessResult("An e-mail was sent to address " + mailAddress);
    }

}
