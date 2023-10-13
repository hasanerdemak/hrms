package kodlamaio.hrms.core.utilities.adapters.mernis;

import kodlamaio.hrms.entities.concretes.JobSeeker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class MernisServiceAdapter implements JobSeekerCheckService {
    @Override
    public boolean checkIfRealPerson(JobSeeker jobSeeker) {
        return true;
        /*

        GESKPSPublicSoap client = new GESKPSPublicSoap();

        try {
            return client.TCKimlikNoDogrula(Long.parseLong(jobSeeker.getIdentityNumber()),
                    jobSeeker.getFirstName().toUpperCase(), jobSeeker.getLastName().toUpperCase(),
                    LocalDate.parse(jobSeeker.getBirthDate().toString()).getYear());
        } catch (Exception e) {
        }

        return false;

         */
    }
}