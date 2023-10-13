package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.entities.concretes.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationCodeDao extends JpaRepository<VerificationCode, Integer> {
    /*
    @Query("SELECT v FROM VerificationCode v WHERE v.user = :user")
    VerificationCode findByUser(@Param("user") User user);
     */

    VerificationCode findByCode(String code);

    VerificationCode findByUser(User user);
}