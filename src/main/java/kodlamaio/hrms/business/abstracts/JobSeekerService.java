package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface JobSeekerService extends BaseEntityService<JobSeeker, Integer> {
    Result verify(Integer jobSeekerId, String verificationCode);
}
