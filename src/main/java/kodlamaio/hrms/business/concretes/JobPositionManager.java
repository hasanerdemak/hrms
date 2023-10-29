package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPositionManager implements JobPositionService {

    private final JobPositionDao jobPositionDao;

    @Autowired
    public JobPositionManager(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    @Override
    public Result add(JobPosition jobPosition) {
        if (jobPositionDao.existsByName(jobPosition.getName())) {
            return new ErrorResult("Job Position with name: " + jobPosition.getName() + " already exists.");
        }

        jobPositionDao.save(jobPosition);
        return new SuccessResult("Job Position added: " + jobPosition.getName());
    }

    @Override
    public Result update(Integer id, JobPosition jobPosition) {
        JobPosition existingJobPosition = getById(id).getData();
        if (existingJobPosition == null) {
            return new ErrorResult("Job Position with id: " + id + " cannot be updated. It does not exist.");
        }

        existingJobPosition.setName(jobPosition.getName());

        jobPositionDao.save(existingJobPosition);

        return new SuccessResult("Job Position with id: " + id + " updated.");
    }

    @Override
    public Result delete(Integer id) {
        JobPosition jobPosition = getById(id).getData();

        if (jobPosition == null) {
            return new ErrorResult("Job Position with id: " + id + " cannot be deleted. It does not exist.");
        }

        jobPositionDao.delete(jobPosition);

        return new SuccessResult("Job Position deleted with id: " + id);
    }

    @Override
    public DataResult<JobPosition> getById(Integer id) {
        Optional<JobPosition> optionalJobPosition = jobPositionDao.findById(id);

        if (optionalJobPosition.isEmpty()) {
            return new ErrorDataResult<>("Job Position with id: " + id + " does not exist.");
        }

        return new SuccessDataResult<>(optionalJobPosition.get(), "Job Position with id: " + id + " found and retrieved successfully");
    }

    @Override
    public DataResult<List<JobPosition>> getAll() {
        return new SuccessDataResult<>(jobPositionDao.findAll(), "Job Position data listed");
    }
}
