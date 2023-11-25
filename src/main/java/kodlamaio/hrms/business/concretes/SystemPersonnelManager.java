package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.SystemPersonnelService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.SystemPersonnelDao;
import kodlamaio.hrms.entities.concretes.SystemPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemPersonnelManager implements SystemPersonnelService {
    private final SystemPersonnelDao systemPersonnelDao;

    @Autowired
    public SystemPersonnelManager(SystemPersonnelDao systemPersonnelDao) {
        this.systemPersonnelDao = systemPersonnelDao;
    }

    @Override
    public Result add(SystemPersonnel systemPersonnel) {
        systemPersonnelDao.save(systemPersonnel);
        return new SuccessResult("System Personnel added: " + systemPersonnel.getFirstName());
    }

    @Override
    public Result update(Integer id, SystemPersonnel systemPersonnel) {
        SystemPersonnel existingSystemPersonnel = getById(id).getData();
        if (existingSystemPersonnel == null || existingSystemPersonnel.getIsDeleted()) {
            return new ErrorResult("System Personnel with id: " + id + " cannot be updated. He/She does not exist.");
        }

        existingSystemPersonnel.setEmail(systemPersonnel.getEmail());
        existingSystemPersonnel.setPassword(systemPersonnel.getPassword());
        existingSystemPersonnel.setIsDeleted(systemPersonnel.getIsDeleted());

        existingSystemPersonnel.setFirstName(systemPersonnel.getFirstName());
        existingSystemPersonnel.setLastName(systemPersonnel.getLastName());

        systemPersonnelDao.save(existingSystemPersonnel);

        return new SuccessResult("System Personnel with id: " + id + " updated.");
    }

    @Override
    public Result delete(Integer id) {
        SystemPersonnel systemPersonnel = getById(id).getData();

        if (systemPersonnel == null) {
            return new ErrorResult("System Personnel with id: " + id + " cannot be deleted. He/She does not exist.");
        }

        systemPersonnel.setIsDeleted(true);
        systemPersonnelDao.save(systemPersonnel);

        return new SuccessResult("System Personnel deleted with id: " + id);
    }

    @Override
    public DataResult<SystemPersonnel> getById(Integer id) {
        Optional<SystemPersonnel> optionalSystemPersonnel = systemPersonnelDao.findById(id);

        if (optionalSystemPersonnel.isEmpty() || optionalSystemPersonnel.get().getIsDeleted()) {
            return new ErrorDataResult<>("System Personnel with id: " + id + " does not exist.");
        }

        return new SuccessDataResult<>(optionalSystemPersonnel.get(), "System Personnel with id: " + id + " found and retrieved successfully");
    }

    @Override
    public DataResult<List<SystemPersonnel>> getAll() {
        return new SuccessDataResult<>(systemPersonnelDao.findByIsDeletedFalse(), "System Personnel data listed");
    }
}
