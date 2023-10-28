package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.entities.concretes.VerificationCode;

public interface VerificationCodeService extends BaseEntityService<VerificationCode, Integer> {

    VerificationCode getByCode(String code);

    String generateCode();

}
