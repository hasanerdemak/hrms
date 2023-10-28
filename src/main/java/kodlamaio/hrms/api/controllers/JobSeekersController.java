package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.mappers.EntityDtoMapper;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.JobSeekerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobSeekers")
public class JobSeekersController {
    private final JobSeekerService jobSeekerService;
    private final EntityDtoMapper entityDtoMapper;

    @Autowired
    public JobSeekersController(JobSeekerService jobSeekerService, EntityDtoMapper entityDtoMapper) {
        this.jobSeekerService = jobSeekerService;
        this.entityDtoMapper = entityDtoMapper;
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobSeekerDto jobSeekerDto) {
        JobSeeker jobSeeker = entityDtoMapper.convertToEntity(jobSeekerDto, JobSeeker.class);
        return this.jobSeekerService.add(jobSeeker);
    }

    @GetMapping("/{id}")
    public DataResult<JobSeeker> getJobSeeker(@PathVariable Integer id) {
        return this.jobSeekerService.getById(id);
    }

    @GetMapping("/getall")
    public DataResult<List<JobSeeker>> getAll() {
        return this.jobSeekerService.getAll();
    }


}
