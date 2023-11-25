package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.SystemPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemPersonnelDao extends JpaRepository<SystemPersonnel, Integer> {
    List<SystemPersonnel> findByIsDeletedFalse();

    SystemPersonnel findByEmail(String email);
}
