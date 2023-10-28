package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.mappers.EntityDtoMapper;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dtos.EmployerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployersController {
    private final EmployerService employerService;
    private final EntityDtoMapper entityDtoMapper;

    @Autowired
    public EmployersController(EmployerService employerService, EntityDtoMapper entityDtoMapper) {
        this.employerService = employerService;
        this.entityDtoMapper = entityDtoMapper;
    }

    @PostMapping("/add")
    public Result add(@RequestBody EmployerDto employerDto) {
        Employer employer = entityDtoMapper.convertToEntity(employerDto, Employer.class);
        return this.employerService.add(employer);
    }

    @GetMapping("/{id}")
    public DataResult<Employer> getEmployer(@PathVariable Integer id) {
        return this.employerService.getById(id);
    }

    @GetMapping("/getall")
    public DataResult<List<Employer>> getAll() {
        return this.employerService.getAll();
    }


}
