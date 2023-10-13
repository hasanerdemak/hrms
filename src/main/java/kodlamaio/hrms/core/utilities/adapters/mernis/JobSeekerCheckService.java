package kodlamaio.hrms.core.utilities.adapters.mernis;

import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface JobSeekerCheckService {
    boolean checkIfRealPerson(JobSeeker jobSeeker);
}
