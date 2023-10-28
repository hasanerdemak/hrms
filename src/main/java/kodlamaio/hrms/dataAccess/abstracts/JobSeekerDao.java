package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer> {
    List<JobSeeker> findByIsDeletedFalse();

    JobSeeker findByEmail(String email);

    JobSeeker findByIdentityNumber(String identityNumber);
}
