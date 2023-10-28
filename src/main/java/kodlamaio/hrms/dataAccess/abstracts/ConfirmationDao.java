package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.Confirmation;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.SystemPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationDao extends JpaRepository<Confirmation, Integer> {
    Confirmation findByEmployer(Employer employer);

    Confirmation findBySystemPersonnel(SystemPersonnel systemPersonnel);
}
